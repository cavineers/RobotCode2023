package frc.robot.commands.AutoArmCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;

public class ArmRestPosition extends CommandBase {
    
    private boolean isDone;
    private double m_timestamp;

    public ArmRestPosition() {
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
        // Command uses encoder values to turn motors until the claw is positioned above the bumper
        // This command checks whether the arm is going up or down to decide whether the chain motors or extension motors move first
        if(Robot.armAngle.getArmChainMotorPosition() < (Constants.Arm.ArmRestPositionAngleRotations) - Constants.Arm.AngleEncoderDeadzone) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.ON);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.ON);
            this.isDone = false;
        } else if (Robot.armExtension.getArmExtensionMotorPosition() < (Constants.Arm.ArmRestPositionExtensionRotations) - Constants.Arm.ExtensionEncoderDeadzone) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.ON);
            this.isDone = false;
        } else if(Robot.armExtension.getArmExtensionMotorPosition() > (Constants.Arm.ArmRestPositionExtensionRotations) + Constants.Arm.ExtensionEncoderDeadzone) {      
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.REVERSED);
            this.isDone = false;
        } else if (Robot.armAngle.getArmChainMotorPosition() > (Constants.Arm.ArmRestPositionAngleRotations) + Constants.Arm.AngleEncoderDeadzone) {
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