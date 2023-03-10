package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class ExtendArm extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public ExtendArm() {
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
        if (Robot.arm.getArmExtensionMotorPosition() <= Constants.Arm.MaxExtensionRotations) {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.ON);
        } else {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
        }
    }   



    @Override
    public void end(boolean interrupted) {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}