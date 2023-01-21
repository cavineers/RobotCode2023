package frc.robot.subsystems;

<<<<<<< Updated upstream
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
    
    public enum ClawMotorState {
        ON,
        OFF,
        HIGHPRESSURE,
        LOWPRESSURE,
        REVERSED
    }

    boolean Opening;
    boolean Closing;

    public CANSparkMax m_ClawMotor = new CANSparkMax(Constants.Claw.ClawID, MotorType.kBrushless);
    public ClawMotorState m_ClawMotorState = ClawMotorState.OFF;

    // Claw Motor state
    public void setClawMotorState(ClawMotorState state) {
        // set the current state
        this.m_ClawMotorState = state;
        
        // set motor state
        switch (state) {
            case ON:
                // turns on the claw
                this.m_ClawMotor.set(Constants.Claw.ClawSpeed);
                break;
            case OFF:
                // turns off the claw
                this.m_ClawMotor.set(0.0);
                break;
            case REVERSED:
                // reverses the claw
                this.m_ClawMotor.set(Constants.Claw.ClawSpeedRev);
                break;
            default:
                this.setClawMotorState(ClawMotorState.OFF);
        }
    }
}
=======
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {

    public enum ClawMotorState {
        ON,
        OFF,
        HIGH_PRESSURE,
        LOW_PRESSURE,
        REVERSE,
    }

    boolean isOn;
    boolean opening;
    boolean closing;
    boolean highPressure;
    boolean lowPressure;

    public CANSparkMax m_ClawMotor = new CANSparkMax(Constants.Claw.ClawID, MotorType.MiniNEO);
    public ClawMotorState m_ClawMotorState = ClawMotorState.OFF;

    public void setClawMotorState(ClawMotorState state) {
        // set current state
        this.m_ClawMotorState = state;
        // set motor state
        switch (state) {
            case ON:
                // turns on motor
                this.m_ClawMotor.set(Constants.Claw.clawHighPressure);
                this.m_ClawMotor.set(Constants.Claw.clawLowPressure);
                break;
            case OFF:
                // turns off motor
                
                break;
            case REVERSED:
                // reverses intake
                this.m_intakeMotorRight.set(Constants.Intake.IntakeSpeedRevRight);
                this.m_intakeMotorLeft.set(Constants.Intake.IntakeSpeedRevLeft);
                break;
            default:
                this.setIntakeMotorState(IntakeMotorState.OFF);
        }
        
    }
    


}
    
>>>>>>> Stashed changes
