package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.commands.AutoPath;
import frc.robot.commands.NumPad.BottomLeft;
import frc.robot.commands.NumPad.BottomMid;
import frc.robot.commands.NumPad.BottomRight;
import frc.robot.commands.NumPad.MidLeft;
import frc.robot.commands.NumPad.MidMid;
import frc.robot.commands.NumPad.MidRight;
import frc.robot.commands.NumPad.TopLeft;
import frc.robot.commands.NumPad.TopMid;
import frc.robot.commands.NumPad.TopRight;
import frc.robot.commands.ControllerPresets.HomeArm;
import frc.robot.commands.SwitchMode;
import frc.robot.commands.ControllerPresets.BottomNode;
import frc.robot.commands.ControllerPresets.MidNode;
import frc.robot.commands.ControllerPresets.TopNode;
import frc.robot.commands.ClawClose;
import frc.robot.commands.ClawOpen;
import frc.robot.commands.ClawToggle;
import frc.robot.commands.SwerveCommand;
import frc.robot.commands.ToggleDeployIntake;
import frc.robot.commands.ToggleUndeployIntake;
import frc.robot.commands.AprilTagHomingCommand;

import frc.robot.commands.ControllerPresets.TopNode;
import frc.robot.commands.ControllerPresets.MidNode;
import frc.robot.commands.ControllerPresets.BottomNode;
import frc.robot.commands.ControllerPresets.HomeArm;

import frc.robot.commands.manualOverrideCommands.ExtendArm;
import frc.robot.commands.manualOverrideCommands.RaiseArm;
import frc.robot.commands.manualOverrideCommands.RetractArm;
import frc.robot.commands.manualOverrideCommands.SwitchMode;
import frc.robot.commands.manualOverrideCommands.LowerArm;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.SwerveDriveSubsystem;


public class RobotContainer  {
    
    public SendableChooser<Command> auto = new SendableChooser<Command>();

    //Swerve Subsystem
    private final SwerveDriveSubsystem swerveSubsystem = new SwerveDriveSubsystem();

    public Command m_autoCommand;

    //Arm Commands
    public Command m_armHome;
    public Command m_armBottomNode;
    public Command m_armMidNode;
    public Command m_armTopNode;
    public Command m_switchMode;

    //NumPad Commands
    public Command m_armBottomLeft;
    public Command m_armBottomMid;
    public Command m_armBottomRight;
    public Command m_armMidLeft;
    public Command m_armMidMid;
    public Command m_armMidRight;
    public Command m_armTopLeft;
    public Command m_armTopMid;
    public Command m_armTopRight;

    //Intake Commands
    public Command m_intake;
    public Command m_lowerIntake;
    public Command m_raiseIntake;

    //Claw Commands
    public Command m_claw;
    public Command m_clawClose;
    public Command m_clawOpen;

    //AUTO COMMAND


  
    // Driver Controller
    public Command m_tagHoming;
    
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

    // FOR AUTO

    SendableChooser<String> m_chooser;
  // Driver Numpad
  public Joystick joy2 = new Joystick(1);
  public JoystickButton a_button2 = new JoystickButton(joy2, 1);
  public JoystickButton b_button2 = new JoystickButton(joy2, 2);
  public JoystickButton x_button2 = new JoystickButton(joy2, 3);
  public JoystickButton y_button2 = new JoystickButton(joy2, 4);
  public JoystickButton l_bump2 = new JoystickButton(joy2, 5);
  public JoystickButton r_bump2 = new JoystickButton(joy2, 6);
  public JoystickButton left_menu2 = new JoystickButton(joy2, 7);
  public JoystickButton right_menu2 = new JoystickButton(joy2, 8);
  public JoystickButton left_stick2 = new JoystickButton(joy2, 9);
  public JoystickButton right_stick2 = new JoystickButton(joy2, 10);

