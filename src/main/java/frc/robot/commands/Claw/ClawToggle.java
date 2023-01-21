package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;

public class ClawToggle extends CommandBase {

    private boolean isFinished = false;
    private boolean holding = false;

    public ClawToggle() {
        this.addRequirements(Robot.claw);
    }
} 