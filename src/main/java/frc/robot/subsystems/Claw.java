package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.CANIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase{
    
    public boolean isClosed = false;
    
    public CANSparkMax m_clawMotor = new CANSparkMax(CANIds.ClawMotor, MotorType.kBrushless);

    public CANSparkMax getMotor() {
        return this.m_clawMotor;
    }

    public clawMotorState m_clawMotorState = clawMotorState.OFF;

    public enum clawMotorState {
        ON,
        OFF,
        REVERSE
    }

    public clawMotorState getMotorState() {
        return this.m_clawMotorState;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClawMotorPosition(double position) {
        this.m_clawMotor.getEncoder().setPosition(position);
      }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void getOutputCurrent() {
        this.m_clawMotor.getOutputCurrent();
    }

    private double maxCurrent = 30;

    public void setMotorState(clawMotorState state) {
        // set current state
        this.m_clawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns motor on forward
                if (m_clawMotor.getOutputCurrent() < maxCurrent) {
                this.m_clawMotor.set(Constants.Claw.kClawSpeed);
                } else {
                    this.m_clawMotor.set(0.0);
                }
                break;
            case OFF:
                // turns off motor
                this.m_clawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses claw
                if (m_clawMotor.getOutputCurrent() < maxCurrent) {
                this.m_clawMotor.set(-Constants.Claw.kClawSpeed);
                } else {
                    this.m_clawMotor.set(0.0);
                }
                break;
            default: 
                this.setMotorState(clawMotorState.OFF);
        }
    }
}
