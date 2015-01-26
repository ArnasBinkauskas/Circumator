import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

public class CircuitDraw extends JFrame {
	
	public static void main(String args[]){
			new CircuitDraw();
	}
	
	public CircuitDraw(){
		super("Attempt to draw!");
		setSize(400,300);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		       {public void windowClosing(WindowEvent e)
		          {dispose(); System.exit(0);}
		       }
		     );
	}
	 
	 public void paint(Graphics g) {
		   ArrayList<Point> f = new ArrayList<Point>();
		   f.add(new Point(50, 60));
		   f.add(new Point(50, 40));
		   f.add(new Point(70, 40));
		   Wire b = new Wire("b");
		   WNode Start = new WNode("s");
		   Start.setCordinates(new Point(40,60));
		   WNode End = new WNode("e");
		   End.setCordinates(new Point(72, 45));
		   b.setStart(Start);
		   b.setCoordinates(f);
		   b.setEnd(End);
		   b.paint(g);
	}
}