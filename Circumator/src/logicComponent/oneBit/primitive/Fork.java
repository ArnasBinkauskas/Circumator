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

public class Fork extends LogicComponent implements Pushable{
	int width = 50;
	int height = 25;
	
	int inXCordX = xCord - width/10;
	int inXCordY = yCord + height/2;
	
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Fork(){
		super(1,2);
		input.put("x", null);
		output.put("1", null);
		output.put("2", null);
		label = "Fork";
		setCordinates(0,0);
		gateDelay = 0;
	}
	/**
	 * Constructor to plug in the in/out nodes to component and 
	 * set screen coordinates to (x,y)
	 * */
	public Fork(WNode in_nodeX, ArrayList<WNode> out_node, int x, int y){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		for(Integer i = 0; i < out_node.size(); i++){
			output.put(i.toString(), null);
			plugOutput(out_node.get(i), i.toString());
		}
		label = "Fork";
		setCordinates(x,y);
		gateDelay = 0;
	}
	
	@Override
	public void updateInOut(){
		inXCordX = xCord - width/10;
		inXCordY = yCord + height/4;
	
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
	
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(xCord,yCord, width,height);
		    g.drawString(label, xCord + 8, yCord + 18);
		    g.drawLine(xCord, inXCordY, inXCordX, inXCordY);
	 }
	
	
}
