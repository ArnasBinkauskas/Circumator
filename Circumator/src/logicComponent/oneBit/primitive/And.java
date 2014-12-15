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


	
public class And extends LogicComponent implements Pushable{
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
		xCord = 0;
		yCord = 0;
		setCordinates(0,0);
		label = "And";
	}
	
	public And(ENode in_nodeX, ENode in_nodeY, SNode out_node, int x, int y){
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		in_nodeX.plugTo(this, "x");
		in_nodeY.plugTo(this, "y");
		out_node.plugTo(this, "u");
		setCordinates(x,y);
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
	
	@Override
	public void updateInOut(){
		inXCordX = xCord - width/10;
		inXCordY = yCord + height/4;
		
		inYCordX = xCord - width/10;
		inYCordY = yCord + (height/4) * 3;
		
		outUCordX = xCord + width + width/10;
		outUCordY = yCord + height/2;
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