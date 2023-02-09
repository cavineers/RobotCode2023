package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class BottomNode extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public BottomNode() {
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
    // Called when arm is raising
    // 5 and 3 are not permenant
    if(Robot.arm.getArmChainMotorPosition() < 13) {
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.ON);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.ON);
    } else if (Robot.arm.getExtensionMotorPosition() < 13) {
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.ON);
    } else {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF)
        Robot.arm.setArmChainMotorState(Arm.ArmExtensionMotorState.OFF)
        Robot.arm.setArmChainMotor2State(Arm.ArmExtensionMotorState.OFF)
    }
    // Called when arm is lowering
    if(Robot.arm.getArmExtensionMotorPosition() > 15) {      
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
    } else if (Robot.arm.getChainMotorPosition() > 15) {
        Robot.arm.setArmExtensionMotorState(Arm.ArmChainMotorState.OFF);
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
    } else {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF)
        Robot.arm.setArmChainMotorState(Arm.ArmExtensionMotorState.OFF)
        Robot.arm.setArmChainMotor2State(Arm.ArmExtensionMotorState.OFF)
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