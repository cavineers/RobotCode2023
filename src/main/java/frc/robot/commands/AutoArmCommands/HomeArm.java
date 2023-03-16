package frc.robot.commands.AutoArmCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;

public class HomeArm extends CommandBase {
    
    private boolean isDone;
    private double m_timestamp;
    private boolean upDone;

    public HomeArm() {
        this.addRequirements(Robot.armExtension, Robot.armAngle);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.armAngle.getArmChainMotor().set(0.0);
        Robot.armAngle.getArmChainMotor2().set(0.0);
        Robot.armExtension.getArmExtensionMotor().set(0.0);
        this.upDone = false;
        this.isDone = false;
    }
    

    @Override
    public void execute() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 2 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.upDone = true;
        }
        if (upDone == false) {
            Robot.armAngle.getArmChainMotor().set(0.1);
            Robot.armAngle.getArmChainMotor2().set(-0.1);
        }else {
    
        if (Robot.armExtension.getExtensionSwitch() == false) {
            Robot.armExtension.getArmExtensionMotor().set(-0.15);
        }else if (Robot.armExtension.getExtensionSwitch() == true) {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
            Robot.armExtension.setArmExtensionMotorPosition(0.0);
        }
        if (Math.abs(Robot.armExtension.getArmExtensionMotorPosition()) <= 0.1) {
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
}     


    
    @Override
    public void end(boolean interrupted) {
        Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
        Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
        Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
    }

    @Override
    public boolean isFinished() {
             this.isDone = true;
    
        return this.isDone;
    }
}