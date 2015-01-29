package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit And gate in a circuit
 * Inputs of this component is going to be called "x" and "y"
 * Output of this component is going to be called "u"
 * */


	
public class And extends TwoOne{
	/**
	 * No value constructor- initialise input/output names
	 * */
	public And(){
		super();
		label = "And";
		gateDelay = 1;
	}
	
	public And(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(gateID, in_nodeX, in_nodeY, out_node, centreCoords);
		label = "And";
		gateDelay = 1;
	}
	
	/**attempts to push the signals over the gate iff both of the input nodes are ready
	*Updates pathDeph of the gate and the gate delay of the output signal in the process
	*Also updates the output signal value according to the gate logic
	*returns false if one of the inputs is not ready
	*/
	@Override
	public boolean pushSignal(){
		if (super.pushSignal()){
			Signal inX = input.get("x").getSignal();
			Signal inY = input.get("y").getSignal();
			Signal out = output.get("u").getSignal();
			out.setValue((inX.getValue() && inY.getValue()));
			return true;
		}else 
			return false;
	}
	

	
}