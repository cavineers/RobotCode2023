package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {

    public ToggleIntake() {
        this.addRequirements(Robot.intake);
    }

    public void initialize() {
    }

    public void execute() {
        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
        } else {
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
        }
    }

    public void end(boolean interrupted) {
    }

    public boolean isFinished() {
        return true;
    }

}
