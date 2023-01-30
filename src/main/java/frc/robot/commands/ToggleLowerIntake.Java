package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleLowerIntake extends CommandBase {
    
    private boolean isDone = false;

    public ToggleLowerIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.LOWER);
            this.isDone = false;
        } else {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            this.isDone = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}