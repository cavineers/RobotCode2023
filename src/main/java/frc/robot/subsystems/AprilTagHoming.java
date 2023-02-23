
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
//Drive imports
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.Robot;

import java.lang.Math;



public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private Transform3d currentDistance; // Distance from the camera to the target 
    private double yaw;
    private int targetID;
    private HolonomicDriveController holonomicDriverController;
    private ChassisSpeeds chassisSpeeds;
    private PhotonCamera camera;
    
    
    public AprilTagHoming() { //Initi
        this.camera = new PhotonCamera("HD_Pro_Webcam_C920");
        this.holonomicDriverController = new HolonomicDriveController(new PIDController(Constants.PIDControllerConstants.kP, Constants.PIDControllerConstants.kI, Constants.PIDControllerConstants.kD), new PIDController(Constants.PIDControllerConstants.kP, Constants.PIDControllerConstants.kI, Constants.PIDControllerConstants.kD),
        new ProfiledPIDController(1, 0, 0,
          new TrapezoidProfile.Constraints(6.28, 3.14)));    
    }

    public ChassisSpeeds calculatePID(Trajectory.State goal) {
        if (this.target != null && this.currentDistance != null) {
            this.chassisSpeeds = this.holonomicDriverController.calculate(new Pose2d(new Translation2d(this.currentDistance.getX(), this.currentDistance.getY()), new Rotation2d(this.yaw)),
             goal,
             new Rotation2d(0));
            return this.chassisSpeeds;
        }
        return this.chassisSpeeds;  // If we lose the target, continue on the current course until we see a tag again
    }
    
    public void periodic() {
        // This method will be called once per scheduler run
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {
            this.target = results.getBestTarget(); // Target that photon vision is focused on
            this.currentDistance = target.getBestCameraToTarget(); // Distance from the camera to the target (X = forward, Y = left, Z = up)
            this.yaw = target.getYaw();
            this.targetID = target.getFiducialId();
            SmartDashboard.putNumber("TargetID", this.targetID);
            SmartDashboard.putNumber("X", this.currentDistance.getX());
            SmartDashboard.putNumber("Y", this.currentDistance.getY());
            SmartDashboard.putNumber("Yaw", this.yaw);
        }
    }

    public boolean checkFinished() {
        if (currentDistance.getX() < 0.1 && currentDistance.getY() < 0.1 && Math.abs(yaw) < 5) {
            return true;
        }
        return false;
    }
    public void driveToTarget(Trajectory.State goal) {
        
        if (this.target == null) {return;} // If we don't see a target, don't do anything
        
        // Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(this.chassisSpeeds);
        
        // Output each module states to wheels
        Robot.m_swerveDriveSubsystem.setModuleStates(moduleStates);
    }
}
