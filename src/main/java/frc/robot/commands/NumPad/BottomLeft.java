package frc.robot.commands.NumPad;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class BottomLeft extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public BottomLeft() {
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
    // 7.2 is angle rotations and 46 is extension rotations
    if(Robot.arm.getArmChainMotorPosition() < 7.0) {
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.ON);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.ON);
        this.isDone = false;
    } else if (Robot.arm.getArmExtensionMotorPosition() < 45.8) {
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.ON);
        this.isDone = false;
    } else if(Robot.arm.getArmExtensionMotorPosition() > 46.2) {      
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
        this.isDone = false;
    } else if (Robot.arm.getArmChainMotorPosition() > 7.4) {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
        this.isDone = false;
    }else {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
        this.isDone = true;
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