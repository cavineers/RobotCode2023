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
import frc.robot.commands.NumPad.ArmHybrid;
import frc.robot.commands.NumPad.ArmMidShelf;
import frc.robot.commands.NumPad.ArmMidPeg;
import frc.robot.commands.NumPad.ArmTopShelf;
import frc.robot.commands.NumPad.ArmTopPeg;
import frc.robot.commands.ManualOverrideCommands.ExtendArm;
import frc.robot.commands.ManualOverrideCommands.LowerArm;
import frc.robot.commands.ManualOverrideCommands.RaiseArm;
import frc.robot.commands.ManualOverrideCommands.RetractArm;
import frc.robot.commands.ClawToggle;
import frc.robot.commands.SwerveCommand;
import frc.robot.commands.BalanceControlCommand;
import frc.robot.commands.NewBalanceCommand;
import frc.robot.commands.ClawHoming;
import frc.robot.commands.ManualOverrideCommands.ClawOpen;
import frc.robot.commands.ManualOverrideCommands.ClawClose;

import frc.robot.commands.IntakeCube;
import frc.robot.commands.FlushCube;
import frc.robot.commands.AutoArmCommands.HomeArm;
import frc.robot.commands.AutoCommands.ExitCommunity;
import frc.robot.commands.AutoCommands.ChargeStationCube;
import frc.robot.commands.AutoCommands.DoublePieceNoBump;
import frc.robot.commands.AutoCommands.TemplateAuto;
import frc.robot.commands.AutoArmCommands.ArmRestPosition;
import frc.robot.commands.AutoArmCommands.ArmAtBumperCommand;
import frc.robot.commands.AutoArmCommands.ArmDoubleSubStation;

import frc.robot.commands.SwerveHoming;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.SwerveDriveSubsystem;


public class RobotContainer  {
    

    public int intakeState = 1;

    //Swerve Subsystem
    private final SwerveDriveSubsystem swerveSubsystem;

    public Command m_autoCommand;

    //Arm Commands
    public Command m_armHome;
    public Command m_armSubStation;
    public Command m_armRestPosition;
    public Command m_armBumperPosition;
    public Command m_armRaise;
    public Command m_armLower;
    public Command m_armExtend;
    public Command m_armRetract;
    public Command m_armIntake;

    //NumPad Commands
    public Command m_armHybrid;
    public Command m_armMidShelf;
    public Command m_armMidPeg;
    public Command m_armTopShelf;
    public Command m_armTopPeg;

    //April Tag Presets
    
    public Command m_aprilTagLeft;
    public Command m_aprilTagCenter;
    public Command m_aprilTagRight;

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

  SendableChooser<Command> m_chooser;

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

    public Command intake;
    public Command outtake;


    public RobotContainer() {

      swerveSubsystem = new SwerveDriveSubsystem();

      m_raiseIntake = new FlushCube();
      m_lowerIntake = new IntakeCube();
      m_armHome = new HomeArm();
      m_armSubStation = new ArmDoubleSubStation();
      m_armRestPosition = new ArmRestPosition();
      m_armBumperPosition = new ArmAtBumperCommand();
      m_armRaise = new RaiseArm();
      m_armLower = new LowerArm();
      m_armExtend = new ExtendArm();
      m_armRetract = new RetractArm();
      m_armHybrid = new ArmHybrid();
      m_armMidShelf = new ArmMidShelf();
      m_armMidPeg = new ArmMidPeg();
      m_armTopShelf = new ArmTopShelf();
      m_armTopPeg = new ArmTopPeg();

      m_clawClose = new ClawClose();
      m_clawOpen = new ClawOpen();

      intake = new IntakeCube();
      outtake = new FlushCube();
    


      swerveHomingCommand = new SwerveHoming(swerveSubsystem);

      swerveSubsystem.setDefaultCommand(new SwerveCommand(
          swerveSubsystem,
          () -> -joy.getRawAxis(OIConstants.kDriverYAxis),
          () -> joy.getRawAxis(OIConstants.kDriverXAxis),
          () -> joy.getRawAxis(OIConstants.kDriverRotAxis),
          () -> !joy.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

      configureButtonBindings();
      
      configureSendableChooser();
    };

    private void configureButtonBindings() {


      //opens and closes claw
      this.povDown.onTrue(new ClawToggle());

      //zeros heading
      this.povUp.onTrue(new InstantCommand() {
        public void initialize() {
          swerveSubsystem.zeroHeading();
        }
      });
      
      this.right_stick.onTrue(new NewBalanceCommand(swerveSubsystem));

      //claw manual buttons
      this.povRight.onTrue(m_clawOpen);
      this.povRight.onFalse(new InstantCommand(){
        public void initialize() {
          m_clawOpen.cancel();
        }
      });
      this.povLeft.onTrue(m_clawClose);
      this.povLeft.onFalse(new InstantCommand(){
        public void initialize() {
          m_clawClose.cancel();
        }
      });

      // deploys intake on button hold and undeploys on release
      this.l_bump.onTrue(intake);
      this.l_bump.onFalse(new InstantCommand(){
        public void initialize(){
          intake.cancel();
        }
      });

      this.r_bump.onTrue(outtake);
      this.r_bump.onFalse(new InstantCommand(){
        public void initialize(){
          outtake.cancel();
        }
      });

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
      this.l_bump2.onTrue(m_armRestPosition);
      this.left_stick2.onTrue(m_armSubStation);

      
      // this.l_bump.onTrue(m_armIntake);
      this.right_menu.onTrue(new ClawHoming());

      this.b_button2.onTrue(m_armHybrid);
      this.povUp2.onTrue(m_armMidShelf);
      this.povRight2.onTrue(m_armMidPeg);
      this.povDown2.onTrue(m_armTopShelf);
      this.r_bump2.onTrue(m_armTopPeg);
      this.a_button2.onTrue(m_armBumperPosition);
    }


    // FOR AUTO CHOOSER

  


    public Command getAutoCommand(){
      return this.m_chooser.getSelected();
    }

    private void configureSendableChooser() {
      this.m_chooser = new SendableChooser<Command>();
     //m_chooser.setDefaultOption("Bottom Charge", "Bottom Charge");
      m_chooser.setDefaultOption("Exit Community", new ExitCommunity(swerveSubsystem));
      m_chooser.addOption("Charge Station Cube", new ChargeStationCube(swerveSubsystem));
      m_chooser.addOption("2 Piece NO BUMP", new DoublePieceNoBump(swerveSubsystem));
      

      SmartDashboard.putData("Auto Path Selector", this.m_chooser);
    }

    

    public double getJoystickRawAxis(int id) {
        return -m_joy.getRawAxis(id);
    };

    public Command getHomingCommand() {
      return this.swerveHomingCommand;
    }

    public SwerveDriveSubsystem getSwerveSubsystem() {
      return this.swerveSubsystem;
    }
}
