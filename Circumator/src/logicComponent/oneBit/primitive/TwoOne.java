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
	int width = 40;
	int height = 24;
	
	Point corner;
	Point inXCoord;
	Point inYCoord;
	Point outUCoord;

	
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
	}
	
	public TwoOne(String gateID, WNode in_nodeX, WNode in_nodeY, WNode out_node, Point centreCoords){
		super(2,1);
		ID = gateID;
		plugInput(in_nodeX, "x");
		plugInput(in_nodeY, "y");
		plugOutput(out_node, "u");
		corner = new Point();
		inXCoord = new Point();
		inYCoord = new Point();
		outUCoord = new Point();
		setCoordinates(centreCoords);
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
	
	@Override
	public void updateCoords(){
		corner.computeFrom(center, 0 - width/2, 0 - height/2);
		
		inXCoord.computeFrom(corner, 0, height/4);
		
		inYCoord.computeFrom(corner, 0, (height/4) * 3);
		
		outUCoord.computeFrom(corner, width, height/2);
	}
	
	 @Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(corner.getX(),corner.getY(), width,height);
		    g.drawString(label, center.getX() - 8, center.getY() + 4);
		    
		    g.drawLine(inXCoord.getX(), inXCoord.getY(), 
		    			input.get("x").getCordinates().getX(), 
		    			input.get("x").getCordinates().getY());
		    g.drawLine(inYCoord.getX(), inYCoord.getY(), 
	    				input.get("y").getCordinates().getX(), 
	    				input.get("y").getCordinates().getY());
		    g.drawLine(outUCoord.getX(), outUCoord.getY(), 
	    				output.get("u").getCordinates().getX(), 
	    				output.get("u").getCordinates().getY());		       
	 }
	
}
