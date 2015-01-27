package logicComponent.oneBit.primitive;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;


public class OneMany extends LogicComponent {
	int width = 50;
	int height = 25;
	
	int inXCordX;
	int inXCordY;
	
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public OneMany(){
		super(1,2);
		input.put("x", null);
		output.put("1", null);
		output.put("2", null);
		setCordinates(new Point(0,0));
	}
	
	public OneMany(WNode in_nodeX, WNode out_nodeU, WNode out_nodeD, Point c){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		plugOutput(out_nodeU, "u");
		plugOutput(out_nodeD, "d");
		setCordinates(c);
	}
	
	public OneMany(WNode in_nodeX, WNode out_nodeU, WNode out_nodeM, WNode out_nodeD, Point c){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		plugOutput(out_nodeU, "u");
		plugOutput(out_nodeM, "m");
		plugOutput(out_nodeD, "d");
		setCordinates(c);
	}
	
	/**
	 * Constructor to plug in the in/out nodes to component and 
	 * set screen coordinates to (x,y)
	 * */
	public OneMany(WNode in_nodeX, ArrayList<WNode> out_node, Point c){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		for(Integer i = 0; i < out_node.size(); i++){
			output.put(i.toString(), null);
			plugOutput(out_node.get(i), i.toString());
		}
		setCordinates(c);
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
	
	@Override
	public void updateInOut(){
		inXCordX = center.getX() - width/10;
		inXCordY = center.getY() + height/4;
	
	}
	
}
