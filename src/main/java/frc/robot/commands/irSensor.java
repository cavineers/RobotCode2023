package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class irSensor extends CommandBase{
    private boolean isFinished = false;

    public irSensor(){
        this.addRequirements(Robot.intake);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(Robot.intake.getIRSensor() == true) {
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.DEPLOY);
        }if(Robot.intake.getIRSensor() == false){
            Robot.intake.setIntakeDropMotorState(Intake.IntakeDropMotorState.OFF);
            Robot.intake.getIntakeDropMotor().getEncoder().setPosition(0);
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}

    



