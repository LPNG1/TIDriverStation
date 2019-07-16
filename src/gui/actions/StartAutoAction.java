package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

import communication.tcp.TCPCommunicator;
import communication.udp.UDPCommunicator;
import communication.udp.UDPReaderThread;
import gui.DriverStation;

/**
 * Sends a start auto event to the robot
 * @author John
 *
 */
public class StartAutoAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JSONObject startAutoEvent = new JSONObject();
		startAutoEvent.put("event-id", "start-auto");
		TCPCommunicator.sendMessage(startAutoEvent);
		
		if(TCPCommunicator.getNextResponse()) {
			DriverStation.getInstance().toggleAutoButton();
		} else {
			System.out.println("Action failed!");
		}
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(15100);
				} catch (InterruptedException e) {
					return;
				}
				
				if(DriverStation.getInstance().getAutoText() == "Stop Auto") {
					DriverStation.getInstance().toggleAutoButton();
				}
			}
		}).start();
	}

}
