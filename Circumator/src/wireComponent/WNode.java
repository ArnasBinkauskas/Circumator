package wireComponent;
import java.awt.geom.Point2D;
import main.Signal;

public class WNode {
	private String nodeID;
	private Signal signal_value; // holds the signal on this node at current time
	private Point2D.Double cordinates;

	public WNode(String NodeID, Point2D.Double cords) {
		nodeID = NodeID;
		signal_value = new Signal();
		cordinates = (Point2D.Double) cords;
	}

	public WNode(String NodeID, double xCordinate, double yCordinate) {
		nodeID = NodeID;
		signal_value = new Signal();
		cordinates = new Point2D.Double(xCordinate, yCordinate);
	}

	public Point2D getCords() {
		return cordinates;
	}
}