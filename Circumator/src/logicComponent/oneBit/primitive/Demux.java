package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Demux extends LogicComponent implements Pushable{
	int width = 40;
	int height = 32;
	
	Point corner;
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
	
	public Demux(String gateID,  WNode dataIn, WNode loadIn, WNode output1, WNode output2, 
			Point centerCoords){
		super(2,2);
		label = "dmux";
		gateDelay = 2;
		ID = gateID;
		plugInput(loadIn, "ldc");
		plugInput(dataIn, "d");
		plugOutput(output1, "out0");
		plugOutput(output2, "out1");
		corner = new Point();
		ldCoord = new Point();
		dataCoord = new Point();
		out0Coord = new Point();
		out1Coord = new Point();
		setCoordinates(centerCoords);
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
			Signal inData = input.get("d").getSignal();
			Signal out0 = output.get("out0").getSignal();
			Signal out1 = output.get("out1").getSignal();
			if (inLd.getValue())
				out1.setValue(inData.getValue());
			else 
				out0.setValue(inData.getValue());
			out0.setPathDepth(pathDeph + gateDelay);
			out1.setPathDepth(pathDeph + gateDelay);
			return true;
		}else 
			return false;
	}
	
	@Override
	public void updateCoords(){
		
		corner.computeFrom(center, 0 - width/2, 0 - height/2);
		
		dataCoord.computeFrom(corner, 0, height/2);
		
		ldCoord.computeFrom(corner, width/2, height);
		
		out0Coord.computeFrom(corner, width, height/4);
		out1Coord.computeFrom(corner, width, (height/4) * 3);
	}
	
	
	@Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(corner.getX(),corner.getY(), width,height);
		    g.drawString(label, center.getX() - 15, center.getY() + 4);
		    
		    g.drawLine(dataCoord.getX(), dataCoord.getY(), 
		    			input.get("d").getCordinates().getX(),
		    			input.get("d").getCordinates().getY());
		    g.drawLine(ldCoord.getX(), ldCoord.getY(), 
	    			input.get("ldc").getCordinates().getX(),
	    			input.get("ldc").getCordinates().getY());
		    g.drawLine(out0Coord.getX(), out0Coord.getY(), 
	    			output.get("out0").getCordinates().getX(),
	    			output.get("out0").getCordinates().getY());
		    g.drawLine(out1Coord.getX(), out1Coord.getY(), 
	    			output.get("out1").getCordinates().getX(),
	    			output.get("out1").getCordinates().getY());
	 }
	

	
}
