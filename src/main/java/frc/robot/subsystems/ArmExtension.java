package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmExtension extends SubsystemBase {

    public void periodic(){
        SmartDashboard.putNumber("ExtensionRotations", getArmExtensionMotorPosition());
        SmartDashboard.putBoolean("ExtensionSwitch", getExtensionSwitch());

        if (getExtensionSwitch() == true) {
            setArmExtensionMotorPosition(0.0);
        }
    }
    public enum ArmExtensionMotorState {
        ON,
        OFF,
        REVERSED
    }

    public CANSparkMax m_armExtensionMotor = new CANSparkMax(Constants.Arm.ArmExtensionMotor, MotorType.kBrushless);

    private DigitalInput m_extensionLimitSwitch = new DigitalInput(Constants.DIO.ArmExtensionSwitch);

    public ArmExtensionMotorState m_armExtensionMotorState = ArmExtensionMotorState.OFF;

    public ArmExtension() {
        this.m_armExtensionMotor.setIdleMode(IdleMode.kBrake);

        this.m_armExtensionMotor.setInverted(false);

        this.m_armExtensionMotor.setSmartCurrentLimit(40);
       
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
    public ArmExtensionMotorState getArmExtensionMotorState() {
        return this.m_armExtensionMotorState;
    }
    public CANSparkMax getArmExtensionMotor() {
        return this.m_armExtensionMotor;
    }
   public double getArmExtensionMotorPosition() {
    return this.m_armExtensionMotor.getEncoder().getPosition();
    }
    public void setArmExtensionMotorPosition(double position) {
        this.m_armExtensionMotor.getEncoder().setPosition(position);
  }
      public boolean getExtensionSwitch() {
        return this.m_extensionLimitSwitch.get();
      }  
}