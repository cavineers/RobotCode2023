package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NtW_Tables extends SubsystemBase{
    
    
    public NetworkTable getTable(){
        return this.table;
    }
}