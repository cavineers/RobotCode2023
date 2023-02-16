package frc.robot;

public class Vision {
    public static boolean CUBE = false;
    public static boolean CONE = false;

    public Vision() {
        // TODO Auto-generated constructor stub
    
    }

    public int getObjectClass() {
        return (int) (Math.random() * 2);
    }

    public void defineObject() {
    if(this.getObjectClass() == 0) {
        CUBE = true;
        CONE = false;
    } else if (this.getObjectClass() == 1) {
        CONE = true;
        CUBE = false;
    }
    }
}
