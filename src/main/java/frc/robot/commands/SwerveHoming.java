package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class TestCommand extends CommandBase{

    private SwerveDriveSubsystem swerveSubsystem;
    private boolean isFinished;

    public TestCommand (SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);

        isFinished = false;
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        swerveSubsystem.testStates();
        if(swerveSubsystem.checkFinished()){
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}