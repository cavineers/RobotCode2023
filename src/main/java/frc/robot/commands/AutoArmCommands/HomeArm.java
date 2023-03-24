package frc.robot.commands.AutoArmCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;

public class HomeArm extends CommandBase {
    
    private boolean isDone;
    private double m_timestamp;
    private boolean extensionHomed;

    public HomeArm() {
        this.addRequirements(Robot.armExtension, Robot.armAngle);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.armAngle.getArmChainMotor().set(0.0);
        Robot.armAngle.getArmChainMotor2().set(0.0);
        Robot.armExtension.getArmExtensionMotor().set(0.0);
        this.isDone = false;
        this.extensionHomed = false;
    }
    

    @Override
    public void execute() {
        if (Robot.armExtension.getExtensionSwitch() == false) {
            Robot.armExtension.getArmExtensionMotor().set(-0.3);
        }else if (Robot.armExtension.getExtensionSwitch() == true) {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
            Robot.armExtension.setArmExtensionMotorPosition(0.0);
            this.extensionHomed = true;
        }
        if (extensionHomed) {
            if (Robot.armAngle.getAngleProxSensor() == true) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
            Robot.armAngle.setArmChainMotorPosition(0.0);
            Robot.armAngle.setArmChainMotor2Position(0.0);
            this.isDone = true;
        } else {
            Robot.armAngle.getArmChainMotor().set(-0.05);
            Robot.armAngle.getArmChainMotor2().set(0.05);
            }
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