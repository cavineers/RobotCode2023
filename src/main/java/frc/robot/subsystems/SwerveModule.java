package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;

import edu.wpi.first.math.MathUtil;

public class SwerveModule {

    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final PIDController turningPidController;

    private final PIDController test; 

    private final CANCoder absoluteEncoder;
    private final double absoluteEncoderOffsetDeg;
    private final int id;

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed,
            int absoluteEncoderId, double absoluteEncoderOffset) {
        
        this.id = absoluteEncoderId;
        this.absoluteEncoderOffsetDeg = absoluteEncoderOffset;
        this.absoluteEncoder = new CANCoder(absoluteEncoderId);

        absoluteEncoder.configMagnetOffset(absoluteEncoderOffsetDeg);
        
        driveMotor = new CANSparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new CANSparkMax(turningMotorId, MotorType.kBrushless);

        driveMotor.setIdleMode(IdleMode.kCoast);
        turningMotor.setIdleMode(IdleMode.kCoast);

        driveMotor.setInverted(driveMotorReversed);
        turningMotor.setInverted(turningMotorReversed);

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();

        driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        driveEncoder.setVelocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        turningEncoder.setPositionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad);
        turningEncoder.setVelocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);

        turningPidController = new PIDController(ModuleConstants.kPTurning, 0, 0);
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        test = new PIDController(0.03, 0, 0);
        test.enableContinuousInput(0, 360);


        resetEncoders();
    }

    public double getAbsolutePosition(){
        return absoluteEncoder.getAbsolutePosition();
    }

    public double getEncoderPosition(){
        return turningEncoder.getPosition();
    }

    public double getOffset() {
        return absoluteEncoder.configGetMagnetOffset();
    }

    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turningEncoder.getPosition();
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            getDrivePosition()*Constants.ModuleConstants.kDriveEncoderRot2Meter, new Rotation2d(getTurningPosition()));
    }
    
    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public void toggleIdleMode() {
        if (turningMotor.getIdleMode() == IdleMode.kCoast) {
            turningMotor.setIdleMode(IdleMode.kBrake);
        }
        else{
            turningMotor.setIdleMode(IdleMode.kBrake);
        }
    }

    public double getTurningVelocity() {
        return turningEncoder.getVelocity();
    }

    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(0);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        turningMotor.set(turningPidController.calculate(getTurningPosition(), state.angle.getRadians()));
    }

    public void setState() {
        double results = MathUtil.clamp(test.calculate(absoluteEncoder.getAbsolutePosition(),0),-.1,.1);
        // SmartDashboard.putNumber(id + "Unclamped Results", test.calculate(absoluteEncoder.getAbsolute(), 0));
        SmartDashboard.putNumber(id + "Results", results);
        
        if (!checkZeroed()){
            turningMotor.set(results);
        }
        else{
            stop();
        }
    }

    public boolean checkZeroed(){
        if ( //((absoluteEncoder.getAbsolutePosition() > 179) && (absoluteEncoder.getAbsolutePosition() < 181))
             ((absoluteEncoder.getAbsolutePosition() > 359) || (absoluteEncoder.getAbsolutePosition() < 1))
        ) {
            return true;
        }
        return false;
    }

    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }
}