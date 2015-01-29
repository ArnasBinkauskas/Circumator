package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import wireComponent.Point;
import wireComponent.WNode;

public abstract class OneOne extends LogicComponent {
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int width = 50;
	int height = 25;
	
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
		setCordinates(new Point(0,0));
	}
	
	public OneOne(String gateID, WNode in_node, WNode out_node, Point centreCoords){
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		plugInput(in_node, "x");
		plugOutput(out_node, "u");
		setCordinates(centreCoords);
	}
	
	@Override
	public void updateInOut(){
		inCordX = center.getX() - width/5;
		inCordY = center.getY() + height/2;
		outCordX = center.getX() + width + width/5;
		outCordY = center.getY() + height/2;
	}
	
	 @Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inCordY, inCordX, inCordY);
		    g.drawLine(outCordX - width/5, outCordY, outCordX, outCordY);
	 }
}
