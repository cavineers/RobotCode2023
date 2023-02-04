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
        CUBEHOLD,
        CONEHOLD
    }

    boolean isOn;
    boolean opening;
    boolean closing;
    boolean holdingCube;
    boolean holdingCone;

    public CANSparkMax m_ClawMotor = new CANSparkMax(Constants.Claw.ClawID, MotorType.kBrushless);
    public ClawMotorState m_ClawMotorState = ClawMotorState.OFF;

    public ClawMotorState getClawMotorState() {
        return this.m_ClawMotorState;
    }

    public void setMotorState(ClawMotorState state) {
        // set current state
        this.m_ClawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns on motor
                this.m_ClawMotor.set(Constants.Claw.ClawSpeed);
                break;
            case OFF:
                // turns off motor
                this.m_ClawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses intake
                this.m_ClawMotor.set(Constants.Claw.ClawSpeedRev);
                break;
            default:
                this.setMotorState(ClawMotorState.OFF);
        }
    }
}
