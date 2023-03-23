package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeMotorState;
import edu.wpi.first.wpilibj.Timer;

public class FlushCube extends CommandBase {

    boolean isFinished;
    private double m_timestamp;

    public FlushCube() {
        this.addRequirements(Robot.intake);
    }

    @Override
    public void initialize() {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.REVERSE);
        this.isFinished = false;
    }

    @Override
    public void execute(){
    }


    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isFinished = true;
        }
        return this.isFinished;
    }
}