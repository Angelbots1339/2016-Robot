package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;

/**
 *
 */
public class ArmDown extends CommandBase {
	
    public ArmDown(double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDArm);
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	PIDArm.control(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    @SuppressWarnings("static-access")
	protected boolean isFinished() {
        return isTimedOut() || !HardwareAdapter.kLeftHallEffect.get() || !HardwareAdapter.kRightHallEffect.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	PIDArm.control(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	PIDArm.control(0);
    }
}
