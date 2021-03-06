package org.usfirst.frc.team1339.auto.commandgroup;

import org.usfirst.frc.team1339.auto.commands.DefenseChill;
import org.usfirst.frc.team1339.auto.commands.DrivePIDArm;
import org.usfirst.frc.team1339.auto.commands.PIDDriveForwardEncoder;
import org.usfirst.frc.team1339.auto.commands.PIDShortDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutomatedPortcullis extends CommandGroup {
    
    public  AutomatedPortcullis() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new PIDShortDrive());
    	addParallel(new DrivePIDArm(-5000, 2));
    	addSequential(new DefenseChill(-4000));
    	addSequential(new PIDDriveForwardEncoder(2, -1000));
    }
}
