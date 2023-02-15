package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class AL8067_XBOX_CONTROL {


  /*
     * Instantiate all joysticks/controllerdrivers and their buttons here
     *
     * Examples:
     * public final Joystick leftStick = new Joystick(0);
     * public final XboxController manettePilote_1 = new XboxController(0);
     *
     * private final JoystickButton leftTrigger = new JoystickButton(leftStick, 0);
     */

     public final static XboxController manettePilote_1 = new XboxController(Constants.ControllerConstants.MANETTE_PILOTE1_ID);
     public final static XboxController manettePilote_2 = new XboxController(Constants.ControllerConstants.MANETTE_PILOTE2_ID);
     public final static XboxController manettePilote_3 = new XboxController(Constants.ControllerConstants.MANETTE_PILOTE3_ID);
 
     public final JoystickButton dA = new JoystickButton(manettePilote_1, 1);
     public final JoystickButton dB = new JoystickButton(manettePilote_1, 2);
     public final JoystickButton dX = new JoystickButton(manettePilote_1, 3);
     public final JoystickButton dY = new JoystickButton(manettePilote_1, 4);
     public final JoystickButton dLBump = new JoystickButton(manettePilote_1, 5);
     public final JoystickButton dRBump = new JoystickButton(manettePilote_1, 6);
     public final JoystickButton dBack = new JoystickButton(manettePilote_1, 7);
     public final JoystickButton dStart = new JoystickButton(manettePilote_1, 8);
     public final JoystickButton dLStickBtn = new JoystickButton(manettePilote_1, 9);
     public final JoystickButton dRStickBtn = new JoystickButton(manettePilote_1, 10);
    // public final AxisButton dRTrigger = new AxisButton(manettePilote_1, 3, .05);
    // public final AxisButton dLTrigger = new AxisButton(manettePilote_1, 2, .05);
 
     public final POVButton dDPadUp = new POVButton(manettePilote_1, 0);
     public final POVButton dDPadRight = new POVButton(manettePilote_1, 90);
     public final POVButton dDPadDown = new POVButton(manettePilote_1, 180);
     public final POVButton dDPadLeft = new POVButton(manettePilote_1, 270);
 
     public final JoystickButton aA = new JoystickButton(manettePilote_2, 1);
     public final JoystickButton aB = new JoystickButton(manettePilote_2, 2);
     public final JoystickButton aX = new JoystickButton(manettePilote_2, 3);
     public final JoystickButton aY = new JoystickButton(manettePilote_2, 4);
     public final JoystickButton aLBump = new JoystickButton(manettePilote_2, 5);
     public final JoystickButton aRBump = new JoystickButton(manettePilote_2, 6);
     public final JoystickButton aBack = new JoystickButton(manettePilote_2, 7);
     public final JoystickButton aStart = new JoystickButton(manettePilote_2, 8);
     public final JoystickButton aLStickBtn = new JoystickButton(manettePilote_2, 9);
     public final JoystickButton aRStickBtn = new JoystickButton(manettePilote_2, 10);
    // public final AxisButton aRTrigger = new AxisButton(manettePilote_2, 3, .05);
     //public final AxisButton aLTrigger = new AxisButton(manettePilote_2, 2, .05);
     public final POVButton aDPadUp = new POVButton(manettePilote_2, 0);
     public final POVButton aDPadRight = new POVButton(manettePilote_2, 90);
     public final POVButton aDPadDown = new POVButton(manettePilote_2, 180);
     public final POVButton aDPadLeft = new POVButton(manettePilote_2, 270);
 
     public final JoystickButton tA = new JoystickButton(manettePilote_3, 1);
     public final JoystickButton tB = new JoystickButton(manettePilote_3, 2);
     public final JoystickButton tX = new JoystickButton(manettePilote_3, 3);
     public final JoystickButton tY = new JoystickButton(manettePilote_3, 4);
     public final JoystickButton tLBump = new JoystickButton(manettePilote_3, 5);
     public final JoystickButton tRBump = new JoystickButton(manettePilote_3, 6);
     public final JoystickButton tBack = new JoystickButton(manettePilote_3, 7);
     public final JoystickButton tStart = new JoystickButton(manettePilote_3, 8);
     public final JoystickButton tLStickBtn = new JoystickButton(manettePilote_3, 9);
     public final JoystickButton tRStickBtn = new JoystickButton(manettePilote_3, 10);
     //public final AxisButton tRTrigger = new AxisButton(manettePilote_3, 3, .05);
     //public final AxisButton tLTrigger = new AxisButton(manettePilote_3, 2, .05);
     public final POVButton tDPadUp = new POVButton(manettePilote_3, 0);
     public final POVButton tDPadRight = new POVButton(manettePilote_3, 90);
     public final POVButton tDPadDown = new POVButton(manettePilote_3, 180);
     public final POVButton tDPadLeft = new POVButton(manettePilote_3, 270);


    

     public AL8067_XBOX_CONTROL() {

/*
     *
     * Create controller actions here
     *
     * Examples:
     * dRTrigger.whileTrue(new CollectCommand());
     * dLTrigger.onTrue(new EjectCommand());
     * dA.whileTrue(new RepeatCommand(new RapidFire());
     *
     *  onTrue (fka whenPressed)    Init->Execute repeats until IsFinished = true->End, will not start again at Init if still held down
     *  whileTrue (fka whenHeld)    Init->Execute repeats until IsFinished = true or button released->End, will not start again at Init if still held down
     *  whileTrue(new RepeatCommand()) (fka whileHeld)   Init->Execute repeats until IsFinished = true or button released->End, will start again at Init if still held down
     *
     */
/* 
        // arm
        aB.onTrue(new MoveUp());

        // elevator
        aA.onTrue(new InstantCommand(Robot.elevator::zeroEncoder));
        aDPadUp.onTrue(new SmartElevatorPosition(SmartElevatorPosition.Location.LOAD_FROM_DOUBLE_SUBSTATION));
        aDPadRight.onTrue(new SmartElevatorPosition(SmartElevatorPosition.Location.LOAD_FROM_SINGLE_SUBSTATION));
        aDPadDown.onTrue(new SmartElevatorPosition(SmartElevatorPosition.Location.COLLECT_FROM_GROUND));

        // collector
        aX.onTrue(new CollectorRun());
        aA.onTrue(new CollectorEject());
                // aB.onTrue(new Co)
        aA.onTrue(new InstantCommand(Robot.elevator::zeroEncoder));
        if( Robot.isSwervePBot || Robot.isCompBot )
            dB.onTrue(new ResetWheels());
*/
     }


     public void setRumble(boolean rumble) {
        manettePilote_1.setRumble(GenericHID.RumbleType.kLeftRumble, rumble ? 1.0 : 0.0);
        manettePilote_1.setRumble(GenericHID.RumbleType.kRightRumble, rumble ? 1.0 : 0.0);
        manettePilote_2.setRumble(GenericHID.RumbleType.kLeftRumble, rumble ? 1.0 : 0.0);
        manettePilote_2.setRumble(GenericHID.RumbleType.kRightRumble, rumble ? 1.0 : 0.0);
    }




}
