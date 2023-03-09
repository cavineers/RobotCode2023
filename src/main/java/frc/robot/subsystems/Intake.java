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
    public CANSparkMax m_intakeMotorBottom;
    public CANSparkMax m_intakeRightDropMotor;
    public CANSparkMax m_intakeLeftDropMotor;

    public Intake(){

        m_intakeRightDropMotor = new CANSparkMax(Constants.Intake.IntakeRightDropMotorID, MotorType.kBrushless);
        m_intakeLeftDropMotor = new CANSparkMax(Constants.Intake.IntakeLeftDropMotorID, MotorType.kBrushless);
        m_intakeMotorBottom = new CANSparkMax(Constants.Intake.IntakeBottomID, MotorType.kBrushless);
        m_intakeMotorTop = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);

        m_intakeMotorTop.setInverted(true);
        m_intakeMotorBottom.setInverted(false);
        m_intakeRightDropMotor.setInverted(false);
        m_intakeLeftDropMotor.setInverted(true);

        m_intakeMotorTop.setIdleMode(IdleMode.kCoast);
        m_intakeMotorBottom.setIdleMode(IdleMode.kCoast);
        m_intakeRightDropMotor.setIdleMode(IdleMode.kCoast);
        m_intakeLeftDropMotor.setIdleMode(IdleMode.kCoast);

    }
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
                this.m_intakeRightDropMotor.set(0.0);
                this.m_intakeLeftDropMotor.set(0.0);
                break;
            case DEPLOY:
                //Deploys intake
                this.m_intakeRightDropMotor.set(Constants.Intake.IntakeLowerRightSpeed);
                this.m_intakeLeftDropMotor.set(Constants.Intake.IntakeLowerLeftSpeed);
                break;
            case UNDEPLOY:
                //Undeploys intake
                this.m_intakeRightDropMotor.set(-Constants.Intake.IntakeRaiseRightSpeed);
                this.m_intakeLeftDropMotor.set(-Constants.Intake.IntakeRaiseLeftSpeed);
                break;
            default:
                this.setIntakeDropMotorState(IntakeDropMotorState.OFF);
        }
    }


    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorState;
    }

    public CANSparkMax getIntakeRightDropMotor() {
        return this.m_intakeRightDropMotor;
    }
    public CANSparkMax getIntakeLeftDropMotor() {
        return this.m_intakeLeftDropMotor;
    }

    public IntakeDropMotorState getIntakeDropMotorState() {
        // return the current motor state
        return this.m_intakeDropMotorState;
    }
    public boolean getIntakeSwitch() {
        return this.m_intakeSwitch.get();
      }
    
    public void periodic(){
        SmartDashboard.putBoolean("Intake Limit Switch", getIntakeSwitch());
        SmartDashboard.putNumber("Left Position", m_intakeLeftDropMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Position", m_intakeRightDropMotor.getEncoder().getPosition());
        SmartDashboard.putString("State", ""+getIntakeDropMotorState());
    }
    /**
     * Get the current intake state.
     * @return intake state
     */
}