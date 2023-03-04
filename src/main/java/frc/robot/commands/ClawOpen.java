package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawOpen extends CommandBase {

    private boolean isFinished;
    private double requestedRevs;
    
    public ClawOpen() {
        this.addRequirements(Robot.claw);
        isFinished = false;
    }
    
    @Override
    public void initialize() {
        Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
    }

    @Override
    public void execute() {
        if(Robot.claw.getEncoderPosition() <= 0 || Robot.claw.getLimitSwitch()) {
            Robot.claw.setMotorState(Claw.clawMotorState.OFF);
            Robot.claw.setClosed(false);
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}