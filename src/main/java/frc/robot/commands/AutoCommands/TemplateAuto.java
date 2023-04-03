package frc.robot.commands.AutoCommands;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ManualOverrideCommands.ClawClose;
import frc.robot.commands.ManualOverrideCommands.ClawOpen;
import frc.robot.commands.ManualOverrideCommands.RetractArm;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.commands.NumPad.ArmTopPeg;
import frc.robot.commands.NumPad.ArmTopShelf;
import frc.robot.Robot;

import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.revrobotics.CANSparkMax.IdleMode;

import com.pathplanner.lib.PathConstraints;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.commands.AutoArmCommands.HomeArm;
import frc.robot.commands.AutoArmCommands.ArmRestPosition;
import frc.robot.commands.ClawToggle;
import frc.robot.commands.AutoArmCommands.ArmAtBumperCommand;
import frc.robot.commands.AutoArmCommands.ArmAutopickup;
import frc.robot.commands.AutoArmCommands.RetractCompletely;

import frc.lib.AutoCommandGroups;

public class TemplateAuto extends CommandBase {
    private final SwerveDriveSubsystem swerveSubsystem;
    private boolean isActive;

    private String pathName; // Name of the path file
    private List<PathPlannerTrajectory> pathGroup;

    SwerveAutoBuilder builder;
    private Command m_autoCommand; 
    private SequentialCommandGroup autoCommandGroup;
  
    public TemplateAuto(SwerveDriveSubsystem swerveSubsystem) {

      this.pathName = "EXAMPLE";

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
        AutoCommandGroups.createHomingGroup(),
        new ArmAutopickup(),

        new WaitCommand(1),

        new ClawToggle(),

        new WaitCommand(1),

        AutoCommandGroups.createPlaceConeGroup(),
        
        new WaitCommand(100),

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
  
    public void configCommand(List<PathPlannerTrajectory> pathGroup) {
      
      this.isActive = true;
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