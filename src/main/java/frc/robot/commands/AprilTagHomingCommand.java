package frc.robot.commands;

import org.photonvision.PhotonCamera;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class AprilTagHomingCommand extends CommandBase {

    private PhotonCamera camera;

    public AprilTagHomingCommand() {
        addRequirements(Robot.taghoming);
    }

    @Override
    public void initialize() {
       
    }

    
    public void execute() {
        //Robot.taghoming.driveToTarget(goal=Trajectory.Sta);
    }
    //Called by the scheduler automatically
    @Override
    public boolean isFinished() {
        return Robot.taghoming.checkFinished(); // Stop if the robot is at the target OR if there is no result
    }

    @Override
    public void end(boolean interrupted) {
        // set the motors to 0
        Robot.m_swerveDriveSubsystem.stopModules();
    }
}