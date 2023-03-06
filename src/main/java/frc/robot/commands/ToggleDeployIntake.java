package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleDeployIntake extends CommandBase {
    private boolean isFinished = false;
    
    public ToggleDeployIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.DEPLOY);
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
        } if (Robot.intake.getIntakeLeftDropMotor().getEncoder().getPosition() >= Constants.Intake.RevolutionsToLower && 
        Robot.intake.getIntakeRightDropMotor().getEncoder().getPosition() >= Constants.Intake.RevolutionsToLower) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
        Robot.intake.getIntakeRightDropMotor().getEncoder().setPosition(0);
        Robot.intake.getIntakeLeftDropMotor().getEncoder().setPosition(0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}