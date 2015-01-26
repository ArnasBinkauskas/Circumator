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


	
public class Or extends LogicComponent implements Pushable{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int width = 50;
	int height = 25;
	
	int inXCordX = xCord - width/10;
	int inXCordY = yCord + height/4;
	
	int inYCordX = xCord - width/10;
	int inYCordY = yCord + (height/4) * 3;
	
	int outUCordX = xCord + width + width/10;
	int outUCordY = yCord + height/2;
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Or(){
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		label = "Or";
		setCordinates(0,0);
	}
	
	public Or(WNode in_nodeX, WNode in_nodeY, WNode out_node, int x, int y){
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		plugInput(in_nodeX, "x");
		plugInput(in_nodeY, "y");
		plugOutput(out_node, "u");
		label = "Or";
		setCordinates(x,y);
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	
	@Override
	public void updateInOut(){
		inXCordX = xCord - width/10;
		inXCordY = yCord + height/4;
		
		inYCordX = xCord - width/10;
		inYCordY = yCord + (height/4) * 3;
		
		outUCordX = xCord + width + width/10;
		outUCordY = yCord + height/2;
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
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(xCord,yCord, width,height);
		    g.drawString(label, xCord + 8, yCord + 18);
		    g.drawLine(xCord, inXCordY, inXCordX, inXCordY);
		    g.drawLine(xCord, inYCordY, inYCordX, inYCordY);
		    g.drawLine(outUCordX - width/10, outUCordY, outUCordX, outUCordY);
	 }
	
}