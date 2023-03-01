package frc.robot.subsystems;


import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class PathPlanning extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public PathPlanning() {

  }

  public PathPlannerTrajectory onTheFlyGenerationRelative(Rotation2d currentHeading, Rotation2d holonomicRotation, Translation2d goalTranslation, Rotation2d goalRotation, Rotation2d goalHeading){
    PathPlannerTrajectory traj = PathPlanner.generatePath(
      new PathConstraints(4, 3),
      new PathPoint(new Translation2d(0,0), currentHeading, holonomicRotation), // INITIAL position, heading(direction of travel), holonomic rotation 
      new PathPoint(goalTranslation, goalHeading, goalRotation) // position, heading(direction of travel), holonomic rotation
    );
    return traj;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}