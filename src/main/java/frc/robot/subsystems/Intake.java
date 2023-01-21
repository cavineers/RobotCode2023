package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    
    public enum IntakeMotorStateRight {
        ON,
        OFF,
        REVERSED
    }

    public enum IntakeMotorStateLeft {
        ON,
        OFF,
        REVERSED
    }

    public CANSparkMax m_intakeMotorRight = new CANSparkMax(Constants.Intake.IntakeID, MotorType.kBrushless);
    public CANSparkMax m_intakeMotorLeft = new CANSparkMax(Constants.Intake.IntakeID, MotorType.kBrushless);
    public IntakeMotorStateRight m_intakeMotorStateRight = IntakeMotorStateRight.OFF;
    public IntakeMotorStateLeft m_intakeMotorStateLeft = IntakeMotorStateLeft.OFF;



    //Intake states

    public void setRightMotorState(IntakeMotorStateRight state) {
        // set the current state
        this.m_intakeMotorStateRight = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeMotorRight.set(Constants.Intake.IntakeSpeedRight);
                break;
            case OFF:
                // Off
                this.m_intakeMotorRight.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeMotorRight.set(Constants.Intake.IntakeSpeedRevRight);
                break;
            default:
                this.setRightMotorState(IntakeMotorStateRight.OFF);
        }
    }

    public void setLeftMotorState(IntakeMotorStateLeft state) {
        // set the current state
        this.m_intakeMotorStateLeft = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeMotorLeft.set(Constants.Intake.IntakeSpeedLeft);
                break;
            case OFF:
                // Off
                this.m_intakeMotorLeft.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeMotorLeft.set(Constants.Intake.IntakeSpeedRevLeft);
                break;
            default:
                this.setLeftMotorState(IntakeMotorStateLeft.OFF);
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */

}



