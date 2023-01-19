package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private CANSparkMax m_armJointOne = new CANSparkMax(Constants.Arm.ArmJointOne, MotorType.kBrushless);
    private CANSparkMax m_armJointTwo = new CANSparkMax(Constants.Arm.ArmJointTwo, MotorType.kBrushless);

    public Arm() {
        this.m_armJointOne.setIdleMode(IdleMode.kBrake);
        this.m_armJointTwo.setIdleMode(IdleMode.kBrake);

        // this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);

        this.m_armJointOne.setSmartCurrentLimit(39);
        this.m_armJointTwo.setSmartCurrentLimit(39);
    }
    public CANSparkMax getArmJointOne() {
        return this.m_armJointOne;
    }

    public CANSparkMax getArmJointTwo() {
        return this.m_armJointTwo;
    }

    // This func will calculate what angle our arm needs to be when we drop the cone or cube

    public static double calculateArmDropAngle(boolean isTopRow, boolean isCone, double armHeight, double distanceFromGrid) {
        double nodeX;
        double nodeY;
        double dropHeight;

        if (isCone) {
            dropHeight = Constants.Arm.dropHeightPeg; //dont have values yet
            if (isTopRow) {
                nodeX = Constants.Arm.TopX;
                nodeY = Constants.Arm.TopPegY;
            } else {
                nodeX = Constants.Arm.MidX;
                nodeY = Constants.Arm.MidPegY;
            }
        } else {
            dropHeight = Constants.Arm.dropHeightShelf; //dont have values yet
            if (isTopRow) {
                nodeX = Constants.Arm.TopX;
                nodeY = Constants.Arm.TopShelfY;
            } else {
                nodeX = Constants.Arm.MidX;
                nodeY = Constants.Arm.MidShelfY;
            }
        }

        double distanceY = nodeY + dropHeight - Constants.Arm.armHeight;
        double distanceX = nodeX + distanceFromGrid + Constants.Arm.armDistanceFromFront;
        return Math.tan(distanceY/distanceX);
    }

}
