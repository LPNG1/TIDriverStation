package input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Utilities for working with joysticks
 * @author John
 *
 */
public class JoystickManager {

	/**
	 * Gets the first available controller of a certain type.
	 * @param type controller type to search for
	 * @return first matching controller found, or null if none are found
	 */
	public static Controller getNextController(Controller.Type type) {
		
		Controller[] allControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		for (int i = 0; i < allControllers.length; i++) {
			if (allControllers[i].getType() == type) {
				System.out.println("Found: " + allControllers[i].getName());
				return allControllers[i];
			}
		}
		
		return null;
	}
	
	/**
	 * Prints all component names and identifiers of a certain controller
	 * @param c controller to scan
	 */
	public static void printComponents(Controller c) {
		
		Component[] cps = c.getComponents();
		System.out.println("Total Components: " + cps.length);
        
        for (int i = 0; i < cps.length; i++) {
            System.out.print(i + 1 + ": " + cps[i].getName() + " Id: " + cps[i].getIdentifier());

            System.out.print("    ComponentType: ");
            if (cps[i].isAnalog()) {
                System.out.print(" Analog");
            } else {
                System.out.print(" Digital");
            }
            System.out.println();
        }
	}
	
	/**
	 * Reads all component values of a certain controller
	 * @return array of [id,value] pairs
	 */
	public static ComponentData[] getComponentValues(Controller c){
		
		Component[] cps = c.getComponents();
		ComponentData[] cData = new ComponentData[cps.length];
		
		c.poll();
		
		for (int i = 0; i < cps.length; i++) {
			String id = cps[i].getIdentifier().toString();
			float value = cps[i].getPollData();
			cData[i] = new ComponentData(id, value);
		}
		return cData;
	}
	
}
