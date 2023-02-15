// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BasePilotable_SS;

public class DriveWJoystick_CMD extends CommandBase {
  /** Creates a new DriveWJoystick_CMD. */

  private BasePilotable_SS m_BasePilotable_SS;
  private final Joystick m_joystick;
  private final double m_pourcentageVitesse;



  public DriveWJoystick_CMD(BasePilotable_SS p_BasePilotable_SS, Joystick joystick, double pourcentageVitesse) {
    // Use addRequirements() here to declare subsystem dependencies.

    m_BasePilotable_SS = p_BasePilotable_SS;
    m_joystick = joystick;
    m_pourcentageVitesse = pourcentageVitesse;
    addRequirements(p_BasePilotable_SS);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_BasePilotable_SS.resetEncoders();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

   // double currSpd =  m_joystickCMD.m_joystick.getY();
    //double rot = m_joystickCMD.m_joystick.getX();


     // m_BasePilotable_SS.arcadeDrive(currSpd, -rot); 
      m_BasePilotable_SS.arcadeDrive(-m_joystick.getRawAxis(1)*m_pourcentageVitesse, -m_joystick.getRawAxis(0)*m_pourcentageVitesse);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
