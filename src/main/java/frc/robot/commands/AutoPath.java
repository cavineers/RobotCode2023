package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.Presets.TopNode;
import frc.robot.commands.ToggleDeployIntake;
import frc.robot.commands.ToggleUndeployIntake;

import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.pathplanner.lib.PathConstraints;

import frc.robot.RobotContainer;

public class AutoPath extends CommandBase {
  boolean isActive;

  Command m_lowerIntake;
  Command m_placeTop;
  Command m_autoCommand;

  SwerveDriveSubsystem swerveSubsystem;

  SwerveAutoBuilder builder;

  public AutoPath(SwerveDriveSubsystem swerveSubsystem) {
    this.m_lowerIntake = new ToggleDeployIntake();
    this.m_placeTop = new TopNode();
    this.builder = this.createAutoBuilder();
    this.swerveSubsystem = swerveSubsystem;
    addRequirements(swerveSubsystem);
  }
  
  public void initialize(){
  
  }

  public void periodic() {
    if (this.isActive) {
      if (this.m_autoCommand.isFinished()) {
        this.isActive = false;
      }
    }
  }

  private List<PathPlannerTrajectory> generateAutonomousPath(String pathName) {
    pathName = "src/main/deploy/paths/" + pathName + ".path";
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(pathName, new PathConstraints(Constants.PathPlanning.kMaxSpeedMetersPerSecond, Constants.PathPlanning.kMaxAccelerationMetersPerSecond));
    return pathGroup;
  }

  private HashMap<String, Command> generateEventMapping(){
    HashMap<String, Command> eventMap = new HashMap<>();
    eventMap.put("ToggleIntake", this.m_lowerIntake);
    eventMap.put("PlaceCone", this.m_placeTop);
    return eventMap;
  }
    
  public boolean isPathing() {
    return this.isActive;
  }

  public SwerveAutoBuilder createAutoBuilder(){
    SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    this.swerveSubsystem::getPose, // Pose2d supplier
    this.swerveSubsystem::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
    Constants.DriveConstants.kDriveKinematics, // SwerveDriveKinematics
    new PIDConstants(5.0, 0.0, 0.0), // PID constants to correct for translation error (used to create the X and Y PID controllers)
    new PIDConstants(0.5, 0.0, 0.0), // PID constants to correct for rotation error (used to create the rotation controller)
    this.swerveSubsystem::setModuleStates, // Module states consumer used to output to the drive subsystem
    generateEventMapping(),
    true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
    swerveSubsystem // The drive subsystem. Used to properly set the requirements of path following commands
    );

    return autoBuilder;

  }

  public void startPathing(List<PathPlannerTrajectory> pathGroup) {
    this.isActive = true;
    this.m_autoCommand = this.builder.fullAuto(pathGroup);
    this.m_autoCommand.schedule();
  }


}