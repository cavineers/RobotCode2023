package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;

public class OpenClaw extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public OpenClaw() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.claw.getClawMotor().set(0.0);
        }
    

    @Override
    public void execute() {
    // 0 and 2 are not permenant
    if(Robot.claw.getClawMotorPosition() < 0) {
        Robot.claw.setClawMotorState(Claw.ClawMotorState.ON);
    } else if (Robot.claw.getClawMotorPosition() > 2) {
        Robot.claw.setClawMotorState(Claw.ClawMotorState.REVERSE);
    } else {
        Robot.claw.setClawMotorState(Claw.ClawMotorState.OFF);
    }
}
    @Override
    public void end(boolean interrupted) {
        Robot.claw.setClawMotorState(Claw.ClawMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}