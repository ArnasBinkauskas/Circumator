package wireComponent;
import logicComponent.LogicComponent;
import main.Signal;

/**
 * Class to represent the starting point of the wire (Or you can think of this
 * as an output node of any logic component)
 * */
public class SNode extends WNode {
	// holds a pointer to the wire this node is on
	Wire on_wire; 
	// holds a pointer to the logic component and the output name this Node is plugged into
	LogicComponent pluged_to;
	public String output_name;
	
	public SNode(Wire on_w, String ID){
		on_wire = on_w;
		nodeID = ID;
		signal_value = new Signal();
	}
	
	
	public boolean plugTo(LogicComponent n, String out_name){
		if (n.plugOutput(this, out_name)){
			pluged_to = n;
			output_name = out_name;
			return true;
		}
		else return false;
	}
}
