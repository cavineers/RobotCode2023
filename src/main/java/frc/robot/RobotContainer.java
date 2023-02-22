package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Arm;
import frc.robot.commands.NumPad.BottomLeft;
import frc.robot.commands.NumPad.BottomMid;
import frc.robot.commands.NumPad.BottomRight;
import frc.robot.commands.NumPad.MidLeft;
import frc.robot.commands.NumPad.MidMid;
import frc.robot.commands.NumPad.MidRight;
import frc.robot.commands.NumPad.TopLeft;
import frc.robot.commands.NumPad.TopMid;
import frc.robot.commands.NumPad.TopRight;
import java.awt.event.KeyEvent;

public class RobotContainer {
    public String node;

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
  
  
    
  private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);
    
    public CurrentMode mode = CurrentMode.DRIVE; 

    public RobotContainer() {
      if(this.mode == CurrentMode.DRIVE) {
        configureButtonBindings();
      } 
    };
  
    public void keyPressed(KeyEvent e) {
		
    int code = e.getKeyCode();
      switch (code) {
        case 49: //1
        node = "bottomLeft";
        break;
        case 50: //2
        node = "bottomMid";
        break;
        case 51: //3
        node = "bottomRight";
        break;
        case 52: //4
        node = "midLeft";
        break;
        case 53: //5
        node = "midMid";
        break;
        case 54: //6
        node = "midRight";
        break;
        case 55: //7
        node = "topLeft";
        break;
        case 56: //8
        node = "topMid";
        break;
        case 57: //9
        node = "topRight";
        break;
        default:
        break;
        //wrong button idiot
      }

    }

    private void configureButtonBindings() {
      switch (node) {
        case"bottomLeft":
        new InstantCommand() {
          public void initialize() {
            m_armBottomLeft = new BottomLeft();
            m_armBottomLeft.schedule();
          }
        };
        break;
        case"bottomMid":
        break;
        case"bottomRight":
        break;
        case"midLeft":
        break;
        case"midMid":
        break;
        case"midRight":
        break;
        case"topLeft":
        break;
        case"topMid":
        break;
        case"topRight":
        break;
      }
    }


    
   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
