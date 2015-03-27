package main;
/**
 * Class to represent a 1bit signal in circuits
 * */
/**
 * @author Arnas
 *
 */
public class Signal {
	private boolean value;
	private int pathDepth;//stores gate delay of the signal as a natural number
	
	/**
	 * No value constructor*/
	public Signal(){
		pathDepth = 0;
		value = false;
	}
	
	/**
	 * Creates a new signal with value val*/
	public Signal(boolean val){
		pathDepth = 0;
		value = val;
	}
	
	/**
	 * Creates a new signal with value val and given gate delay*/
	public Signal(boolean val, int current_delay){
		value = val;
		pathDepth = current_delay;
	}

	//Standard getters and setters
	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public int getPathDepth() {
		return pathDepth;
	}

	public void setPathDepth(int gate_delay) {
		this.pathDepth = gate_delay;
	}	
}
