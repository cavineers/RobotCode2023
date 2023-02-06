package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class HomeArm extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public HomeArm() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.arm.getArmChainMotor().set(0.0);
        Robot.arm.getArmChainMotor2().set(0.0);
        Robot.arm.getArmExtensionMotor().set(0.0);
        }
    

    @Override
    public void execute() {
    
        if(Robot.arm.getArmChainMotorPosition() > 0){
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
        } else if (Robot.arm.getArmChainMotorPosition() < -5){
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.ON);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.ON);
        } 
         else {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
    if (Robot.arm.getArmChainMotorPosition() > 0 && Robot.arm.getArmChainMotorPosition() < -5)
        if(Robot.arm.getArmExtensionMotorPosition() > 0){
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
        }else if(Robot.arm.getArmExtensionMotorPosition() < -5){
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        } else { 
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        }
        }
    }

    
    @Override
    public void end(boolean interrupted) {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}