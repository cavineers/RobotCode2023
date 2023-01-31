package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {
    private boolean isDone = false;

    private double m_timestamp;

    public ToggleIntake() {
        this.addRequirements(Robot.intake);
    }

    public void initialize() {

        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
            this.isDone = false;
        } else {
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
            this.isDone = true;
        }
    }

    public void execute() {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
    }

    public void end(boolean interrupted) {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
    }

    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.4 && Robot.m_robotContainer.joy.getRawButton(6)) {
            this.isDone = true;
        }

        return this.isDone;
    }

}
