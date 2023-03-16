package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.NumPad.TopLeft;
import frc.robot.Robot;

import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.HashMap;
import java.util.List;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.pathplanner.lib.PathConstraints;

public class AutoPath extends CommandBase {
    private boolean isActive;

    private Command m_lowerIntake;
    private Command m_placeTop;
    private Command m_autoCommand; 

    private SequentialCommandGroup autoCommandGroup;
  
    private String pathName; // Name of the path file
    private String pathPath; // Path to the path file
  
    private List<PathPlannerTrajectory> pathGroup;
  
    private final SwerveDriveSubsystem swerveSubsystem;
  
    SwerveAutoBuilder builder;
  
    public AutoPath(SwerveDriveSubsystem swerveSubsystem) {
      this.m_lowerIntake = new ToggleDeployIntake();
      this.m_placeTop = new TopLeft();
      this.swerveSubsystem = swerveSubsystem;
      this.builder = this.createAutoBuilder(); // requires swerveSubsystem
      if (swerveSubsystem == null) {
        System.out.println("SwerveSubsystem is null in AutoPath constructor");
      }

      addRequirements(swerveSubsystem);
    }
    
    public void initialize(){
      this.autoCommandGroup = new SequentialCommandGroup();
      this.pathName = Robot.m_robotContainer.getAutoPath();
      this.pathGroup = generateAutonomousPath();
  
      this.isActive = true;
      configCommand(this.pathGroup);
      this.autoCommandGroup.addCommands(
        this.m_placeTop,
        this.m_autoCommand
        // Schedule Balance command here
      );
      this.autoCommandGroup.schedule();
    }

    public void execute(){
      if (this.autoCommandGroup.isFinished()) {
        this.isActive = false;
      }
    }
  
  
    private List<PathPlannerTrajectory> generateAutonomousPath() {
      this.pathPath = pathName;
      List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(this.pathPath, new PathConstraints(Constants.PathPlanning.kMaxSpeedMetersPerSecond, Constants.PathPlanning.kMaxAccelerationMetersPerSecond));
      return pathGroup;
    }
  
    private HashMap<String, Command> generateEventMapping(){
      HashMap<String, Command> eventMap = new HashMap<>();
      eventMap.put("ToggleIntake", this.m_lowerIntake);
      eventMap.put("PlaceCone", this.m_placeTop);
      return eventMap;
    }
      
  
    public SwerveAutoBuilder createAutoBuilder(){
      SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
      swerveSubsystem::getPose, // Pose2d supplier
      swerveSubsystem::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
      Constants.DriveConstants.kDriveKinematics, // SwerveDriveKinematics
      Constants.PathPlanning.kAutoDriveVelocityPID, // PID constants to correct for translation error (used to create the X and Y PID controllers)
      Constants.PathPlanning.kAutoDriveTurnPID, // PID constants to correct for rotation error (used to create the rotation controller)
      swerveSubsystem::setModuleStates, // Module states consumer used to output to the drive subsystem
      generateEventMapping(),
      false, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
      swerveSubsystem // The drive subsystem. Used to properly set the requirements of path following commands
      );
  
      return autoBuilder;
  
    }
  
    public void configCommand(List<PathPlannerTrajectory> pathGroup) {
      this.isActive = true;
      this.m_autoCommand = this.builder.fullAuto(pathGroup);
    }
  
    public boolean isFinished(){
      return !this.isActive; // Stop if the robot is not currently pathing
    }
}