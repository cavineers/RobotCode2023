package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;

public class RetractArm extends CommandBase {
    private boolean isDone = false;
    private double m_timestamp;

    public RetractArm() {
        this.addRequirements(Robot.armExtension);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.armExtension.getArmExtensionMotorPosition() > Constants.Arm.MinExtensionRotations) {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.REVERSED);
    }   else {
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