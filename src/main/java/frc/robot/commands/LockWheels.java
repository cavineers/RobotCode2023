package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;

public class LockWheels extends CommandBase{

    private SwerveDriveSubsystem swerveSubsystem;
    private SwerveModuleState[] states;
    private boolean isFinished;
    private double startTimestamp;
    
    public LockWheels(SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        this.addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        this.isFinished = false;
        this.startTimestamp = Timer.getFPGATimestamp();

        ChassisSpeeds chassisSpeeds;
        chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(0, 0, 0.01, this.swerveSubsystem.getRotation2d());

        states = Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
    }

    @Override
    public void execute() {
        this.swerveSubsystem.setModuleStates(states);
        if (Timer.getFPGATimestamp() - this.startTimestamp > 0.6){
            this.isFinished = true;
        }
    }

    public void end(boolean interrupted) {
        this.swerveSubsystem.stopModules();
    }

    public boolean isFinished() {
        return this.isFinished;
    }

}
