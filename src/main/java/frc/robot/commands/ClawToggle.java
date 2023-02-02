package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;


public class ClawToggle extends CommandBase {

    private boolean isDone = false;
    private boolean isHolding = false;
    private double m_timestamp;
    private Joystick joy;
    
    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor On / Off
    @Override
    public void initialize() {
        this.m_timestamp = Timer.getFPATimestamp();
        
        if (Robot.claw.getClawMotorState() == Claw.ClawMotorState.OFF) {
            Robot.claw.setMotorState(Claw.ClawMotorState.ON);
            this.isDone = false;
        } else {
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
            this.isDone = true;
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
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.4 && Robot.m_robotContainer.joy.getRawButton(2)) {
            this.isDone = true;
        }

        return this.isDone;
    }
} 
