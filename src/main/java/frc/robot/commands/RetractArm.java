package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class RetractArm extends CommandBase {
    private boolean isDone = false;
    private double m_timestamp;

    public RetractArm() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.arm.getArmExtensionMotorState() == Arm.ArmExtensionMotorState.OFF) {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
            this.isDone = false;
        } else {
            Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.OFF);
            this.isDone = true;
        }
    }

    @Override
    public void execute() {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.REVERSED);
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