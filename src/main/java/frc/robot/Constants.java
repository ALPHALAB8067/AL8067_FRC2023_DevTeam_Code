// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class BP_Constants {
    public static final int kLeftFrontMotorPort = 1;
    public static final int kLeftBackMotorPort = 2;
    public static final int kRightFrontMotorPort = 3;
    public static final int kRightBackMotorPort = 4;
    public static final int kCTREMagEncoderTicks = 4096;
    public static final double kWheelDiameterMeters = 0.15;   
    public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (double) kCTREMagEncoderTicks;


    public static final double DIAMETRE_ROUE= 15.24; // 6" en cm
    public static final double CIRCONFERENCE_ROUE= (DIAMETRE_ROUE * Math.PI);
    public static final int CTRE_MAGENCODERSRX_TICKS = 4096;
    public static final double DISTANCE_ENCODEUR_CM= (DIAMETRE_ROUE * Math.PI) / (double) CTRE_MAGENCODERSRX_TICKS;

    public static final boolean kGyroReversed = true;

    public static final double kEncoderTicks2M = 1.0/4096 * 15.24 * Math.PI/1;

    // conversion pour un éventuel bras comportant un gearbox  - doit considérer gear ratio

    public static final double kArmEncoderTicks2Meters = 360.0/512 * 0.1524 * 26/42 * 60*18 *18/84;

    // il faudrait essayer cdette formule la voir si les data concorde
    public final double kEncoderTick2Cm = 1.0/128*(15.24*Math.PI/30.48);


    // nouvelle constantes pour balancement sur station de recharge

    public static final double ANGLE_VISEE_POUR_BALANCEMENT = 0.0;
    public static final double SEUIL_ANGLE_POUR_BALANCEMENT = 1.0;
    public static final double VITESSE_MOTEURS_BALANCEMENT_KP = 0.015; // P (Proportional) constant of a PID loop
 
    public static final double BLANCEMENT_ARRIERE_EXTRA_PUISSANCE_MOTEURS = 1.35;



    
  }

  public static class ControllerConstants {
   
    public static final int MANETTE_PILOTE1_ID = 0;
    public static final int MANETTE_PILOTE2_ID = 1;
    public static final int MANETTE_PILOTE3_ID  = 2;

  }





}
