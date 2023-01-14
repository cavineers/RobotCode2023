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
    private CANSparkMax m_armJointThree = new CANSparkMax(Constants.Arm.ArmJointThree, MotorType.kBrushless);

    public Arm() {
        this.m_armJointOne.setIdleMode(IdleMode.kBrake);
        this.m_armJointTwo.setIdleMode(IdleMode.kBrake);
        this.m_armJointThree.setIdleMode(IdleMode.kBrake);

        // this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);

        this.m_armJointOne.setSmartCurrentLimit(39);
        this.m_armJointTwo.setSmartCurrentLimit(39);
        this.m_armJointThree.setSmartCurrentLimit(39);
    }
    public CANSparkMax getArmJointOne() {
        return this.m_armJointOne;
    }

    public CANSparkMax getArmJointTwo() {
        return this.m_armJointTwo;
    }

    public CANSparkMax getArmJointThree() {
        return this.m_armJointThree;
    }
    public double getArmJointOnePosition() {
        return -this.m_armJointOne.getEncoder().getPosition();
    }

    public void setArmJointOnePosition(double position) {
        this.m_armJointOne.getEncoder().setPosition(position);
    }

    public double getArmJointTwoPosition() {
        return -this.m_armJointTwo.getEncoder().getPosition();
    }

    public void setArmJointTwoPosition(double position) {
        this.m_armJointTwo.getEncoder().setPosition(position);
    }
    public double getArmJointThreePosition() {
        return -this.m_armJointThree.getEncoder().getPosition();
    }

    public void setArmJointThreePosition(double position) {
        this.m_armJointThree.getEncoder().setPosition(position);
    }


}
