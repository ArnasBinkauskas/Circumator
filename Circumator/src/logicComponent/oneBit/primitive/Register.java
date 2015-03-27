package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Register extends LogicComponent implements Pushable, Clocked{
	Signal memory;
	
	boolean justTicked;
	
	int width = 40;
	int height = 64;
	
	Point corner;
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
		justTicked = false;
		label = "Reg";
		gateDelay = 3;
		painted = 0;
		ID = gateID;
		plugInput(data, "d");
		plugInput(ldControl, "ldc");
		plugInput(clock, "clock");
		plugOutput(output, "out");
		corner = new Point();
		ldCoord = new Point();
		dataCoord = new Point();
		outCoord = new Point();
		setCoordinates(centerCoords);
	}
	
	/**attempts to push the signals over the gate iff both of the input nodes are ready
	*Updates pathDeph of the gate and the gate delay of the output signal in the process
	*Also updates the output signal value according to the gate logic
	*returns false if one of the inputs is not ready
	*/
	@Override
	public boolean pushSignal(){
		if (super.pushSignal() && justTicked){
			Signal inLd = input.get("ldc").getSignal();
			Signal inData = input.get("d").getSignal();
			Signal out = output.get("out").getSignal();
			if (inLd.getValue())
				memory.setValue(inData.getValue());
			out.setValue(memory.getValue());
			out.setPathDepth(pathDeph + gateDelay);
			justTicked = false;
			return true;
		}else 
			return false;
	}

	@Override
	public void updateCoords(){
		
		corner.computeFrom(center, 0 - width/2, 0 - height/2);
		
		dataCoord.computeFrom(corner, 0, height/4);
		
		ldCoord.computeFrom(corner, 0, (height/4) * 3);
		
		outCoord.computeFrom(corner, width, height/2);
	}
	
	public void tick(){
		justTicked = true;
	}
	
	
	@Override
	 public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.drawRect(corner.getX(),corner.getY(), width,height);
	    g.drawString(label, center.getX() - 8, center.getY() + 4);
	    
	    g.drawLine(dataCoord.getX(), dataCoord.getY(), 
	    			input.get("d").getCordinates().getX(),
	    			input.get("d").getCordinates().getY());
	    g.drawLine(ldCoord.getX(), ldCoord.getY(), 
    			input.get("ldc").getCordinates().getX(),
    			input.get("ldc").getCordinates().getY());
	    g.drawLine(outCoord.getX(), outCoord.getY(), 
    			output.get("out").getCordinates().getX(),
    			output.get("out").getCordinates().getY());
	 }
}
