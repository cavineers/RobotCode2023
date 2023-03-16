package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.NumPad.BottomLeft;
import frc.robot.commands.NumPad.BottomMid;
import frc.robot.commands.NumPad.BottomRight;
import frc.robot.commands.NumPad.MidLeft;
import frc.robot.commands.NumPad.MidMid;
import frc.robot.commands.NumPad.MidRight;
import frc.robot.commands.NumPad.TopLeft;
import frc.robot.commands.NumPad.TopMid;
import frc.robot.commands.NumPad.TopRight;
import frc.robot.commands.ManualOverrideCommands.ExtendArm;
import frc.robot.commands.ManualOverrideCommands.HomeArm;
import frc.robot.commands.ManualOverrideCommands.LowerArm;
import frc.robot.commands.ManualOverrideCommands.RaiseArm;
import frc.robot.commands.ManualOverrideCommands.RetractArm;
import frc.robot.commands.ClawToggle;
import frc.robot.commands.SwerveCommand;
import frc.robot.commands.BalanceControlCommand;
import frc.robot.commands.ClawHoming;
import frc.robot.commands.ManualOverrideCommands.ClawOpen;
import frc.robot.commands.ManualOverrideCommands.ClawClose;

import frc.robot.commands.ToggleDeployIntake;
import frc.robot.commands.ToggleUndeployIntake;
import frc.robot.commands.SwerveHoming;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.SwerveDriveSubsystem;


public class RobotContainer  {
    
    public SendableChooser<Command> auto = new SendableChooser<Command>();

    public int intakeState = 1;

    //Swerve Subsystem
    private final SwerveDriveSubsystem swerveSubsystem;

    public Command m_autoCommand;

    //Arm Commands
    public Command m_armHome;
    public Command m_armRaise;
    public Command m_armLower;
    public Command m_armExtend;
    public Command m_armRetract;

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

    public SwerveHoming swerveHomingCommand;


    public RobotContainer() {

      swerveSubsystem = new SwerveDriveSubsystem();

      m_raiseIntake = new ToggleUndeployIntake();
      m_lowerIntake = new ToggleDeployIntake();
      m_armHome = new HomeArm();
      m_armRaise = new RaiseArm();
      m_armLower = new LowerArm();
      m_armExtend = new ExtendArm();
      m_armRetract = new RetractArm();
      m_armBottomLeft = new BottomLeft();
      m_armBottomMid = new BottomMid();
      m_armBottomRight = new BottomRight();
      m_armMidLeft = new MidLeft();
      m_armMidMid = new MidMid();
      m_armMidRight = new MidRight();
      m_armTopLeft = new TopLeft();
      m_armTopMid = new TopMid();
      m_armTopRight = new TopRight();

      m_clawClose = new ClawClose();
      m_clawOpen = new ClawOpen();

      swerveHomingCommand = new SwerveHoming(swerveSubsystem);

      swerveSubsystem.setDefaultCommand(new SwerveCommand(
          swerveSubsystem,
          () -> -joy.getRawAxis(OIConstants.kDriverYAxis),
          () -> joy.getRawAxis(OIConstants.kDriverXAxis),
          () -> joy.getRawAxis(OIConstants.kDriverRotAxis),
          () -> !joy.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

      configureButtonBindings();
      configureButtonBindingsNumPad();
    };

    private void configureButtonBindings() {


      //opens and closes claw
      this.povDown.onTrue(new ClawToggle());

      //claw manual buttons
      this.povLeft.onTrue(m_clawOpen);
      this.povLeft.onFalse(new InstantCommand(){
        public void initialize() {
          m_clawOpen.cancel();
        }
      });
      this.povRight.onTrue(m_clawClose);
      this.povRight.onFalse(new InstantCommand(){
        public void initialize() {
          m_clawClose.cancel();
        }
      });

      //deploys intake on button hold and undeploys on release
      this.l_bump.onTrue(new ToggleDeployIntake());
      this.l_bump.onFalse(new ToggleUndeployIntake());

      //Arm Buttons
      this.y_button.onTrue(m_armRaise);
      this.y_button.onFalse(new InstantCommand() {
        public void initialize() {
          m_armRaise.cancel();
        }
      });
      this.a_button.onTrue(m_armLower);
      this.a_button.onFalse(new InstantCommand() {
        public void initialize() {
          m_armLower.cancel();
        }
      });
      this.x_button.onTrue(m_armRetract);
      this.x_button.onFalse(new InstantCommand() {
        public void initialize() {
          m_armRetract.cancel();
        }
      });
      this.b_button.onTrue(m_armExtend);
      this.b_button.onFalse(new InstantCommand() {
        public void initialize() {
          m_armExtend.cancel();
        }
      });
      this.left_menu.onTrue(m_armHome);
      this.right_menu.onTrue(new ClawHoming());
    }

    private void configureButtonBindingsNumPad() {
      
      this.a_button2.onTrue(m_armBottomLeft);
      this.b_button2.onTrue(m_armBottomMid);
      this.x_button2.onTrue(m_armBottomRight);
      this.y_button2.onTrue(m_armMidLeft);
      this.povUp2.onTrue(m_armMidMid);
      this.povRight2.onTrue(m_armMidRight);
      this.povLeft2.onTrue(m_armTopLeft);
      this.povDown2.onTrue(m_armTopMid);
      this.r_bump2.onTrue(m_armTopRight);
    }

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };

    public Command getHomingCommand() {
      return this.swerveHomingCommand;
    }
}
