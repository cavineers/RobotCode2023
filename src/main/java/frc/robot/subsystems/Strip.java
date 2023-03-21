package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Strip extends SubsystemBase{
    
    Spark stripLED;

    public Strip(){
        stripLED = new Spark(Constants.PWM.kStripLEDPort);
    }

    public enum stripLEDState {
        OCEANCOLOREDRAINBOW,
        OFF
    }

    public stripLEDState m_stripLEDState = stripLEDState.OFF;

    public void setStripState (stripLEDState state){
        m_stripLEDState = state;

        switch(state){
            case OCEANCOLOREDRAINBOW:
                stripLED.set(-0.95);
                break;
            case OFF:
                stripLED.set(0);
                break;
        }

    }



}
