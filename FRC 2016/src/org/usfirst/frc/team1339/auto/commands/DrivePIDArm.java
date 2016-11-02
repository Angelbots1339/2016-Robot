package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;

/**
 *
 */
public class DrivePIDArm extends CommandBase {
	double setpoint;
	int counter;
	boolean done;

    public DrivePIDArm(double m_setpoint, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDArm);
    	setpoint = m_setpoint;
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    @SuppressWarnings("static-access")
	protected void initialize() {
    	HardwareAdapter.ArmPID.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	if(PIDArm.down){
    		PIDArm.PIDDriveArm();
    	}
    	if(HardwareAdapter.ArmPID.onTarget(10)){
    		counter++;
    	}
    	else{
    		counter = 0;
    	}
    	if(counter >= 5){
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	PIDArm.control(0);
    	PIDArm.downInit();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	PIDArm.control(0);
    	PIDArm.downInit();
    }
}
