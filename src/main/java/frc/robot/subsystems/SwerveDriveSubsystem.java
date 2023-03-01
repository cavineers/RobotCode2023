package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder.BackendKind;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.kinematics.SwerveModulePosition;


public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveModule frontLeft = new SwerveModule(
        DriveConstants.kFrontLeftDriveCanID, 
        DriveConstants.kFrontLeftTurningCanID, 
        DriveConstants.kFrontLeftDriveEncoderReversed, 
        DriveConstants.kFrontLeftTurningEncoderReversed,
        DriveConstants.kFrontLeftAbsoluteEncoderPort, 
        DriveConstants.kFrontLeftAbsoluteEncoderOffset, 
        DriveConstants.kFrontLeftAbsoluteEncoderReversed);
    
    private final SwerveModule frontRight = new SwerveModule(
        DriveConstants.kFrontRightDriveCanID, 
        DriveConstants.kFrontRightTurningCanID, 
        DriveConstants.kFrontRightDriveEncoderReversed, 
        DriveConstants.kFrontRightTurningEncoderReversed,
        DriveConstants.kFrontRightAbsoluteEncoderPort, 
        DriveConstants.kFrontRightAbsoluteEncoderOffset, 
        DriveConstants.kFrontRightAbsoluteEncoderReversed);

    private final SwerveModule backLeft = new SwerveModule(
        DriveConstants.kBackLeftDriveCanID, 
        DriveConstants.kBackLeftTurningCanID, 
        DriveConstants.kBackLeftDriveEncoderReversed, 
        DriveConstants.kBackLeftTurningEncoderReversed,
        DriveConstants.kBackLeftAbsoluteEncoderPort, 
        DriveConstants.kBackLeftAbsoluteEncoderOffset, 
        DriveConstants.kBackLeftAbsoluteEncoderReversed);

    private final SwerveModule backRight = new SwerveModule(
        DriveConstants.kBackRightDriveCanID, 
        DriveConstants.kBackRightTurningCanID, 
        DriveConstants.kBackRightDriveEncoderReversed, 
        DriveConstants.kBackRightTurningEncoderReversed,
        DriveConstants.kBackRightAbsoluteEncoderPort, 
        DriveConstants.kBackRightAbsoluteEncoderOffset, 
        DriveConstants.kBackRightAbsoluteEncoderReversed);

    private final AHRS gyro = new AHRS(SPI.Port.kMXP); 

    public double getHeading(){
        return Math.IEEEremainder(gyro.getAngle(), 360);
    }

    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(getHeading());
    }

    private final SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(
        DriveConstants.kDriveKinematics, 
        getRotation2d(), 
        getPositions());

    public double getFLAbsolutePosition(){
        return frontLeft.getAbsolutePosition();
    }
    public double getFRAbsolutePosition(){
        return frontRight.getAbsolutePosition();
    }
    public double getBRAbsolutePosition(){
        return backRight.getAbsolutePosition();
    }
    public double getBLAbsolutePosition(){
        return backLeft.getAbsolutePosition();
    }

    
    SwerveDriveOdometry m_odometer = m_odometry;

    public SwerveDriveSubsystem() {
        //Delay reset of navx for proper initialization
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e) {
            }
        }).start();

        //ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0, 1, 0);

        // Convert chassis speeds to individual module states
        //SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        
        // Output each module states to wheels
        //setModuleStates(moduleStates);
    }

    public void zeroHeading() {
        gyro.reset();
    }

    public Pose2d getPose() {
        return m_odometer.getPoseMeters();
    }

    public SwerveModulePosition[] getPositions() {
        return new SwerveModulePosition[] {
            frontLeft.getPosition(),
            frontRight.getPosition(),
            backLeft.getPosition(),
            backRight.getPosition()};
    }
    public void resetOdometry(Pose2d pose) {
        m_odometer.resetPosition(getRotation2d(), getPositions(), pose);
    }

    public void stopModules() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void periodic(){
        m_odometer.update(getRotation2d(), getPositions());

        SmartDashboard.putNumber("FrontLeft Position", frontLeft.getEncoderPosition());
        SmartDashboard.putNumber("FrontRight Position", frontRight.getEncoderPosition());
        SmartDashboard.putNumber("BackLeft Position", backLeft.getEncoderPosition());
        SmartDashboard.putNumber("BackRight Position", backRight.getEncoderPosition());

        SmartDashboard.putNumber("FrontLeft Cancoder", frontLeft.getAbsolutePosition());
        SmartDashboard.putNumber("FrontRight Cancoder", frontRight.getAbsolutePosition());
        SmartDashboard.putNumber("BackLeft Cancoder", backLeft.getAbsolutePosition());
        SmartDashboard.putNumber("BackRight Cancoder", backRight.getAbsolutePosition());

        SmartDashboard.putNumber("FrontLeft Offset", frontLeft.getOffset());
        SmartDashboard.putNumber("FrontRight Offset", frontRight.getOffset());
        SmartDashboard.putNumber("BackLeft Offset", backLeft.getOffset());
        SmartDashboard.putNumber("BackRight Offset", backRight.getOffset());
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }

    public void testStates() {
        frontLeft.setState();
        frontRight.setState();
        backLeft.setState();
        backRight.setState();
    }

    public boolean checkFinished() {
        if (frontLeft.checkZeroed()&&
        backLeft.checkZeroed()&&
        backRight.checkZeroed()&&
        frontRight.checkZeroed()
        )
        return true;
        return false;
    }
}