package org.usfirst.frc.team1339.auto.commandgroup;

import org.usfirst.frc.team1339.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class AutomatedShootBall extends CommandBase {

	private int count;
	
    public AutomatedShootBall() {
        // Use requires() here to declare subsystem dependencies
    	requires(PIDIntake);
    	setTimeout(9);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	//if (!PIDChassis.visionRunning){
    	SmartDashboard.putString("IntakeCommand", "Intake");
    	if (HardwareAdapter.ShooterPID.onTarget(1.5)){
   			count++;
   		}
   		else {
   			count = 0;
   		}
    	if (isTimedOut() || count >= 10){
   			PIDIntake.intake(-1);
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
