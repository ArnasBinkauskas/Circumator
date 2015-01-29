package logicComponent;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;


import main.*;
import wireComponent.*;

//All Components will have a Node input with a meaningful name
//Input and output names MUST be different
public class LogicComponent implements Pushable {
	public String ID;
	
	public HashMap<String, WNode> input; 
	public HashMap<String, WNode> output;
	//coordinates
	public Point center;
	//gate delay 1 by default
	public int gateDelay;
	public String label;
	
	public int pathDeph;


	
	public LogicComponent(){
		input = new HashMap<String, WNode>();
		output = new HashMap<String, WNode>();
	}
	
	/**
	 * Many components will have a fixed number of inputs/outputs
	 * */
	public LogicComponent(int in_capacity, int out_capacity){
		input = new HashMap<String, WNode>(in_capacity);
		output = new HashMap<String, WNode>(out_capacity);
	}
	
	/**@TODO 
	 * Implement to show signals on inputs and outputs
	 */
	public String toString(){
		return ID;
	}
	
	public Collection<WNode> getInputs(){
		return input.values();
	}
	
	public Collection<WNode> getOutputs(){
		return output.values();
	}
	
	public void setCordinates(Point c){
		center = c;
		updateInOut();
	}
	
	public void updateInOut(){}
	
	public void setLabel(String l){
		label = l;
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	/**
	 *  Plugs a node into specific input slot
	 * */
	public void plugInput(WNode n, String in_name){
		n.plugTo(this);
		input.put(in_name, n);
	}
	
	/**
	 * Plugs a node into specific output slot
	 * */
	public void plugOutput(WNode n, String out_name){
		n.plugTo(this);
		output.put(out_name, n);
	}
	
	/**TODO implement this for high level circuits
	 * Must check that all input nodes are ready, if so:
	 * -update pathDepth of LogicComponent 
	 * -update Gate delays of all outputs
	 * -set output WNodes to be 'ready'
	 * */
	public boolean pushSignal(){
	return true;
	}
	
	//TODO appropriate graph
	 public void paint(Graphics g) {
	 }
}
				