
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.math.geometry.Rotation2d;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.wpilibj.DriverStation;

import java.lang.Math;
import java.sql.Time;



public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private Transform3d currentDistance; // Distance from the camera to the target 
    private double yaw;
    private int targetID;
    private PhotonCamera camera;

    private Rotation2d goalRotation;
    private Rotation2d goalHeading;
    
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
    
    public void periodic() {
        // This method will be called once per scheduler run
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {
            this.target = results.getBestTarget(); // Target that photon vision is focused on
            this.currentDistance = target.getBestCameraToTarget(); // Distance from the camera to the target (X = forward, Y = left/right, Z = Vertical (Ignored) )
            this.yaw = target.getYaw();
            this.targetID = target.getFiducialId();
            SmartDashboard.putNumber("TargetID", this.targetID);
            SmartDashboard.putNumber("X To Target", this.currentDistance.getX());
            SmartDashboard.putNumber("Y To Target", this.currentDistance.getY());
            SmartDashboard.putNumber("Yaw", this.yaw);
            SmartDashboard.putBoolean("AprilTagHoming", checkFinished());
            
        }
    }

    public boolean checkFinished() {
        if (currentDistance.getX() < 0.05 && currentDistance.getY() < 0.05 && Math.abs(yaw) < 2) {
            return true;
        }
        return false;
    }

    private Translation2d getTranslationToTag() {
        return new Translation2d(currentDistance.getX(), currentDistance.getY());
    }

    private Translation2d getTranslationWithOffset(Translation2d offset){
        Translation2d translation = getTranslationToTag();

        return translation.minus(offset);
    }

    public PathPlannerTrajectory onTheFlyGenerationRelative(Rotation2d holonomicRotation, Translation2d goalOffset){

        PathPlannerTrajectory traj = PathPlanner.generatePath(
          new PathConstraints(4, 3),
          new PathPoint(new Translation2d(0,0), new Rotation2d(0), holonomicRotation), // INITIAL position, heading(direction of travel), holonomic rotation 
          new PathPoint(getTranslationWithOffset(goalOffset), new Rotation2d(0), new Rotation2d(0)) // position, heading(direction of travel), holonomic rotation
        );
        return traj;
    }
}
