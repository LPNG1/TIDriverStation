package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;
import gui.DriverStation;

/**
 * Sends a start auto event to the robot
 * @author John
 *
 */
public class StartAutoAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("auto started");
		
		JSONObject startAutoEvent = new JSONObject();
		startAutoEvent.put("event-id", "start-auto");
		TCPCommunicator.sendMessage(startAutoEvent);
	}

}
