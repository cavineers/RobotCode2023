package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;

public class ExtendArm extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public ExtendArm() {
        this.addRequirements(Robot.armExtension);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        this.isDone = false;
    }

    @Override
    public void execute() {
        if (Robot.armExtension.getArmExtensionMotorPosition() <= Constants.Arm.MaxExtensionRotations) {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.ON);
        } else {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
        }
    }   

    @Override
    public void end(boolean interrupted) {
        Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}