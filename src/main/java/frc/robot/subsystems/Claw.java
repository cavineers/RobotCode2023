package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.CANIds;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase{

    public static boolean isClosing;

    public CANSparkMax m_clawMotor;

    public DigitalInput clawLimitSwitch;

    public RelativeEncoder clawEncoder;

    public Claw (){

        isClosing = false;
    
        m_clawMotor = new CANSparkMax(CANIds.ClawMotor, MotorType.kBrushless);

        clawEncoder = m_clawMotor.getEncoder();

        m_clawMotor.setInverted(Constants.Claw.kSetClawMotorInverted);
        m_clawMotor.setIdleMode(IdleMode.kCoast);
        m_clawMotor.setSmartCurrentLimit(Constants.Claw.kCurrentLimit);
    
        clawLimitSwitch = new DigitalInput(Constants.Claw.kClawLimitSwitchPort);
    }

    public void resetEncoder(){
        clawEncoder.setPosition(0);
    }

    public clawMotorState m_clawMotorState = clawMotorState.OFF;

    public enum clawMotorState {
        ON,
        OFF,
        REVERSE,
        manualON
    }

    public CANSparkMax getMotor() {
        return this.m_clawMotor;
    }

    public double getEncoderPosition(){
        return this.clawEncoder.getPosition();
    }

    public boolean getLimitSwitch() {
        return this.clawLimitSwitch.get();
    }

    public clawMotorState getMotorState() {
        return this.m_clawMotorState;
    }

    public static boolean isClosing() {
        return isClosing;
    }

    public static void setClosing(boolean closed) {
        isClosing = closed;
    }

    public void setMotorState(clawMotorState state) {
        // set current state
        this.m_clawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns motor on forward
                this.m_clawMotor.set(Constants.Claw.kCLawCloseSpeed);
                break;
            case OFF:
                // turns off motor
                this.m_clawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses claw
                this.m_clawMotor.set(-Constants.Claw.kClawHomeSpeed);
                break;
            case manualON:
                //slowly turns motor forward
                this.m_clawMotor.set(Constants.Claw.kClawManualSpeed);
                break;
            default: 
                this.setMotorState(clawMotorState.OFF);
        }
    }

    public void periodic(){
        SmartDashboard.putBoolean("Claw Limit Switch", this.getLimitSwitch());
        SmartDashboard.putBoolean("Closing?", isClosing());
        SmartDashboard.putNumber("Claw Encoder", this.getEncoderPosition());
    }

}