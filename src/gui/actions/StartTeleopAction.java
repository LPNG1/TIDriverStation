package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;
import gui.DriverStation;

/**
 * Sends a start teleop event to the robot
 * @author John
 *
 */
public class StartTeleopAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSONObject startTeleopEvent = new JSONObject();
		startTeleopEvent.put("event-id", "start-teleop");
		TCPCommunicator.sendMessage(startTeleopEvent);
		
		if(TCPCommunicator.getNextResponse()) {
			DriverStation.getInstance().toggleTeleopButton();
		} else {
			System.out.println("Action failed!");
		}
		
	}

}