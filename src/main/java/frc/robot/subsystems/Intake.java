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

    public CANSparkMax m_intakeMotorRight = new CANSparkMax(Constants.Intake.IntakeID, MotorType.kBrushless);
    public CANSparkMax m_intakeMotorLeft = new CANSparkMax(Constants.Intake.IntakeID, MotorType.kBrushless);
    public IntakeMotorState m_intakeMotorStateRight = IntakeMotorState.OFF;
    public IntakeMotorState m_intakeMotorStateLeft = IntakeMotorState.OFF;

    //Intake states

    public void setIntakeMotorState(IntakeMotorState state) {
        // set the current state
        this.m_intakeMotorStateRight = state;
        this.m_intakeMotorStateLeft = state;
        
        // set motor state
        switch (state) {
            case ON:
                // turns on intake
                this.m_intakeMotorRight.set(Constants.Intake.IntakeSpeedRight);
                this.m_intakeMotorLeft.set(Constants.Intake.IntakeSpeedLeft);
                break;
            case OFF:
                // turns off intake
                this.m_intakeMotorRight.set(0.0);
                this.m_intakeMotorLeft.set(0.0);
                break;
            case REVERSED:
                // reverses intake
                this.m_intakeMotorRight.set(Constants.Intake.IntakeSpeedRevRight);
                this.m_intakeMotorLeft.set(Constants.Intake.IntakeSpeedRevLeft);
                break;
            default:
                this.setIntakeMotorState(IntakeMotorState.OFF);
        }
    }
    /**
     * Get the current intake state.
     * @return intake state
     */
}
