package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Demux extends LogicComponent implements Pushable{
	int width = 50;
	int height = 32;
	
	Point ldCoord;
	Point dataCoord;
	Point out0Coord;
	Point out1Coord;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Demux(){
		super(2,2);
		label = "Demux";
		gateDelay = 2;
	}
	
	public Demux(String gateID, WNode loadIn, WNode dataIn, WNode output1, WNode output2, 
			Point centerCoords){
		super(2,2);
		label = "demux";
		gateDelay = 2;
		ID = gateID;
		center = centerCoords;
		plugInput(loadIn, "ld");
		plugInput(dataIn, "d");
		plugOutput(output1, "out0");
		plugOutput(output2, "out1");
		ldCoord = new Point(0,0);
		dataCoord = new Point(0,0);
		out0Coord = new Point(0,0);
		out1Coord = new Point(0,0);
	}
	
	/**attempts to push the signals over the gate iff both of the input nodes are ready
	*Updates pathDeph of the gate and the gate delay of the output signal in the process
	*Also updates the output signal value according to the gate logic
	*returns false if one of the inputs is not ready
	*/
	@Override
	public boolean pushSignal(){
		if (super.pushSignal()){
			Signal inLd = input.get("ld").getSignal();
			Signal inData = input.get("d").getSignal();
			Signal out0 = output.get("out0").getSignal();
			Signal out1 = output.get("out1").getSignal();
			out0.setValue(inLd.getValue() && inData.getValue());
			out0.setGateDelay(pathDeph + gateDelay);
			out1.setValue((!inLd.getValue()) && inData.getValue());
			out1.setGateDelay(pathDeph + gateDelay);
			return true;
		}else 
			return false;
	}

	public void updateInOut(){
		
		dataCoord.setX(center.getX() - width/5);
		dataCoord.setY(center.getY() + height/4);
		
		ldCoord.setX(center.getX() - width/5);
		ldCoord.setY(center.getY() + (height/4) * 3);
		
		out0Coord.setX(center.getX() + width + width/5);
		out0Coord.setY(center.getY() + height/4);
		
		out1Coord.setX(center.getX() + width + width/5);
		out1Coord.setY(center.getY() + (height/4) * 3);
	}
	
	
	@Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 24);
		    g.drawLine(center.getX(), dataCoord.getY(), dataCoord.getX(), dataCoord.getY());
		    g.drawLine(center.getX(), ldCoord.getY(), ldCoord.getX(), ldCoord.getY());
		    g.drawLine(out0Coord.getX() - width/5, out0Coord.getY(), out0Coord.getX(), out0Coord.getY());
		    g.drawLine(out1Coord.getX() - width/5, out1Coord.getY(), out1Coord.getX(), out1Coord.getY());
	 }
	

	
}
