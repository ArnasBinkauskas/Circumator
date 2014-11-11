package wireComponent;

import java.awt.geom.Point2D;
import logicComponent.oneBit.primitive.*;

public class Try {
	public static void main(String[] args) {
		ENode in = new ENode(null, "this is Input to buffer"); 
		SNode out = new SNode(null, "this is output to buffer");
		Buffer n = new Buffer(in, out);
	}
}