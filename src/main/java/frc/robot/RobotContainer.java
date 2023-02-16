package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.Presets.MidPegTopShelf;
import frc.robot.commands.Presets.MidShelf;
import frc.robot.commands.Presets.TopPeg;
import frc.robot.commands.Presets.BottomNode;
import frc.robot.commands.Presets.HomeArm;
import frc.robot.commands.Presets.HumanPlayerPickup;
import frc.robot.commands.manualOverrideCommands.ExtendArm;
import frc.robot.commands.manualOverrideCommands.RaiseArm;
import frc.robot.commands.manualOverrideCommands.RetractArm;
import frc.robot.commands.manualOverrideCommands.SwitchMode;
import frc.robot.commands.manualOverrideCommands.LowerArm;
import frc.robot.subsystems.Arm;


public class RobotContainer {
    
    public Command m_autoCommand;
    public SendableChooser<Command> auto = new SendableChooser<Command>();

    public Command m_armChainMotorUp;
    public Command m_armChainMotorDown;
    public Command m_armExtendMotor;
    public Command m_armRetractMotor;
    public Command m_armHome;
    public Command m_armBottomNode;
    public Command m_armMidShelf;
    public Command m_armMidPegTopShelf;
    public Command m_armTopPeg;
    public Command m_armHumanPlayerPickup;
    public Command m_arm;

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
  
  public CurrentMode mode = CurrentMode.DRIVE; 
    
  private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);

    public RobotContainer() {
      if(this.mode == CurrentMode.DRIVE) {
        configureButtonBindings();
      } else {
        configureButtonBindingsArm();
      }

        configureButtonBindings();
        configureButtonBindingsArm();
    };

    private void configureButtonBindings() {

    this.right_menu.onTrue(new SwitchMode(this));
        
    this.a_button.onTrue(new InstantCommand() {
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
    this.povLeft.onTrue(new InstantCommand() {
      public void initialize() {
        m_armMidShelf = new MidShelf();
        m_armMidShelf.schedule();
      }
    });
    this.povRight.onTrue(new InstantCommand() {
      public void initialize() {
        m_armMidPegTopShelf = new MidPegTopShelf();
        m_armMidPegTopShelf.schedule();
      }
    });
    this.povUp.onTrue(new InstantCommand() {
      public void initialize() {
        m_armTopPeg = new TopPeg();
        m_armTopPeg.schedule();
      }
    });
    this.right_menu.onTrue(new InstantCommand() {
      public void initialize() {
        m_armHumanPlayerPickup = new HumanPlayerPickup();
        m_armHumanPlayerPickup.schedule();
      }
    });
    
    
    
    }
    private void configureButtonBindingsArm() {

    /*   this.right_menu.onTrue(new SwitchMode(this));

      this.povLeft.onTrue(new InstantCommand() {
        public void initialize() {
          m_armChainMotorDown = new LowerArm();
          m_armChainMotorDown.schedule();
        }
      });
      this.povRight.onTrue(new InstantCommand() {
        public void initialize() {
          m_armChainMotorUp = new RaiseArm();
          m_armChainMotorUp.schedule();
        }
      });
      this.povUp.onTrue(new InstantCommand() {
        public void initialize() {
          m_armExtendMotor = new ExtendArm();
          m_armExtendMotor.schedule();
        }
      });
      this.povDown.onTrue(new InstantCommand() {
        public void initialize() {
          m_armRetractMotor = new RetractArm();
          m_armRetractMotor.schedule();
        }
      });
       */
    }   
   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
