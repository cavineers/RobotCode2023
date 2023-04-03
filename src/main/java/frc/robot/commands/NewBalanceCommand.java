package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.SwerveModule;

public class NewBalanceCommand extends CommandBase {

    private boolean isFinished;
    private double currentAngle;
    private double previousAngle;

    private SwerveDriveSubsystem swerveDriveSubsystem;

    private SwerveModuleState[] states;

    public NewBalanceCommand(SwerveDriveSubsystem swerve) {
        this.addRequirements(swerve);
        this.swerveDriveSubsystem = swerve;
        this.previousAngle = this.swerveDriveSubsystem.getPitch();
    }
    
    @Override
    public void initialize() {
        this.isFinished = false;
        if (this.isWithinThreshold()) {
            this.isFinished = true;
        }
        this.states = generateStates();
    }

    private boolean isWithinThreshold() {
        return (Math.abs(0 - swerveDriveSubsystem.getPitch()) <= Constants.BalanceConstants.kBalancingControlTresholdDegrees);
    }

    private boolean isDecreasing(){
        if (this.currentAngle <= this.previousAngle) {
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
    @Override
    public void execute() {
        this.currentAngle = this.swerveDriveSubsystem.getPitch();
        if (!this.isWithinThreshold()) {
            if (!this.isDecreasing()){
                swerveDriveSubsystem.setModuleStates(states);
            }
        }else{
            this.isFinished = true;
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