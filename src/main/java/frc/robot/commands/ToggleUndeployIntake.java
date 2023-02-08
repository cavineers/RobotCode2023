package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleUndeployIntake extends CommandBase {

    boolean isFinished = false;

    public ToggleUndeployIntake() {
        this.addRequirements(Robot.intake);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.UNDEPLOY);
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
        } if (Robot.intake.getIntakeSwitch() == true) {
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
