package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeDropMotorState;

public class ToggleLowerIntake extends CommandBase {
    
    private boolean isDone = false;

    private double m_timestamp;

    public ToggleLowerIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.LOWER);
            this.isDone = false;
        } else {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            this.isDone = true;
        }
    }

    @Override
    public void execute() {
        Robot.intake.setIntakeDropMotorState(IntakeDropMotorState.LOWER);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeDropMotorState(IntakeDropMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.4 && Robot.m_robotContainer.joy.getRawButton(2)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}