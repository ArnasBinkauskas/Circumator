package wireComponent;
import java.awt.geom.Point2D;
import main.Signal;

public abstract class  WNode {
	public String nodeID;
	Signal signal_value; // holds the signal on this node at current time
	Point2D.Double cordinates;

	public Point2D getCords() {
		return cordinates;
	}
}