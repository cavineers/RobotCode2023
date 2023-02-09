package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class HomeArm extends CommandBase {
    
    private boolean isDone = false;
    private boolean contractionCompleted = false;
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
        if (Robot.arm.getExtensionSwitch() == true) {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF)
            Robot.arm.getArmExtensionMotorPosition().set(0.0);
        } else {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
        }
        if (Robot.arm.getArmExtensionMotorPosition() == 0) {
            if (Robot.arm.getAngleSwitch() == true) {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
            Robot.arm.getArmChainMotorPosition().set(0.0);
            Robot.arm.getArmChainMotor2Position().set(0.0);
        } else {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
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