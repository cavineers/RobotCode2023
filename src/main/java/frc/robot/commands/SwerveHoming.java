package frc.robot.commands;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class SwerveHoming extends CommandBase{

    private SwerveDriveSubsystem swerveSubsystem;
    private boolean isFinished;

    public SwerveHoming (SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        
        addRequirements(swerveSubsystem);
    }
    
    @Override
    public void initialize() {
        swerveSubsystem.toggleIdleMode();
        this.isFinished = false;
    }

    @Override
    public void execute() {
        swerveSubsystem.testStates();
        if(swerveSubsystem.checkFinished()){
            this.isFinished = true;
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        //swerveSubsystem.toggleIdleMode();
        swerveSubsystem.stopModules();
        swerveSubsystem.resetEncoders();
    }

    @Override
    public boolean isFinished() {
        return this.isFinished;
    }

}