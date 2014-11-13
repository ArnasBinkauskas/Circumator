package logicComponent.oneBit.primitive;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Buffer in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Buffer extends LogicComponent implements Pushable{
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
	
	public void pushSignal(){
		Signal in = input.get("x").getSignal();
		Signal out = output.get("u").getSignal();
		if ((in != null) && (out != null)){
			out.setGateDelay(in.getGateDelay() + 1);
			out.setValue(in.getValue());
		}
	}
	
}
