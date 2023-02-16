package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Vision;
import frc.robot.Constants;


public class ClawToggle extends CommandBase {
   
    private boolean isDone = false;
 
    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor On / Off
    @Override
    public void initialize() {
    
    }

    // Motor will move to set position based on vision. Whether Cube || Cone
    @Override
    public void execute() {
        if (Vision.CUBE = true) {
            if(Robot.claw.m_ClawMotor.getEncoder().getPosition() <= Constants.Claw.RevolutionsToCube) {
                Robot.claw.setMotorState(Claw.ClawMotorState.ON);
            } else if(Robot.claw.m_ClawMotor.getEncoder().getPosition() >= Constants.Claw.RevolutionsToCube) {
                Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
                Robot.claw.m_ClawMotor.getEncoder().setPosition(0);
                isDone = true;
            } 
        } else if (Vision.CONE = true) {
            if(Robot.claw.m_ClawMotor.getEncoder().getPosition() <= Constants.Claw.RevolutionsToCone) {
                Robot.claw.setMotorState(Claw.ClawMotorState.ON);
            } else if(Robot.claw.m_ClawMotor.getEncoder().getPosition() >= Constants.Claw.RevolutionsToCone) {
                Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
                Robot.claw.m_ClawMotor.getEncoder().setPosition(0);
                isDone = true;
            } 
        }
    }

    @Override 
    public void end(boolean interrupted) {
        Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
} 
