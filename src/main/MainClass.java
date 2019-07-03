package main;

import communication.Communicator;
import communication.ReaderThread;
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
		
		Controller remote = JoystickManager.getNextController(Controller.Type.GAMEPAD);
		Communicator.init("10.0.0.3", 4590);
		System.out.println("comm init");
		
		while(!Communicator.hasNextMessage());
		
		ReaderThread read = new ReaderThread();
		read.start();
		
		while(!read.isActive());
		
		while(read.isActive()) {
			try {
			System.out.println(read.getSensorData()[1].getValue());
			} catch (NullPointerException e) {
				System.out.println("null");
			}
		}
		
			
//			JSONArray msg = Communicator.getNextMessage();
//			JSONObject sensorContainer = (JSONObject) msg.get(0);
//			JSONArray sensorArray = (JSONArray) sensorContainer.get("sensors");
//			JSONObject touch = (JSONObject) sensorArray.get(1);
//			
//			System.out.println(touch.get("value"));
			
			
		

	}

}