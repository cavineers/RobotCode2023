package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawClose extends CommandBase {

    private boolean isFinished;
    private double requestedRevs;

    public ClawClose() {
        this.addRequirements(Robot.claw);
        isFinished = false;
    }
    
    @Override
    public void initialize() {
        Robot.claw.setMotorState(Claw.clawMotorState.ON);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        Robot.claw.setClosed(true);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}