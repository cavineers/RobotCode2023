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

    public boolean isClosed;

    public CANSparkMax m_clawMotor;

    public DigitalInput clawLimitSwitch;

    public RelativeEncoder clawEncoder;

    public Claw (){

        isClosed = false;
    
        m_clawMotor = new CANSparkMax(CANIds.ClawMotor, MotorType.kBrushless);

        clawEncoder = m_clawMotor.getEncoder();

        m_clawMotor.setInverted(true);
        m_clawMotor.setIdleMode(IdleMode.kCoast);
        m_clawMotor.setSmartCurrentLimit(Constants.Claw.kCurrentLimit);
    
        clawLimitSwitch = new DigitalInput(Constants.Claw.kClawLimitSwitchPort);
    }

    public clawMotorState m_clawMotorState = clawMotorState.OFF;

    public enum clawMotorState {
        ON,
        OFF,
        REVERSE
    }

    public CANSparkMax getMotor() {
        return this.m_clawMotor;
    }

    public double getEncoderPostion(){
        return this.clawEncoder.getPosition();
    }

    public boolean getLimitSwitch() {
        return this.clawLimitSwitch.get();
    }

    public clawMotorState getMotorState() {
        return this.m_clawMotorState;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void setMotorState(clawMotorState state) {
        // set current state
        this.m_clawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns motor on forward
                this.m_clawMotor.set(Constants.Claw.kClawSpeed);
                break;
            case OFF:
                // turns off motor
                this.m_clawMotor.set(0.0);
                break;
            case REVERSE:
                // reverses claw
                this.m_clawMotor.set(-Constants.Claw.kClawSpeed);
                break;
            default: 
                this.setMotorState(clawMotorState.OFF);
        }
    }

    public void periodic(){
        SmartDashboard.putBoolean("Claw Limit Switch", this.getLimitSwitch());
        SmartDashboard.putNumber("Claw Encoder", clawEncoder.getPosition());
    }

}
