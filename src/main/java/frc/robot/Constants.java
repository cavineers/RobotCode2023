package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {
    
    public static class CANIds {
        public static final int kFrontLeftDriveCanID = 20;
        public static final int kFrontLeftTurningCanID = 2;
        public static final int kBackLeftDriveCanID = 3;
        public static final int kBackLeftTurningCanID = 4;
        public static final int kFrontRightDriveCanID = 5;
        public static final int kFrontRightTurningCanID = 6;
        public static final int kBackRightDriveCanID = 7;
        public static final int kBackRightTurningCanID = 8;

        public static final int kFrontLeftAbsoluteEncoderCanID = 12;
        public static final int kBackLeftAbsoluteEncoderCanID = 9;
        public static final int kFrontRightAbsoluteEncoderCanID = 11;
        public static final int kBackRightAbsoluteEncoderCanID = 10;

        public static final int IntakeMotorBottom = 13;
        public static final int IntakeMotorTop = 14;
        public static final int IntakeRightDropMotor = 15;
        public static final int IntakeLeftDropMotor = 16;

        public static final int ArmChainMotor = 17; // Chain Neo
        public static final int ArmChainMotor2 = 18; // Chain Neo 2 same as firt but
        public static final int ArmExtensionMotor = 19; // Chain Neo 2 same as firt but

        public static final int ClawMotor = 1;
       
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
        public static int IntakeRightDropMotorID = CANIds.IntakeRightDropMotor;
        public static int IntakeLeftDropMotorID = CANIds.IntakeLeftDropMotor;

        public static double IntakeSpeed = 0.15;
        public static double IntakeLowerLeftSpeed = 0.2;
        public static double IntakeRaiseLeftSpeed = -0.2;
        public static double IntakeLowerRightSpeed = -0.2;
        public static double IntakeRaiseRightSpeed = 0.2;
        
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
        
        public static double ArmChainSpeed = 0.18;
        public static double ArmChainSpeedRev = -0.18; // Chain reverse speed

        public static double ArmExtensionSpeed = 0.75;
        public static double ArmExtensionSpeedRev = -0.75;
        
    //Node Rotations
        
        // Bottom Node 7.2 is angle rotations and 46 is extension rotations
        public static double BottomNodeAngleRotations = 7.2;
        public static double BottomNodeExtensionRotations = 46;
        // Mid Node Shelf 12.36 is angle rotations and 45.81 is extension rotations
        public static double MidNodeShelfAngleRotations = 12.36;
        public static double MidNodeShelfExtensionRotations = 45.81;
        // Mid Node Peg 14.16 is angle rotations and 41.71 is extension rotations
        public static double MidNodePegAngleRotations = 14.16;
        public static double MidNodePegExtensionRotations = 41.71;
        // Top Shelf 14.89 is angle rotations and 61.65 is extension rotations
        public static double TopNodeShelfAngleRotations = 14.89;
        public static double TopNodeShelfExtensionRotations = 61.65;
        // Top Peg 16.41 is angle rotations and 60.41 is extension rotations
        public static double TopNodePegAngleRotations = 16.41;
        public static double TopNodePegExtensionRotations = 60.41;
        // Encoder deadzones
        public static double ExtensionEncoderDeadzone = 1.8;
        public static double AngleEncoderDeadzone = 0.3;
        // Min and Max Rotations 
        public static double MaxExtensionRotations = 50;
        public static double MinExtensionRotations = 0;
        public static double MaxAngleRotations = 17;
        public static double MinAngleRotations = -20;

    }


    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;
    }
    public static class DIO {
        public static int ArmIrSensor = 0;
        public static int ArmExtensionSwitch = 1;
    }
}

