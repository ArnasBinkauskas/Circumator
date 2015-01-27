package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Or gate in a circuit
 * Inputs of this component is going to be called "x" and "y"
 * Output of this component is going to be called "u"
 * */


	
public class Or extends TwoOne implements Pushable{
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Or(){
		super();
		label = "Or";
	}
	
	public Or(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(gateID, in_nodeX, in_nodeY, out_node, centreCoords);
		label = "Or";
	}
	
	public void pushSignal(){
		Signal inX = input.get("x").getSignal();
		Signal inY = input.get("y").getSignal();
		Signal out = output.get("u").getSignal();
		if ((inX != null) && (inY != null) && (out != null)){
			out.setGateDelay(Math.max(inX.getGateDelay(), inY.getGateDelay()) + gateDelay);
			out.setValue((inX.getValue() || inY.getValue()));
		}
	}
}