package main;

import org.json.simple.JSONArray;

import communication.Communicator;
import data.JSONCreator;
import input.JoystickManager;
import net.java.games.input.Controller;

public class MainClass {

	public static void main(String[] args) {
		Controller remote = JoystickManager.getNextController(Controller.Type.GAMEPAD);

		Communicator.init("10.0.0.3", 4590);

		while(!Communicator.hasNextMessage());
		
		System.out.println(Communicator.getNextMsg());
		
		
//		JSONArray msg;
//		while (true) {
//			msg = JSONCreator.createSendableJSON(remote, "teleop", "enabled");
//			System.out.println(msg);
//			Communicator.sendMessage(msg);
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

}