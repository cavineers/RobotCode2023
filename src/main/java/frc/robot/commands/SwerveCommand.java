package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Robot;
import java.lang.Math;

public class SwerveCommand extends CommandBase {
    public SwerveCommand(SwerveDriveSubsystem subsystem) {

        addRequirements(Robot.m_swerveDriveSubsystem);
    }


    private double applyDead(double value, double deadband) {
        if (Math.abs(value) < deadband) {
            return 0;
        } else {
            return value;
        }
    }
    @Override
    public void execute() {
        double forward = Robot.m_robotContainer.getJoystickRawAxis(1); // Get left stick Y axis

        forward = applyDead(forward, 0.2); // Apply deadband, smooths out imput
        forward = Math.copySign(Math.pow(forward, 2.0), forward); // Square the input (while preserving the sign) to increase fine control while permitting full power

        double strafe = Robot.m_robotContainer.getJoystickRawAxis(0); // Get left stick X axis

        strafe = applyDead(strafe, 0.2); // Apply deadband, smooths out imput
        strafe = Math.copySign(Math.pow(strafe, 2.0), strafe); // Square the input (while preserving the sign) to increase fine control while permitting full power

        double rotation = Robot.m_robotContainer.getJoystickRawAxis(4); // Get right stick X axis

        rotation = applyDead(rotation, 0.2); // Apply deadband, smooths out imput
        rotation = Math.copySign(Math.pow(rotation, 2.0), rotation); // Square the input (while preserving the sign) to increase fine control while permitting full power

        Robot.m_swerveDriveSubsystem.drive(new Translation2d(forward, strafe), rotation, true);
    }
}
