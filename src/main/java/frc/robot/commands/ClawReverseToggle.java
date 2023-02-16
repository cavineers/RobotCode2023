package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Vision;
import frc.robot.Constants;


public class ClawReverseToggle extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public ClawReverseToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor On / Off
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Robot.claw.getClawMotorState() == Claw.ClawMotorState.OFF) {
            Robot.claw.setMotorState(Claw.ClawMotorState.REVERSE);
        } else if (Robot.claw.m_ClawMotor.getEncoder().getPosition() <= Constants.Claw.RevolutionsToCone){
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
            isDone = true;
        }                
    }


    @Override 
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
} 
