package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;
import org.usfirst.frc.team1339.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveForwardEncoder extends CommandBase {

	double m_clicks;
	private int counter;
	private boolean done;
	
    public PIDDriveForwardEncoder(double timeout, double clicks) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDChassis);
    	
    	m_clicks = clicks;
    	
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    @SuppressWarnings("static-access")
	protected void initialize() {
    	HardwareAdapter.GyroPID.setPID(Constants.kAutoGyroKp, Constants.kAutoGyroKi, Constants.kAutoGyroKd);
    	HardwareAdapter.RightDriveEncoderPID.setSetpoint(m_clicks+HardwareAdapter.getRightDriveEnc());
    	HardwareAdapter.LeftDriveEncoderPID.setSetpoint(m_clicks+HardwareAdapter.getLeftDriveEnc());
    	HardwareAdapter.GyroPID.setSetpoint(HardwareAdapter.kSpartanGyro.getAngle());
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	SmartDashboard.putString("AutoCommand", "Enc");
    	PIDChassis.PIDDriveEncoder();
    	if (HardwareAdapter.RightDriveEncoderPID.onTarget(50) || HardwareAdapter.LeftDriveEncoderPID.onTarget(50)){
    		counter++;
    	}
    	if (counter >= 5){
    		done = true;
    	}
    	else{
    		done = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	PIDChassis.driveForward(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	PIDChassis.driveForward(0);
    }
}
