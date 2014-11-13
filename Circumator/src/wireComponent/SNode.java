package wireComponent;
import logicComponent.LogicComponent;
import main.Signal;

/**
 * Class to represent the starting point of the wire (Or you can think of this
 * as an output node of any logic component)
 * */
public class SNode extends WNode {
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
