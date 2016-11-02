package org.usfirst.frc.team1339.robot;

import org.usfirst.frc.team1339.auto.commandgroup.AutomatedCheval;
import org.usfirst.frc.team1339.auto.commandgroup.AutomatedShootBall;
import org.usfirst.frc.team1339.auto.commandgroup.CorrectVision;
import org.usfirst.frc.team1339.auto.commands.PIDDriveForwardEncoder;
import org.usfirst.frc.team1339.auto.commands.PIDDriveShooter;
import org.usfirst.frc.team1339.commands.FlywheelBackwards;
import org.usfirst.frc.team1339.lib.utils.SynchronousPID;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class HardwareAdapter {
	//Motors
	public static CANTalon kLeverMotor = new CANTalon(Constants.kLeverMotor);
	public static CANTalon kAxleMotor = new CANTalon(Constants.kAxleMotor);
	public static CANTalon kLeftMotorOne = new CANTalon(Constants.kLeftMotorOne);
	public static CANTalon kLeftMotorTwo = new CANTalon(Constants.kLeftMotorTwo);
	public static CANTalon kRightMotorOne = new CANTalon(Constants.kRightMotorOne);
	public static CANTalon kRightMotorTwo = new CANTalon(Constants.kRightMotorTwo);
	public static CANTalon kShooterMotorOne = new CANTalon(Constants.kShooterMotorOne);
	public static CANTalon kShooterMotorTwo = new CANTalon(Constants.kShooterMotorTwo);
	public static CANTalon kRollerMotor = new CANTalon(Constants.kLeverMotor);
	public static CANTalon kLiftMotor = new CANTalon(Constants.kLiftMotor);
	//Encoders
	public static Encoder kRightDriveEncoder = new Encoder(
			Constants.kRightDriveAEncoder , Constants.kRightDriveBEncoder);
	public static Encoder kLeftDriveEncoder = new Encoder(
			Constants.kLeftDriveAEncoder , Constants.kLeftDriveBEncoder);
	//Ultrasonic
	public static Ultrasonic kUltrasonicOne = new Ultrasonic(
			Constants.kUltrasonicOnePing, Constants.kUltrasonicOneEcho);
	public static Ultrasonic kUltrasonicTwo = new Ultrasonic(
			Constants.kUltrasonicTwoPing, Constants.kUltrasonicTwoEcho);
	public static Ultrasonic kIntakeUltrasonic = new Ultrasonic(
			Constants.kIntakeUltrasonicPing, Constants.kIntakeUltrasonicEcho);
	//Halleffects
	public static DigitalInput kLeftHallEffect = new DigitalInput(Constants.kLeftHallEffect);
	public static DigitalInput kRightHallEffect = new DigitalInput(Constants.kRightHallEffect);
	//Limit Switch
	public static DigitalInput kLimitSwitch = new DigitalInput(Constants.kLimitSwitch);
	//Gyro + Accelerometer
	public static AnalogGyro kGyro = new AnalogGyro(Constants.kGyro);
	public static ADXRS450_Gyro kSpartanGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	public static Accelerometer kAccelerometer = new BuiltInAccelerometer();
	//Arduino Pins
	public static DigitalOutput kArduinoOne = new DigitalOutput(Constants.kArduinoOne);
	public static DigitalOutput kArduinoTwo = new DigitalOutput(Constants.kArduinoTwo);
	public static DigitalOutput kArduinoThree = new DigitalOutput(Constants.kArduinoThree);
	//Spike
	public static Relay kLight = new Relay(Constants.kLightPort);
	//Joysticks
	private Joystick kRazerStick = new Joystick(Constants.kRazerPort);
	private Joystick kMadCatz = new Joystick(Constants.kMadCatzPort);
	//Razer Joystick Buttons
	private JoystickButton kAButton = new JoystickButton(kRazerStick , 1);
	private JoystickButton kBButton = new JoystickButton(kRazerStick , 2);
	private JoystickButton kXButton = new JoystickButton(kRazerStick , 3);
	private JoystickButton kYButton = new JoystickButton(kRazerStick , 4);
	private JoystickButton kLeftBumper = new JoystickButton(kRazerStick , 5);
	private JoystickButton kRightBumper = new JoystickButton(kRazerStick , 6);
	private JoystickButton kLeftStickButton = new JoystickButton(kRazerStick , 9);
	private JoystickButton kRightStickButton = new JoystickButton(kRazerStick , 10);
	//MadCatz Joystick Buttons
	private JoystickButton kOneButton = new JoystickButton(kMadCatz , 1);
	private JoystickButton kTwoButton = new JoystickButton(kMadCatz , 2);
	private JoystickButton kThreeButton = new JoystickButton(kMadCatz , 3);
	private JoystickButton kFourButton = new JoystickButton(kMadCatz , 4);
	private JoystickButton kFiveButton = new JoystickButton(kMadCatz , 5);
	private JoystickButton kSixButton = new JoystickButton(kMadCatz , 6);
	private JoystickButton kSevenButton = new JoystickButton(kMadCatz , 7);
	//PID Loops
	public static SynchronousPID LeftDriveEncoderPID = new SynchronousPID(
			Constants.kDriveKp , Constants.kDriveKi , Constants.kDriveKd);
	public static SynchronousPID ShortLeftDriveEncoderPID = new SynchronousPID(
			Constants.kShortDriveKp , Constants.kShortDriveKi , Constants.kShortDriveKd);
	public static SynchronousPID RightDriveEncoderPID = new SynchronousPID(
			Constants.kDriveKp , Constants.kDriveKi , Constants.kDriveKd);
	public static SynchronousPID ShortRightDriveEncoderPID = new SynchronousPID(
			Constants.kShortDriveKp , Constants.kShortDriveKi , Constants.kShortDriveKd);
	public static SynchronousPID TurnGyroPID = new SynchronousPID(
			Constants.kTurnKp , Constants.kTurnKi , Constants.kTurnKd);
	public static SynchronousPID ArmPID = new SynchronousPID(
			Constants.kArmKp , Constants.kArmKi , Constants.kArmKd);
	public static SynchronousPID ShooterPID = new SynchronousPID(
			Constants.kShooterKp , Constants.kShooterKi , Constants.kShooterKd);
	public static SynchronousPID AccelPID = new SynchronousPID(
			Constants.kAccelKp, Constants.kAccelKi, Constants.kAccelKd);
	public static SynchronousPID JerkPID = new SynchronousPID(
			Constants.kJerkKp, Constants.kJerkKi, Constants.kJerkKd);
	public static SynchronousPID GyroPID = new SynchronousPID(
			Constants.kGyroKp , Constants.kGyroKi , Constants.kGyroKd);
	
	public HardwareAdapter(){
		kShooterMotorOne.reverseSensor(true);
        HardwareAdapter.kUltrasonicOne.setAutomaticMode(true);
        HardwareAdapter.kUltrasonicTwo.setAutomaticMode(true);
        HardwareAdapter.kIntakeUltrasonic.setAutomaticMode(true);
        
        kGyro.calibrate();
		//kYButton.whileHeld(new IngestGroup());
		kAButton.whenPressed(new AutomatedCheval());
		//kBButton.whenPressed(new ControlFlashlight(true));
		//kBButton.whenReleased(new ControlFlashlight(false));
		kYButton.whenPressed(new PIDDriveForwardEncoder(3, -6735));
		//kBButton.whenPressed(new PIDShortDrive(3, getDriveClicks()));
		
		kOneButton.whileHeld(new AutomatedShootBall());
		kTwoButton.whenPressed(new CorrectVision(3, false));
		kSevenButton.whileHeld(new PIDDriveShooter(80, false));
		kThreeButton.whileHeld(new FlywheelBackwards());
		
		kShooterMotorOne.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		kLeverMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		HardwareAdapter.kRightMotorOne.setPID(0, 0, 0);
		HardwareAdapter.kRightMotorTwo.setPID(0, 0, 0);		
	}

	//Get functions
	public Joystick getRazerStick(){
		return kRazerStick;
	}
	public boolean getAButton(){
		return kAButton.get();
	}
	public boolean getBButton(){
		return kBButton.get();
	}
	public boolean getXButton(){
		return kXButton.get();
	}
	public boolean getYButton(){
		return kYButton.get();
	}
	public boolean getLeftBumper(){
		return kLeftBumper.get();
	}
	public boolean getRightBumper(){
		return kRightBumper.get();
	}
	public boolean getLeftStickButton(){
		return kLeftStickButton.get();
	}
	public boolean getRightStickButton(){
		return kRightStickButton.get();
	}
	public Joystick getMadCatz(){
		return kMadCatz;
	}
	public boolean get1Button(){
		return kOneButton.get();
	}
	public boolean get2Button(){
		return kTwoButton.get();
	}
	public boolean get3Button(){
		return kThreeButton.get();
	}
	public boolean get4Button(){
		return kFourButton.get();
	}
	public boolean get5Button(){
		return kFiveButton.get();
	}
	public boolean get6Button(){
		return kSixButton.get();
	}
	public boolean get7Button(){
		return kSevenButton.get();
	}
	
	public static double getLeftDriveEnc(){
		return (kLeftDriveEncoder.get());
	}
	public static double getRightDriveEnc(){
		return (kRightDriveEncoder.get() * -1);
	}
	public static double getUltraAverage(){
		return ((kUltrasonicOne.getRangeInches() + kUltrasonicTwo.getRangeInches())/2);
	}
	public static double getDriveClicksUltra1(){
		double ultra = kUltrasonicOne.getRangeInches() * -1;
		ultra -= 5;
		double clicks = ultra * 32.25;
		return clicks;
	}

	public static double getDriveClicksUltra2(){
		double ultra = kUltrasonicTwo.getRangeInches() * -1;
		ultra -= 5;
		double clicks = ultra * 32.25;
		return clicks;
	}
	
	
	//Reset Sensors
	public static void resetDriveEncoder(){
		kLeftDriveEncoder.reset();
		kRightDriveEncoder.reset();
	}
	public void resetGyro(){
		kGyro.reset();
		kSpartanGyro.reset();
	}
}
