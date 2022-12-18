package frc.robot;

import frc.robot.commands.IntakeSetCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  
    private final SwerveSubsystem intakeSubsystem = new SwerveSubsystem();
    private final Joystick joystick1 = new Joystick(OIConstants.kDriverJoystickPort);

    public RobotContainer() {

        configureButtonBindings();

    }

    private void configureButtonBindings() {

        new JoystickButton(joystick1, OIConstants.kIntakeCloseButtonIdx)
            .whileActiveOnce(new IntakeSetCommand(intakeSubsystem, false));
    }
}
