package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;

public class ClawCommand extends CommandBase {

    public ClawCommand() {
        addRequirements(Robot.claw);
    }

    @Override
    public void initialize() {
        Robot.claw.toggleClaw(Robot.claw.clawPosition);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}