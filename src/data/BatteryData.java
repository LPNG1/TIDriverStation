package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BatteryData {

	private double voltage;
	private double current;
	private double motorCurrent;
	
	/**
	 * Constructor for battery data structure
	 */
	public BatteryData(JSONArray message) {
		
		JSONObject batteryArrayJSON = (JSONObject) message.get(2);
		JSONObject batteryJSON = (JSONObject) batteryArrayJSON.get("battery");
		
		this.voltage = (double) batteryJSON.get("voltage");
		this.current = (double) batteryJSON.get("current");
		this.motorCurrent = (double) batteryJSON.get("motor-current");
	}
	
	public double getVoltage() {
		return this.voltage;
	}
	
	public double getCurrent() {
		return this.current;
	}
	
	public double getMotorCurrent() {
		return this.motorCurrent;
	}
	
	
}
