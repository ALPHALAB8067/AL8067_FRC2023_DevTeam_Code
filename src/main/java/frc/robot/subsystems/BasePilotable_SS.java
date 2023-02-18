// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BasePilotable_SS extends SubsystemBase {
  /** Creates a new BasePilotable_SS. */


  private final WPI_TalonSRX m_RightFrontMotor1;
  private final WPI_VictorSPX m_RightBackMotor2;
  private final WPI_TalonSRX m_LeftFrontMotor3;
  private final WPI_VictorSPX m_LeftBackMotor4;



  private final MotorControllerGroup m_LeftMotorsGroup;
  private final MotorControllerGroup m_RightMotorsGroup;
  private final DifferentialDrive m_differentialDrive;


  private final AHRS m_gyroNavX;


  public BasePilotable_SS() {

    m_RightFrontMotor1= new WPI_TalonSRX(Constants.BP_Constants.kLeftFrontMotorPort);
    m_RightBackMotor2 =new WPI_VictorSPX(Constants.BP_Constants.kLeftBackMotorPort);
    m_LeftFrontMotor3= new WPI_TalonSRX(Constants.BP_Constants.kRightFrontMotorPort);
    m_LeftBackMotor4 =new WPI_VictorSPX(Constants.BP_Constants.kRightBackMotorPort);

    m_LeftMotorsGroup = new MotorControllerGroup(m_LeftFrontMotor3, m_LeftBackMotor4);
    m_RightMotorsGroup = new MotorControllerGroup(m_RightFrontMotor1, m_RightBackMotor2);

    m_differentialDrive = new DifferentialDrive(m_LeftMotorsGroup, m_RightMotorsGroup);

    m_RightFrontMotor1.configFactoryDefault();
    m_LeftFrontMotor3.configFactoryDefault();

    


    m_RightFrontMotor1.setInverted(true);
    m_RightBackMotor2.setInverted(true);
    m_LeftFrontMotor3.setInverted(false);
    m_LeftBackMotor4.setInverted(false);


    m_LeftBackMotor4.follow(m_LeftFrontMotor3);
    m_RightBackMotor2.follow(m_RightFrontMotor1);


    //assurer que la lecture des encodeurs est en phase ( même orientation que les encodeurs)
    m_RightFrontMotor1.setSensorPhase(true);
    m_LeftFrontMotor3.setSensorPhase(true);
  

// mettre à off la mesure de sécurité des moteurs watchdog 
  //- sinon il génère des erreurs inutiles sur la console
    m_differentialDrive.setSafetyEnabled(false);
    
    //deadband pour le joystick - enlever si jamais bug 
    m_differentialDrive.setDeadband(0.05);
   
    m_RightFrontMotor1.setSafetyEnabled(false);
    m_RightBackMotor2.setSafetyEnabled(false);
    m_LeftFrontMotor3.setSafetyEnabled(false);
    m_LeftBackMotor4.setSafetyEnabled(false);

    // configuration des encodeurs de roues CTRE Mag EncoderSRX 

    m_RightFrontMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m_LeftFrontMotor3.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    m_RightFrontMotor1.setSelectedSensorPosition(0,0,10);
    m_LeftFrontMotor3.setSelectedSensorPosition(0,0,10);
   
   // resetEncoders();

    m_gyroNavX = new AHRS(SPI.Port.kMXP);



  }




  // --------------------- SECTION MÉTHODES POUR ENCODEURS ----------------------------//

  // Méthodes pour Reset les encodeurs 
  public void resetEncoders() {
    m_RightFrontMotor1.setSelectedSensorPosition(0,0,10);
    m_LeftFrontMotor3.setSelectedSensorPosition(0,0,10);
  }

 
  public double getRightEncoderValue() {
    return m_RightFrontMotor1.getSelectedSensorPosition()* Constants.BP_Constants.kEncoderTicks2M;
  }
   
  public double getLeftEncoderValue() {
    return m_LeftFrontMotor3.getSelectedSensorPosition()* Constants.BP_Constants.kEncoderTicks2M;
  }

  public double getAverageEncoderDistance() {
    return (getRightEncoderValue() + getLeftEncoderValue()) / 2 ;
  }



 
      // -------------Code non utilisé , à modfier ou enlever ------------------------//
        public Boolean driveDistanceCM(double motorSpeed, double CMToDrive, Boolean resetEncoder) {
          Boolean driveComplete = false;
          double currentShaftEncoderValue = 0;
          double targetShaftEncoderCount = 0;
        double convertRotationsToCM = 47.88;  // Will need tested, calibrated and revised

          // Reset the shaft encoder value
          if (resetEncoder == true) {
            resetEncoders();
          }

          // Set the speed of the motors using arcade drive with no turn
          m_differentialDrive.arcadeDrive(motorSpeed, 0);
          m_differentialDrive.feed();

          currentShaftEncoderValue = getRightEncoderValue();
          targetShaftEncoderCount = CMToDrive * convertRotationsToCM;  


          if (currentShaftEncoderValue > targetShaftEncoderCount) {
            driveComplete = true;
          }


        return driveComplete;
        }
      // ----------------FIN Code non utilisé , à modfier ou enlever -------------------//

  public void arcadeDrive(double fwd, double rot) {
    
    m_differentialDrive.arcadeDrive(fwd, rot);

    // Affiche les coordonnées du Gyro (direction du robot) dans le shuffleboard
    double currentHeading = get_current_heading();
    int currentHeadingInteger = (int) (currentHeading);
    SmartDashboard.putNumber("Direction robot Arcade ", currentHeadingInteger);


  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
   
    m_differentialDrive.tankDrive(leftSpeed, rightSpeed);
    
    // Affiche les coordonnées du Gyro (direction du robot) dans le shuffleboard
    double currentHeading = get_current_heading();
    int currentHeadingInteger = (int) (currentHeading);
    SmartDashboard.putNumber("Direction robot Tank", currentHeadingInteger);

  }





  public void stop() {
    m_differentialDrive.stopMotor();
  }



  // --------------------- SECTION Gyroscope NAVX----------------------------//
    public void reset_gyro(){
      m_gyroNavX.reset();
    }

    public double get_current_heading(){
      return m_gyroNavX.getAngle();
    }
  
/**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(m_gyroNavX.getAngle(), 360) * (Constants.BP_Constants.kGyroReversed ? -1.0 : 1.0);
  }


  public double getYaw() {
    return m_gyroNavX.getYaw();
  }

  public double getPitch() {
    return m_gyroNavX.getPitch();
  }

  public double getRoll() {
    return m_gyroNavX.getRoll();
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Affichage des valeurs des enncodeurs dans le shuffleboard 
    SmartDashboard.putNumber("Encodeur_Gauche", m_RightFrontMotor1.getSelectedSensorPosition()* Constants.BP_Constants.kEncoderTicks2M); 
    SmartDashboard.putNumber("Encodeur_Droite", m_LeftFrontMotor3.getSelectedSensorPosition()* Constants.BP_Constants.kEncoderTicks2M); 
    SmartDashboard.putNumber("Encodeur_Gauche_Raw", m_RightFrontMotor1.getSelectedSensorPosition()); 
    SmartDashboard.putNumber("Encodeur_Droit_Raw", m_LeftFrontMotor3.getSelectedSensorPosition()); 


    
    SmartDashboard.putNumber("Gyro X(Pitch)",m_gyroNavX.getPitch());
    SmartDashboard.putNumber("Gyro Y(Roll)",m_gyroNavX.getRoll());
    SmartDashboard.putNumber("Gyro Z(Yaw)",m_gyroNavX.getYaw());

  }


}
