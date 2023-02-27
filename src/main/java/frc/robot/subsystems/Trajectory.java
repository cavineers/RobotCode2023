package edu.wpi.first.wpilibj.examples.ramsetecommand.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Trajectory {

    private double distanceAutonomous;
    private boolean returning = false;
    private Trajectory trajectory;
    private Pose2d desiredPose;

    public Trajectory() {
        this.trajectory = generateTrajectory();
        returning = checkReturning();
        distanceAustonomous = Constants.TrajectoryConstants.distanceAutonomous;
        desiredPose =  new Pose2d(distanceAutonomous, 0, new Rotation2d(0));
    }

    public Trajectory generateTrajectory() {
        
       var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                Constants.TrajectoryConstants.ksVolts,
                Constants.TrajectoryConstants.kvVoltSecondsPerMeter,
                Constants.TrajectoryConstants.kaVoltSecondsSquaredPerMeter),
                Constants.TrajectoryConstants.SwerveDriveKinematics,
            10);

        TrajectoryConfig config =
        new TrajectoryConfig(
                Constants.DriveConstants.kPhysicalMaxSpeedMetersPerSecond,
                Constants.DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.DriveConstants.SwerveDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

            Trajectory newTrajectory = TrajectoryGenerator.generateTrajectory(
            //Start facing the direction that we want to go. Consider the robot's starting position as (0,0)
            Constants.TrajectoryConstants.initialPoseInches,
            //Have two waypoints - one 1/3 into the journey and one 2/3 into the journey
            List.of(new Translation2d(distanceAutonomous/3, 1), new Translation2d((2*distanceAutonomous)/3, -1)),
            desiredPose,
            config);

        if (returning == true) {
            //Find the back bearing if going the opposite direction
            trajectory = trajectory - Math.PI;
        } 
        return newTrajectory;
    }  
    
    //Return the command to run during autonomous relating to trajectory
    public Command autonomousTrajectoryCommand() {
        RamseteCommand ramsete..Command =
        new RamseteCommand(
            this.trajectory,
            m_robotDrive::getPose,
            new RamseteController(Constants.TrajectoryConstants.kRamseteB, Constants.TrajectoryConstants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.TrajectoryConstants.ksVolts,
                Constants.TrajectoryConstants.kvVoltSecondsPerMeter,
                Constants.TrajectoryConstants.kaVoltSecondsSquaredPerMeter),
                Constants.TrajectoryConstants.SwerveDriveKinematics,
            m_robotDrive::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            m_robotDrive::swerveDriveVolts,
            m_robotDrive);

         m_robotDrive.resetOdometry(Constants.TrajectoryConstants.initialPoseInches);
     
         //Create a command that, when executed, will follow the trajectory
         return ramseteCommand.andThen(() -> m_robotDrive.swerveDriveVolts(0, 0));
    }

    public boolean checkReturning() {
        if (Robot.m_swerveDriveSubsystem.getPose() == desiredPose) {
            returning = true;
        }
        return returning;
    }

    public Trajectory getTrajectory() {
        return this.trajectory;
    }
}
