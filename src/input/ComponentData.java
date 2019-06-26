package input;

/**
 * Data structure for component ID and value pairs
 * @author John
 *
 */
public class ComponentData {

	private String id;
	private float value;
	
	public ComponentData(String id, float value) {
		this.id = id;
		this.value = value;
	}
	
	public String getID() {
		return this.id;
	}
	
	public float getValue() {
		return this.value;
	}
	
}