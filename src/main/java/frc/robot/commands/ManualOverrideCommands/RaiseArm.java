package frc.robot.commands.ManualOverrideCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class RaiseArm extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public RaiseArm() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (Robot.arm.getArmChainMotorPosition() < Constants.Arm.MaxAngleRotations) {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.ON);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.ON);
    }   else {
            Robot.arm.setArmChainMotorState(Arm.ArmChainMotorState.OFF);
            Robot.arm.setArmChainMotor2State(Arm.ArmChainMotor2State.OFF);
    }
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