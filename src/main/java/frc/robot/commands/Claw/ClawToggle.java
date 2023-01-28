package frc.robot.commands.claw;

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
    
        // Sensors activate High/Low Pressure
        if (joy.getRawButton(2)) { //sensor activates High Pressure
            setPressure(Constants.Claw.ClawHighPressure);
        } else if (joy.getRawButton(2)) { //sensor activates Low Pressure
            setPressure(Constants.Claw.ClawLowPressure);
        } else { //Pressure is set to 0 if nothing else
            setPressure(0);
        }   
    }

    private void setPressure(int pressure) {
        if (pressure == (Constants.Claw.ClawHighPressure)) {
            
        } else if (pressure == (Constants.Claw.ClawLowPressure)) {
            
        } else {

        }
    }

    @Override 
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
} 