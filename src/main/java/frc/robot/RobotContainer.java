package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.ControllerArmCommands.ExtendArm;
import frc.robot.commands.ControllerArmCommands.HomeArm;
import frc.robot.commands.ControllerArmCommands.LowerArm;
import frc.robot.commands.ControllerArmCommands.RaiseArm;
import frc.robot.commands.ControllerArmCommands.RetractArm;
import frc.robot.commands.NumPad.BottomLeft;
import frc.robot.commands.NumPad.BottomMid;
import frc.robot.commands.NumPad.BottomRight;
import frc.robot.commands.NumPad.MidLeft;
import frc.robot.commands.NumPad.MidMid;
import frc.robot.commands.NumPad.MidRight;
import frc.robot.commands.NumPad.TopLeft;
import frc.robot.commands.NumPad.TopMid;
import frc.robot.commands.NumPad.TopRight;

public class RobotContainer  {
    
    public Command m_autoCommand;
    public SendableChooser<Command> auto = new SendableChooser<Command>();

    public Command m_armBottomLeft;
    public Command m_armBottomMid;
    public Command m_armBottomRight;
    public Command m_armMidLeft;
    public Command m_armMidMid;
    public Command m_armMidRight;
    public Command m_armTopLeft;
    public Command m_armTopMid;
    public Command m_armTopRight;
    public Command m_armHome;
    public Command m_armRaise;
    public Command m_armLower;
    public Command m_armExtend;
    public Command m_armRetract;

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

  // Driver Numpad
  public Joystick joy2 = new Joystick(1);
  public JoystickButton a_button2 = new JoystickButton(joy2, 1);
  public JoystickButton b_button2 = new JoystickButton(joy2, 2);
  public JoystickButton x_button2 = new JoystickButton(joy2, 3);
  public JoystickButton y_button2 = new JoystickButton(joy2, 4);
  public JoystickButton r_bump2 = new JoystickButton(joy2, 6);
  public POVButton povUp2 = new POVButton(joy2, 0, 0);
  public POVButton povRight2 = new POVButton(joy2, 90, 0);
  public POVButton povDown2 = new POVButton(joy2, 180, 0);
  public POVButton povLeft2 = new POVButton(joy2, 270, 0); 

  private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);

    public RobotContainer() {

      m_armHome = new HomeArm();

      configureButtonBindings();
      configureButtonBindingsNumPad();
  }

    private void configureButtonBindings(){
      this.povUp.onTrue(new InstantCommand() {
        public void initialize() {
          m_armRaise = new RaiseArm();
          m_armRaise.schedule();
        }
      });
      this.povUp.onFalse(new InstantCommand() {
        public void initialize() {
          m_armRaise.cancel();
        }
      });
      this.povDown.onTrue(new InstantCommand() {
        public void initialize() {
          m_armLower = new LowerArm();
          m_armLower.schedule();
        }
      });
      this.povDown.onFalse(new InstantCommand() {
        public void initialize() {
          m_armLower.cancel();
        }
      });
      this.povLeft.onTrue(new InstantCommand() {
        public void initialize() {
          m_armRetract = new RetractArm();
          m_armRetract.schedule();
        }
      });
      this.povLeft.onFalse(new InstantCommand() {
        public void initialize() {
          m_armRetract.cancel();
        }
      });
      this.povRight.onTrue(new InstantCommand() {
        public void initialize() {
          m_armExtend = new ExtendArm();
          m_armExtend.schedule();
        }
      });
      this.povRight.onFalse(new InstantCommand() {
        public void initialize() {
          m_armExtend.cancel();
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
    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
