package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;;
import com.pathplanner.lib.PathConstraints;

public class PathPlanning extends SubsystemBase {
  public PathPlanning() {
  }

  public List<PathPlannerTrajectory> generateAutonomousPath(String pathName) {
    pathName = "src/main/deploy/paths/" + pathName + ".path";
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(pathName, new PathConstraints(Constants.PathPlanning.kMaxSpeedMetersPerSecond, Constants.PathPlanning.kMaxAccelerationMetersPerSecond));
    return pathGroup;
  
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}