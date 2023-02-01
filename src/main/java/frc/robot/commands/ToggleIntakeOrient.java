package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntakeOrient extends CommandBase{

    public ToggleIntakeOrient() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        if (Robot.intake.getIntakeOrientState() == Intake.IntakeOrientState.OFF) {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.ON);
        } else {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
