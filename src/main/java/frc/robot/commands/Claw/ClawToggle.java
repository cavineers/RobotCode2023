package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;

public class ClawToggle extends CommandBase {

    private boolean isFinished = false;
    private boolean holding = false;
    private double m_timestamp;

    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor On / Off
    @Override
    public void initialize() {
        this.m_timestamp = Timer.getFPGATimestamp();

        if (Robot.claw.getClawMotorState() == Claw.ClawMotorState.OFF) {
            Robot.claw.setMotorState(Claw.ClawMotorState.ON);
            this.isFinished = false;
        } else {
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
            this.isFinished = true;
        }
    }
} 