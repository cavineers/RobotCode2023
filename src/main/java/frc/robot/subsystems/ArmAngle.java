package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ArmAngle extends SubsystemBase {

    public void periodic(){

        // SmartDashboard.putNumber("AngleRotations", getArmChainMotorPosition());
        // SmartDashboard.putNumber("Angle2Rotations", getArmChainMotor2Position()); 
        // SmartDashboard.putNumber("AngleSpeed", (Constants.Arm.ArmChainSpeedUp + (Constants.Arm.MidNodeShelfAngleRotations - Robot.armAngle.getArmChainMotorPosition())/150 ));
        // SmartDashboard.putNumber("Angle2Speed",-(Constants.Arm.ArmChainSpeedUp) - (Constants.Arm.MidNodeShelfAngleRotations - Robot.armAngle.getArmChainMotorPosition())/150 );   
        SmartDashboard.putBoolean("AngleProxSensor", getAngleProxSensor());

        // if (getAngleProxSensor() == true) {
        //     setArmChainMotorPosition(0.0);
        //     setArmChainMotor2Position(0.0);
        // }
    }
    // States for both motors
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

    // Motor Initialization
    public CANSparkMax m_armChainMotor = new CANSparkMax(Constants.Arm.ArmChainMotor, MotorType.kBrushless);
    public CANSparkMax m_armChainMotor2 = new CANSparkMax(Constants.Arm.ArmChainMotor2, MotorType.kBrushless);

    // Sensor initialization
    private DigitalInput m_angleProxSensor = new DigitalInput(Constants.DIO.AngleProxSensor);
    
    // Starts motors in their off state
    public ArmChainMotorState m_armChainMotorState = ArmChainMotorState.OFF;
    public ArmChainMotor2State m_armChainMotor2State = ArmChainMotor2State.OFF;

    // Motor sparkmax settings
    public ArmAngle() {
        this.m_armChainMotor.setIdleMode(IdleMode.kBrake);
        this.m_armChainMotor2.setIdleMode(IdleMode.kBrake);

        this.m_armChainMotor.setInverted(false);
        this.m_armChainMotor2.setInverted(false);

        this.m_armChainMotor.setSmartCurrentLimit(51);
        this.m_armChainMotor2.setSmartCurrentLimit(51);
       
    }
    
    public void setArmChainMotorState(ArmChainMotorState state) {
        // set the current state
        this.m_armChainMotorState = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_armChainMotor.set(Constants.Arm.ArmChainSpeedUp);
                break;
            case OFF:
                // Off
                if (m_armChainMotor.getEncoder().getPosition() >= Constants.Arm.ArmRotationsAddPower && m_armChainMotor.getEncoder().getPosition() <= Constants.Arm.MaxAngleRotations) {
                    this.m_armChainMotor.set(0.03);
                } else if (m_armChainMotor.getEncoder().getPosition() <= Constants.Arm.ArmRotationsAddPower && m_armChainMotor.getEncoder().getPosition() >= Constants.Arm.ArmRotationsAddPowerBottom) {
                    this.m_armChainMotor.set(0.01);
                }
                else {
                    this.m_armChainMotor.set(0);
                }
                break;
            case REVERSED:
                // Reversed
                this.m_armChainMotor.set(Constants.Arm.ArmChainSpeedDown);
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
                this.m_armChainMotor2.set(-(Constants.Arm.ArmChainSpeedUp));
                break;
            case OFF:
                // Off
                if (m_armChainMotor.getEncoder().getPosition() >= Constants.Arm.ArmRotationsAddPower) {
                    this.m_armChainMotor2.set(-0.03);
                }
                else {
                    this.m_armChainMotor2.set(0);
                }
                break;
            case REVERSED:
                // Reversed
                this.m_armChainMotor2.set(-(Constants.Arm.ArmChainSpeedDown));
                break;
            default:
                this.setArmChainMotor2State(ArmChainMotor2State.OFF);
            }
        }

    public ArmChainMotorState getArmChainMotorState() {
        return this.m_armChainMotorState;
    }
    public ArmChainMotor2State getArmChainMotor2State() {
        return this.m_armChainMotor2State;
    }

    public CANSparkMax getArmChainMotor() {
        return this.m_armChainMotor;
    }
    public CANSparkMax getArmChainMotor2() {
        return this.m_armChainMotor2;
    }
    public double getArmChainMotorPosition() {
    return this.m_armChainMotor.getEncoder().getPosition();
  }

  public double getArmChainMotor2Position() {
    return this.m_armChainMotor2.getEncoder().getPosition();
  }

  public void setArmChainMotorPosition(double position) {
    this.m_armChainMotor.getEncoder().setPosition(position);
  }

  public void setArmChainMotor2Position(double position) {
    this.m_armChainMotor2.getEncoder().setPosition(position);
  }
 
    public boolean getAngleProxSensor() {
        return !this.m_angleProxSensor.get();
      }
}