package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleLowerIntake extends CommandBase {
    private boolean inPosition = false;
    
    public ToggleLowerIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotor().getEncoder().getPosition() <= Constants.Intake.RevolutionsToLower) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.LOWER);
        } else if (Robot.intake.getIntakeDropMotor().getEncoder().getPosition() >= -1) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            this.inPosition = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return this.inPosition;
    }
}