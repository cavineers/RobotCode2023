package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {

    public enum ClawMotorState {
        ON,
        OFF,
        REVERSE,
    }

    public CANSparkMax m_clawMotor = new CANSparkMax(Constants.Claw.ClawID, MotorType.kBrushless);
    public ClawMotorState m_clawMotorState = ClawMotorState.OFF;

   

    public void setClawMotorState(ClawMotorState state) {
        // set current state
        this.m_clawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns on motor
                this.m_clawMotor.set(Constants.Claw.ClawSpeed);
                break;
            case OFF:
                // turns off motor
                this.m_clawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses intake
                this.m_clawMotor.set(Constants.Claw.ClawSpeedRev);
                break;

            default: 
                this.setClawMotorState(ClawMotorState.OFF);
        }
    }
     public ClawMotorState getClawMotorState() {
        return this.m_clawMotorState;
    }

    public CANSparkMax getClawMotor() {
        return this.m_clawMotor;
    }
    public double getClawMotorPosition() {
        return this.m_clawMotor.getEncoder().getPosition();
      }
    
      public void setClawMotorPosition(double position) {
        this.m_clawMotor.getEncoder().setPosition(position);
      }
}
