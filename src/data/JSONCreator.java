package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import input.ComponentData;
import input.JoystickManager;
import net.java.games.input.Controller;

/**
 * Utilities for creating JSON data easily
 * @author John
 *
 */
public class JSONCreator {

	/**
	 * Returns a JSON object with current controller data
	 */
	public static JSONObject createJoystickJSON(Controller c) {
		
		ComponentData[] cData = JoystickManager.getComponentValues(c);
		
		JSONObject joystickValues = new JSONObject();
		
		for (int i = 0; i < cData.length; i++) {
			joystickValues.put(cData[i].getID(), cData[i].getValue());
		}
		
		JSONObject joystickData = new JSONObject();
		joystickData.put("joystickValues", joystickValues);
		
		return joystickData;
	}
	
	/**
	 * Returns a JSON object with current gamestate data
	 */
	public static JSONObject createGamestateJSON(String state) {
		//TODO: implement this using the communication API
		
		JSONObject ret = new JSONObject();
		ret.put("gamestate", state);
		return ret;
	}
	
	/**
	 * Returns a JSON object with current status data
	 */
	public static JSONObject createStatusJSON(String status) {
		//TODO: implement this using the communication API
		
		JSONObject ret = new JSONObject();
		ret.put("status", status);
		return ret;
	}
	
	/**
	 * Returns a JSON with all data sent to the robot
	 */
	public static JSONArray createSendableJSON(Controller c, String gamestate, String status) {
		
		JSONArray sendData = new JSONArray();
		
		sendData.add(createJoystickJSON(c));
		sendData.add(createGamestateJSON(gamestate));
		sendData.add(createStatusJSON(status));
		
		return sendData;
	}
	
}
