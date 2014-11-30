package logicComponent.oneBit.primitive;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Inverter in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Inverter extends LogicComponent implements Pushable{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int gateDelay = 1;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Inverter(){
		//ENode dummy_in = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(1,1);
		input.put("x", null);
		output.put("u", null);
	}
	
	public Inverter(ENode in_node, SNode out_node){
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		in_node.plugTo(this, "x");
		out_node.plugTo(this, "u");
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	
	public void pushSignal(){
		Signal in = input.get("x").getSignal();
		Signal out = output.get("u").getSignal();
		if ((in != null) && (out != null)){
			out.setGateDelay(in.getGateDelay() + gateDelay);
			out.setValue(!in.getValue());
		}
	}
	
}
