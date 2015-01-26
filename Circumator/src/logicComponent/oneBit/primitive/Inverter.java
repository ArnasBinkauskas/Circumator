package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Inverter in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Inverter extends LogicComponent implements Pushable{
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
	public Inverter(){
		//ENode dummy_in = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		setCordinates(new Point(0,0));
		label = "Buffer";
	}
	
	public Inverter(String gateID, WNode in_node, WNode out_node, Point centreCoords){
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		plugInput(in_node, "x");
		plugOutput(out_node, "u");
		setCordinates(centreCoords);
	}
	

	
	public void pushSignal(){
		Signal in = input.get("x").getSignal();
		Signal out = output.get("u").getSignal();
		if ((in != null) && (out != null)){
			out.setGateDelay(in.getGateDelay() + gateDelay);
			out.setValue(!in.getValue());
		}
	}
	
	@Override
	public void updateInOut(){
		inCordX = center.getX() - width/10;
		inCordY = center.getY() + height/2;
		outCordX = center.getX() + width + width/10;
		outCordY = center.getY() + height/2;
	}
	
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 18);
		    g.drawLine(center.getX(), inCordY, inCordX, inCordY);
		    g.drawLine(outCordX - width/10, outCordY, outCordX, outCordY);
	 }
	
}
