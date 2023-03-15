package frc.robot.commands.ManualOverrideCommands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawClose extends CommandBase {

    private boolean isFinished;
    private double m_timestamp;

    public ClawClose() {
        this.addRequirements(Robot.claw);
    }
    
    @Override
    public void initialize() {
        this.isFinished = false;
        Robot.claw.setMotorState(Claw.clawMotorState.ON);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        Robot.claw.setMotorState(Claw.clawMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isFinished = true;
        }
        return isFinished;
    }
}