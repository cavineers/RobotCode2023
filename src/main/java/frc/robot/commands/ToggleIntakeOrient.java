package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntakeOrient extends CommandBase{

    private boolean isDone = false;
    private double m_timestamp;
    
    public ToggleIntakeOrient() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.intake.getIntakeOrientState() == Intake.IntakeOrientState.OFF) {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.ON);
            this.isDone = false;
        } else {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.OFF);
            this.isDone = true;
        }
    }

    @Override
    public void execute() {
        Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.ON);
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.4 && Robot.m_robotContainer.joy.getRawButton(2)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}
