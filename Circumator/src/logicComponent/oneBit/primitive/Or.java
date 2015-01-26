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
	
	int inXCordX ;
	int inXCordY;
	
	int inYCordX ;
	int inYCordY ;
	
	int outUCordX ;
	int outUCordY ;
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Or(){
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		label = "Or";
		setCordinates(new Point(0,0));
	}
	
	public Or(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(2,1);
		ID = gateID;
		plugInput(in_nodeX, "x");
		plugInput(in_nodeY, "y");
		plugOutput(out_node, "u");
		label = "Or";
		setCordinates(centreCoords);
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	
	@Override
	public void updateInOut(){
		inXCordX = center.getX() - width/10;
		inXCordY = center.getY() + height/4;
		
		inYCordX = center.getX() - width/10;
		inYCordY = center.getY() + (height/4) * 3;
		
		outUCordX = center.getX() + width + width/10;
		outUCordY = center.getY() + height/2;
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
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inXCordY, inXCordX, inXCordY);
		    g.drawLine(center.getX(), inYCordY, inYCordX, inYCordY);
		    g.drawLine(outUCordX - width/10, outUCordY, outUCordX, outUCordY);
	 }
	
}