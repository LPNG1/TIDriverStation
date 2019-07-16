package main;

import gui.DriverStation;
import input.JoystickManager;
import net.java.games.input.Controller;

public class MainClass {
	
	public static void main(String[] args) {	
		JoystickManager.findController(Controller.Type.GAMEPAD);
		DriverStation.init();
	}

}