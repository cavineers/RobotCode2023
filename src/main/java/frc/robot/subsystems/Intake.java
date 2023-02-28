package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{


    //Intake states
    public enum IntakeMotorState {
        ON,
        OFF
    }
    public enum IntakeDropMotorState {
        UNDEPLOY,
        DEPLOY,
        OFF
    }

    // SparkMax's
    public CANSparkMax m_intakeMotorTop = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);
    public CANSparkMax m_intakeMotorBottom = new CANSparkMax(Constants.Intake.IntakeBottomID, MotorType.kBrushless);
    public CANSparkMax m_intakeDropMotor = new CANSparkMax(Constants.Intake.IntakeDropMotorID, MotorType.kBrushless);
    public CANSparkMax m_intakeDropMotor2 = new CANSparkMax(Constants.Intake.IntakeDropMotor2ID, MotorType.kBrushless);

    // Limit Switch
    public DigitalInput m_intakeSwitch = new DigitalInput(Constants.DIO.IntakeSwitch);

    // Intake states
    public IntakeMotorState m_intakeMotorState = IntakeMotorState.OFF;
    public IntakeDropMotorState m_intakeDropMotorState = IntakeDropMotorState.OFF;

    public void setIntakeMotorState(IntakeMotorState state) {
        // set the current state of top and bottom motor
        this.m_intakeMotorState = state;
        
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
            default:
                this.setIntakeMotorState(IntakeMotorState.OFF);
        }
    }


    public void setIntakeDropMotorState(IntakeDropMotorState state) {

        this.m_intakeDropMotorState = state;

        switch (state) {
            case OFF:
                this.m_intakeDropMotor.set(0.0);
                this.m_intakeDropMotor2.set(0.0);
                break;
            case DEPLOY:
                //Deploys intake
                this.m_intakeDropMotor.set(Constants.Intake.IntakeLowerSpeed);
                this.m_intakeDropMotor2.set(Constants.Intake.IntakeLowerSpeed);
                break;
            case UNDEPLOY:
                //Undeploys intake
                this.m_intakeDropMotor.set(Constants.Intake.IntakeRaiseSpeed);
                this.m_intakeDropMotor2.set(Constants.Intake.IntakeRaiseSpeed);
                break;
            default:
                this.setIntakeDropMotorState(IntakeDropMotorState.OFF);
        }
    }


    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorState;
    }

    public CANSparkMax getIntakeDropMotor() {
        return this.m_intakeDropMotor;
    }

    public IntakeDropMotorState getIntakeDropMotorState() {
        // return the current motor state
        return this.m_intakeDropMotorState;
    }
    public boolean getIntakeSwitch() {
        return this.m_intakeSwitch.get();
      }
    /**
     * Get the current intake state.
     * @return intake state
     */
}