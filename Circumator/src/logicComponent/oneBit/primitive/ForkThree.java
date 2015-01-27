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

public class ForkThree extends OneMany implements Pushable{
	/**
	 * No value constructor- initialise input/output names
	 * */
	public ForkThree(){
		super();
		label = "Fork3";
		gateDelay = 0;
	}
	/**
	 * Constructor to plug in the in/out nodes to component and 
	 * set screen coordinates to (x,y)
	 * */
	public ForkThree(String ID, WNode in_nodeX, WNode out_nodeU, WNode out_nodeM, WNode out_nodeD, Point coords){
		super(ID, in_nodeX, out_nodeU, out_nodeM, out_nodeD, coords);
		label = "Fork3";
		gateDelay = 0;
	}
	
	//TODO appropriate graph
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inXCordY, inXCordX, inXCordY);
	 }
	
	
}