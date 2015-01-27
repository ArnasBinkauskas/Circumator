package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Inverter in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Inverter extends OneOne implements Pushable{
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Inverter(){
		super();
		label = "Invert";
	}
	
	public Inverter(String gateID, WNode in_node, WNode out_node, Point centreCoords){
		super(gateID, in_node, out_node, centreCoords);
		label = "Invert";
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
