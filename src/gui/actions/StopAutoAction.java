package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;

/**
 * Sends a stop auto event to the robot
 * @author John
 *
 */
public class StopAutoAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("auto stopped");
		
		JSONObject stopAutoEvent = new JSONObject();
		stopAutoEvent.put("event-id", "stop-auto");
		TCPCommunicator.sendMessage(stopAutoEvent);
	}
	
}
