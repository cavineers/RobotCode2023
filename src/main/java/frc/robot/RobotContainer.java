package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RaiseArm;
import frc.robot.commands.RetractArm;
import frc.robot.commands.ExtendArm;
import frc.robot.commands.HomeArm;
import frc.robot.commands.LowerArm;
import frc.robot.subsystems.Arm;


public class RobotContainer {
    
    public Command m_autoCommand;
    public SendableChooser<Command> auto = new SendableChooser<Command>();

    public Command m_armChainMotorUp;
    public Command m_armChainMotorDown;
    public Command m_armExtendMotor;
    public Command m_armRetractMotor;
    public Command m_armHome;
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


    

    private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);

    public RobotContainer() {

        configureButtonBindings();

    };

    private void configureButtonBindings() {
        
    
    this.povRight.onTrue(new InstantCommand() {
      public void initialize() {
        m_armChainMotorDown = new LowerArm();
        m_armChainMotorDown.schedule();
      }
    });
    this.povRight.onFalse(new InstantCommand() {
      public void initialize() {
        m_armChainMotorDown.cancel();
      }
    });

    this.povLeft.onTrue(new InstantCommand() {
      public void initialize() {
        m_armChainMotorUp = new RaiseArm();
        m_armChainMotorUp.schedule();
      }
    });
    this.povLeft.onFalse(new InstantCommand() {
      public void initialize() {
        m_armChainMotorUp.cancel();
      }
    });
    this.povUp.onTrue(new InstantCommand() {
      public void initialize() {
        m_armExtendMotor = new ExtendArm();
        m_armExtendMotor.schedule();
      }
    });
    this.povUp.onFalse(new InstantCommand() {
      public void initialize() {
        m_armExtendMotor.cancel();
      }
    });
    this.povDown.onTrue(new InstantCommand() {
      public void initialize() {
        m_armRetractMotor = new RetractArm();
        m_armRetractMotor.schedule();
      }
    });
    this.povDown.onFalse(new InstantCommand() {
      public void initialize() {
        m_armRetractMotor.cancel();
      }
    });
    this.a_button.onTrue(new InstantCommand() {
      public void initialize() {
        m_armHome = new HomeArm();
        m_armHome.schedule();
      }
    });
    
    }   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
