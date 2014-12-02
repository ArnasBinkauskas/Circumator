package logicComponent.oneBit.primitive;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import logicComponent.LogicComponent;
import wireComponent.*;
import main.*;
/**Class to represent a 1-bit Buffer in a circuit
 * Input of this component is going to be called "x"
 * Output of this component is going to be called "u"
 * */
public class Buffer extends LogicComponent implements Pushable{
	/*From LogicComponent
	 * Map<String, ENode> input; 
	 * Map<String, SNode> output;
	 * */
	int width = 50;
	int height = 25;
	
	int inCordX = xCord - 5;
	int inCordY = yCord + 5;
	
	int outCordX = xCord - 5;
	int outCordY = yCord + 5;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Buffer(){
		//ENode dummy_in = new ENode(new Wire(),"dummy");
		//SNode dummy_out = new SNode(new Wire(),"dummy");
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		xCord = 0;
		yCord = 0;
		label = "Buffer";
	}
	
	public Buffer(ENode in_node, SNode out_node, int xDraw, int yDraw){
		super(1,1);
		input.put("x", null);
		output.put("u", null);
		in_node.plugTo(this, "x");
		out_node.plugTo(this, "u");
		xCord = xDraw;
		yCord = yDraw;
	}
	

	
	public void pushSignal(){
		Signal in = input.get("x").getSignal();
		Signal out = output.get("u").getSignal();
		if ((in != null) && (out != null)){
			out.setGateDelay(in.getGateDelay() + gateDelay);
			out.setValue(in.getValue());
		}
	}
	
	@Override
	public void updateInOut(){
		inCordX = xCord - 7;
		inCordY = yCord + 5;
	}
	
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(xCord,yCord,50,25);
		    g.drawString("x , y", xCord, yCord);
		    g.drawString("in", inCordX, inCordY);
		    g.drawString(label, xCord + 8, yCord + 18);
		    g.drawLine(xCord, inCordY, inCordX, inCordY);
	 }
	
}
