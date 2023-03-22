package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeMotorState;

public class FlushCube extends CommandBase {

    public FlushCube() {
        this.addRequirements(Robot.intake);
    }

    @Override
    public void initialize() {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.REVERSE);
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