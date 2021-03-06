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
	boolean ready;
		
	public WNode(String ID){
			nodeID = ID;
			signal_value = new Signal();
			ready = false;
	}
	
	public boolean isReady(){
		return ready;
	}
	
	public void setReady(boolean t){
		ready = t;
	}
	//is this node a start of the wire?
	public boolean isWStart(){
		boolean ans = false;
		try{ans = this.isEqual(on_wire.start);}
		catch (NullPointerException e){}
		return ans; 
	}
	
	public boolean isWEnd(){
		boolean ans = false;
		try{ans = this.isEqual(on_wire.end);}
		catch (NullPointerException e){}
		return ans; 
	}
	
	public Wire getWire(){
		return on_wire;
	}
	
	public boolean hasWire(){
		return on_wire != null;
	}
	
	public boolean hasComponent(){
		return plugedTo != null;
	}
	
	public LogicComponent getComponent(){
		return plugedTo;
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
		if (coordinates != null)
			return nodeID + " " + coordinates.toString();
		else return nodeID;
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
	
	public void setSignalVal(boolean val){
		signal_value.setValue(val);
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
	 
	 public void paintValue(Graphics g){
		 g.setColor(Color.black);
		 if (this.signal_value.getValue())
			 g.drawString("1", coordinates.getX() - 2, coordinates.getY() - 2);
		 else
			 g.drawString("0", coordinates.getX() - 2, coordinates.getY() - 2);
	 }
}