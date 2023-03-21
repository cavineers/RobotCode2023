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

        public static final int IntakeMotorBottom = 17;
        public static final int IntakeMotorTop = 16;
        public static final int IntakeRightDropMotor = 18;
        public static final int IntakeLeftDropMotor = 15;

        public static final int ArmChainMotor = 14; // left side
        public static final int ArmChainMotor2 = 20; // right side
        public static final int ArmExtensionMotor = 19;

        public static final int ClawMotor = 13;
       
    }
    public static class PWM {
        public static final int kStripLEDPort = 0;
    }
    public static class DIO {
        public static int AngleProxSensor = 9;
        public static int ArmExtensionSwitch = 0;
        public static int IntakeSwitch = 7;
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

        public static double IntakeLeftMultiplier = 1.0;

        public static double IntakeLowerLeftSpeed = IntakeSpeed*IntakeLeftMultiplier;
        public static double IntakeRaiseLeftSpeed = IntakeSpeed;
        public static double IntakeLowerRightSpeed = IntakeSpeed;
        public static double IntakeRaiseRightSpeed = IntakeSpeed;
        
        public static double IntakeSpeedTop = .3; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedBottom = .2; // Intake motor speed (-1.0 -- 1.0)

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
        public static final double kMaxSpeedMetersPerSecond = .1;
        public static final double kMaxAccelerationMetersPerSecond = .05;

        public static final PIDConstants kAutoDriveVelocityPID = new PIDConstants(1, 0, 0);
        public static final PIDConstants kAutoDriveTurnPID = new PIDConstants(0.5, 0, 0);
        
        public static final double positionalTolerance = 0.05; // meters
        public static final double angularTolerance = 2; // degrees
        
    }
    public static class Arm {
     // Joint one is closest to chassis and joint two is the furthest
        public static int ArmChainMotor = CANIds.ArmChainMotor;
        public static int ArmChainMotor2 = CANIds.ArmChainMotor2;
        public static int ArmExtensionMotor = CANIds.ArmExtensionMotor;
     
        public static double ArmChainSpeedUp = 0.175;
        public static double ArmChainSpeedDown = -0.08;

        public static double ArmExtensionSpeed = 0.95;
        public static double ArmExtensionSpeedRev = -0.95;

        public static double ArmRotationsAddPower = 11.1190;
        public static double ArmRotationsAddPowerBottom = 5;
     
    //Node Rotations
        
        public static double ArmIntakeAngleRotations = 0;
        public static double ArmIntakeExtensionRotations = 18;
        public static double ArmAutoAngleRotations = 4.65;
        public static double ArmAutoExtensionRotations = 0.1;
        public static double ArmRestPositionAngleRotations = 5;
        public static double ArmRestPositionExtensionRotations = 1;
        public static double ArmBumperAngleRotations = 7.2;
        public static double ArmBumperExtensionRotations = 52.;
        public static double BottomNodeAngleRotations = 10;
        public static double BottomNodeExtensionRotations = 56;
        public static double MidNodeShelfAngleRotations = 16;
        public static double MidNodeShelfExtensionRotations = 64;
        public static double MidNodePegAngleRotations = 20;
        public static double MidNodePegExtensionRotations = 53;
        public static double TopNodeShelfAngleRotations = 19.5;
        public static double TopNodeShelfExtensionRotations = 130;
        public static double TopNodePegAngleRotations = 22;
        public static double TopNodePegExtensionRotations = 130;
    // Encoder deadzones
     public static double ExtensionEncoderDeadzone = 2.5;
        public static double AngleEncoderDeadzone = 0.16;
    // Min and Max Rotations 
        public static double MaxExtensionRotations = 132;
        public static double MinExtensionRotations = -15;
        public static double ExtensionLowerSpeedRotations = 6;
        public static double MaxAngleRotations = 21;
        public static double MinAngleRotations = -100;

    }

    public static final class PresetTranslations {
        //IN METERS needs to be adjusted 

        public static final Translation2d kLeftPosition = new Translation2d(1.5, Units.inchesToMeters(-22));  //0.8128
        public static final Translation2d kShelfPosition = new Translation2d(1.5, 0); 
        public static final Translation2d kRightPosition = new Translation2d(1.5, Units.inchesToMeters(22)); //0.8128

        public static final Translation2d kSubstationPosition = new Translation2d(0.5, 0); 

    }

    public static final class Claw {
        public static final double kCLawCloseSpeed = .25;
        public static final double kClawHomeSpeed = .05;
        public static final double kClawManualSpeed = .05;
        public static final double kRevolutionsToHome = 0; //0.225714238882065
        public static final int kClawLimitSwitchPort = DIO.clawLimitSwitch; 
        public static final int kCurrentLimit = 10; //limit in amps
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
        
        public static final double kBalancingControlDriveP = 0.015; // P (Proportional) constant of a PID loop
        public static final double kBalancingControlDriveI = 0.0; // I (Integral) constant of a PID loop
        public static final double kBalancingControlDriveD = 0.0; // D (Derivative) constant of a PID loop

        public static final double kBalancingControlBackwardsPowerMultiplier = 1;
    }
}

