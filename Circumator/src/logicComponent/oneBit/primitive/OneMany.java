package logicComponent.oneBit.primitive;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;


public class OneMany extends LogicComponent implements Pushable{
	
	/**
	 * No value constructor- initialise input/output names
	 * */
	public OneMany(){
		super(1,2);
		input.put("x", null);
		output.put("1", null);
		output.put("2", null);
		setCoordinates(new Point(0,0));
	}
	
	
	public OneMany(String cID, WNode in_nodeX, WNode out_nodeU, WNode out_nodeD, Point c){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		plugOutput(out_nodeU, "u");
		plugOutput(out_nodeD, "d");
		ID = cID;
		setCoordinates(c);
	}
	
	
	public OneMany(String cID, WNode in_nodeX, WNode out_nodeU, WNode out_nodeM, WNode out_nodeD, Point c){
		super(1,2);
		input.put("x", null);
		plugInput(in_nodeX, "x");
		plugOutput(out_nodeU, "u");
		plugOutput(out_nodeM, "m");
		plugOutput(out_nodeD, "d");
		setCoordinates(c);
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
		setCoordinates(c);
	}
	
	public boolean pushSignal(){
		WNode inX = input.get("x");
		if (inX.isReady()){
			pathDeph = inX.getSignal().getPathDepth(); 
			Iterator outNode = output.values().iterator();
			while (outNode.hasNext()){
				WNode nextBranch = (WNode)outNode.next();
				nextBranch.getSignal().setPathDepth(inX.getSignal().getPathDepth() + gateDelay);
				nextBranch.setReady(true);
				}
			return true;
		}else
			return false;
	}
}
