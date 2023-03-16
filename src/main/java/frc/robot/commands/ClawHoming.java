package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawHoming extends CommandBase {

    private boolean isFinished;
    private double requestedRevs;
    private boolean limitHit;

    public ClawHoming() {
        this.addRequirements(Robot.claw);
    }
    
    @Override
    public void initialize() {
        this.isFinished = false;
        this.limitHit = false;
        Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
    }

    @Override
    public void execute() {
        if (Robot.claw.getLimitSwitch()){
            this.limitHit = true;
            Robot.claw.resetEncoder();
        }
        if (limitHit){
            Robot.claw.setMotorState(Claw.clawMotorState.manualON);
            if (Robot.claw.getEncoderPosition()>=Constants.Claw.kRevolutionsToHome){
                Robot.claw.setMotorState(Claw.clawMotorState.OFF);
                Robot.claw.resetEncoder();
                this.isFinished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}