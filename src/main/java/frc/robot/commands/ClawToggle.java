package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawToggle extends CommandBase {

    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }
    
    private boolean isFinished = false;
    private double requestedRevs;
    
    @Override
    public void initialize() {
        if (!Robot.claw.isClosed()) {
            Robot.claw.setMotorState(Claw.clawMotorState.ON);
            requestedRevs = Constants.Claw.kRevolutionsToCube;
        } else if (Robot.claw.isClosed()) {
            Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
            requestedRevs = 0;
        }
    }

    @Override
    public void execute() {
        //stop opening claw if it is open
        if (Robot.claw.getMotorState()==Claw.clawMotorState.REVERSE) {
            if (Robot.claw.getMotor().getEncoder().getPosition() <= requestedRevs) {
                Robot.claw.setMotorState(Claw.clawMotorState.OFF);
                Robot.claw.setClosed(false);
                isFinished = true;
            }
        }
        //stop closing claw if it is closed
        if (Robot.claw.getMotorState()==Claw.clawMotorState.ON) {
            if (Robot.claw.getMotor().getEncoder().getPosition() >= requestedRevs) {
                Robot.claw.setMotorState(Claw.clawMotorState.OFF);
                Robot.claw.setClosed(true);
                isFinished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
