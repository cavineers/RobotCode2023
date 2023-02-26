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
    private boolean returning;
    private Trajectory trajectory;

    public Trajectory() {
        this.trajectory = generateTrajectory();
        returning = false;
        distanceAustonomous = Constants.TrajectoryConstants.distanceAutonomous;
    }

    public Trajectory generateTrajectory() {
        
       var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                Constants.DriveConstants.ksVolts,
                Constants.DriveConstants.kvVoltSecondsPerMeter,
                Constants.DriveConstants.kaVoltSecondsSquaredPerMeter),
                Constants.DriveConstants.SwerveDriveKinematics,
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
            Constants.DriveConstants.initialPoseInches,
            //Have two waypoints - one 1/3 into the journey and one 2/3 into the journey
            List.of(new Translation2d(distanceAutonomous/3, 1), new Translation2d((2*distanceAutonomous)/3, -1)),
            new Pose2d(distanceAutonomous, 0, new Rotation2d(0)),
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
                Constants.DriveConstants.ksVolts,
                Constants.DriveConstants.kvVoltSecondsPerMeter,
                Constants.DriveConstants.kaVoltSecondsSquaredPerMeter),
                Constants.DriveConstants.SwerveDriveKinematics,
            m_robotDrive::getWheelSpeeds,
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            m_robotDrive::swerveDriveVolts,
            m_robotDrive);

         //Create a command that, when executed, will follow the trajectory
         m_robotDrive.resetOdometry(Constants.TrajectoryConstants.initialPoseInches);
     
         return ramseteCommand.andThen(() -> m_robotDrive.swerveDriveVolts(0, 0));
    }

    public Trajectory getTrajectory() {
        return this.trajectory;
    }
}
