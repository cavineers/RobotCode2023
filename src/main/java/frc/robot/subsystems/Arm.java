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
    
    

    private CANSparkMax m_armChainMotor = new CANSparkMax(Constants.Arm.ArmChainMotor, MotorType.kBrushless);
    private CANSparkMax m_armExtensionMotor = new CANSparkMax(Constants.Arm.ArmExtensionMotor, MotorType.kBrushless);
    
    public ArmChainMotorState m_chainMotorState = ArmChainMotorState.OFF;
    public ArmExtensionMotorState m_extensionMotorState = ArmExtensionMotorState.OFF;
    
    public Arm() {
        
    }
    public CANSparkMax getArmChainMotor() {
        return this.m_armChainMotor;
    }

    public CANSparkMax getArmExtensionMotor() {
        return this.m_armExtensionMotor;
    }

    //LimeLight will determine this. How u might ask? No clue 
    public enum NodeMode {
        TOP_PEG,
        MID_PEG,
        TOP_SHELF,
        MID_SHELF
        }

        public double[] getNodePlacementTime(NodeMode state, double distanceFromGrid, double currentArmDistance, double currentArmAngle){
            
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
