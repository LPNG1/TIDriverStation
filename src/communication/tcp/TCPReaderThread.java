package communication.tcp;

/**
 * Thread that reads the TCP stream constantly
 * @author John
 *
 */
public class TCPReaderThread extends Thread {

	private volatile boolean active;

	/**
	 * Reads the scanner as long as it has a message in the queue
	 */
	@Override
	public void run() {

		while (!this.isInterrupted()) {
			if (TCPCommunicator.hasNextMessage()) {
				
				/*
				 * TODO New read code goes here
				 */
				
				this.active = true;

			}

		}

		this.active = false;

	}
	
	public boolean isActive() {
		return this.active;
	}

}
