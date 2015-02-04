package logicComponent.oneBit.primitive;

import java.awt.Color;
import java.awt.Graphics;

import logicComponent.*;
import main.Signal;
import wireComponent.Point;
import wireComponent.WNode;
import main.*;

public class Mux extends LogicComponent implements Pushable{
	int width = 40;
	int height = 32;
	
	Point corner;
	
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
		plugInput(data0, "d0");
		plugInput(data1, "d1");
		plugInput(ldControl, "ldc");
		plugOutput(output, "out");
		corner = new Point();
		ldCoord = new Point();
		data0Coord = new Point();
		data1Coord = new Point();
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
	
	@Override
	public void updateCoords(){
		
		corner.computeFrom(center, 0 - width/2, 0 - height/2);
		
		data0Coord.computeFrom(corner, 0, height/4);
		
		data1Coord.computeFrom(corner, 0, (height/4) * 3);
		
		ldCoord.computeFrom(corner, width/2, height); //bottom of square
		
		outCoord.computeFrom(corner, width, height/2);
	}
	
	
	@Override
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    g.drawRect(corner.getX(),corner.getY(), width,height);
		    g.drawString(label, center.getX() - 8, center.getY() + 4);
		    
		    g.drawLine(data0Coord.getX(), data0Coord.getY(), 
		    			input.get("d0").getCordinates().getX(),
		    			input.get("d0").getCordinates().getY());
		    g.drawLine(data1Coord.getX(), data1Coord.getY(), 
	    			input.get("d1").getCordinates().getX(),
	    			input.get("d1").getCordinates().getY());
		    g.drawLine(ldCoord.getX(), ldCoord.getY(), 
	    			input.get("ldc").getCordinates().getX(),
	    			input.get("ldc").getCordinates().getY());
		    g.drawLine(outCoord.getX(), outCoord.getY(), 
	    			output.get("out").getCordinates().getX(),
	    			output.get("out").getCordinates().getY());
	 }
}