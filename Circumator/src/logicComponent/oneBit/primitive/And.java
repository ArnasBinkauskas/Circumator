package logicComponent.oneBit.primitive;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit And gate in a circuit
 * Inputs of this component is going to be called "x" and "y"
 * Output of this component is going to be called "u"
 * */


	
public class And extends LogicComponent implements Pushable{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int gateDelay = 1;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public And(){
		//ENode dummy_inX = new ENode(new Wire(),"dummy");
		//ENode dummy_inY = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
	}
	
	public And(ENode in_nodeX, ENode in_nodeY, SNode out_node){
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		in_nodeX.plugTo(this, "x");
		in_nodeY.plugTo(this, "y");
		out_node.plugTo(this, "u");
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	
	public void pushSignal(){
		Signal inX = input.get("x").getSignal();
		Signal inY = input.get("y").getSignal();
		Signal out = output.get("u").getSignal();
		if ((inX != null) && (inY != null) && (out != null)){
			out.setGateDelay(Math.max(inX.getGateDelay(), inY.getGateDelay()) + gateDelay);
			out.setValue((inX.getValue() && inY.getValue()));
		}
	}
	
}