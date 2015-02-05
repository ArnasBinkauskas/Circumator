package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**
 * Class to represent a forked wire
 * 1 input, min 2 outputs, no gate delay
 * x is input, outputs are "1" to "n" where n is an integer greater than 1
 * */

public class Fork extends OneMany implements Pushable{
	int radius = 10;
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Fork(){
		super();
		label = "Fork";
		gateDelay = 0;
	}
	/**
	 * Constructor to plug in the in/out nodes to component and 
	 * set screen coordinates to (x,y)
	 * */
	public Fork(String ID, WNode in_nodeX, WNode out_nodeU, WNode out_nodeD, Point coords){
		super(ID, in_nodeX, out_nodeU, out_nodeD, coords);
		label = "Fork";
		gateDelay = 0;
	}
	
	
	public boolean pushSignal(){
		if (super.pushSignal()){
			Signal inX = input.get("x").getSignal();
			output.get("u").getSignal().setValue(inX.getValue());
			output.get("d").getSignal().setValue(inX.getValue());
			return true;
		}else 
			return false;
		}
	
	//TODO appropriate graph
	 @Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.fillOval(center.getX() - radius/2,center.getY() - radius/2, radius, radius);
		    for (WNode in : input.values())
		    	g.drawLine(center.getX(), center.getY(), in.getCordinates().getX(), in.getCordinates().getY());
		    for (WNode out : output.values())
		    	g.drawLine(center.getX(), center.getY(), out.getCordinates().getX(), out.getCordinates().getY());
	 }
	
	 @Override
	 public void pass(Graphics g){
			 g.setColor(Color.green);
			 g.fillOval(center.getX() - radius/2,center.getY() - radius/2, radius, radius);
	 }
	
}
