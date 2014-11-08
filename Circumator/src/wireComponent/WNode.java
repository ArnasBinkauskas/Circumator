package wireComponent;
import java.awt.geom.Point2D;

public class WNode {
	private String nodeID;
	private int signal; // holds the signal on this node at current time
	private Point2D.Double cordinates;

	public WNode(String NodeID, int Signal, Point2D.Double cords) {
		nodeID = NodeID;
		signal = Signal;
		cordinates = (Point2D.Double) cords;
	}

	public WNode(String NodeID, int Signal, double xCordinate, double yCordinate) {
		nodeID = NodeID;
		signal = Signal;
		cordinates = new Point2D.Double(xCordinate, yCordinate);
	}

	public Point2D getCords() {
		return cordinates;
	}
}