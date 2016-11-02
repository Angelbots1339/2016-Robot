package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;

/**
 *
 */
public class IngestBallPID extends CommandBase {

	@SuppressWarnings("unused")
	private double counter = 0;
	private double setpoint;
	
    public IngestBallPID(double setPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDArm);
    	setpoint = setPoint;
    	//setTimeout(10);
    }

    // Called just before this Command runs the first time
    @SuppressWarnings("static-access")
	protected void initialize() {
    	HardwareAdapter.ArmPID.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	PIDArm.PIDDriveArm();
    	if(HardwareAdapter.ArmPID.onTarget(5)){
    		counter++;
    	}
    	else{
    		counter = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
