package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class IntakeCube extends CommandBase {
    boolean isFinished;
    
    public IntakeCube() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
        this.isFinished = false;
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}