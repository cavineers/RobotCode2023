package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class Mid_Shelf_Preset extends CommandBase {
    
    private boolean isDone = false;
    private double m_timestamp;

    public Mid_Shelf_Preset() {
        this.addRequirements(Robot.arm);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.arm.getArmExtensionMotorState() == Arm.ArmExtensionMotorState.OFF) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    @Override
    public void execute() {
        double[] times = Robot.arm.getNodePlacementTimes(Arm.NodeMode.MID_SHELF, );
        
    }

    @Override
    public void end(boolean interrupted) {
       
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0 && Robot.m_robotContainer.joy.getRawButton(0)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}