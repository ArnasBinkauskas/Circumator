package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Register extends LogicComponent implements Pushable{
	Signal memory;
	
	int width = 50;
	int height = 64;
	
	Point dataCoord;
	Point ldCoord;
	Point outCoord;
	/**
	 * No value constructor- initialise input/output names
	 * */
	public Register(){
		super(3,1);
		label = "Reg";
		gateDelay = 3;
		memory = new Signal();
	}
	
	public Register(String gateID, WNode data, WNode ldControl, WNode clock, WNode output, 
			Point centerCoords){
		super(3,1);
		memory = new Signal();
		label = "Mux";
		gateDelay = 3;
		ID = gateID;
		center = centerCoords;
		plugInput(data, "d");
		plugInput(ldControl, "1d");
		plugInput(clock, "clock");
		plugOutput(output, "out");
		ldCoord = new Point(0,0);
		dataCoord = new Point(0,0);
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
			Signal inLd = input.get("ld").getSignal();
			Signal inData = input.get("d").getSignal();
			Signal out = output.get("out").getSignal();
			if (inLd.getValue())
				memory.setValue(inData.getValue());
			out.setValue(memory.getValue());
			out.setGateDelay(pathDeph + gateDelay);
			return true;
		}else 
			return false;
	}
	@Override
	public void updateInOut(){
		
		dataCoord.setX(center.getX() - width/5);
		dataCoord.setY(center.getY() + height/4);
		
		ldCoord.setX(center.getX() - width/5);
		ldCoord.setY(center.getY() + (height/4) * 3);
		
		outCoord.setX(center.getX() + width + width/5);
		outCoord.setY(center.getY() + height/2);
	}
	
	
	@Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(center.getX(),center.getY(), width,height);
		    g.drawString(label, center.getX() + 8, center.getY() + 24);
		    g.drawLine(center.getX(), dataCoord.getY(), dataCoord.getX(), dataCoord.getY());
		    g.drawLine(center.getX(), ldCoord.getY(), ldCoord.getX(), ldCoord.getY());
		    g.drawLine(outCoord.getX() - width/5, outCoord.getY(), outCoord.getX(), outCoord.getY());
	 }
}
