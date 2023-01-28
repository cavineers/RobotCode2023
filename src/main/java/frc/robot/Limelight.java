package frc.robot;
import edu.wpi.first.hal.ThreadsJNI;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    
    public enum ledMode{
        ON,
        OFF,
        DEFAULT,
        BLINK
    }

    private NetworkTable limelightTable;

    public Limelight(){
        this.limelightTable = NetworkTableInstance.getDefault().getTable("LimeLight");
    }

    public NetworkTable getTable(){
        return this.limelightTable;
    }

    public double getHorizontalOffset() {
        double tx = this.limelightTable.getEntry("tx").getDouble(0.0);
        return tx;
    }

    public double getVerticalOffset() {
        return this.limelightTable.getEntry("ty").getDouble(0.0);
    }

    public boolean validTargets() {
        return this.limelightTable.getEntry("tv").getDouble(0.0) > 0;
    }

    public double getRange() {
        return this.limelightTable.getEntry("ta").getDouble(0.0);
    }

    public double getScreenFill() {
        return this.limelightTable.getEntry("ta").getDouble(0.0);
    }

    public void setLimeLight_Mode(ledMode state){
        switch(state){
            case ON:
                this.limelightTable.getEntry("ledMode").setNumber(3);
                break;
            case OFF:
                this.limelightTable.getEntry("ledMode").setNumber(1);
                break;
            case DEFAULT:
                this.limelightTable.getEntry("ledMode").setNumber(0);
                break;
            case BLINK:
                this.limelightTable.getEntry("ledMode").setNumber(2);
        }
    }

    public double[] getDistance() {
        double height1 = Constants.Limelight.limelight2Ground;
        double height2 = Constants.Limelight.ground2Node;
        double angle1 = Constants.Limelight.limelightMountedAngle;
        double angle2 = this.limelightTable.getEntry("ty").getDouble(0.0);
        double finalDistance = (height2 - height1) / (Math.sin(Math.toRadians(angle1 + angle2)));
        double finalAngle = (angle2 - angle1);
        double[] vals = {finalDistance, finalAngle};
        return vals;

    }

    public boolean isInRangeOfDistance(double min, double max) {
        double distance = getDistance()[1];
        if (min < distance && distance > max) {
            return true;
        }
        return false;
    }

    public boolean isInRangeOfAngle(double min, double max) {
        double angle = getDistance()[2];
        if (min < angle && angle > max) {
            return true;
        }
        return false;
    }
    
    //TODO in range of distance max and angle max 
    public void recognizeNode(){            
        if (isInRangeOfAngle(0, 50) && isInRangeOfDistance(0, 157) && validTargets()){
            this.limelightTable.getEntry("Node").setString("Top");          
        } else if (isInRangeOfAngle(0,57) &&  isInRangeOfDistance(0, 105)){
            this.limelightTable.getEntry("Node").setString("Middle");  
        }else{
            this.limelightTable.getEntry("Node").setString("None Detected");
        }
    }
}
