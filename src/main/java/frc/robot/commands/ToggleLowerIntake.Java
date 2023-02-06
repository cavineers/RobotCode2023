package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Intake;

public class ToggleLowerIntake extends CommandBase {
    
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
        } else if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.LOWER) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}