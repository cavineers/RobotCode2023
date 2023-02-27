package frc.robot.commands.manualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class SwitchMode extends CommandBase {
    private boolean isDone = false;
    private double m_timestamp;
    private RobotContainer rc;

    public SwitchMode(RobotContainer container) {
        this.rc = container;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (this.rc.mode == RobotContainer.CurrentMode.DRIVE) {
            this.rc.mode = RobotContainer.CurrentMode.ARM;
        } else {
            this.rc.mode = RobotContainer.CurrentMode.DRIVE;
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}
