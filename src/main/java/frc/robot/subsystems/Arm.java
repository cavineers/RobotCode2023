package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

    public void periodic(){

        SmartDashboard.putNumber("AngleRotations", getArmChainMotorPosition());
        SmartDashboard.putNumber("Angle2Rotations", getArmChainMotor2Position());
        SmartDashboard.putNumber("ExtensionRotations", getArmExtensionMotorPosition());
        SmartDashboard.putBoolean("AngleProxSensor", getAngleProxSensor());
        SmartDashboard.putBoolean("ExtensionSwitch", getExtensionSwitch());

        if (getExtensionSwitch() == true) {
            setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
            setArmExtensionMotorPosition(0.0);
        }
        if (getAngleProxSensor() == true) {
            setArmChainMotorPosition(0.0);
            setArmChainMotor2Position(0.0);
        }
    }

     public enum ArmChainMotorState {
        ON,
        OFF,
        REVERSED
    }
    public enum ArmChainMotor2State {
        ON,
        OFF,
        REVERSED
    }
    public enum ArmExtensionMotorState {
        ON,
        OFF,
        REVERSED
    }

    
    

    public CANSparkMax m_armChainMotor = new CANSparkMax(Constants.Arm.ArmChainMotor, MotorType.kBrushless);
    public CANSparkMax m_armChainMotor2 = new CANSparkMax(Constants.Arm.ArmChainMotor2, MotorType.kBrushless);
    public CANSparkMax m_armExtensionMotor = new CANSparkMax(Constants.Arm.ArmExtensionMotor, MotorType.kBrushless);

    private DigitalInput m_angleProxSensor = new DigitalInput(Constants.DIO.AngleProxSensor);
    private DigitalInput m_extensionLimitSwitch = new DigitalInput(Constants.DIO.ArmExtensionSwitch);
    
    
    public ArmChainMotorState m_armChainMotorState = ArmChainMotorState.OFF;
    public ArmChainMotor2State m_armChainMotor2State = ArmChainMotor2State.OFF;
    public ArmExtensionMotorState m_armExtensionMotorState = ArmExtensionMotorState.OFF;

    public Arm() {
        this.m_armChainMotor.setIdleMode(IdleMode.kBrake);
        this.m_armChainMotor2.setIdleMode(IdleMode.kBrake);
        this.m_armExtensionMotor.setIdleMode(IdleMode.kBrake);

        this.m_armChainMotor.setInverted(false);
        this.m_armChainMotor2.setInverted(false);
        this.m_armExtensionMotor.setInverted(false);



        this.m_armChainMotor.setSmartCurrentLimit(39);
        this.m_armChainMotor2.setSmartCurrentLimit(39);
        this.m_armExtensionMotor.setSmartCurrentLimit(39);
       
    }
    
    public void setArmChainMotorState(ArmChainMotorState state) {
        // set the current state
        this.m_armChainMotorState = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_armChainMotor.set(Constants.Arm.ArmChainSpeed);
                break;
            case OFF:
                // Off
                this.m_armChainMotor.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_armChainMotor.set(Constants.Arm.ArmChainSpeedRev);
                break;
            default:
                this.setArmChainMotorState(ArmChainMotorState.OFF);
        }
    }
    public void setArmChainMotor2State(ArmChainMotor2State state) {
        // set the current state
        this.m_armChainMotor2State = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_armChainMotor2.set(Constants.Arm.ArmChainSpeedRev);
                break;
            case OFF:
                // Off
                this.m_armChainMotor2.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_armChainMotor2.set(Constants.Arm.ArmChainSpeed);
                break;
            default:
                this.setArmChainMotor2State(ArmChainMotor2State.OFF);
            }
        }
        public void setArmExtensionMotorState(ArmExtensionMotorState state) {
            // set the current state
            this.m_armExtensionMotorState = state;
            
            // set motor state
            switch (state) {
                case ON:
                    // On
                    this.m_armExtensionMotor.set(Constants.Arm.ArmExtensionSpeed);
                    break;
                case OFF:
                    // Off
                    this.m_armExtensionMotor.set(0.0);
                    break;
                case REVERSED:
                    // Reversed
                    this.m_armExtensionMotor.set(Constants.Arm.ArmExtensionSpeedRev);
                    break;
                default:
                    this.setArmExtensionMotorState(ArmExtensionMotorState.OFF);
            }
    }
    public ArmChainMotorState getArmChainMotorState() {
        return this.m_armChainMotorState;
    }
    public ArmChainMotor2State getArmChainMotor2State() {
        return this.m_armChainMotor2State;
    }
    public ArmExtensionMotorState getArmExtensionMotorState() {
        return this.m_armExtensionMotorState;
    }

    public CANSparkMax getArmChainMotor() {
        return this.m_armChainMotor;
    }
    public CANSparkMax getArmChainMotor2() {
        return this.m_armChainMotor2;
    }
    public CANSparkMax getArmExtensionMotor() {
        return this.m_armExtensionMotor;
    }
    public double getArmChainMotorPosition() {
    return this.m_armChainMotor.getEncoder().getPosition();
  }

  public double getArmChainMotor2Position() {
    return this.m_armChainMotor2.getEncoder().getPosition();
  }

   public double getArmExtensionMotorPosition() {
    return this.m_armExtensionMotor.getEncoder().getPosition();
  }

  public void setArmChainMotorPosition(double position) {
    this.m_armChainMotor.getEncoder().setPosition(position);
  }

  public void setArmChainMotor2Position(double position) {
    this.m_armChainMotor2.getEncoder().setPosition(position);
  }

  public void setArmExtensionMotorPosition(double position) {
    this.m_armExtensionMotor.getEncoder().setPosition(position);
  }
  
    
    public boolean getAngleProxSensor() {
        return !this.m_angleProxSensor.get();
      }
    
      public boolean getExtensionSwitch() {
        return this.m_extensionLimitSwitch.get();
      }  
}