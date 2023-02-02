package frc.robot.subsystems;

import java.util.Dictionary;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

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

    private DigitalInput m_angleLimitSwitch = new DigitalInput(Constants.DIO.ArmAngleSwitch);
    private DigitalInput m_extensionLimitSwitch = new DigitalInput(Constants.DIO.ArmExtensionSwitch);
    
    
    public ArmChainMotorState m_armChainMotorState = ArmChainMotorState.OFF;
    public ArmChainMotor2State m_armChainMotor2State = ArmChainMotor2State.OFF;
    public ArmExtensionMotorState m_armExtensionMotorState = ArmExtensionMotorState.OFF;
    
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
    
    public boolean getAngleSwitch() {
        return this.m_angleLimitSwitch.get();
      }
    
      public boolean getExtensionSwitch() {
        return this.m_extensionLimitSwitch.get();
      }


   


    public int[] getIntakeRotations() {
        double finalAngle = Constants.Arm.IntakeAngle;
        double finalDistance = Constants.Arm.IntakeDistance;
        Long angleRotations = Math.round(finalAngle * Constants.Arm.RotationsPerDegree);
        Long distanceRotations = Math.round(finalDistance * Constants.Arm.RotationsPerCM);
        int[] rotations = {angleRotations.intValue(), distanceRotations.intValue()};
        return rotations;
    }

    public enum PlacementNode {
        TOP_PEG,
        MID_PEG,
        TOP_SHELF,
        MID_SHELF
    }

    public int[] getPlacementNodeRotations(PlacementNode state){
            
        double nodeX, nodeY;

        //Switches State
        switch(state){
            case TOP_PEG:
                nodeX = Constants.Arm.TopX;
                nodeY = Constants.Arm.TopPegY;
                break;
            case TOP_SHELF:
                nodeX = Constants.Arm.TopX;
                nodeY = Constants.Arm.TopShelfY;
                break;
            case MID_PEG:
                nodeX = Constants.Arm.MidX;
                nodeY = Constants.Arm.MidPegY;
                break;
            case MID_SHELF:
                nodeX = Constants.Arm.MidX;
                nodeY = Constants.Arm.MidShelfY;
                break;
            default:
                nodeX = 0;
                nodeY = 0;
        }
            
        //Calculations for Placement and angle
        double distanceY = nodeY + Constants.Arm.DropHeight - Constants.Arm.ArmHeight;
        double distanceX = nodeX + Constants.Arm.ArmDistanceFromFront; 
        double finalAngle = Math.toDegrees(Math.atan(distanceY/distanceX));
        double finalDistance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
        Long angleRotations = Math.round(finalAngle * Constants.Arm.RotationsPerDegree);
        Long distanceRotations = Math.round(finalDistance * Constants.Arm.RotationsPerCM);
        int[] rotations = {angleRotations.intValue(), distanceRotations.intValue()};
        return rotations;
    }
}
