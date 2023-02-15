// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.AL8067_XBOX_CONTROL;
import frc.robot.Constants;
import frc.robot.subsystems.BasePilotable_SS;

public class DriveToDistance_CMD extends CommandBase {
  /** Creates a new DriveToDistance_CMD. */

  private BasePilotable_SS  m_BasePilotable_SS;
  private boolean m_driveComplete;
  private double driveSpeed = 0.3;
  private double driveDistanceCM = 2 * 47.88;   // 39.37 inches per meter

  // variables pour PID
  private final double kP = 0.08;

  private double setPoint = 0;
  private double encoderPostion;
  private double error;
  private double motorSpeedPID;




  public DriveToDistance_CMD(BasePilotable_SS p_Basepilotable_SS, double p_setPoint) {
    // Use addRequirements() here to declare s ubsystem dependencies.

    m_BasePilotable_SS = p_Basepilotable_SS;
    setPoint = p_setPoint;


  

    addRequirements(p_Basepilotable_SS);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_BasePilotable_SS.resetEncoders();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    encoderPostion =m_BasePilotable_SS.getLeftEncoderValue() * Constants.BP_Constants.kEncoderTicks2M;
    error = setPoint - encoderPostion;
    motorSpeedPID = kP * error;
    // TO DO - verrifier s'il ne faut pas donner une valeur négative à un des moteurs 
    m_BasePilotable_SS.tankDrive(motorSpeedPID, motorSpeedPID);
    System.out.println("Cette commande est exécuté");
    System.out.println("Erreur" + error );

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
    m_BasePilotable_SS.stop();


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return false;

      return Math.abs(error) <= 3.8;

  }
}