  public POVButton povUp2 = new POVButton(joy2, 0, 0);
  public POVButton povRight2 = new POVButton(joy2, 90, 0);
  public POVButton povDown2 = new POVButton(joy2, 180, 0);
  public POVButton povLeft2 = new POVButton(joy2, 270, 0); 
  
    private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);

    public enum CurrentMode {
      DRIVE,
      ARM
    }
  
    public CurrentMode mode = CurrentMode.DRIVE; 

    public RobotContainer() {

      this.m_raiseIntake = new ToggleUndeployIntake();
      this.m_lowerIntake = new ToggleDeployIntake();
      this.m_claw = new ClawToggle();
      this.m_clawClose = new ClawClose();
      this.m_clawOpen = new ClawOpen();


      swerveSubsystem.setDefaultCommand(new SwerveCommand(
          swerveSubsystem,
          () -> -joy.getRawAxis(OIConstants.kDriverYAxis),
          () -> joy.getRawAxis(OIConstants.kDriverXAxis),
          () -> joy.getRawAxis(OIConstants.kDriverRotAxis),
          () -> !joy.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

      if(this.mode == CurrentMode.DRIVE) {
        configureButtonBindings();
        configureButtonBindingsNumPad();
      } else {
        configureButtonBindingsArm();
      }

      configureSendableChooser();

    };

    private void configureButtonBindings() {

      //Claw Buttons
      //this.a_button.onTrue(m_claw);
      if(Robot.claw.isClosed()){
        this.a_button.onTrue(m_clawClose);
      } else {
        this.a_button.onTrue(m_clawOpen);
      }
      
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
    }

    private void configureButtonBindingsNumPad() {
      
      this.a_button2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armBottomLeft = new BottomLeft();
            m_armBottomLeft.schedule();
          }
        });
        this.b_button2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armBottomMid = new BottomMid();
            m_armBottomMid.schedule();
          }
        });
        this.x_button2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armBottomRight = new BottomRight();
            m_armBottomRight.schedule();
          }
        });
        this.y_button2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armMidLeft = new MidLeft();
            m_armMidLeft.schedule();
          }
        });
        this.povUp2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armMidMid = new MidMid();
            m_armMidMid.schedule();
          }
        });
        this.povRight2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armMidRight = new MidRight();
            m_armMidRight.schedule();
          }
        });
        this.povLeft2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armTopLeft = new TopLeft();
            m_armTopLeft.schedule();
          }
        });
        this.povDown2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armTopMid = new TopMid();
            m_armTopMid.schedule();
          }
        });
        this.r_bump2.onTrue(new InstantCommand() {
          public void initialize() {
            m_armTopRight = new TopRight();
            m_armTopRight.schedule();
          }
        });

    
    }

    // FOR AUTO CHOOSER

    public Command getAutonomousCommand(){
      return new AutoPath(swerveSubsystem);
    }


    public String getAutoPath(){
      return this.m_chooser.getSelected();
    }

    private void configureSendableChooser() {
      this.m_chooser = new SendableChooser<>();
      m_chooser.setDefaultOption("Bottom Charge", "Bottom Charge");
      m_chooser.addOption("Bottom", "Bottom");
      m_chooser.addOption("Middle Charge", "Middle Charge");
      m_chooser.addOption("Middle", "Middle");
      m_chooser.addOption("Top Charge", "Top Charge");
      m_chooser.addOption("Top", "Top");
      

      SmartDashboard.putData("Auto Path Selector", this.m_chooser);
    }

    private void configureButtonBindingsArm() {
      
    }
    
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

      //For April Tag Homing 
      this.left_stick.onTrue(new InstantCommand() {
        @Override
        public void initialize() {
          if(m_tagHoming.isScheduled()) {
            m_tagHoming.cancel();
          } 

          m_tagHoming = new AprilTagHomingCommand(swerveSubsystem, Robot.aprilTagHoming, Constants.PresetTranslations.kLeftPosition);
          m_tagHoming.schedule();
        }
      });
    }   
   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
