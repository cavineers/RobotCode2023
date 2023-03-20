package frc.robot.commands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ManualOverrideCommands.ClawClose;
import frc.robot.commands.ManualOverrideCommands.ClawOpen;
import frc.robot.commands.ManualOverrideCommands.RetractArm;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.commands.NumPad.TopLeft;
import frc.robot.commands.NumPad.TopMid;
import frc.robot.Robot;

import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.revrobotics.CANSparkMax.IdleMode;

import com.pathplanner.lib.PathConstraints;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.commands.AutoArmCommands.HomeArm;
import frc.robot.commands.AutoArmCommands.ArmRestPosition;
import frc.robot.commands.AutoArmCommands.ArmAtBumperCommand;
import frc.robot.commands.AutoArmCommands.ArmAutopickup;
import frc.robot.commands.AutoArmCommands.ArmIntakePreset;
import frc.robot.commands.AutoArmCommands.ArmAtBumperCommand;
import frc.robot.commands.AutoArmCommands.RetractCompletely;


public class AutoPath extends CommandBase {
    private boolean isActive;

    private Command m_autoCommand; 

    private SequentialCommandGroup autoCommandGroup;
  
    private String pathName; // Name of the path file
    private String pathPath; // Path to the path file
  
    private List<PathPlannerTrajectory> pathGroup;
  
    private final SwerveDriveSubsystem swerveSubsystem;
  
    SwerveAutoBuilder builder;
  
    public AutoPath(SwerveDriveSubsystem swerveSubsystem) {

      
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

      swerveSubsystem.toggleIdleMode(IdleMode.kBrake);
  
      this.isActive = true;
      configCommand(this.pathGroup);
      this.autoCommandGroup.addCommands(
        generateHomingGroup(),
        new ArmAutopickup(),
        generateWait(1000),
        new ClawToggle(),

        generateWait(1000),

        generatePlaceConeGroup(),
        
        generateWait(1000),

        new ParallelCommandGroup(
          new ArmRestPosition(),
          this.m_autoCommand
        ));
      this.autoCommandGroup.schedule();
    }

    public void execute(){
      if (this.autoCommandGroup.isFinished()) {
        this.isActive = false;
      }
    }
  
  
    private List<PathPlannerTrajectory> generateAutonomousPath() {
      this.pathPath = pathName;
      List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(this.pathPath, new PathConstraints(1, 0.25));//Constants.PathPlanning.kMaxSpeedMetersPerSecond
      return pathGroup;
    }

  
    private HashMap<String, Command> generateEventMapping(){
      HashMap<String, Command> eventMap = new HashMap<>();
      eventMap.put("DeployIntake", new ToggleDeployIntake());
      eventMap.put("UnDeployIntake", new ToggleUndeployIntake());

      eventMap.put("CloseClaw", new ClawToggle());
      eventMap.put("OpenClaw", new ClawToggle());
      eventMap.put("PlaceCone", generatePlaceConeGroup());
      eventMap.put("PlaceCube", generatePlaceCubeGroup());
      eventMap.put("RestArm", new ArmRestPosition());
      eventMap.put("BumperArm", new ArmAtBumperCommand());
      eventMap.put("ArmIntake", generateGrabIntakeGroup());

      //eventMap.put("Balance", new BalanceControlCommand(swerveSubsystem));
    

      return eventMap;
    }

    private Command generateWait(int time){
      return new InstantCommand(){
        public void initialize(){
          try{
            Thread.sleep(time);
          }catch(InterruptedException e){}
        }
      };
    }

    private SequentialCommandGroup generatePlaceConeGroup(){

      return new SequentialCommandGroup(
        new TopLeft(),
        new ClawToggle()
      );
    }

    private SequentialCommandGroup generatePlaceCubeGroup(){

      return new SequentialCommandGroup(
        new TopMid(),
        new ClawToggle()
        //new HomeArm()
        
      );
    }

    private SequentialCommandGroup generateGrabIntakeGroup(){
      return new SequentialCommandGroup(
        new ArmIntakePreset(),
        new ClawToggle(),
        new InstantCommand(){
          public void initialize(){
            try{
              Thread.sleep(1000);
            }catch(InterruptedException e){}
          }
        },
        new RetractCompletely()
      );
    }

    private ParallelCommandGroup generateHomingGroup() {
      return new ParallelCommandGroup(
        new HomeArm(),
        new ClawHoming()
      );
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
      true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
      swerveSubsystem // The drive subsystem. Used to properly set the requirements of path following commands
      );
  
      return autoBuilder;
  
    }

    public List<PathPlannerTrajectory> inversePathGroup(List<PathPlannerTrajectory> pathGroup){
      List<PathPlannerTrajectory> transform = new ArrayList<>();

      for (PathPlannerTrajectory path : pathGroup){
        PathPlannerTrajectory transformPath = PathPlannerTrajectory.transformTrajectoryForAlliance(path, DriverStation.Alliance.Red);
        transform.add(transformPath);
      }
      return transform;
    }
  
    public void configCommand(List<PathPlannerTrajectory> pathGroup) {
      this.isActive = true;
      this.m_autoCommand = this.builder.fullAuto(inversePathGroup(pathGroup));
    }
  
    public boolean isFinished(){
      return !this.isActive; // Stop if the robot is not currently pathing
    }

    public void end(){
      swerveSubsystem.toggleIdleMode(IdleMode.kCoast);
    }
}