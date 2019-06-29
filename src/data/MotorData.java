package data;

import org.json.simple.JSONObject;

public class MotorData {
	
	private String port;
	private String type;
	private double encoderValue;
	
	/**
	 * Constructor for motor data structure
	 */
	public MotorData(JSONObject motor) {
		this.port = (String) motor.get("port");
		this.type = (String) motor.get("type");
		this.encoderValue = (double) motor.get("encoder-value");
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getEncoderValue() {
		return this.encoderValue;
	}
	
}
