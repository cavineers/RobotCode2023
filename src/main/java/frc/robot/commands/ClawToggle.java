package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawToggle extends CommandBase {

    private boolean isFinished;
    private double requestedRevs;
    private boolean close;

    public ClawToggle(boolean close) {
        this.addRequirements(Robot.claw);
        this.close = close;
        isFinished = false;
    }
    
    @Override
    public void initialize() {
        if (close) {
            Robot.claw.setMotorState(Claw.clawMotorState.ON);
        } else if (!close) {
            Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
            requestedRevs = Robot.claw.getEncoderPosition();
        }
    }

    @Override
    public void execute() {
        //open claw to starting position which is zero
        if (Robot.claw.getMotorState()==Claw.clawMotorState.REVERSE) {
            //Note -- negative value will break
            if (Robot.claw.getEncoderPosition() <= requestedRevs||Robot.claw.getLimitSwitch()) {
                Robot.claw.setMotorState(Claw.clawMotorState.OFF);
                isFinished = true;
                System.out.println("Claw is open");
            }
        }
        //close claw constant pressure
        if (Robot.claw.getMotorState()==Claw.clawMotorState.ON) {
            isFinished = true;
            System.out.println("Claw is closing");
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}