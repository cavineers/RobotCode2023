
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Transform2d;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.List;

public class AprilTagHoming extends SubsystemBase { // Drive and orient to the tag

    private PhotonTrackedTarget target;
    private Transform2d currentDistance;
    private double yaw;
    private int targetID;
    private PhotonCamera camera;

    //Instance variables for tracking whether or not the robot is at position
    private boolean atPosition;
    private boolean atRotation;

    public AprilTagHoming() { 
        this.camera = new PhotonCamera("HD_Pro_Webcam_C920");
        atPosition = false;
        atRotation = false;
    }

    //Values returned by these two functions will determine when the scheduler is finished (see the april tag command)
    public boolean atPositon() {
        return atPosition;
    }

    public boolean getAtRotation() {
        return atRotation;
    }
    
    public void periodic() {
        // This method will be called once per scheduler run
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {

            this.target = results.getBestTarget();
            this.distance = target.getDistanceMeters();
            this.yaw = target.getYaw();
            this.targetID = target.getFiducialId();

            //After updating, see if the robot has reached the target and if the robot is correctly orientated
            if (this.distance > 1 && this.distance < 2 ) {
                atPosition = true;
            }

            if (yaw == 0) {
                atRotation = true;
            } 
        }
    }

    public void driveToTarget() {
        if (this.target != null) {
            // Drive to the target
        }
    }

    public void home() {
        driveToTarget();
    }
}