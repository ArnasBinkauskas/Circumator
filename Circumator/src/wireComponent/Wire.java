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
		wireCoords = new ArrayList<Point>();
	}
	
	public Wire(String WireID, WNode Start, WNode End){
		wireID = WireID;
		start = Start;
		end = End;
		wireCoords = new ArrayList<Point>();
		start.plugWire(this);
		end.plugWire(this);
	}
	
	public boolean pushSignal(){
		if (start.isReady()){
			end.setSignal(start.getSignal());
			end.getSignal().setPathDepth(start.getSignal().getPathDepth());
			end.setReady(true);
			return true;
		}else 
			return false;
	}
	
	public String toString(){
		return wireID + " (" + start.toString() + "," + end.toString() + ")";
	}
	
	public WNode getStart(){
		return start;
	}
	
	public WNode getEnd(){
		return end;
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
		    Point to;
		    for (Point buffer : wireCoords){
		    	to = buffer;
		    	g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
		    	from = to;
		    }
		    to = end.getCordinates();
		    g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
	 }
	 
	public void paintWInfo(Graphics g) {
	    g.setColor(Color.black);
	    Point from = start.getCordinates();
	    start.paint(g);
	    Point to;
	    for (Point buffer : wireCoords){
	    	to = buffer;
	    	g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
	    	from = to;
	    }
	    to = end.getCordinates();
	    end.paint(g);
	    g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
 }
	public void paintWValues(Graphics g){
		g.setColor(Color.black);
	    Point from = start.getCordinates();
	    start.paintValue(g);
	    Point to;
	    for (Point buffer : wireCoords){
	    	to = buffer;
	    	g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
	    	from = to;
	    }
	    to = end.getCordinates();
	    end.paintValue(g);
	    g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
	}
	 
}