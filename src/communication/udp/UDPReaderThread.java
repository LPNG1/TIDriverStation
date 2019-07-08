package communication.udp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import data.BatteryData;
import data.MotorData;
import data.SensorData;

/**
 * Thread that constantly reads the UDP stream
 * 
 * @author John
 *
 */
public class UDPReaderThread extends Thread {

	private volatile boolean active;

	private BatteryData bat;
	private MotorData[] motors;
	private SensorData[] sensors;

	@Override
	public void run() {

		this.active = true;

		while (!this.isInterrupted()) {

			// get next UDP message
			JSONArray msg = null;
			try{
				msg = UDPCommunicator.getMessage();
			} catch (NullPointerException e) {
				continue;
			}

			// update battery data
			this.bat = new BatteryData(msg);

			// update sensor data
			JSONObject sensorContainer = (JSONObject) msg.get(0);
			JSONArray sensorArray = (JSONArray) sensorContainer.get("sensors");

			sensors = new SensorData[sensorArray.size()];
			for (int i = 0; i < sensorArray.size(); i++) {
				JSONObject sensor = (JSONObject) sensorArray.get(i);
				sensors[i] = new SensorData(sensor);
			}

			// update motor data
			JSONObject motorContainer = (JSONObject) msg.get(1);
			JSONArray motorArray = (JSONArray) motorContainer.get("motors");

			motors = new MotorData[motorArray.size()];
			for (int i = 0; i < motorArray.size(); i++) {
				JSONObject motor = (JSONObject) motorArray.get(i);
				motors[i] = new MotorData(motor);

			}

		}

		this.active = false;

	}

	public BatteryData getBatteryData() {
		return this.bat;
	}

	public SensorData[] getSensorData() {
		return this.sensors;
	}

	public MotorData[] getMotorData() {
		return this.motors;
	}

	public boolean isActive() {
		return this.active;
	}

}
