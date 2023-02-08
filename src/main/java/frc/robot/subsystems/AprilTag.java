import edu.wpi.first.math.util.Units;
package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.List;

public class AprilTag extends SubsystemBase {

    private PhotonCamera camera;
    private PhotonPipelineResult result;
    private List<PhotonTrackedTarget> targets;
    private PhotonTrackedTarget target;
    private double yaw;
    private double pitch;
    private double area;
    private int targetID;
    private double poseAmbiguity;
    private Transform3d bestCameraToTarget;

    //Use constants
    private static double cameraHeight = Constants.AprilTagConstants.CAMERA_HEIGHT_METERS;
    private static double targetHeight = Constants.AprilTagConstants.TARGET_HEIGHT_METERS;
    private static double cameraPitch = Constants.AprilTagConstants.CAMERA_PITCH_RADIANS;

    public AprilTag(PhotonCamera c) {

        this.camera = c;
        //Get specific April Tag Data
        //targetID = target.getFiducialId();
    }

    public void periodic() {
        result = camera.getLatestResult();
        if (result.hasTargets()) {
            targets = result.getTargets();
            target = result.getBestTarget();
            yaw = target.getYaw();
            pitch = target.getPitch();
            area = target.getArea();
            poseAmbiguity = target.getPoseAmbiguity();
            bestCameraToTarget = target.getBestCameraToTarget();
        }
    }

    public boolean atAprilTag() {
        //If the distance is below a certain value, return true
        boolean atTag = false;
        double distanceMeters = PhotonUtils.calculateDistanceToTargetMeters(
            cameraHeight,
            targetHeight,
            cameraPitch,
            Units.degreesToRadians(result.getBestTarget().getPitch()));

        if (distanceMeters <= 1) {
            atTag = true;
        }
        return atTag;
    }

    public boolean alignedWithAprilTag() {
        return yaw == 0; 
    }
}