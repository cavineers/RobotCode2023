package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;   

public class LockWheels extends CommandBase{

    SwerveDriveSubsystem swerveSubsystem;
    
    public LockWheels(SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        this.addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        swerveSubsystem.lockWheels();
    }
    
    @Override
    public boolean isFinished(){
        return true;
    }
}
