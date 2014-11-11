package logicComponent.oneBit.primitive;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;

/**Class to represent a 1-bit Buffer in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Buffer extends LogicComponent{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Buffer(){
		//ENode dummy_in = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(1,1);
		input.put("x", null);
		output.put("u", null);
	}
	
	public Buffer(ENode in_node, SNode out_node){
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		in_node.plugTo(this, "x");
		out_node.plugTo(this, "u");
	}
	
}
