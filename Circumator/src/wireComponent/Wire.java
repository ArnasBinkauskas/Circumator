package wireComponent;

import main.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Wire implements Pushable {
	String wireID;
	WNode start;
	WNode end;
	ArrayList<Point> wireCoords;
	
	public Wire(String WireID){
		wireID = WireID;
	}
	
	public Wire(String WireID, WNode Start, WNode End){
		wireID = WireID;
		start = Start;
		end = End;
		start.plugWire(this);
		end.plugWire(this);
	}
	
	public void pushSignal(){
		end.setSignal(start.getSignal());
	}
	
	public String toString(){
		return wireID + " (" + start.toString() + "," + end.toString() + ")";
	}
	
	public void setStart(WNode Start){
		start = Start;
	}
	
	public void setEnd(WNode End){
		end = End;
	}

	public void setCoordinates(ArrayList<Point> WireCords) {
		wireCoords = WireCords;
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