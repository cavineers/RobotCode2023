package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;


public final class Constants {
    
    public static class CANIds {
        public static int IntakeMotorBottom   = 1;
        public static int IntakeMotorTop      = 2;
        public static int IntakeDropMotor     = 3;
        public static int IntakeDropMotor2    = 4;
        public static int ArmJointOne         = 5; // Joint 1 Neo
        public static int ArmJointTwo         = 6; // Joint 2 Neo      One of these wil be a mini but is undecided
        public static int ArmJointThree       = 7; // Joint 3 Neo
    }

    public static final class PIDController {
        public static final double kP = 0.1;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }
    
    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 1 / 5.8462;
        public static final double kTurningMotorGearRatio = 1 / 18.0;
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
        public static final double kPTurning = 0.5;
    }

    public static final class DIO {
        public static int IntakeSwitch = 0;
    }

    public static final class Intake{
        public static int IntakeTopID = CANIds.IntakeMotorTop;
        public static int IntakeBottomID = CANIds.IntakeMotorBottom;
        public static int IntakeDropMotorID = CANIds.IntakeDropMotor;
        public static int IntakeDropMotor2ID = CANIds.IntakeDropMotor2;

        public static double IntakeSpeed = 0.7;
        public static double IntakeLowerSpeed = -0.2;
        public static double IntakeRaiseSpeed = -(IntakeLowerSpeed);
        
        public static double IntakeSpeedTop = IntakeSpeed; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedBottom = -(IntakeSpeed); // Intake motor speed (-1.0 -- 1.0)

        public static double RevolutionsToLower= -20;
    }

    public static final class DriveConstants {

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;
        

        public static final int kFrontLeftDriveCanID = 8;
        public static final int kBackLeftDriveCanID = 2;
        public static final int kFrontRightDriveCanID = 6;
        public static final int kBackRightDriveCanID = 4;

        public static final int kFrontLeftTurningCanID = 7;
        public static final int kBackLeftTurningCanID = 1;
        public static final int kFrontRightTurningCanID = 5;
        public static final int kBackRightTurningCanID = 3;

        public static final int kFrontLeftAbsoluteEncoderPort = 0; //TBD
        public static final int kBackLeftAbsoluteEncoderPort = 1; //TBD
        public static final int kFrontRightAbsoluteEncoderPort = 2; //TBD
        public static final int kBackRightAbsoluteEncoderPort = 3; //TBD

        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        public static final boolean kFrontLeftDriveEncoderReversed = true;
        public static final boolean kBackLeftDriveEncoderReversed = true;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        public static final boolean kFrontLeftAbsoluteEncoderReversed = true; //TBD
        public static final boolean kBackLeftAbsoluteEncoderReversed = true; //TBD
        public static final boolean kFrontRightAbsoluteEncoderReversed = false; //TBD
        public static final boolean kBackRightAbsoluteEncoderReversed = false; //TBD

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;

        public static final double kFrontLeftAbsoluteEncoderOffset = 0; 
        public static final double kBackLeftAbsoluteEncoderOffset = 0; 
        public static final double kFrontRightAbsoluteEncoderOffset = 0; 
        public static final double kBackRightAbsoluteEncoderOffset = 0; 
        
        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(21); //TBD
        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(25.5); //TBD
       
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));
    }


    public static class Arm {
        // Joint one is closest to chassis and joint two is the furthest
        public static int ArmJointOne = CANIds.ArmJointOne;
        public static int ArmJointTwo = CANIds.ArmJointTwo;
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.1;
    }

    public static class AprilTagConstants {
        public static final double TARGET_HEIGHT_METERS = Units.inchesToMeters(23.375);
        public static final double CAMERA_HEIGHT_METERS = 0; /*needs to be set */
        public static final double CAMERA_PITCH_RADIANS = 0; /*needs to be set */
    }
}

