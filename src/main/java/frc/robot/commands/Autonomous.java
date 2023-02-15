package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Autonomous extends CommandBase{
    private RobotContainer rc;

    private double startTime = 0;
    private double middleTime = 0;

    private double heading;
    private boolean scheduledIntake = false;
    private boolean scheduledIntakeDrop = false;

    public Autonomous(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
        this.heading = Robot.gyro.getAngle();
    }

    @Override
    public void execute() {
        double error = this.heading - Robot.gyro.getAngle();

        if (Timer.getFPGATimestamp() - this.startTime >= 3) {
        }
    }
    
    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 15);
    }
}
