package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;

public class ClawReverseToggle extends CommandBase {

    private boolean isFinished = false;
    private boolean holding = false;

    public ClawReverseToggle() {
        this.addRequirements(Robot.claw);
    }

    // Set Motor State to REVERSED / OFF
    @Override
    public void initialize() {
        if (Robot.claw.getClawMotorState() == Claw.ClawMotorState.OFF) {
            Robot.claw.setMotorState(Claw.ClawMotorState.REVERSE);
        } else {
            Robot.claw.setMotorState(Claw.ClawMotorState.OFF);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}