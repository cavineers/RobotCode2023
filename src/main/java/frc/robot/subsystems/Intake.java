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

    public CANSparkMax m_intakeMotorTop = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);
    public CANSparkMax m_intakeMotorBottom = new CANSparkMax(Constants.Intake.IntakeBottomID, MotorType.kBrushless);
    public IntakeMotorState m_intakeMotorStateTop = IntakeMotorState.OFF;
    public IntakeMotorState m_intakeMotorStateBottom = IntakeMotorState.OFF;

    //Intake states

    public Intake() {
        this.setIntakeMotorState(IntakeMotorState.OFF);
    }

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

    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorStateTop;
    }
    /**
     * Get the current intake state.
     * @return intake state
     */
}
