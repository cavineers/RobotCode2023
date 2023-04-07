package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.List;


import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.commands.AutoArmCommands.ArmRestPosition;
import frc.robot.commands.BalanceControlCommand;
import frc.robot.commands.ClawHoming;
import frc.robot.commands.ClawToggle;
import frc.robot.commands.AutoArmCommands.ArmAutopickup;
import frc.lib.AutoCommandGroups;

public class ConeStationary extends CommandBase {
    private final SwerveDriveSubsystem swerveSubsystem;
    private boolean isActive;

    private String pathName; // Name of the path file
    private List<PathPlannerTrajectory> pathGroup;

    SwerveAutoBuilder builder;
    private Command m_autoCommand; 
    private SequentialCommandGroup autoCommandGroup;

    public ConeStationary(SwerveDriveSubsystem swerveSubsystem) {

      this.pathName = "ChargeStationCube";

      this.swerveSubsystem = swerveSubsystem;
      this.builder = AutoCommandGroups.createAutoBuilder(swerveSubsystem); 
      if (swerveSubsystem == null) {
        System.out.println("SwerveSubsystem is null in AutoPath constructor");
      }

      addRequirements(swerveSubsystem);
    }
    
    public void initialize(){

      this.autoCommandGroup = new SequentialCommandGroup();

      this.pathGroup = AutoCommandGroups.createAutonomousPath(this.pathName);

      swerveSubsystem.toggleIdleMode(IdleMode.kBrake);
  
      this.isActive = true;
      configCommand(this.pathGroup);

      this.autoCommandGroup.addCommands(
        new ClawToggle(),
        //AutoCommandGroups.createHomingGroup(),
        //new ArmAutopickup(),

        new WaitCommand(1),

        

        //new WaitCommand(1),

        AutoCommandGroups.createPlaceConeGroup(),
        
        new WaitCommand(.1),

        

        new ParallelCommandGroup(
          new ClawHoming(),

          new SequentialCommandGroup(
            new WaitCommand(1),

            new ArmRestPosition()
          )
        ));
        //new BalanceControlCommand(swerveSubsystem));
      this.autoCommandGroup.schedule();
    }

    public void execute(){

      if (this.autoCommandGroup.isFinished()) {
        this.isActive = false;
      }
    }
  
    public void configCommand(List<PathPlannerTrajectory> pathGroup) {
      this.m_autoCommand = this.builder.fullAuto(this.pathGroup);
    }
  
    public boolean isFinished(){
      return !this.isActive; // Stop if the robot is not currently pathing
    }

    public void end(){
      swerveSubsystem.toggleIdleMode(IdleMode.kCoast);

      if (this.m_autoCommand.isScheduled()) {
        this.m_autoCommand.cancel();
      }
      
    }
}