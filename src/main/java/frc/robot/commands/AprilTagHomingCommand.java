package frc.robot.commands;

import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.DriveConstants;

import frc.robot.subsystems.AprilTagHoming;



public class AprilTagHomingCommand extends CommandBase {


    private SwerveDriveSubsystem swerveSubsystem;
    private AprilTagHoming tagHomingSubsystem;

    private PPSwerveControllerCommand swerveTrajFollower;

    private Translation2d goalOffset; // The peg/shelf/substation that is selected

    

    public AprilTagHomingCommand(SwerveDriveSubsystem swerveSubsystem, AprilTagHoming tagHomingSubsystem, Translation2d goalOffset) {
        addRequirements(swerveSubsystem, tagHomingSubsystem);

        this.goalOffset = goalOffset; // The peg/shelf/substation translation2d that is selected
        this.swerveSubsystem = swerveSubsystem;
        this.tagHomingSubsystem = tagHomingSubsystem;

    }

    
    public void initialize() {
        this.swerveTrajFollower = new PPSwerveControllerCommand(
            this.tagHomingSubsystem.onTheFlyGenerationRelative( // Trajectory generator
                swerveSubsystem.getRotation2d(), // Current robot holonomic rotation
                this.goalOffset
            ), // Trajectory to follow.

            this.swerveSubsystem::getPose, // Pose supplier
            DriveConstants.kDriveKinematics, // SwerveDriveKinematics

            new PIDController(Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD), // X controller
            new PIDController(Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD), // Y controller
            new PIDController(Constants.HomingRotationalPIDControllerConstants.kP, Constants.HomingRotationalPIDControllerConstants.kI, Constants.HomingRotationalPIDControllerConstants.kD), // Rotation controller

            this.swerveSubsystem::setModuleStates,
            true,// Mirror path depending on alliance clr
            this.swerveSubsystem // required sub
        );

        swerveTrajFollower.schedule();
    }

    
    
    public boolean isFinished() {
        return (tagHomingSubsystem.checkFinished()); // Stop if the robot is at the target OR if there is no result
    }

    
    public void end(boolean interrupted) {
        this.swerveTrajFollower.cancel();
        swerveSubsystem.stopModules();
    }
}