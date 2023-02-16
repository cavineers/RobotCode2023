
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import java.util.Arrays;
//Drive imports
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;



public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private Transform3d currentDistance; // Distance from the camera to the target 
    private double yaw;
    private int targetID;
    
    private PIDController drivePIDController = new PIDController(Constants.PIDControllerConstants.kP, Constants.PIDControllerConstants.kI, Constants.PIDControllerConstants.kD);

    private PhotonCamera camera;
    
    public AprilTagHoming() { //Initi
        this.camera = new PhotonCamera("HD_Pro_Webcam_C920");
    }
    
    private double[] getDrivePIDValues() {
        if (this.target != null) {
            var distance = this.currentDistance.getTranslation();
            var angle = this.yaw;
            var distanceXPID = this.drivePIDController.calculate(distance.getX(), 0);
            var distanceYPID = this.drivePIDController.calculate(distance.getY(), 0);
            var anglePID = this.drivePIDController.calculate(angle, 0);
            double[] vals = {distanceXPID, distanceYPID, anglePID} // Returns the PID calculated values for the distance and angle
            return vals;
        }
       
    }
    public void periodic() {
        // This method will be called once per scheduler run
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {
            this.target = results.getBestTarget(); // Target that photon vision is focused on
            this.currentDistance = target.getBestCameraToTarget(); // Distance from the camera to the target (X = forward, Y = left, Z = up)
            this.yaw = target.getYaw(); // Yaw of the target (rotation around the y-axis)
            this.targetID = target.getFiducialId();
        }
    }


    public void driveToTarget() {
        ChassisSpeeds chassisSpeeds;
        if (this.target != null) {
            chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);
        }
        
        // Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        
        // Output each module states to wheels
        swerveSubsystem.setModuleStates(moduleStates);
            
        }
    }
}