package frc.robot.commands.AutoArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmExtension;

public class RetractCompletely extends CommandBase {
    private boolean isDone;
    private double m_timestamp;

    public RetractCompletely() {
        this.addRequirements(Robot.armExtension);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        this.isDone = false;
    }

    @Override
    public void execute() {
        if (Robot.armExtension.getArmExtensionMotorPosition() > Constants.Arm.ExtensionLowerSpeedRotations){
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.REVERSED);
        } else if (Robot.armExtension.getArmExtensionMotorPosition() < Constants.Arm.ExtensionLowerSpeedRotations) {
            Robot.armExtension.getArmExtensionMotor().set(-0.25);
    }   else {
            Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
    }
        if (Robot.armExtension.getExtensionSwitch()){
            this.isDone = true;
        }
}
    
    @Override
    public void end(boolean interrupted) {
        Robot.armExtension.setArmExtensionMotorState(ArmExtension.ArmExtensionMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.isDone;
    }
}
