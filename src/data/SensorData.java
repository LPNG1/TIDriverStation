package data;

import org.json.simple.JSONObject;

public class SensorData {

	private String port;
	private String type;
	private double value;
	
	/**
	 * Constructor for sensor data structure
	 */
	public SensorData(JSONObject sensor) {
		this.port = (String) sensor.get("port");
		this.type = (String) sensor.get("type");
		this.value = (double) sensor.get("value");
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getValue() {
		return this.value;
	}
}
