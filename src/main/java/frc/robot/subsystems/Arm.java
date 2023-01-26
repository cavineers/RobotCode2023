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
     public enum ArmExtensionMotorState {
        ON,
        OFF,
        REVERSED
    }
    
    

    public CANSparkMax m_armChainMotor = new CANSparkMax(Constants.Arm.ArmChainMotor, MotorType.kBrushless);
    public CANSparkMax m_armExtensionMotor = new CANSparkMax(Constants.Arm.ArmExtensionMotor, MotorType.kBrushless);
    
    public ArmChainMotorState m_armChainMotorState = ArmChainMotorState.OFF;
    public ArmExtensionMotorState m_armExtensionMotorState = ArmExtensionMotorState.OFF;
    
    public void setMotorState(ArmChainMotorState state) {
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
                this.setMotorState(ArmChainMotorState.OFF);
        }
    }
    public ArmChainMotorState getArmChainMotorState() {
        return this.m_armChainMotorState;
    }

    public void setMotorState(ArmExtensionMotorState state) {
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
                this.setMotorState(ArmExtensionMotorState.OFF);
        }
    }
    public ArmExtensionMotorState getArmExtensionMotorState() {
        return this.m_armExtensionMotorState;
    }

    public CANSparkMax getArmExtensionMotor() {
        return this.m_armExtensionMotor;
    }

   

    //LimeLight will determine this. How u might ask? No clue 
    public enum NodeMode {
        TOP_PEG,
        MID_PEG,
        TOP_SHELF,
        MID_SHELF,
        OUTPUT_SHELF
        }

        public double[] getNodePlacementTimes(NodeMode state, double distanceFromGrid, double currentArmDistance, double currentArmAngle){
            
            double nodeX, nodeY;
            double height = Constants.Arm.DropHeight;

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
                case OUTPUT_SHELF:
                    nodeX = Constants.Arm.OutputShelfX;
                    nodeY = Constants.Arm.OutputShelfY;
                    height = Constants.Arm.PickupHeight;
                    break;
                default:
                nodeX = 0;
                nodeY = 0;
            }
            
            //Calculations for Placement and angle
             double distanceY = nodeY + height - Constants.Arm.ArmHeight;
             double distanceX = nodeX + distanceFromGrid + Constants.Arm.ArmDistanceFromFront;
             double finalAngle = Math.tan(distanceY/distanceX);
             double finalDistance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
             double angleTime = (finalAngle - currentArmAngle) * Constants.Arm.ArmChainSpeedRevMPS;
             double distanceTime = (finalDistance - currentArmDistance) * Constants.Arm.ArmExtensionSpeedMPS;
             double[] values = {angleTime, distanceTime, finalDistance, finalAngle};
             return values;
        }
    }
    
    


//TODO add intake pickup, and shelf pick/ start motors for certain time.  
