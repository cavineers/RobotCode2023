package frc.lib;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

import frc.robot.commands.AutoArmCommands.HomeArm;
import frc.robot.commands.AutoArmCommands.ArmRestPosition;
import frc.robot.commands.AutoArmCommands.ArmAtBumperCommand;
import frc.robot.commands.AutoArmCommands.ArmAutopickup;
import frc.robot.commands.AutoArmCommands.RetractCompletely;
import frc.robot.commands.NumPad.ArmTopPeg;
import frc.robot.commands.NumPad.ArmTopShelf;

import frc.robot.commands.ClawToggle;

import frc.robot.commands.IntakeCube;
import frc.robot.commands.FlushCube;





public class AutoCommandGroups {

    private static HashMap<String, Command> createEventMapping(){

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("Intake", new IntakeCube());
        eventMap.put("Flush", new FlushCube());
  
        eventMap.put("CloseClaw", new ClawToggle());
        eventMap.put("OpenClaw", new ClawToggle());
        eventMap.put("PlaceCone", createPlaceConeGroup());
        eventMap.put("PlaceCube", createPlaceCubeGroup());
        eventMap.put("RestArm", new ArmRestPosition());
        eventMap.put("BumperArm", new ArmAtBumperCommand());

        return eventMap;
    }

    public static SequentialCommandGroup createPlaceCubeGroup(){ // Drops cube on the top height

        return new SequentialCommandGroup(
          new ArmTopShelf(),
          new ClawToggle()
          //new HomeArm()
          
        );
    }

    public static SequentialCommandGroup createPlaceConeGroup(){ // Drops cone on the top height

        return new SequentialCommandGroup(
          new ArmTopPeg(),
          new ClawToggle()
        );
      }

    public static ParallelCommandGroup createHomingGroup() { // Homes the arm and claw
        return new ParallelCommandGroup(
          new HomeArm()
          // new ClawHoming()
        );
    }

    

    private static List<PathPlannerTrajectory> inversePathGroup(List<PathPlannerTrajectory> pathGroup){
        List<PathPlannerTrajectory> transform = new ArrayList<>();
  
        for (PathPlannerTrajectory path : pathGroup){
          PathPlannerTrajectory transformPath = PathPlannerTrajectory.transformTrajectoryForAlliance(path, DriverStation.Alliance.Red);
          transform.add(transformPath);
        }
        return transform;
    }

    public static List<PathPlannerTrajectory> createAutonomousPath(String pathName) {
        
        
        List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(
            pathName,
            Constants.PathPlanning.kMaxSpeedMetersPerSecond,   
            Constants.PathPlanning.kMaxAccelerationMetersPerSecond
        );
        return inversePathGroup(pathGroup);
    }

    
    public static SwerveAutoBuilder createAutoBuilder(SwerveDriveSubsystem swerveSubsystem){
        SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
        swerveSubsystem::getPose, // Pose2d supplier
        swerveSubsystem::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
        Constants.DriveConstants.kDriveKinematics, // SwerveDriveKinematics
        Constants.PathPlanning.kAutoDriveVelocityPID, // PID constants to correct for translation error (used to create the X and Y PID controllers)
        Constants.PathPlanning.kAutoDriveTurnPID, // PID constants to correct for rotation error (used to create the rotation controller)
        swerveSubsystem::setModuleStates, // Module states consumer used to output to the drive subsystem
        createEventMapping(),
        true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
        swerveSubsystem // The drive subsystem. Used to properly set the requirements of path following commands
        );
    
        return autoBuilder;
    
    }



}
