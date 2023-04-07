package frc.robot.commands.AutoArmCommands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoClawToggle extends CommandBase {

    private boolean isFinished;

    public AutoClawToggle() {
        this.addRequirements(Robot.claw);
    }
    
    @Override
    public void initialize() {
        if (!Claw.isClosing()) {
            Robot.claw.setMotorState(Claw.clawMotorState.ON);
            Claw.setClosing(true);
        } else {
            Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
            Claw.setClosing(false);
        }
        this.isFinished = false;
    }

    @Override
    public void execute() {
        //open claw to starting position which is zero
        if (Robot.claw.getMotorState()==Claw.clawMotorState.REVERSE) {
            //Note -- negative value will break
            if (Robot.claw.getEncoderPosition()<=-1) {
                Robot.claw.setMotorState(Claw.clawMotorState.OFF);
                this.isFinished = true;
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