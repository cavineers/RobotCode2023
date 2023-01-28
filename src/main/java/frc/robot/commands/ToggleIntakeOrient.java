package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntakeOrient extends CommandBase{
    public ToggleIntakeOrient() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeLeftOrient().getEncoder().getPosition() > Constants.Intake.RevolutionsToLower) {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.ON);
        } else if (Robot.intake.getIntakeLeftOrient().getEncoder().getPosition() <= Constants.Intake.RevolutionsToLower) {
            Robot.m_robotContainer.m_intake.schedule();
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
