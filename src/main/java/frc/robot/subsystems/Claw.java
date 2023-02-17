package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.CANIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Claw {
    
    private boolean isClosed = false;
    
    private CANSparkMax m_clawMotor = new CANSparkMax(CANIds.ClawMotor, MotorType.kBrushless);

    private clawMotorState m_clawMotorState = clawMotorState.OFF;

    private enum clawMotorState {
        ON,
        OFF,
        REVERSE
    }

    public void setMotorState(clawMotorState state) {
        // set current state
        this.m_clawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns on motor
                this.m_clawMotor.set(Constants.Claw.kClawSpeed);
                break;
            case OFF:
                // turns off motor
                this.m_clawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses claw
                this.m_clawMotor.set(-Constants.Claw.kClawSpeed);
                break;
            default: 
                this.setMotorState(clawMotorState.OFF);
        }
    }


}
