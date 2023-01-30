package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntakeOrient extends CommandBase{

    private boolean isDone = false;
    
    public ToggleIntakeOrient() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeOrientState() == Intake.IntakeOrientState.OFF) {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.ON);
            this.isDone = false;
        } else {
            Robot.intake.setIntakeOrientState(Intake.IntakeOrientState.OFF);
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
