package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import frc.robot.commands.ClawToggle;

import frc.robot.commands.SwerveCommand;

import frc.robot.commands.ToggleDeployIntake;
import frc.robot.commands.ToggleUndeployIntake;

import frc.robot.commands.presets.TopNode;
import frc.robot.commands.presets.MidNode;
import frc.robot.commands.presets.BottomNode;
import frc.robot.commands.presets.HomeArm;

import frc.robot.commands.manualOverrideCommands.ExtendArm;
import frc.robot.commands.manualOverrideCommands.RaiseArm;
import frc.robot.commands.manualOverrideCommands.RetractArm;
import frc.robot.commands.manualOverrideCommands.SwitchMode;
import frc.robot.commands.manualOverrideCommands.LowerArm;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class RobotContainer {

    //Swerve Subsystem
    private final SwerveDriveSubsystem swerveSubsystem = new SwerveDriveSubsystem();

    public Command m_autoCommand;

    //Arm Commands
    public Command m_armChainMotorUp;
    public Command m_armChainMotorDown;
    public Command m_armExtendMotor;
    public Command m_armRetractMotor;
    public Command m_armHome;
    public Command m_armBottomNode;
    public Command m_armMidNode;
    public Command m_armTopNode;
    public Command m_arm;

    //Intake Commands
    public Command m_intake;
    public Command m_lowerIntake;
    public Command m_raiseIntake;

    //Claw Commands
    public Command m_claw;

    // Driver Controller
    public Joystick joy = new Joystick(0);
    public JoystickButton a_button = new JoystickButton(joy, 1);
    public JoystickButton b_button = new JoystickButton(joy, 2);
    public JoystickButton x_button = new JoystickButton(joy, 3);
    public JoystickButton y_button = new JoystickButton(joy, 4);
    public JoystickButton l_bump = new JoystickButton(joy, 5);
    public JoystickButton r_bump = new JoystickButton(joy, 6);
    public JoystickButton left_menu = new JoystickButton(joy, 7);
    public JoystickButton right_menu = new JoystickButton(joy, 8);
    public JoystickButton left_stick = new JoystickButton(joy, 9);
    public JoystickButton right_stick = new JoystickButton(joy, 10);

    public POVButton povUp = new POVButton(joy, 0, 0);
    public POVButton povRight = new POVButton(joy, 90, 0);
    public POVButton povDown = new POVButton(joy, 180, 0);
    public POVButton povLeft = new POVButton(joy, 270, 0); 

    public enum CurrentMode {
      DRIVE,
      ARM
    }
  
    private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);
    
    public CurrentMode mode = CurrentMode.DRIVE; 

    public RobotContainer() {

      this.m_raiseIntake = new ToggleUndeployIntake();
      this.m_lowerIntake = new ToggleDeployIntake();
      this.m_claw = new ClawToggle();

      //swerveSubsystem.setDefaultCommand(new SwerveHoming(swerveSubsystem));

      swerveSubsystem.setDefaultCommand(new SwerveCommand(
          swerveSubsystem,
          () -> -joy.getRawAxis(OIConstants.kDriverYAxis),
          () -> joy.getRawAxis(OIConstants.kDriverXAxis),
          () -> joy.getRawAxis(OIConstants.kDriverRotAxis),
          () -> !joy.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

      if(this.mode == CurrentMode.DRIVE) {
        configureButtonBindings();
      } else {
        configureButtonBindingsArm();
      }

    };

    private void configureButtonBindings() {
      
      //Intake Buttons
      this.r_bump.onTrue(new InstantCommand(){
        public void initialize() {
          if(m_raiseIntake.isScheduled()) {
            m_raiseIntake.cancel();
          }
        m_lowerIntake = new ToggleDeployIntake();
        m_lowerIntake.schedule();
        }
      });
      this.l_bump.onTrue(new InstantCommand(){
        public void initialize() {
          if(m_lowerIntake.isScheduled()) {
            m_lowerIntake.cancel();
            }
        m_raiseIntake = new ToggleUndeployIntake();
        m_raiseIntake.schedule();
        }
      });

      //Arm Buttons
      this.right_menu.onTrue(new InstantCommand() {
        public void initialize() {
          mode = CurrentMode.ARM;
        }
      });
        
      this.left_menu.onTrue(new InstantCommand() {
        public void initialize() {
          m_armHome = new HomeArm();
          m_armHome.schedule();
        }
      });
      this.povDown.onTrue(new InstantCommand() {
        public void initialize() {
          m_armBottomNode = new BottomNode();
          m_armBottomNode.schedule();
        }
      });
      this.povRight.onTrue(new InstantCommand() {
        public void initialize() {
          m_armMidNode = new MidNode();
          m_armMidNode.schedule();
        }
      });
      this.povUp.onTrue(new InstantCommand() {
        public void initialize() {
          m_armTopNode = new TopNode();
          m_armTopNode.schedule();
        }
      });
      //Claw Buttons
      this.a_button.onTrue(new InstantCommand() {
        public void initialize() {
          m_claw = new ClawToggle();
          m_claw.schedule();
        }
      });
  
      /*this.a_button.onFalse(new InstantCommand() {
        public void initialize() {
          if (m_claw.isScheduled()) {
            m_claw.cancel();
          }
        }
      }); */
    
    }
    private void configureButtonBindingsArm() {
    
      this.right_menu.onTrue(new InstantCommand() {
        public void initialize() {
          mode = CurrentMode.DRIVE;
        }
      });

      this.x_button.onTrue(new InstantCommand() {
        public void initialize() {
          m_armChainMotorDown = new LowerArm();
          m_armChainMotorDown.schedule();
        }
      });
    }   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
