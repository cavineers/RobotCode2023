package frc.robot;

import frc.robot.commands.IntakeSetCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final Joystick joystick1 = new Joystick(OIConstants.kDriverJoystickPort);

    public RobotContainer() {

        configureButtonBindings();

    }

    private void configureButtonBindings() {

        new JoystickButton(joystick1, OIConstants.kIntakeCloseButtonIdx)
            .whileActiveOnce(new IntakeSetCommand(intakeSubsystem, false));
    }
}
