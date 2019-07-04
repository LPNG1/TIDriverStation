package main;

import org.json.simple.JSONArray;

import communication.Communicator;
import communication.tcp.TCPCommunicator;
import communication.udp.UDPCommunicator;
import communication.udp.UDPReaderThread;
import communication.udp.UDPSenderThread;
import data.JSONCreator;
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
		Communicator.initCommuniction("10.0.0.3");
		System.out.println("comm init");
		
		UDPReaderThread read = new UDPReaderThread();
		read.start();
		
		UDPSenderThread send = new UDPSenderThread(remote, 50);
		send.start();
		
		while(true) {
		
			try {
				System.out.println(read.getBatteryData().getMotorCurrent());
			} catch (NullPointerException e) {
				System.out.println("null");
			}
		}

	}

}