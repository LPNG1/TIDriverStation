package communication.udp;

import org.json.simple.JSONArray;

import communication.tcp.TCPCommunicator;
import data.JSONCreator;
import net.java.games.input.Controller;

/**
 * Thread that sends messages over the UDP stream at regular intervals
 * @author John
 *
 */
public class UDPSenderThread extends Thread{

	private boolean active;
	private JSONArray msg = null;
	private Controller c;
	private long messageInterval;
	
	//TODO: implement this from the server
	private String gamestate = "teleop";
	private String status = "enabled";

	/**
	 * Creates a new UDP sender thread
	 * @param c controller to read joystick data from
	 * @param interval
	 */
	public UDPSenderThread(Controller c, long interval) {
		this.c = c;
		this.messageInterval = interval;
	}
	
	
	@Override
	public void run() {

		this.active = true;

		while (!this.isInterrupted()) {

			JSONArray msg = JSONCreator.createSendableJSON(c, gamestate, status);
			UDPCommunicator.sendMessage(msg);
			
			try {
				Thread.sleep(messageInterval);
			} catch (InterruptedException e) {
				return;
			}

		}

		this.active = false;

	}

	public boolean isActive() {
		return this.active;
	}

	
	
	
}
