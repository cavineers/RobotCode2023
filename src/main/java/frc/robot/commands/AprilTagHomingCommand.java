package frc.robot.commands;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.DriveConstants;

import frc.robot.subsystems.AprilTagHoming;



public class AprilTagHomingCommand extends CommandBase {


    private SwerveDriveSubsystem swerveSubsystem;
    private AprilTagHoming tagHomingSubsystem;

    private PIDController xSpeedPID;
    private PIDController rotationPID;

    private Translation2d goal; // The peg/shelf/substation that is selected
    private Translation2d goalRelativeRobot;


    
    private boolean hasInitialTarget;

    private SwerveDriveOdometry relativeOdometer;

    

<<<<<<< Updated upstream
    public AprilTagHomingCommand(SwerveDriveSubsystem swerveSubsystem, AprilTagHoming tagHomingSubsystem, Translation2d goalOffset) {
=======
    public AprilTagHomingCommand(SwerveDriveSubsystem swerveSubsystem, AprilTagHoming tagHomingSubsystem) {
>>>>>>> Stashed changes
        
        this.swerveSubsystem = swerveSubsystem;
        this.tagHomingSubsystem = tagHomingSubsystem;

        addRequirements(swerveSubsystem, tagHomingSubsystem);

        this.relativeOdometer = swerveSubsystem.getNewOdometer();
        

<<<<<<< Updated upstream
        this.goal = goalOffset; // The peg/shelf/substation translation2d that is selected
=======
        this.goal = new Translation2d(Constants.AprilTagOffsetConstants.kAprilTagOffsetX, Constants.AprilTagOffsetConstants.kAprilTagOffsetY); // The peg/shelf/substation translation2d that is selected
>>>>>>> Stashed changes

        this.xSpeedPID = new PIDController(Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD); // X controller
        this.rotationPID = new PIDController(Constants.HomingRotationalPIDControllerConstants.kP, Constants.HomingRotationalPIDControllerConstants.kI, Constants.HomingRotationalPIDControllerConstants.kD); // Rotation controller

    }

    public void initialize() {
        swerveSubsystem.toggleIdleMode(IdleMode.kCoast);
        
        if (tagHomingSubsystem.hasTargets) {
            resetOdometry();
            this.hasInitialTarget = true;
        } else {
            this.hasInitialTarget = false;
        }
        setSetPoint();
    }

    public void setSetPoint() {
        this.goalRelativeRobot = calculateGoalRelativeRobot();
 
        this.xSpeedPID.setSetpoint(goalRelativeRobot.getX());
        
        if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            this.rotationPID.setSetpoint(0);
        } 
        else {
            this.rotationPID.setSetpoint(-180);
        }
    }
    
    public void execute(){
        relativeOdometer.update(this.swerveSubsystem.getRotation2d(), this.swerveSubsystem.getPositions());
        
        ChassisSpeeds chassisSpeeds = calculateChassisSpeeds();
        SwerveModuleState[] moduleStates =  DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        swerveSubsystem.setModuleStates(moduleStates);
       
        SmartDashboard.putString("POSEAPRIL", relativeOdometer.getPoseMeters().toString());
        SmartDashboard.putString("Goal Pose Relative to Robot", calculateGoalRelativeRobot().toString());
    
        SmartDashboard.putString("GOALPOSE", this.goal.toString());
        SmartDashboard.putString("Chassis Speeds", chassisSpeeds+"");
    }

    //Find the dist goal relative to the robot, inverse it because we are driving backwards
    private Translation2d calculateGoalRelativeRobot() {
        return ((this.tagHomingSubsystem.getTranslationToTag().minus(this.goal))); //Find the goal relative to the robot, inverse it because we are driving backwards
    }

    //resets the odometry to 0,0 as we are relative to the robot, and keeps the gyro the same
    private void resetOdometry() {

        // Create a new Pose2d object representing (0,0), with the same gyro angle as the current pose
        Pose2d newPose = new Pose2d(0, 0, this.swerveSubsystem.getPose().getRotation());

        // Reset the odometry pose to the newPose object using the odometry object's resetPosition() method
        relativeOdometer.resetPosition(this.swerveSubsystem.getRotation2d(), this.swerveSubsystem.getPositions(), newPose);
    }

    private double clampSpeeds(double speed){
        if (Math.signum(speed) == 1) {
            return MathUtil.clamp(speed, 0.1, .5);
        } else if (Math.signum(speed) == -1) {
            return MathUtil.clamp(speed, -0.5, -.1);
        }
        return 0;
    }

    private ChassisSpeeds calculateChassisSpeeds() {
        
        double xSpeed = clampSpeeds(xSpeedPID.calculate(this.relativeOdometer.getPoseMeters().getX()));
        double rotationSpeed = clampSpeeds(rotationPID.calculate(this.relativeOdometer.getPoseMeters().getRotation().getDegrees()));
        
        ChassisSpeeds newSpeeds = new ChassisSpeeds();
        newSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, 0, rotationSpeed, relativeOdometer.getPoseMeters().getRotation());
        
        if (isAtXGoal()) {
            newSpeeds.vxMetersPerSecond = 0;
        }

        if (isAtThetaGoal()) {
            newSpeeds.omegaRadiansPerSecond = 0;
        }

        return newSpeeds;
    }


    private boolean isAtXGoal() {
        if (Math.abs(this.goalRelativeRobot.getX() - this.relativeOdometer.getPoseMeters().getX()) <= 0.05){
            return true;
        }
        return false;
    }
    
    private boolean isAtThetaGoal() {
        if (((Math.abs(this.relativeOdometer.getPoseMeters().getRotation().getDegrees()) <= 0.1)
            && (Math.abs(this.relativeOdometer.getPoseMeters().getRotation().getDegrees()) >= -0.1))
            || ((Math.abs(this.relativeOdometer.getPoseMeters().getRotation().getDegrees()) >= -179.9) 
            && (Math.abs(this.relativeOdometer.getPoseMeters().getRotation().getDegrees()) <= -180.1)))
            {
            return true;
        }
        return false;
    }

    private boolean checkPosition(){
        if (isAtXGoal() && isAtThetaGoal()) {
            return true;
        }
        return false;
    }


    public boolean isFinished() {
        return ((!this.hasInitialTarget) || checkPosition());
    }

    
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
        if (!interrupted) {
            swerveSubsystem.toggleIdleMode(IdleMode.kBrake);
        }
    }
}