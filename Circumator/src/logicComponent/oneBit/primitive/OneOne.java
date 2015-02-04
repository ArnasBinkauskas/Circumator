package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public abstract class OneOne extends LogicComponent implements Pushable {
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int width = 40;
	int height = 24;
	
	Point corner;
	Point inCoord;
	Point outCoord;
	
	int inCordX ;
	int inCordY ;
	
	int outCordX;
	int outCordY ;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public OneOne(){
		//ENode dummy_in = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		setCoordinates(new Point(0,0));
	}
	
	public OneOne(String gateID, WNode in_node, WNode out_node, Point centreCoords){
		super(1,1);
		ID = gateID;
		plugInput(in_node, "in");
		plugOutput(out_node, "out");
		corner = new Point();
		inCoord = new Point();
		outCoord = new Point();
		setCoordinates(centreCoords);
	}
	

	public void updateInOut(){
		inCordX = center.getX() - width/5;
		inCordY = center.getY() + height/2;
		outCordX = center.getX() + width + width/5;
		outCordY = center.getY() + height/2;
	}
	
	public boolean pushSignal(){
		WNode in = input.get("in");
		WNode out = output.get("out");
		if (in.isReady()){
			pathDeph = in.getSignal().getGateDelay();
			out.getSignal().setGateDelay(pathDeph + gateDelay);
			out.setReady(true);
			return true;
		}else 
			return false;
	}
	
	@Override
	public void updateCoords(){
		corner.computeFrom(center, 0 - width/2, 0 - height/2);
		
		inCoord.computeFrom(corner, 0, height/2);
		
		outCoord.computeFrom(corner, width, height/2);
	}
	
	 @Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(corner.getX(),corner.getY(), width,height);
		    g.drawString(label, center.getX() - 15, center.getY() + 4);
		    
		    g.drawLine(inCoord.getX(), inCoord.getY(), 
		    			input.get("in").getCordinates().getX(), 
		    			input.get("in").getCordinates().getY());
		    g.drawLine(outCoord.getX(), outCoord.getY(), 
	    				output.get("out").getCordinates().getX(), 
	    				output.get("out").getCordinates().getY());		       
	 }
}
