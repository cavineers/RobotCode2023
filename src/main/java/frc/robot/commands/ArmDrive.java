package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class ArmDrive extends CommandBase {
    
    private Joystick joy;
    private boolean isDone = false;
    private double m_timestamp;
    private RobotContainer rc;

    public ArmDrive(RobotContainer container, Joystick joy) {
        this.joy = joy;
        this.rc = container;
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        // 70 not permenant
        if (Robot.arm.getArmChainMotorPosition <= 70) {
            Robot.arm.getArmChainMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
            Robot.arm.getArmChainMotor2().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
        } else {
            Robot.arm.getArmChainMotor().set(0.0);
        }
    }

    @Override
    public void execute() {
        Robot.arm.setArmExtensionMotorState(Arm.ArmExtensionMotorState.ON);
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