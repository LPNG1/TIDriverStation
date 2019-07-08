package main;

import gui.DriverStation;
import input.JoystickManager;
import net.java.games.input.Controller;

public class MainClass {
	
	/*
	 * logic:
	 * opens driver station GUI, with defined layout and logic. the DS sends the 
	 * joystick data all the time to the robot (which reads all the time) and
	 * sends commands when they are clicked on.
	 */
	
	public static void main(String[] args) {	
		JoystickManager.findController(Controller.Type.GAMEPAD);
		DriverStation.init();
	}

}