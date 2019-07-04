package communication.tcp;

import org.json.simple.JSONArray;

import data.JSONCreator;
import net.java.games.input.Controller;

/**
 * Thread that sends messages to the TCP stream at regular intervals
 * @author John
 *
 */
public class TCPSenderThread extends Thread {

	private boolean active;
	private JSONArray msg = null;
	
	private long messageInterval;
	
	//TODO: implement this from the server
	private String gamestate = "teleop";
	private String status = "enabled";

	/**
	 * Creates a new TCP sender thread
	 * @param interval
	 */
	public TCPSenderThread(long interval) {
		this.messageInterval = interval;
	}
	
	
	@Override
	public void run() {

		this.active = true;

		while (!this.isInterrupted()) {

			/*
			 * TODO New send code goes here
			 */

		}

		this.active = false;

	}

	public boolean isActive() {
		return this.active;
	}

}
