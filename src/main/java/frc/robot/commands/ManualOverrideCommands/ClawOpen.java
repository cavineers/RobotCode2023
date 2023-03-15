package frc.robot.commands.ManualOverrideCommands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawOpen extends CommandBase {

    private boolean isFinished;
    private double requestedRevs;
    private boolean limitHit;

    public ClawOpen() {
        this.addRequirements(Robot.claw);
    }
    
    @Override
    public void initialize() {
        this.isFinished = true;
        if (Robot.claw.getEncoderPosition()<=0){
            Robot.claw.setMotorState(Claw.clawMotorState.OFF);
        } else {
            Robot.claw.setMotorState(Claw.clawMotorState.REVERSE);
        }
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
        return isFinished;
    }
}