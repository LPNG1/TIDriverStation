package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;
import gui.DriverStation;

/**
 * Sends a disable event to the robot
 * @author John
 *
 */
public class RobotDisableAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSONObject disableEvent = new JSONObject();
		disableEvent.put("event-id", "disable");
		TCPCommunicator.sendMessage(disableEvent);
		
		if(TCPCommunicator.getNextResponse()) {
			DriverStation.getInstance().allowRobotEnable(true);
			DriverStation.getInstance().allowRobotDisable(false);
		}
	}

}
