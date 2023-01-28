package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleRaiseIntake extends CommandBase {
    
    public ToggleRaiseIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotor().getEncoder().getPosition() > Constants.Intake.RevolutionsToLower) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.RAISE);
        } else if (Robot.intake.getIntakeDropMotor().getEncoder().getPosition() <= Constants.Intake.RevolutionsToLower) {
            Robot.m_robotContainer.m_intake.schedule();
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}