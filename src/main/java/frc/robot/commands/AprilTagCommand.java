package frc.robot.commands;
import org.photonvision.PhotonCamera;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AprilTagCommand extends CommandBase {

    boolean atPosition;
    boolean atRotation;

    public AprilTagCommand() {
        atPosition = false;
        atRotation = false;
    }

    @Override
    public void initialize() {
        new PhotonCamera("HD_Pro_Webcam_C920");
    }

    
    public void execute() {
        Robot.taghoming.home();
    }
    //Called by the scheduler automatically
    @Override
    public boolean isFinished() {
        return atPosition && atRotation; // Stop if the robot is at the target OR if there is no result
    }

    @Override
    public void end(boolean interrupted) {

    }
}