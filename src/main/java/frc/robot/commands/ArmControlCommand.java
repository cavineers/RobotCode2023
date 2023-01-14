package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ArmControlCommand extends CommandBase {

    private Joystick joy;
    private RobotContainer rc;

    public ArmControlCommand(RobotContainer container, Joystick joy) {
        this.joy = joy;
        this.rc = container;
        this.addRequirements(Robot.arm);
    }
 
    @Override
    public void initialize() {
        Robot.arm.getArmJointOne().set(0.0);
        Robot.arm.getArmJointTwo().set(0.0); 
    }
   
    @Override
    public void execute() {
        // Joint One

        // Joint Two

    }
    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        Robot.arm.getArmJointOne().set(0.0);
        Robot.arm.getArmJointTwo().set(0.0);
    }
}
