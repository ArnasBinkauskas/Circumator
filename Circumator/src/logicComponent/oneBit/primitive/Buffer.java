package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Buffer in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Buffer extends OneOne implements Pushable{
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Buffer(){
		super();
		label = "Buffer";
		gateDelay = 1;
	}
	
	public Buffer(String gateID, WNode in_node, WNode out_node, Point centreCoords){
		super(gateID, in_node, out_node, centreCoords);
		label = "Buffer";
		gateDelay = 1;
	}
		
	public boolean pushSignal(){
		if (super.pushSignal()){
			Signal in = input.get("in").getSignal();
			Signal out = output.get("out").getSignal();
			out.setValue(in.getValue());
			return true;
		}else 
			return false;
	}
	
}
