package wireComponent;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Wire {
	private String wireID;
	private WNode start;
	private WNode end;
	private ArrayList<Point2D> wireCords;
	
	public Wire(){
		
	}

	public Wire(String wireID, WNode Start, WNode End,
			ArrayList<Point2D> WireCords) {
		start = Start;
		end = End;
		wireCords.add(start.getCordinates());
		wireCords.addAll(WireCords);
		wireCords.add(end.getCordinates());
	}
}