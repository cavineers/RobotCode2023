package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    
    public static class CANIds {
        public static int IntakeMotorBottom   = 1;
        public static int IntakeMotorTop      = 2;
        public static int IntakeDropMotor     = 3;
        public static int IntakeDropMotor2    = 4;
        public static int ArmChainMotor = 5; // Chain Neo
        public static int ArmChainMotor2 = 6; // Chain Neo 2 same as firt but
        public static int ArmExtensionMotor = 7; // Chain Neo 2 same as firt but
       
    }
    public static class DIO {
        public static int ArmAngleSwitch = 0;
        public static int ArmExtensionSwitch = 1;
        public static int IntakeSwitch = 2;
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
    public static final class Intake{
        public static int IntakeTopID = CANIds.IntakeMotorTop;
        public static int IntakeBottomID = CANIds.IntakeMotorBottom;
        public static int IntakeDropMotorID = CANIds.IntakeDropMotor;
        public static int IntakeDropMotor2ID = CANIds.IntakeDropMotor2;

        public static double IntakeSpeed = 0.35;
        public static double IntakeLowerSpeed = 0.2;
        public static double IntakeRaiseSpeed = -(IntakeLowerSpeed);
        
        public static double IntakeSpeedTop = IntakeSpeed; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedBottom = -(IntakeSpeed); // Intake motor speed (-1.0 -- 1.0)

        public static double RevolutionsToLower= 3.35;
    }
    public static final class DriveConstants {

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final int kFrontLeftDriveCanID = 8;
        public static final int kBackLeftDriveCanID = 9;
        public static final int kFrontRightDriveCanID = 10;
        public static final int kBackRightDriveCanID = 11;

        public static final int kFrontLeftTurningCanID = 12;
        public static final int kBackLeftTurningCanID = 13;
        public static final int kFrontRightTurningCanID = 14;
        public static final int kBackRightTurningCanID = 15;

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
    public static class Arm {
        // Joint one is closest to chassis and joint two is the furthest
        public static int ArmChainMotor = CANIds.ArmChainMotor;
        public static int ArmChainMotor2 = CANIds.ArmChainMotor2;
        public static int ArmExtensionMotor = CANIds.ArmExtensionMotor;
        
        public static double ArmChainSpeed = 0.1;
        public static double ArmChainSpeedRev = -0.1; // Chain reverse speed

        public static double ArmExtensionSpeed = 0.1;
        public static double ArmExtensionSpeedRev = -0.1;
        // Extension reverse speed
    }
    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 2;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.1;

    }
}

