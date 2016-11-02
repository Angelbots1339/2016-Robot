package org.usfirst.frc.team1339.auto.commands;

import org.usfirst.frc.team1339.commands.CommandBase;
import org.usfirst.frc.team1339.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDShortDrive extends CommandBase {

	double m_clicksLeft = 0;
	double m_clicksRight = 0;
	private int counter;
	@SuppressWarnings("unused")
	private boolean done;
	
    public PIDShortDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(PIDChassis);
    	
    	setTimeout(2);
    	SmartDashboard.putNumber("PID Drive Setpoint PLSPLSPLSPLS Hi", m_clicksLeft);
    }

    // Called just before this Command runs the first time
    @SuppressWarnings("static-access")
	protected void initialize() {
    	PIDArm.chillInit();
    	m_clicksLeft = HardwareAdapter.getDriveClicksUltra1();
    	m_clicksLeft += 900;
    	m_clicksRight = HardwareAdapter.getDriveClicksUltra2();
    	m_clicksRight += 900;
    	
    	HardwareAdapter.GyroPID.setPID(Constants.kAutoGyroKp, Constants.kAutoGyroKi, Constants.kAutoGyroKd);
    	HardwareAdapter.ShortRightDriveEncoderPID.setSetpoint(m_clicksRight+HardwareAdapter.getRightDriveEnc());
    	HardwareAdapter.ShortLeftDriveEncoderPID.setSetpoint(m_clicksLeft+HardwareAdapter.getLeftDriveEnc());
    	HardwareAdapter.GyroPID.setSetpoint(HardwareAdapter.kSpartanGyro.getAngle());
    	SmartDashboard.putNumber("PID Drive Setpoint Beginning", m_clicksLeft+HardwareAdapter.getRightDriveEnc());
    	SmartDashboard.putNumber("Drive PID clicks", m_clicksLeft);
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	SmartDashboard.putString("AutoCommand", "Enc");
    	PIDChassis.PIDShortDriveEncoder();
    	if (HardwareAdapter.ShortRightDriveEncoderPID.onTarget(50) || HardwareAdapter.ShortLeftDriveEncoderPID.onTarget(50)){
    		counter++;
    	}
    	if (counter >= 10){
    		done = true;
    		PIDArm.downFinished();
    	}
    	else{
    		done = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return PIDArm.getChill();
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
