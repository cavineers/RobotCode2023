package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    
    public static class CANIds {
        public static int IntakeMotorTop        = 16; // IntakeMotor (neo)
        public static int IntakeMotorBottom     = 17; // IntakeMotor (neo)
        public static int IntakeRightDropMotor  = 18; // IntakeDropMotor (neo)
        public static int IntakeLeftDropMotor   = 15;
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
        public static int IntakeSwitch = 8;
    }

    public static final class Intake{
        public static int IntakeTopID = CANIds.IntakeMotorTop;
        public static int IntakeBottomID = CANIds.IntakeMotorBottom;
        public static int IntakeRightDropMotorID = CANIds.IntakeRightDropMotor;
        public static int IntakeLeftDropMotorID = CANIds.IntakeLeftDropMotor;

        public static boolean kInvertRightDeployMotor = false;
        public static boolean kInvertLeftDeployMotor = false;
        public static boolean kInvertTopFlyWheel = false;
        public static boolean kInvertBottom = false;

        public static double IntakeSpeed = 0.4;
        public static double FlyWheelSpeed = 0.15;

        public static double IntakeLowerLeftSpeed = IntakeSpeed;
        public static double IntakeRaiseLeftSpeed = IntakeSpeed;
        public static double IntakeLowerRightSpeed = IntakeSpeed;
        public static double IntakeRaiseRightSpeed = IntakeSpeed;
        
        public static double IntakeSpeedTop = FlyWheelSpeed; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedBottom = FlyWheelSpeed; // Intake motor speed (-1.0 -- 1.0)

        public static double RevolutionsToLower = 53;
    }

    public static final class DriveConstants {

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final int kFrontLeftDriveMotorPort = 8;
        public static final int kBackLeftDriveMotorPort = 2;
        public static final int kFrontRightDriveMotorPort = 6;
        public static final int kBackRightDriveMotorPort = 4;

        public static final int kFrontLeftTurningMotorPort = 7;
        public static final int kBackLeftTurningMotorPort = 1;
        public static final int kFrontRightTurningMotorPort = 5;
        public static final int kBackRightTurningMotorPort = 3;

        public static final int kFrontLeftAbsoluteEncoderPort = 0; /* Needs to be set */
        public static final int kBackLeftAbsoluteEncoderPort = 1; /* Needs to be set */
        public static final int kFrontRightAbsoluteEncoderPort = 2; /* Needs to be set */
        public static final int kBackRightAbsoluteEncoderPort = 3; /* Needs to be set */

        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        public static final boolean kFrontLeftDriveEncoderReversed = true;
        public static final boolean kBackLeftDriveEncoderReversed = true;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        public static final boolean kFrontLeftAbsoluteEncoderReversed = true; /* Values need to be checked */
        public static final boolean kBackLeftAbsoluteEncoderReversed = true;
        public static final boolean kFrontRightAbsoluteEncoderReversed = false;
        public static final boolean kBackRightAbsoluteEncoderReversed = false;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;

        public static final double kFrontLeftAbsoluteEncoderOffset = 0; /* Needs to be set */
        public static final double kBackLeftAbsoluteEncoderOffset = 0; /* Needs to be set */
        public static final double kFrontRightAbsoluteEncoderOffset = 0; /* Needs to be set */
        public static final double kBackRightAbsoluteEncoderOffset = 0; /* Needs to be set */

        public static final double kTrackWidth = Units.inchesToMeters(19.5); // Needs editing - The distance between the centers of wheels on opposite sides */;
        public static final double kWheelBase = Units.inchesToMeters(23.5); //Needs editing - The distance between the centers of wheels on the same side
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;
    }

}

