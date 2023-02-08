package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleRaiseIntake extends CommandBase {

    boolean isFinished = false;

    public ToggleRaiseIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Limit Switch", Robot.intake.getIntakeSwitch());
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.RAISE);
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
        }
        if(Robot.intake.getIntakeSwitch() == true) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
