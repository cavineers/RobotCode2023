package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleLowerIntake;
import frc.robot.commands.ToggleRaiseIntake;

public class RobotContainer {

    public Command m_intake;
    public Command m_lowerIntake;
    public Command m_raiseIntake;
    
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

      this.m_intake = new ToggleIntake();
      this.m_raiseIntake = new ToggleRaiseIntake();
      this.m_lowerIntake = new ToggleLowerIntake();
      configureButtonBindings();

    };
    
    //TOGGLE INTAKE ON AND OFF
    private void configureButtonBindings() {
       this.r_bump.onTrue(new InstantCommand() { 
        @Override
         public void initialize() {
          m_intake = new ToggleIntake();
          m_intake.schedule();
        }
       }
      );

      this.r_bump.onFalse(new InstantCommand() {
        @Override
         public void initialize() {
          if(m_intake.isScheduled()) {
            m_intake.cancel();
          }
        }
       }
      );

      //LOWERS AND RAISES INTAKE
      this.l_bump.onTrue(new InstantCommand() {
        @Override
         public void initialize() {
          if(m_raiseIntake.isScheduled()) {
            m_raiseIntake.cancel();
          }
          m_lowerIntake = new ToggleLowerIntake();
          m_lowerIntake.schedule();
         }
        });

      this.l_bump.whileTrue(new InstantCommand() {
        @Override
        public void initialize() {
          if(Robot.intake.getIntakeSwitch() == true) {
            m_raiseIntake.cancel();
          }
        }
      });

      this.l_bump.onFalse(new InstantCommand() {
        @Override
         public void initialize() {
            if(m_lowerIntake.isScheduled()) {
            m_lowerIntake.cancel();
            } 
           m_raiseIntake = new ToggleRaiseIntake();
           m_raiseIntake.schedule();
        }
      }); 
    }   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}

/*package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleLowerIntake;
import frc.robot.commands.ToggleRaiseIntake;

public class RobotContainer {

    public Command m_intake;
    public Command m_lowerIntake;
    public Command m_raiseIntake;
    
    public Joystick joy = new Joystick(0);
    public XboxController a_button = new XboxController(1);
    public XboxController b_button = new XboxController(2);
    public XboxController x_button = new XboxController(3);
    public XboxController y_button = new XboxController(4);
    public XboxController l_bump = new XboxController(5);
    public XboxController r_bump = new XboxController(6);
    public XboxController left_menu = new XboxController(7);
    public XboxController right_menu = new XboxController(8);
    public XboxController left_stick = new XboxController(9);
    public XboxController right_stick = new XboxController(10);
  
    public POVButton povUp = new POVButton(joy, 0, 0);
    public POVButton povRight = new POVButton(joy, 90, 0);
    public POVButton povDown = new POVButton(joy, 180, 0);
    public POVButton povLeft = new POVButton(joy, 270, 0); 

    private Joystick m_joy = new Joystick(OIConstants.kDriverJoystickPort);

    public POVButton m_povUp = new POVButton(m_joy, 0, 0);

    public RobotContainer() {

      this.m_intake = new ToggleIntake();
      this.m_raiseIntake = new ToggleRaiseIntake();
      this.m_lowerIntake = new ToggleLowerIntake();
      configureButtonBindings();

    };
    
    //TOGGLE INTAKE ON AND OFF
    private void configureButtonBindings() {
       if(this.r_bump.getRightBumper() == true) {
          m_intake = new ToggleIntake();
          m_intake.schedule();
       }
       if(this.r_bump.getRightBumper() == false) {
        if(m_intake.isScheduled()) {
          m_intake.cancel();
        }
     }

     //LOWERS AND RAISES INTAKE
    if(this.l_bump.getLeftBumper() == true) {
      if(m_raiseIntake.isScheduled()) {
        m_raiseIntake.cancel();
      }
      m_lowerIntake = new ToggleLowerIntake();
      m_lowerIntake.schedule();
     }
     if(this.l_bump.getLeftBumper() == false) {
      if(m_raiseIntake.isScheduled()) {
            m_raiseIntake.cancel();
          }
          m_lowerIntake = new ToggleLowerIntake();
          m_lowerIntake.schedule();
     }

      this.l_bump.whileFalse(new InstantCommand() {
        @Override
        public void initialize() {
          if(Robot.intake.getIntakeSwitch() == true) {
            m_raiseIntake.cancel();
          }
        }
      });

      this.l_bump.onFalse(new InstantCommand() {
        @Override
         public void initialize() {
            if(m_lowerIntake.isScheduled()) {
            m_lowerIntake.cancel();
            } 
           m_raiseIntake = new ToggleRaiseIntake();
           m_raiseIntake.schedule();
        }
      }); 
    }   

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };
}
 */
