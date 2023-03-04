package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        Robot.intake.getIntakeSwitch();
        SmartDashboard.putBoolean("Limit switch", Robot.intake.getIntakeSwitch());
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.UNDEPLOY);
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
        } if (Robot.intake.getIntakeSwitch()) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            Robot.intake.getIntakeDropMotor().getEncoder().setPosition(0);
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