package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {
    
    public static class CANIds {

        public static final int kFrontLeftDriveCanID = 1;
        public static final int kBackLeftDriveCanID = 3;
        public static final int kFrontRightDriveCanID = 7;
        public static final int kBackRightDriveCanID = 5;

        public static final int kFrontLeftTurningCanID = 2;
        public static final int kBackLeftTurningCanID = 4;
        public static final int kFrontRightTurningCanID = 8;
        public static final int kBackRightTurningCanID = 6;

        public static final int kFrontLeftAbsoluteEncoderCanID = 12;
        public static final int kBackLeftAbsoluteEncoderCanID = 9;
        public static final int kFrontRightAbsoluteEncoderCanID = 11;
        public static final int kBackRightAbsoluteEncoderCanID = 10;

        public static final int IntakeMotorBottom = 17;
        public static final int IntakeMotorTop = 16;
        public static final int IntakeRightDropMotor = 18;
        public static final int IntakeLeftDropMotor = 15;

        public static final int ArmChainMotor = 14; // left side
        public static final int ArmChainMotor2 = 20; // right side
        public static final int ArmExtensionMotor = 19;

        public static final int ClawMotor = 13;
       
    }
    public static class DIO {
        public static int ArmIrSensor = 0;
        public static int ArmExtensionSwitch = 3;
        public static int IntakeSwitch = 2;
        public static int clawLimitSwitch = 1;
        public static int intakeInfaredSensor = 4;
    }
    
    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 1/ 6.75;
        public static final double kTurningMotorGearRatio = 1/ (150.0/7);
        public static final double kTurningDegreesToRad = Math.PI/180;
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

        public static final boolean kFrontLeftTurningEncoderReversed = false;
        public static final boolean kBackLeftTurningEncoderReversed = false;
        public static final boolean kFrontRightTurningEncoderReversed = false;
        public static final boolean kBackRightTurningEncoderReversed = false;

        public static final boolean kFrontLeftDriveEncoderReversed = false;
        public static final boolean kBackLeftDriveEncoderReversed = false;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        public static final boolean kFrontLeftAbsoluteEncoderReversed = true;
        public static final boolean kBackLeftAbsoluteEncoderReversed = true;
        public static final boolean kFrontRightAbsoluteEncoderReversed = false;
        public static final boolean kBackRightAbsoluteEncoderReversed = false;

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;

        public static final double kFrontLeftAbsoluteEncoderOffset = 176.49; //185.97 offset //-5.97 +80.63
        public static final double kBackLeftAbsoluteEncoderOffset = 180.44; //178.41 //-178.41 -224.03
        public static final double kFrontRightAbsoluteEncoderOffset = -171.56; //170.94 //-170.94
        public static final double kBackRightAbsoluteEncoderOffset = -57.65; //58.79 //-58.79
        
        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(23.75); 
        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(24.75);
       
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));
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
    public static final class Claw {
        public static final double kClawSpeed = .2;
        public static final double kRevolutions = 1;
        public static final int kClawLimitSwitchPort = DIO.clawLimitSwitch; 
        public static final int kCurrentLimit = 10; //limit in amps
        public static final boolean kSetClawMotorInverted = true;
    }
    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.1;
    }
}

