package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class LowerArm extends CommandBase {
    private boolean isDone = false;
    private double m_timestamp;

    public LowerArm() {
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
        Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
        Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
    }

    @Override
    public void end(boolean interrupted) {
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