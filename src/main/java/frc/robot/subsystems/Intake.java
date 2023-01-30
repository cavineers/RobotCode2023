package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    
    public enum IntakeMotorState {
        ON,
        OFF,
        REVERSED
    }

    public enum IntakeDropMotorState {
        RAISE,
        LOWER,
        OFF
    }

    public enum IntakeOrientState {
        ON,
        OFF
    }

    public CANSparkMax m_intakeMotorTop = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);
    public CANSparkMax m_intakeMotorBottom = new CANSparkMax(Constants.Intake.IntakeBottomID, MotorType.kBrushless);
    public CANSparkMax m_intakeDropMotor = new CANSparkMax(Constants.Intake.IntakeDropMotorID, MotorType.kBrushless);
    public CANSparkMax m_intakeLeftOrient = new CANSparkMax(Constants.Intake.IntakeLeftOrientID, MotorType.kBrushless);
    public CANSparkMax m_intakeRightOrient = new CANSparkMax(Constants.Intake.IntakeRightOrientID, MotorType.kBrushless);
    public IntakeMotorState m_intakeMotorStateTop = IntakeMotorState.OFF;
    public IntakeMotorState m_intakeMotorStateBottom = IntakeMotorState.OFF;
    public IntakeDropMotorState m_intakeDropMotorState = IntakeDropMotorState.OFF;
    public IntakeOrientState m_IntakeOrientState = IntakeOrientState.OFF;
    //Intake states

    public void setIntakeMotorState(IntakeMotorState state) {
        // set the current state
        this.m_intakeMotorStateTop = state;
        this.m_intakeMotorStateBottom = state;
        
        // set motor state
        switch (state) {
            case ON:
                // turns on intake
                this.m_intakeMotorTop.set(Constants.Intake.IntakeSpeedTop);
                this.m_intakeMotorBottom.set(Constants.Intake.IntakeSpeedBottom);
                break;
            case OFF:
                // turns off intake
                this.m_intakeMotorTop.set(0.0);
                this.m_intakeMotorBottom.set(0.0);
                break;
            case REVERSED:
                // reverses intake
                this.m_intakeMotorTop.set(Constants.Intake.IntakeSpeedRevTop);
                this.m_intakeMotorBottom.set(Constants.Intake.IntakeSpeedRevBottom);
                break;
            default:
                this.setIntakeMotorState(IntakeMotorState.OFF);
        }
    }

    public void setIntakeDropMotorState(IntakeDropMotorState state) {
        this.m_intakeDropMotorState = state;

        switch (state) {
            case LOWER:
                // turns on intake
                this.m_intakeDropMotor.set(Constants.Intake.IntakeDropSpeed);
                break;
            case OFF:
                // turns off intake
                this.m_intakeDropMotor.set(0.0);
                break;
            case RAISE:
                // reverses intake
                this.m_intakeDropMotor.set(Constants.Intake.IntakeRaiseSpeed);
                break;
            default:
                this.setIntakeDropMotorState(IntakeDropMotorState.OFF);
        }
    }

    public void setIntakeOrientState(IntakeOrientState state) {
        this.m_IntakeOrientState = state;

        switch (state) {
            case ON:
                this.m_intakeLeftOrient.set(Constants.Intake.IntakeLeftOrientSpeed);
                this.m_intakeRightOrient.set(Constants.Intake.IntakeRightOrientSpeed);
                break;
            case OFF:
                this.m_intakeLeftOrient.set(0.0);
                this.m_intakeRightOrient.set(0.0);
                break;
            default:
                this.setIntakeOrientState(IntakeOrientState.OFF);
        }
    }

    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorStateTop;
    }
    
    public CANSparkMax getIntakeLeftOrient() {
        return this.m_intakeLeftOrient;
    }

    public IntakeDropMotorState getIntakeDropMotorState() {
        // return the current motor state
        return this.m_intakeDropMotorState;
    }

    public CANSparkMax getIntakeDropMotor() {
        // return the current motor state
        return this.m_intakeDropMotor;
    }
    /**
     * Get the current intake state.
     * @return intake state
     */
}
