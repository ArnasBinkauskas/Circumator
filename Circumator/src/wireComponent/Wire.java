package wireComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Wire {
	String wireID;
	WNode start;
	WNode end;
	ArrayList<Point> wireCoords;
	
	public Wire(){
		
	}

	public Wire(String wireID, WNode Start, WNode End,
			ArrayList<Point> WireCords) {
		start = Start;
		end = End;
		//wireCords.add(start.getCordinates());
		wireCoords.addAll(WireCords);
		//wireCords.add(end.getCordinates());
	} 
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    Point from = start.getCordinates();
		    Point to = wireCoords.get(0);
		    for (int i = 1; i < wireCoords.size(); i++){
		    	
		    }
		    	
	 }
}