
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.wpilibj.DriverStation;

import java.lang.Math;



public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private PhotonCamera camera;

    private Transform3d currentDistance; // Distance from the camera to the target 
    private double yaw; // Yaw of camera relative to target

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

            
        }else {
            this.hasTargets = false;
        }
        SmartDashboard.putNumber("Yaw", this.yaw);
        SmartDashboard.putBoolean("HasTags", hasTargets);
        
        if (this.getCurrentCommand() != null) {
            SmartDashboard.putBoolean("Homing Tag", true);
        } else {
            SmartDashboard.putBoolean("Homing Tag", false);
        }
    }


    public Translation2d getTranslationToTag() {
        return new Translation2d(currentDistance.getX(), currentDistance.getY());
    }




    public PathPlannerTrajectory onTheFlyGenerationRelative(Rotation2d holonomicRotation, Translation2d goalOffset){
        PathPlannerTrajectory traj = PathPlanner.generatePath(
          new PathConstraints(Constants.PathPlanning.kMaxSpeedMetersPerSecond, Constants.PathPlanning.kMaxAccelerationMetersPerSecond),
          new PathPoint(new Translation2d(0,0), new Rotation2d(0), holonomicRotation), // INITIAL position, heading(direction of travel), holonomic rotation 
          new PathPoint(goalOffset, new Rotation2d(0), new Rotation2d(0)) // position, heading(direction of travel), holonomic rotation
        );
        return traj;
    }
}
