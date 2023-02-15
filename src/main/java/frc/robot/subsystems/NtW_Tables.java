package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NtW_Tables extends SubsystemBase{

    private NetworkTable photonVisionTable;

    public void PhotonVision(){
        this.photonVisionTable = NetworkTableInstance.getDefault().getTable("Photon Vision");
    }
    public NetworkTable getTable(){
        return this.photonVisionTable;
    }

    public boolean validTargets() {
        return this.photonVisionTable.getEntry("hasTarget").getBoolean();
    }

    public double getTargetPixX() {
        double tx = this.photonVisionTable.getEntry("targetPixelsX").getDouble(0.0);
        return tx;
    }

    public double getTargetPxY() {
        return this.photonVisionTable.getEntry("targetPixelsY").getDouble(0.0);
    }

    public double[] getTargetPose(){
        return this.photonVisionTable.getEntry("targetPose").getDouble([]);
    }

    public double getTargetSkew(){
        return this.photonVisionTable.getEntry("targetSkew").getDouble(0.0);
    }

    public double getTargetYaw(){
        return this.photonVisionTable.getEntry("targetYaw").getDouble(0.0);
    }

    public double getTargetArea(){
        return this.photonVisionTable.getEntry("targetArea").getDouble(0.0);
    }

    public rawBytes[] getRawBytes(){
        return this.photonVisionTable.getEntry("rawBytes").getRawBytes(0.0);
    }

    public double getLatencyMillis(){
        return this.photonVisionTable.getEntry("latencyMillis").getDouble(0.0);
    }
}
//!false - its funny because its true
