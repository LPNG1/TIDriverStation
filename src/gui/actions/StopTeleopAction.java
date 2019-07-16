package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;
import gui.DriverStation;

/**
 * Sends a stop teleop event to the robot
 * @author John
 *
 */
public class StopTeleopAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSONObject stopTeleopEvent = new JSONObject();
		stopTeleopEvent.put("event-id", "stop-teleop");
		TCPCommunicator.sendMessage(stopTeleopEvent);
		
		if(TCPCommunicator.getNextResponse()) {
			DriverStation.getInstance().toggleTeleopButton();
		} else {
			System.out.println("Action failed!");
		}
	}

}