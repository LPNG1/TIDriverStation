package communication;

import org.json.simple.JSONArray;

public class SenderThread extends Thread {

	private boolean active;
	private JSONArray msg = null;

	@Override
	public void run() {

		this.active = true;

		while (!this.isInterrupted()) {

			if (msg != null) {
				Communicator.sendMessage(msg);
				msg = null;
			}

		}

		this.active = false;

	}

	public void setMessage(JSONArray msg) {
		this.msg = msg;
	}

}
