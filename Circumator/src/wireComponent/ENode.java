package wireComponent;
import logicComponent.LogicComponent;

/**
 * Class to represent the starting point of the wire (Or you can think of this
 * as an output node of any logic component)
 * */
public class ENode extends WNode {
	// holds a pointer to the wire this node is on
	Wire on_wire; 
	// holds a pointer to the logic component and the output name this Node is plugged into
	LogicComponent pluged_to;
	String input_name;
}

