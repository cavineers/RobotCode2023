package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class HomeArm extends CommandBase {
    
    private boolean isDone;
    //private double m_timestamp;

    public HomeArm() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.arm.getArmChainMotor().set(0.0);
        Robot.arm.getArmChainMotor2().set(0.0);
        Robot.arm.getArmExtensionMotor().set(0.0);
        this.isDone = false;
        }
    

    @Override
    public void execute() {
        if (Robot.arm.getExtensionSwitch() == false) {
            Robot.arm.getArmExtensionMotor().set(-0.05);
        }else if (Robot.arm.getExtensionSwitch() == true) {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
            Robot.arm.setArmExtensionMotorPosition(0.0);
        }
        if (Math.abs(Robot.arm.getArmExtensionMotorPosition()) <= 0.1) {
            if (Robot.arm.getAngleProxSensor() == true) {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
            Robot.arm.setArmChainMotorPosition(0.0);
            Robot.arm.setArmChainMotor2Position(0.0);
            this.isDone = true;
        } else {
            Robot.arm.getArmChainMotor().set(-0.03);
            Robot.arm.getArmChainMotor2().set(0.03);
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
        // if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
        //     this.isDone = true;
        // }

        return this.isDone;
    }
}