package wireComponent;

import logicComponent.LogicComponent;
import main.Signal;

public abstract class  WNode {
	String nodeID;
	Signal signal_value; // holds the signal on this node at current time
	Point coordinates;
	// holds a pointer to the wire this node is on
	Wire on_wire; 
	// holds a pointer to the logic component and the output name this Node is plugged into
	LogicComponent pluged_to;
	
	
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

	public Wire getWire() {
		return on_wire;
	}

	public void setWire(Wire on_wire) {
		this.on_wire = on_wire;
	}

	public LogicComponent getPluged_to() {
		return pluged_to;
	}

}