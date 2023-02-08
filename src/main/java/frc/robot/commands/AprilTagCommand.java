package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AprilTag;

public class AprilTagCommand extends CommandBase {
    private PhotonCamera camera;
    boolean atPosition;
    boolean atRotation;


    public AprilTagCommand(PhotonCamera c) {
        this.camera = c;
        atPosition = false;
        atRotation = false;
    }

    @Override
    public void initialize() {
        AprilTag tag = new AprilTag(camera);
        this.execute(tag);
    }

    @Override
    public void execute(AprilTag t) {
        //call drives
        atPosition = t.atAprilTag();
        atRotation = t.alignedWithAprilTag();
        if (!atPosition || !atRotation) {
            //Drive towards target / orientate the bot
        }
    }

    //Called by the scheduler automatically
    @Override
    public boolean isFinished() {
        return atPosition && atRotation;
    }

    @Override
    public void end(boolean interrupted) {

    }
}