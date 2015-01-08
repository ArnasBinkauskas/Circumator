package wireComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Wire {
	String wireID;
	WNode start;
	WNode end;
	ArrayList<Point> wireCoords;
	
	public Wire(String WireID){
		wireID = WireID;
	}
	
	public void  setStart(WNode Start){
		start = Start;
	}
	
	public void setEnd(WNode End){
		end = End;
	}

	public void setCoordinates(ArrayList<Point> WireCords) {
		//wireCords.add(start.getCordinates());
		wireCoords = WireCords;
		//wireCords.add(end.getCordinates());
	} 
	
	 public void paint(Graphics g) {
		    g.setColor(Color.black);
		    Point from = start.getCordinates();
		    Iterator cIter = wireCoords.iterator();
		    Point to;
		    while (cIter.hasNext()){
		    	to = (Point)cIter.next();
		    	g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
		    	from = to;
		    }
		    to = end.getCordinates();
		    g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
	 }
}