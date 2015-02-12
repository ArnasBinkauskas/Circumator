package logicComponent;
import java.awt.Color;
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
	//components with gate delay > 1 will take more steps to paint
	public int painted; 
	
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
	
	public void setCoordinates(Point c){
		center = c;
		updateCoords();
	}
	
	//implement for each class
	public void updateCoords(){}
	
	
	public void setLabel(String l){
		label = l;
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
		painted = gateDelay - 1;
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
	 * -set output WNodes to be 'ready'
	 * child classes are responsible to update their output delays
	 * and signal values.
	 * */
	public boolean pushSignal(){
		int maxInDelay = 0; 
		for (WNode in: input.values()){
			if (!in.isReady()){
				return false;
			}
			if (maxInDelay < in.getSignal().getGateDelay())
				maxInDelay = in.getSignal().getGateDelay();
		}
		for (WNode out: output.values())
			out.setReady(true);
		pathDeph = maxInDelay;
		return true;
	}
	
	//TODO appropriate graph
	 public void paint(Graphics g) {
	 }
	 
	 public void clearAnimation(Graphics g){
		 g.setColor(Color.black);
		 g.clearRect(center.getX() - 19, center.getY() -10, 34, 19);
		 g.drawString(label, center.getX() - 8, center.getY() + 4);
		 painted = 0;
	 }
	 
	 public void pass(Graphics g){
		 if (painted < gateDelay){
			 g.setColor(Color.green);
			 g.fillRect(center.getX() - 20 + 10*painted, center.getY() -5, 10, 10);
			 painted++;
		 }
		// g.setColor(Color.black);
		// g.drawString(label, center.getX() - 8, center.getY() + 4);
	 }
}
				