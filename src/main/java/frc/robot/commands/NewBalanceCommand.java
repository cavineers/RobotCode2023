package frc.robot.commands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.SwerveModule;
import edu.wpi.first.wpilibj.Timer;

public class NewBalanceCommand extends CommandBase {

    private boolean isFinished;
    private double currentAngle;
    private double previousAngle;
    private boolean continueBalance;
    private double startTimestamp;

    private SwerveDriveSubsystem swerveDriveSubsystem;

    private SwerveModuleState[] states;
    private SwerveModuleState[] lock;

    public NewBalanceCommand(SwerveDriveSubsystem swerve) {
        this.addRequirements(swerve);
        this.swerveDriveSubsystem = swerve;
       
    }
    
    @Override
    public void initialize() {
        this.isFinished = false;
        this.continueBalance = true;
        this.previousAngle = this.swerveDriveSubsystem.getPitch();
        if (this.isWithinThreshold()) {
            this.isFinished = true;
        }
        this.states = generateStates();
        this.lock = generateSidewaysStates();

        this.swerveDriveSubsystem.toggleIdleMode(IdleMode.kBrake);
    }

    private boolean isWithinThreshold() {
        return currentAngle <= Constants.BalanceConstants.kBalancingControlTresholdDegrees;
    }

    private boolean isDecreasing(){
        if (this.currentAngle <= this.previousAngle - Constants.BalanceConstants.kBalancingControlDeadbandDegrees) {
            this.previousAngle = this.currentAngle;
            this.previousAngle = this.currentAngle;
            return true;
        }
        this.previousAngle = this.currentAngle;
        return false;
    }

    public SwerveModuleState[] generateStates() {
        ChassisSpeeds chassisSpeeds;
        chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            0.15, 0, 0, swerveDriveSubsystem.getRotation2d());
          

        return Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
    }
    public SwerveModuleState[] generateSidewaysStates() {
        ChassisSpeeds z;
        z = ChassisSpeeds.fromFieldRelativeSpeeds(
            0, 0, .01, swerveDriveSubsystem.getRotation2d());
          

        return Constants.DriveConstants.kDriveKinematics.toSwerveModuleStates(z);
    }

    @Override
    public void execute() {
        this.currentAngle = this.swerveDriveSubsystem.getPitch();
        if (continueBalance&&(!this.isWithinThreshold()||!this.isDecreasing())) {
            swerveDriveSubsystem.setModuleStates(states);
        } else if (continueBalance&&this.isWithinThreshold()) {
            continueBalance = false;
            this.startTimestamp = Timer.getFPGATimestamp();
        }

        if(!continueBalance){
            swerveDriveSubsystem.setModuleStates(lock);
            if(Timer.getFPGATimestamp() - this.startTimestamp >= 0.6){
                this.isFinished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        swerveDriveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}