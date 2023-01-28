package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class LowerArm extends CommandBase {
    
    public LowerArm() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.arm.getArmChainMotor().getEncoder().getPosition() > Constants.Arm.RevolutionsToLower) {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
        } else if (Robot.arm.getArmChainMotor().getEncoder().getPosition() <= Constants.Arm.RevolutionsToLower) {
            Robot.m_robotContainer.m_armChainMotorDown.schedule();
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.REVERSED);
        }
        if (Robot.arm.getArmChainMotor2().getEncoder().getPosition() > Constants.Arm.RevolutionsToLower) {
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
        } else if (Robot.arm.getArmChainMotor2().getEncoder().getPosition() <= Constants.Arm.RevolutionsToLower) {
            Robot.m_robotContainer.m_armChainMotorDown.schedule();
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.REVERSED);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}