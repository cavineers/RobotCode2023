package frc.robot.commands;

import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
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
        swerveSubsystem.toggleIdleMode(IdleMode.kCoast);
        new Rotation2d();
        this.swerveTrajFollower = new PPSwerveControllerCommand(
            this.tagHomingSubsystem.onTheFlyGenerationRelative( // Trajectory generator
                Rotation2d.fromDegrees(tagHomingSubsystem.getYaw()), // Current robot holonomic rotation
                this.goalOffset
            ), // Trajectory to follow.

            this.swerveSubsystem::getPose, // Pose supplier
            DriveConstants.kDriveKinematics, // SwerveDriveKinematics

            new PIDController(-Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD), // X controller
            new PIDController(-Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD), // Y controller
            new PIDController(Constants.HomingRotationalPIDControllerConstants.kP, Constants.HomingRotationalPIDControllerConstants.kI, Constants.HomingRotationalPIDControllerConstants.kD), // Rotation controller

            this.swerveSubsystem::setModuleStates,
            false,// Mirror path depending on alliance clr
            this.swerveSubsystem // required sub
        );


        swerveTrajFollower.schedule();
    }

    
    
    public boolean isFinished() {
        return (this.swerveTrajFollower.isFinished()); //tagHomingSubsystem.checkFinished()
    }

    
    public void end(boolean interrupted) {
        this.swerveTrajFollower.cancel();
        swerveSubsystem.stopModules();
        if (!interrupted) {
            swerveSubsystem.toggleIdleMode(IdleMode.kBrake);
        }
    }
}