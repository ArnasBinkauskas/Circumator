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
	
	int inXCordX ;
	int inXCordY ;
	
	int inYCordX ;
	int inYCordY ;
	
	int outUCordX ;
	int outUCordY ;
	
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
		setCordinates(new Point (0,0));
		label = "And";
	}
	
	public And(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(2,1);
		ID = gateID;
		plugInput(in_nodeX, "x");
		plugInput(in_nodeY, "y");
		plugOutput(out_node, "u");
		setCordinates(centreCoords);
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
		inXCordX = center.getX() - width/10;
		inXCordY = center.getY() + height/4;
		
		inYCordX = center.getX() - width/10;
		inYCordY = center.getY() + (height/4) * 3;
		
		outUCordX = center.getX() + width + width/10;
		outUCordY = center.getY() + height/2;
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