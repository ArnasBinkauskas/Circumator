package wireComponent;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.LogicComponent;
import main.Signal;

public class  WNode {
	String nodeID;
	Signal signal_value; // holds the signal on this node at current time
	Point coordinates;
	LogicComponent plugedTo; //pointer to the component the node is plugged to
	Wire on_wire;
		
	public WNode(String ID){
			nodeID = ID;
			signal_value = new Signal();
	}
	
	public void plugTo(LogicComponent c){
		plugedTo = c;
	}
	
	public void plugWire(Wire w){
		on_wire = w;
	}
	
	public void unplug(){
		plugedTo = null;
	}
	
	public String toString(){
		return nodeID + " " + coordinates.toString();
	}
	
	
	//getters and setters
	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public Signal getSignal() {
		return signal_value;
	}
	
	public void setSignal(Signal s){
		signal_value = s;
	}
	
	public Point getCordinates() {
		return coordinates;
	}

	public void setCordinates(Point coords) {
		this.coordinates = coords;
	}
	
	public boolean isEqual(WNode n){
		return n.nodeID.equals(this.nodeID);
	}
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawString(nodeID, coordinates.getX() - 5, coordinates.getY() - 2);
	 }	    
}