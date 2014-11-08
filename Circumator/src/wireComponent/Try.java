package wireComponent;

import java.awt.geom.Point2D;

public class Try {
	public static void main(String[] args) {
		Point2D.Double c = new Point2D.Double(3, 5);
		WNode fnode = new WNode("candy", 8, c);
		System.out.println(fnode.getCords().getX());
	}
}