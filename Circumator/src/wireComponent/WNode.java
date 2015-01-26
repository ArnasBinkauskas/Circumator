package wireComponent;

import logicComponent.LogicComponent;
import main.Signal;

public class  WNode {
	String nodeID;
	Signal signal_value; // holds the signal on this node at current time
	Point coordinates;

		
	public WNode(String ID){
			nodeID = ID;
			signal_value = new Signal();
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
	
	public Point getCordinates() {
		return coordinates;
	}

	public void setCordinates(Point coords) {
		this.coordinates = coords;
	}

}