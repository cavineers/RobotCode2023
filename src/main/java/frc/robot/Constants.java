package frc.robot;


import edu.wpi.first.math.util.Units;

import com.pathplanner.lib.auto.PIDConstants;

import edu.wpi.first.math.geometry.Translation2d;
import com.pathplanner.lib.auto.PIDConstants;
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

        public static final int IntakeMotorTop = 16;

        public static final int ArmChainMotor = 14; // left side
        public static final int ArmChainMotor2 = 20; // right side
        public static final int ArmExtensionMotor = 19;

        public static final int ClawMotor = 13;
       
    }
    public static class DIO {
        public static int AngleProxSensor = 9;
        public static int ArmExtensionSwitch = 0;
        public static int IntakeSwitch = 7;
        public static int clawLimitSwitch = 1;
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

        public static boolean kInvertTopFlyWheel = false;

        public static int kIntakeCurrentLimit = 40;

        public static double IntakeSpeedTop = .4; // Intake motor speed (-1.0 -- 1.0)

        public static double RevolutionsToLower = 5;
    }
    public static final class DriveConstants {

        public static final double kPhysicalMaxSpeedMetersPerSecond = 2;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;

        public static final int kFrontLeftDriveCanID = CANIds.kFrontLeftDriveCanID;
        public static final int kBackLeftDriveCanID = CANIds.kBackLeftDriveCanID;
        public static final int kFrontRightDriveCanID = CANIds.kFrontRightDriveCanID;
        public static final int kBackRightDriveCanID = CANIds.kBackRightDriveCanID;

        public static final int kFrontLeftTurningCanID = CANIds.kFrontLeftTurningCanID;
        public static final int kBackLeftTurningCanID = CANIds.kBackLeftTurningCanID;
        public static final int kFrontRightTurningCanID = CANIds.kFrontRightTurningCanID;
        public static final int kBackRightTurningCanID = CANIds.kBackRightTurningCanID;

        public static final int kFrontLeftAbsoluteEncoderPort = CANIds.kFrontLeftAbsoluteEncoderCanID;
        public static final int kBackLeftAbsoluteEncoderPort = CANIds.kBackLeftAbsoluteEncoderCanID;
        public static final int kFrontRightAbsoluteEncoderPort = CANIds.kFrontRightAbsoluteEncoderCanID;
        public static final int kBackRightAbsoluteEncoderPort = CANIds.kBackRightAbsoluteEncoderCanID;

        public static final boolean kFrontLeftTurningEncoderReversed = false;
        public static final boolean kBackLeftTurningEncoderReversed = false;
        public static final boolean kFrontRightTurningEncoderReversed = false;
        public static final boolean kBackRightTurningEncoderReversed = false;

        public static final boolean kFrontLeftDriveEncoderReversed = false;
        public static final boolean kBackLeftDriveEncoderReversed = false;
        public static final boolean kFrontRightDriveEncoderReversed = true;
        public static final boolean kBackRightDriveEncoderReversed = true;

        public static final boolean kFrontLeftAbsoluteEncoderReversed = true;
        public static final boolean kBackLeftAbsoluteEncoderReversed = true; //TBD
        public static final boolean kFrontRightAbsoluteEncoderReversed = false; //TBD
        public static final boolean kBackRightAbsoluteEncoderReversed = false; //TBD

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond;
        public static final double kTeleDriveMinSpeedMetersPerSecond = 5/3;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = Math.PI/2;
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
    public static final class PathPlanning {
        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecond = 1;

        public static final PIDConstants kAutoDriveVelocityPID = new PIDConstants(1, 0, 0);
        public static final PIDConstants kAutoDriveTurnPID = new PIDConstants(0.5, 0, 0);
        
        public static final double positionalTolerance = 0.05; // meters
        public static final double angularTolerance = 2; // degrees
        
    }
    public static class Arm {
    // 2.7 angle rotations subtracted by all preset angle rotations to acount for the new home distance from old home

        public static int ArmChainMotor = CANIds.ArmChainMotor;
        public static int ArmChainMotor2 = CANIds.ArmChainMotor2;
        public static int ArmExtensionMotor = CANIds.ArmExtensionMotor;
    // Arm goes up faster to account for gravity

        public static double ArmChainSpeedUp = 0.17;
        public static double ArmChainSpeedDown = -0.08;

        public static double ArmExtensionSpeed = .95;
        public static double ArmExtensionSpeedRev = -.95;

    // Amount of rotations the chain motors do before adding a constant power
        public static double ArmRotationsAddPower = 8.419;
        public static double ArmRotationsAddPowerBottom = 2.3;
     
    //Node Rotations (amount of rotations each motor does to reach a preset command)
        
        public static double ArmAutoAngleRotations = 1;
        public static double ArmAutoExtensionRotations = 0.1;
        public static double ArmRestPositionAngleRotations = 2.3;
        public static double ArmRestPositionExtensionRotations = 1;
        public static double ArmBumperAngleRotations = 4.25;
        public static double ArmBumperExtensionRotations = 53;
        public static double BottomNodeAngleRotations = 7.3;
        public static double BottomNodeExtensionRotations = 56;
        public static double MidNodeShelfAngleRotations = 13.3;
        public static double MidNodeShelfExtensionRotations = 64;
        public static double MidNodePegAngleRotations = 17.3;
        public static double MidNodePegExtensionRotations = 53;
        public static double TopNodeShelfAngleRotations = 16.8;
        public static double TopNodeShelfExtensionRotations = 130;
        public static double TopNodePegAngleRotations = 19.3;
        public static double TopNodePegExtensionRotations = 130;
        public static double DoubleSubStationAngleRotations = 14.2;
        public static double DoubleSubStationExtensionRotations = 3;
    // Encoder deadzones is the zone of rotation amount in which the motor will be in its off state when reached

        public static double ExtensionEncoderDeadzone = 3;
        public static double AngleEncoderDeadzone = 0.16;
    // Min and Max Rotations sets code stops and boundries for the arm

        public static double MaxExtensionRotations = 130;
        public static double MinExtensionRotations = -150;
        public static double ExtensionLowerSpeedRotations = 6;
        public static double MaxAngleRotations = 23;
        public static double MinAngleRotations = -22;
    }

    public static final class PresetTranslations {
        //IN METERS needs to be adjusted 

        public static final Translation2d kLeftPosition = new Translation2d(1.5, Units.inchesToMeters(-22));  //0.8128
        public static final Translation2d kShelfPosition = new Translation2d(1.5, 0); 
        public static final Translation2d kRightPosition = new Translation2d(1.5, Units.inchesToMeters(22)); //0.8128

        public static final Translation2d kSubstationPosition = new Translation2d(0.5, 0); 

    }

    public static final class Claw {
        public static final double kCLawCloseSpeed = .2;
        public static final double kClawHomeSpeed = .1;
        public static final double kClawManualSpeed = .1;
        public static final double kRevolutionsToHome = 6.404763698577;
        public static final int kClawLimitSwitchPort = DIO.clawLimitSwitch; 
        public static final int kCurrentLimit = 27; //limit in amps
        public static final boolean kSetClawMotorInverted = true;
    }
    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 9;

        public static final double kDeadband = 0.05; //blue xbox controller
    }

    public static final class HomingDrivePIDControllerConstants {
        public static final double kP = 1; //ADJUST
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }

    public static final class HomingRotationalPIDControllerConstants {
        public static final double kP = 0.1; //ADJUST
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }

    
    public static final class BalanceConstants {
        public static final double kBalancingControlGoalDegrees = 0;
        public static final double kBalancingControlTresholdDegrees = 2.5;
        public static final double kBalancingControlDeadbandDegrees = .25;
        
        public static final double kBalancingControlDriveP = 0.0125; // P (Proportional) constant of a PID loop
        public static final double kBalancingControlDriveI = 0.0; // I (Integral) constant of a PID loop
        public static final double kBalancingControlDriveD = 0.0; // D (Derivative) constant of a PID loop

        public static final double kBalancingControlBackwardsPowerMultiplier = 1;
    }

    public static final class AprilTagOffsetConstants {
        public static final double kAprilTagOffsetX = 0.0;
        public static final double kAprilTagOffsetY = 0.0;
    }
}

