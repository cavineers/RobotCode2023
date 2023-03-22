package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.Constants.BalanceConstants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.controller.PIDController;

public class BalanceControlCommand extends CommandBase {
    
    private double error;
    private double previousError;

    private double currentAngle;
    private double drivePower;
    private final SwerveDriveSubsystem swerveSubsystem;
    private int counter;
    private boolean m_isFinished;
    private PIDController pidController;


    public BalanceControlCommand(SwerveDriveSubsystem swerveSubsystem){
      System.out.println("Started Balance");
      this.swerveSubsystem = swerveSubsystem;

      this.pidController = new PIDController(BalanceConstants.kBalancingControlDriveP, BalanceConstants.kBalancingControlDriveI, BalanceConstants.kBalancingControlDriveD);
      this.pidController.setSetpoint(0);
      this.pidController.enableContinuousInput(-180, 180);
      
      addRequirements(swerveSubsystem);
      }
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        this.currentAngle = swerveSubsystem.getRoll();
        this.m_isFinished = false;
        this.error = 0 - currentAngle;
        this.counter = 0;
        this.previousError = error;
      }

      public double calculateError(){
          double pitchRadians = Math.toRadians(swerveSubsystem.getPitch());
          double rollRadians = Math.toRadians(swerveSubsystem.getRoll());
          double hypotenuse = Math.cos(pitchRadians) * Math.cos(rollRadians);
          double error = Math.sqrt(1 - hypotenuse * hypotenuse);
          return error;
      }
      

      // Called every time the scheduler runs while the command is scheduled.

      @Override
      public void execute() {
        // Uncomment the line below this to simulate the gyroscope axis with a controller joystick
        // Double currentAngle = -1 * Robot.controller.getRawAxis(Constants.LEFT_VERTICAL_JOYSTICK_AXIS) * 45;
        //  if (this.runChecks()) {
      
        this.currentAngle = swerveSubsystem.getRoll();
        this.error = 0 - currentAngle;
        
        if (!(Math.abs(error) < Math.abs(this.previousError))){
          this.drivePower = pidController.calculate(error);
          ChassisSpeeds chassisSpeeds;
          chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            -drivePower, 0, 0, swerveSubsystem.getRotation2d());
          
          SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
            // Output each module states to wheels
          this.swerveSubsystem.setModuleStates(moduleStates);
          counter();
          SmartDashboard.putNumber("Error ", error);
          SmartDashboard.putNumber("Drive Power: ", drivePower);
        }
        this.previousError = error;
      }

      public void lockWheels(){
        ChassisSpeeds chassisSpeedsStopForward;
        ChassisSpeeds chassisSpeedsStopSide;
        chassisSpeedsStopForward = ChassisSpeeds.fromFieldRelativeSpeeds(0, .1, 0, swerveSubsystem.getRotation2d());
        chassisSpeedsStopSide = ChassisSpeeds.fromFieldRelativeSpeeds(.1, 0, 0, swerveSubsystem.getRotation2d());


      
        SwerveModuleState[] moduleStatesForward = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeedsStopForward);
        SwerveModuleState[] moduleStatesSide = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeedsStopSide);
    
        SwerveModuleState[] combinedStates = new SwerveModuleState[] {
          moduleStatesForward[0],
          moduleStatesSide[1],
          moduleStatesSide[2],
          moduleStatesForward[3]
        };

        swerveSubsystem.setModuleStates(combinedStates);
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
        System.out.println("COMMAND FINISHED");
        
        this.lockWheels();
  

        swerveSubsystem.stopModules();
      }

      public static double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
      }

      public void counter(){ // Checks if the robot is balanced or starting to balance
        if (Math.abs(error) < BalanceConstants.kBalancingControlTresholdDegrees){
          SmartDashboard.putNumber("Counter", counter);
          counter++;
        } else {
          counter = 0;
        }
        if (counter > 75){
          m_isFinished = true;
        }
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        // End the command when we are within the specified threshold of being 'flat' (gyroscope pitch of 0 degrees)
        return this.m_isFinished;
      }
    }
