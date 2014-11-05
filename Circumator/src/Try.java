import java.awt.geom.Point2D;

public class Try {

	public static void main(String[] args) {
		
		Point2D.Float c = new Point2D.Float(3, 5);
		
		WNode fnode = new WNode("candy", 8, c);
		
		System.out.println(fnode.getCords().getX());

	}

}
