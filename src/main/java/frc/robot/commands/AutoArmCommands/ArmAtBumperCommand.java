package frc.robot.commands.AutoArmCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;

public class ArmAtBumperCommand extends CommandBase {
    
    private boolean isDone;
    private double m_timestamp;

    public ArmAtBumperCommand() {
        this.addRequirements(Robot.armExtension, Robot.armAngle);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.armAngle.getArmChainMotor().set(0.0);
        Robot.armAngle.getArmChainMotor2().set(0.0);
        Robot.armExtension.getArmExtensionMotor().set(0.0);
        this.isDone = false;
    }
    

    @Override
    public void execute() {
        if(Robot.armAngle.getArmChainMotorPosition() < (Constants.Arm.ArmBumperAngleRotations) - Constants.Arm.AngleEncoderDeadzone) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.ON);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.ON);
            this.isDone = false;
        } else if (Robot.armExtension.getArmExtensionMotorPosition() < (Constants.Arm.ArmBumperExtensionRotations) - Constants.Arm.ExtensionEncoderDeadzone) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.ON);
            this.isDone = false;
        } else if(Robot.armExtension.getArmExtensionMotorPosition() > (Constants.Arm.ArmBumperExtensionRotations) + Constants.Arm.ExtensionEncoderDeadzone) {      
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.REVERSED);
            this.isDone = false;
        } else if (Robot.armAngle.getArmChainMotorPosition() > (Constants.Arm.ArmBumperAngleRotations) + Constants.Arm.AngleEncoderDeadzone) {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.REVERSED);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.REVERSED);
            this.isDone = false;
        }else {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
            this.isDone = true;
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