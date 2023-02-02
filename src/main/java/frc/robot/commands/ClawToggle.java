package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.Vision;


public class ClawToggle extends CommandBase {
    
    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor On / Off
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Robot.claw.getClawMotorState() == Claw.ClawMotorState.OFF) {
            Robot.claw.setMotorState(Claw.ClawMotorState.ON);
        } else {
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
        }
    }


    @Override 
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
} 
