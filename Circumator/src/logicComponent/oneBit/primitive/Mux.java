package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Mux extends LogicComponent implements Pushable{
	int width = 50;
	int height = 32;
	
	Point ldCoord;
	Point data0Coord;
	Point data1Coord;
	Point outCoord;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Mux(){
		super(3,1);
		label = "Mux";
		gateDelay = 3;
	}
	
	public Mux(String gateID, WNode data0, WNode data1, WNode ldControl, WNode output, 
			Point centerCoords){
		super(3,1);
		label = "Mux";
		gateDelay = 3;
		ID = gateID;
		center = centerCoords;
		plugInput(data0, "d0");
		plugInput(data1, "d1");
		plugInput(ldControl, "ldc");
		plugOutput(output, "out");
		ldCoord = new Point(0,0);
		data0Coord = new Point(0,0);
		data1Coord = new Point(0,0);
		outCoord = new Point(0,0);
	}
	
	/**attempts to push the signals over the gate iff both of the input nodes are ready
	*Updates pathDeph of the gate and the gate delay of the output signal in the process
	*Also updates the output signal value according to the gate logic
	*returns false if one of the inputs is not ready
	*/
	@Override
	public boolean pushSignal(){
		if (super.pushSignal()){
			Signal inLd = input.get("ldc").getSignal();
			Signal inData0 = input.get("d0").getSignal();
			Signal inData1 = input.get("d1").getSignal();
			Signal out = output.get("out").getSignal();
			out.setValue(inLd.getValue() && inData0.getValue() ||
						 (!inLd.getValue()) && inData1.getValue());
			out.setGateDelay(pathDeph + gateDelay);
			return true;
		}else 
			return false;
	}
	
	public void updateInOut(){
		
		data0Coord.setX(center.getX() - width/5);
		data0Coord.setY(center.getY() + height/4);
		
		data1Coord.setX(center.getX() - width/5);
		data1Coord.setY(center.getY() + height/2);
		
		ldCoord.setX(center.getX() - width/5);
		ldCoord.setY(center.getY() + (height/8) * 7);
		
		outCoord.setX(center.getX() + width + width/5);
		outCoord.setY(center.getY() + height/2);
	}
	
	
	@Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 24);
		    g.drawLine(center.getX(), data0Coord.getY(), data0Coord.getX(), data0Coord.getY());
		    g.drawLine(center.getX(), data1Coord.getY(), data1Coord.getX(), data1Coord.getY());
		    g.drawLine(center.getX(), ldCoord.getY(), ldCoord.getX(), ldCoord.getY());
		    g.drawLine(outCoord.getX() - width/5, outCoord.getY(), outCoord.getX(), outCoord.getY());
	 }
}