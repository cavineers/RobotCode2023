package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    
    public static class CANIds {
        public static int ArmChainMotor = 2; // Chain Neo
        public static int ArmChainMotor2 = 3; // Chain Neo 2 same as firt but
        public static int ArmExtensionMotor = 4; // Chain Neo 2 same as firt but
       
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

        public static double IntakeSpeed = 0.7;
        public static double IntakeSpeedRev = -0.65; // Intake reverse speed
        
        public static double IntakeSpeedRight = IntakeSpeed; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedLeft = -(IntakeSpeed); // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedRevRight = IntakeSpeedRev; // Intake reverse speed
        public static double IntakeSpeedRevLeft = -(IntakeSpeedRev); // Intake reverse speed

        public static double DropSpeed = -0.2;
        public static double LiftSpeed = 0.2;

        public static double RevolutionsToLower = -31;
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
    public static class Arm {
        // Joint one is closest to chassis and joint two is the furthest
        public static int ArmChainMotor = CANIds.ArmChainMotor;
        public static int ArmChainMotor2 = CANIds.ArmChainMotor2;
        public static int ArmExtensionMotor = CANIds.ArmExtensionMotor;
        public static double TopX = 101;
        public static double MidX = 58;
        public static double TopPegY = 117;
        public static double MidPegY = 87;
        public static double TopShelfY = 90;
        public static double MidShelfY = 60;
        public static double OutputShelfX = 16.5;
        public static double OutputShelfY = 95;
        public static double IntakeAngle = 0; 
        public static double IntakeDistance = 0; 

        public static double DropHeight = 0; //Not yet known

        public static double ArmHeight = 0;//
        public static double ArmDistanceFromFront = 0;//
        
        public static double ArmChainSpeed = 0.09;
        public static double ArmChainSpeedRev = -0.09; // Chain reverse speed

        public static double ArmExtensionSpeed = 0.09;
        public static double ArmExtensionSpeedRev = -0.09;
        ; // Extension reverse speed

        //Speed of motor movement in meters per second
        public static double RotationsPerDegree = 0; //Not yet known
        public static double RotationsPerCM = 0; //Not yet known
        

        public static double ArmExtensionMetersPerRevolution = Math.PI / 367.3221; //pi/9.33, then converted into meters

        public static double MaxChainRevolutions = 89; //89 is temporary
        public static double MaxExtensionRevolutions = 89; //89 is temporary

    }


    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;
    }
    public static class DIO {
        public static int ArmAngleSwitch = 0;
        public static int ArmExtensionSwitch = 1;
    }
}

