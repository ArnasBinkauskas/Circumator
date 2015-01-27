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
	public Fork(WNode in_nodeX, WNode out_nodeU, WNode out_nodeD, Point coords){
		super(in_nodeX, out_nodeU, out_nodeD, coords);
		label = "Fork";
		gateDelay = 0;
	}
	
	
	public void pushSignal(){
		Signal inX = input.get("x").getSignal();
		Iterator outNode = output.values().iterator();
		while (outNode.hasNext()){
			WNode nextBranch = (WNode)outNode.next();
			nextBranch.getSignal().setGateDelay(inX.getGateDelay());
			nextBranch.getSignal().setValue(inX.getValue());;
			}
		}
	
	//TODO appropriate graph
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inXCordY, inXCordX, inXCordY);
	 }
	
	
}
