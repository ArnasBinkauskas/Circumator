package wireComponent;
import logicComponent.LogicComponent;
import main.Signal;

/**
 * Class to represent the starting point of the wire (Or you can think of this
 * as an output node of any logic component)
 * */
public class ENode extends WNode {
	String input_name;
	
	public ENode(Wire on_w, String ID){
		on_wire = on_w;
		on_wire.setEnd(this);
		nodeID = ID;
		signal_value = new Signal();
	}
	
	public boolean plugTo(LogicComponent n, String in_name){
		if (n.plugInput(this, in_name)){
			pluged_to = n;
			input_name = in_name;
			return true;
		}
		else return false;
	}
}

