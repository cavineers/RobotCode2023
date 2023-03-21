package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Strip;
import frc.robot.subsystems.Strip.stripLEDState;

public class SetLED extends CommandBase {

    public SetLED() {
        this.addRequirements(Robot.strip);
    }

    @Override
    public void initialize() {
        Robot.strip.setStripState(Strip.stripLEDState.OCEANCOLOREDRAINBOW);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
