// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.concurrent.TimeoutException;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BasePilotable_SS;

public class CS_PIDBalance_CMD extends CommandBase {
  /** Creates a new CS_PIDBalance_CMD. */

  private BasePilotable_SS m_BasePilotable_SS;

  private double erreur;
  private double angleActuel;
  private double puissanceMoteur;
  private PIDController m_pidcontroller1 = new PIDController(Constants.BP_Constants.VITESSE_MOTEURS_BALANCEMENT_KP, Constants.BP_Constants.VITESSE_MOTEURS_BALANCEMENT_KI, 
  Constants.BP_Constants.VITESSE_MOTEURS_BALANCEMENT_KD);
  

  public CS_PIDBalance_CMD( BasePilotable_SS p_BasePilotable_S) {
    
    m_pidcontroller1.setTolerance(3);
    m_BasePilotable_SS= p_BasePilotable_S;
    
    addRequirements(m_BasePilotable_SS); 

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      SmartDashboard.putNumber("AnglePID", angleActuel);


    angleActuel = m_BasePilotable_SS.getPitch();
    //erreur = Constants.BP_Constants.ANGLE_VISEE_POUR_BALANCEMENT - angleActuel;
    puissanceMoteur = m_pidcontroller1.calculate(angleActuel, Constants.BP_Constants.ANGLE_VISEE_POUR_BALANCEMENT);
    if (puissanceMoteur >= 0.5){
      puissanceMoteur = 0.5;
    } else if( puissanceMoteur <= -0.5) {
      puissanceMoteur = -0.5;
    }
    
    // puissanceMoteur = Math.min(Constants.BP_Constants.VITESSE_MOTEURS_BALANCEMENT_KP * erreur, 1);
   

    // plus de puissance pour le robot reculant, il faudra ajuster la constante
   //if (puissanceMoteur < 0) {
    //puissanceMoteur *= Constants.BP_Constants.BLANCEMENT_ARRIERE_EXTRA_PUISSANCE_MOTEURS;
    //}

   // Limit the max power
  /*
   if (Math.abs(puissanceMoteur) > 0.4) {
    puissanceMoteur = Math.copySign(0.4, puissanceMoteur);
    }
  */
    m_BasePilotable_SS.tankDrive(puissanceMoteur, puissanceMoteur);


    // Pour le débuggage du coe 
    //System.out.println("Angle Actuel: " + angleActuel);
    //System.out.println("Erreur " + erreur);
    //System.out.println("Puissance Moteur: " + puissanceMoteur);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_BasePilotable_SS.stop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
   return false; //m_pidcontroller1.atSetpoint();
    //return Math.abs(erreur) < Constants.BP_Constants.SEUIL_ANGLE_POUR_BALANCEMENT; 
    
    // La commande s'arrête lorsque l'angle du Gyro est plut petit que le seuil de 1 degré en rapport au gyroscope qui doit etre a 0 
  }
}
