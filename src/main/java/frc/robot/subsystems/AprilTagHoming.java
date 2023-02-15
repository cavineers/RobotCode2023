
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
    
    public AprilTagHoming() { //Initi
        this.camera = new PhotonCamera("HD_Pro_Webcam_C920");
    }
    
    public void periodic() {
        // This method will be called once per scheduler run
        var results = this.camera.getLatestResult();
        if (results.hasTargets()) {
            this.target = results.getBestTarget();
            this.distance = target.getDistanceMeters();
            this.yaw = target.getYaw();
            this.targetID = target.getFiducialId();
        }
    }

    public void driveToTarget() {
        if (this.target != null) {
            // Drive to the target
            var distance = this.target.getDistanceMeters();
            var yaw = this.target.getYaw();
            var targetID = this.target.getFiducialId();
    }
}