package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import wireComponent.Point;
import wireComponent.WNode;
import main.Pushable;
import main.Signal;

public abstract class TwoOne extends LogicComponent implements Pushable{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int width = 50;
	int height = 24;
	
	int inXCordX ;
	int inXCordY ;
	
	int inYCordX ;
	int inYCordY ;
	
	int outUCordX ;
	int outUCordY ;
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public TwoOne(){
		//ENode dummy_inX = new ENode(new Wire(),"dummy");
		//ENode dummy_inY = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(2,1);
		input.put("x", null);
		input.put("y", null);
		output.put("u", null);
		setCordinates(new Point (0,0));
	}
	
	public TwoOne(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(2,1);
		ID = gateID;
		plugInput(in_nodeX, "x");
		plugInput(in_nodeY, "y");
		plugOutput(out_node, "u");
		setCordinates(centreCoords);
	}
	
	/**attempts to push the signals over the gate iff both of the input nodes are ready
	*Updates pathDeph of the gate and the gate delay of the output signal in the process
	*returns false if one of the inputs is not ready
	*/
	public boolean pushSignal(){
		WNode inX = input.get("x");
		WNode inY = input.get("y");
		WNode out = output.get("u");
		boolean inputsReady = inX.isReady() && inY.isReady();
		if (inputsReady){
			int signalPathDeph = Math.max(inX.getSignal().getGateDelay(), inY.getSignal().getGateDelay());
			pathDeph = signalPathDeph;
			out.getSignal().setGateDelay(signalPathDeph + gateDelay);
			out.setReady(true);
		}
		return inputsReady;
	}
	
	public void setGateDelay(int value){
		gateDelay = value;
	}
	
	public void updateInOut(){
		inXCordX = center.getX() - width/5;
		inXCordY = center.getY() + height/4;
		
		inYCordX = center.getX() - width/5;
		inYCordY = center.getY() + (height/4) * 3;
		
		outUCordX = center.getX() + width + width/5;
		outUCordY = center.getY() + height/2;
	}
	
	 @Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inXCordY, inXCordX, inXCordY);
		    g.drawLine(center.getX(), inYCordY, inYCordX, inYCordY);
		    g.drawLine(outUCordX - width/5, outUCordY, outUCordX, outUCordY);
	 }
	
}
