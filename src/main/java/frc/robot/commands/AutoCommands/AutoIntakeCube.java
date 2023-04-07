package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class AutoIntakeCube extends CommandBase {
    
    boolean isFinished;

    private double m_timestamp;
    
    public AutoIntakeCube() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.ON);
        //this.startTime = Timer.getFPGATimestamp();
        this.isFinished = false;
    }

    @Override
    public void execute(){
    }


    @Override
    public void end(boolean interrupted) {
        Robot.intake.setIntakeMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Robot.intake.getIntakeIR() == true) {
            this.isFinished = true;
        }
        return this.isFinished;
    }
}