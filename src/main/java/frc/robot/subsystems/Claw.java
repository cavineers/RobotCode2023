package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.concurrent.CancellationException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {

   private CANSparkMax clawMotor = new CANSparkMax(Constants.ClawConstants.kClawID, MotorType.kBrushless);z

}

