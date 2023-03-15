package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ArmAngle;

public class LowerArm extends CommandBase {
    private boolean isDone = false;
    private double m_timestamp;

    public LowerArm() {
        this.addRequirements(Robot.armAngle);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.armAngle.getArmChainMotorPosition() > Constants.Arm.MinAngleRotations) {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.REVERSED);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.REVERSED);
        } else {
            Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
            Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.armAngle.setArmChainMotorState(ArmAngle.ArmChainMotorState.OFF);
        Robot.armAngle.setArmChainMotor2State(ArmAngle.ArmChainMotor2State.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}