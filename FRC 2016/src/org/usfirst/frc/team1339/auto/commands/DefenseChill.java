package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;

/**
 *
 */
public class DefenseChill extends CommandBase {
	boolean done = false;
	private double m_setpoint;

    public DefenseChill(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	if(HardwareAdapter.kLeverMotor.get() >= m_setpoint){
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
