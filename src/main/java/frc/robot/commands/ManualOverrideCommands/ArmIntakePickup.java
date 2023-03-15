package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;

public class ArmIntakePickup extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public ArmIntakePickup() {
        this.addRequirements(Robot.armExtension, Robot.armAngle);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.armAngle.getArmChainMotor().set(0.0);
        Robot.armAngle.getArmChainMotor2().set(0.0);
        Robot.armExtension.getArmExtensionMotor().set(0.0);
    }
    

    @Override
    public void execute() {
    // 7.2 is angle rotations and 46 is extension rotations
    if (Robot.armAngle.getAngleProxSensor() == true) {
        Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
        Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
        Robot.armAngle.setArmChainMotorPosition(0.0);
        Robot.armAngle.setArmChainMotor2Position(0.0);
        this.isDone = true;
    } else {
        Robot.armAngle.getArmChainMotor().set(-0.08);
        Robot.armAngle.getArmChainMotor2().set(0.08);
        }
    }


    @Override
    public void end(boolean interrupted) {
        Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
        Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
        Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
       }

        return this.isDone;
    }
}

