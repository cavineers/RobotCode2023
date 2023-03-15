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
    private PIDController ySpeedPID;
    private PIDController rotationPID;

    private PPHolonomicDriveController swerveTrajFollower;

    private Translation2d goal; // The peg/shelf/substation that is selected

    private PathPlannerTrajectory trajectory;
    
    private boolean hasInitialTarget;

    private SwerveDriveOdometry relativeOdometer;

    

    public AprilTagHomingCommand(SwerveDriveSubsystem swerveSubsystem, AprilTagHoming tagHomingSubsystem, Translation2d goalOffset) {
        
        this.swerveSubsystem = swerveSubsystem;
        this.tagHomingSubsystem = tagHomingSubsystem;

        addRequirements(swerveSubsystem, tagHomingSubsystem);

        this.relativeOdometer = swerveSubsystem.getNewOdometer();
        

        this.goal = goalOffset; // The peg/shelf/substation translation2d that is selected

        this.xSpeedPID = new PIDController(Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD); // X controller
        this.ySpeedPID = new PIDController(-Constants.HomingDrivePIDControllerConstants.kP, Constants.HomingDrivePIDControllerConstants.kI, Constants.HomingDrivePIDControllerConstants.kD); // Y controller
        this.rotationPID = new PIDController(Constants.HomingRotationalPIDControllerConstants.kP, Constants.HomingRotationalPIDControllerConstants.kI, Constants.HomingRotationalPIDControllerConstants.kD); // Rotation controller

        this.swerveTrajFollower = new PPHolonomicDriveController(this.xSpeedPID, this.ySpeedPID, this.rotationPID);
        this.swerveTrajFollower.setTolerance(new Pose2d(new Translation2d(0.05, 0.05), new Rotation2d(0.05)));
    }

    public void initialize() {
        swerveSubsystem.toggleIdleMode(IdleMode.kCoast);
        this.trajectory = this.getPointAsPlannerState();
        if (tagHomingSubsystem.hasTargets) {
            resetOdometry();
            this.hasInitialTarget = true;
        } else {
            this.hasInitialTarget = false;
        }
    }
    
    public void execute(){
        relativeOdometer.update(this.swerveSubsystem.getRotation2d(), this.swerveSubsystem.getPositions());
        
        ChassisSpeeds chassisSpeeds = calculateChassisSpeeds();
        SwerveModuleState[] moduleStates =  DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        swerveSubsystem.setModuleStates(moduleStates);
       
        SmartDashboard.putString("POSEAPRIL", relativeOdometer.getPoseMeters().toString());
    
        SmartDashboard.putString("GOALPOSE", trajectory.getEndState().toString());
        SmartDashboard.putString("Chassis Speeds", chassisSpeeds+"");
    }

    //Find the dist goal relative to the robot, inverse it because we are driving backwards
    private Translation2d calculateGoalRelativeRobot() {
        return (this.tagHomingSubsystem.getTranslationToTag().minus(this.goal) ).times(-1); //Find the goal relative to the robot, inverse it because we are driving backwards
    }

    //resets the odometry to 0,0 as we are relative to the robot, and keeps the gyro the same
    private void resetOdometry() {

        // Create a new Pose2d object representing (0,0), with the same gyro angle as the current pose
        Pose2d newPose = new Pose2d(0, 0, this.swerveSubsystem.getPose().getRotation());

        // Reset the odometry pose to the newPose object using the odometry object's resetPosition() method
        relativeOdometer.resetPosition(this.swerveSubsystem.getRotation2d(), this.swerveSubsystem.getPositions(), newPose);
    }

    private PathPlannerTrajectory getPointAsPlannerState() {
        return this.tagHomingSubsystem.onTheFlyGenerationRelative(this.swerveSubsystem.getRotation2d(), calculateGoalRelativeRobot());
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
        ChassisSpeeds speeds = swerveTrajFollower.calculate(relativeOdometer.getPoseMeters(), this.trajectory.getEndState());
        ChassisSpeeds newSpeeds = new ChassisSpeeds(clampSpeeds(speeds.vxMetersPerSecond), clampSpeeds(speeds.vyMetersPerSecond), clampSpeeds(speeds.omegaRadiansPerSecond));
        return newSpeeds;
    }


    public boolean isFinished() {
        return (this.swerveTrajFollower.atReference()) || (!this.hasInitialTarget);
    }

    
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
        if (!interrupted) {
            swerveSubsystem.toggleIdleMode(IdleMode.kBrake);
        }
    }
}