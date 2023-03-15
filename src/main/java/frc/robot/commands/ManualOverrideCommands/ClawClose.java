package frc.robot.commands.ManualOverrideCommands;

import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawClose extends CommandBase {

    private boolean isFinished;

    public ClawClose() {
        this.addRequirements(Robot.claw);
    }
    
    @Override
    public void initialize() {
        this.isFinished = true;
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
        return isFinished;
    }
}