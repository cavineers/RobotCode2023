package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{

    public CANSparkMax m_intakeMotorTop;

    public DigitalInput m_intakeIR;

    public Intake(){

        m_intakeMotorTop = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);

        m_intakeMotorTop.setInverted(true);

        m_intakeMotorTop.setIdleMode(IdleMode.kCoast);

        m_intakeMotorTop.setSmartCurrentLimit(Constants.Intake.kIntakeCurrentLimit);

        m_intakeIR = new DigitalInput(Constants.DIO.IntakeSwitch);

    }
    //Intake states
    public enum IntakeMotorState {
        ON,
        REVERSE,
        OFF
    }

    // Intake states
    public IntakeMotorState m_intakeMotorState = IntakeMotorState.OFF;

    public void setIntakeMotorState(IntakeMotorState state) {
        // set the current state of top and bottom motor
        this.m_intakeMotorState = state;
        
        // set motor state
        switch (state) {
            case ON:
                // turns on intake
                this.m_intakeMotorTop.set(Constants.Intake.IntakeSpeedTop);
                break;
            case REVERSE:
                this.m_intakeMotorTop.set(-Constants.Intake.IntakeSpeedTop);
                break;
            case OFF:
                // turns off intake
                this.m_intakeMotorTop.set(0.0);
                break;
            default:
                this.setIntakeMotorState(IntakeMotorState.OFF);
        }
    }

    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorState;
    }

    public boolean getIntakeIR() {
        return this.m_intakeIR.get();
      }
    
    public void periodic(){
        SmartDashboard.putBoolean("IntakeIrSensor", getIntakeIR());
    }
    /**
     * Get the current intake state.
     * @return intake state
     */
}