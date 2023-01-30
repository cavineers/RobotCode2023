package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Constants;


public class ClawToggle extends CommandBase {

    private boolean isFinished = false;
    private boolean isHolding = false;
    private double m_timestamp;
    private Joystick joy;
    
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
            this.isHolding = true;
        } else {
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
            this.isHolding = false;
        }
    }

    @Override
    public void execute() {
    Robot.claw.setMotorState(Claw.ClawMotorState.ON); 
    }


    @Override 
    public void end(boolean interrupted) {
    Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
} 