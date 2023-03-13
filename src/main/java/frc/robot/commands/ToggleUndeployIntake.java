package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleUndeployIntake extends CommandBase {

    boolean isFinished;

    public ToggleUndeployIntake() {
        this.addRequirements(Robot.intake);
    }

    @Override
    public void initialize() {
        this.isFinished = false;
    }

    @Override
    public void execute() {
        if (Robot.intake.getIntakeDropMotorState() == Intake.IntakeDropMotorState.OFF) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.UNDEPLOY);
            Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
        } if (Robot.intake.getIntakeSwitch() == true) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
        if(!interrupted){
            Robot.intake.getIntakeLeftDropMotor().getEncoder().setPosition(0);
            Robot.intake.getIntakeRightDropMotor().getEncoder().setPosition(0);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}