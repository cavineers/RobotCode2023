package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;

public class ClawReverseToggle extends CommandBase {

    private boolean isFinished = false;
    private boolean holding = false;

    public ClawReverseToggle() {
        this.addRequirements(Robot.claw);
    }
} 