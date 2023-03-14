
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Rotation2d;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.wpilibj.DriverStation;

import java.lang.Math;



public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private PhotonCamera camera;

    private Transform3d currentDistance; // Distance from the camera to the target 
    private double yaw; // Yaw of camera relative to target
    private Translation2d goal; // The peg/shelf/substation translation2d that is selected

    private int targetID;
    

    private boolean hasTargets;

    
    
    public AprilTagHoming() { 
        int counter = 0;
    
        camera = null;

        while ( (camera == null) && (counter <= 10) ) {
            try {
                camera = new PhotonCamera("HD_Pro_Webcam_C920");
            } catch (RuntimeException ex ) {
                  DriverStation.reportError("Error instantiating PhotonCamera  " + ex.getMessage(), true);
                 
            }
              
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex){}

            counter += 1;
            
        }

    }
    
    public double getYaw(){
        return this.yaw;
    }
    public void periodic() {
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {
            this.hasTargets = true;

            this.target = results.getBestTarget(); // Target that photon vision is focused on
            this.currentDistance = target.getBestCameraToTarget(); // Distance from the camera to the target (X = forward, Y = left/right, Z = Vertical (Ignored) )
            this.yaw = target.getYaw();
            
            this.targetID = target.getFiducialId();
            SmartDashboard.putNumber("TargetID", this.targetID);
            SmartDashboard.putNumber("X To Target", this.currentDistance.getX());
            SmartDashboard.putNumber("Y To Target", this.currentDistance.getY());
            SmartDashboard.putNumber("Yaw", this.yaw);
            SmartDashboard.putBoolean("AprilTagHoming", checkFinished());
            SmartDashboard.putBoolean("HasTags", hasTargets);
            
        }else {
            this.hasTargets = false;
        }
    }
    int counter = 0;
    public boolean checkFinished() {
        

        if (hasTargets && (this.goal != null) ) { // Check for targets, and a goal
            Translation2d value = getTranslationToTag();
            double previousdist = value.getDistance(goal); // magnitude

            if ((previousdist >= 0 && previousdist <= Constants.PathPlanning.positionalTolerance) && // Are we within positional tolerance
                (Math.abs(Math.abs(yaw)-180) <= Constants.PathPlanning.angularTolerance)) {
                
                //Wait 0.5s and
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e){}
                
                //Did we remain in positional tolerance?
                if (Math.abs(previousdist - getTranslationToTag().getDistance(goal)) <= Constants.PathPlanning.positionalTolerance) {
                    counter += 1;
                    if (counter > 10) {
                        return true;
                    }
                }
                
            }else {
                return false;
            }
        }
        return true;
        
        // If the bot is not in the position, and we don't have a target, return false
    }

    private Translation2d getTranslationToTag() {
        return new Translation2d(currentDistance.getX(), currentDistance.getY());
    }

    private Translation2d getTranslationWithOffset(Translation2d goal){
        Translation2d translation = getTranslationToTag();
        Translation2d distgoal = goal.minus(translation);
        
        return distgoal;
    }

    public PathPlannerTrajectory onTheFlyGenerationRelative(Rotation2d holonomicRotation, Translation2d goalOffset){
        this.goal = goalOffset;
        PathPlannerTrajectory traj = PathPlanner.generatePath(
          new PathConstraints(Constants.PathPlanning.kMaxSpeedMetersPerSecond, Constants.PathPlanning.kMaxAccelerationMetersPerSecond),
          new PathPoint(getTranslationToTag(), new Rotation2d(0), holonomicRotation), // INITIAL position, heading(direction of travel), holonomic rotation 
          new PathPoint(goalOffset, new Rotation2d(0), new Rotation2d(0)) // position, heading(direction of travel), holonomic rotation
        );
        return traj;
    }
}
