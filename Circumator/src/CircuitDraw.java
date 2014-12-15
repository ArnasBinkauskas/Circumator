import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import logicComponent.oneBit.primitive.*;

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
		    //Here is how we used to draw a square with width
		    //of 200, height of 200, and starting at x=50, y=50.
		   Inverter b = new Inverter();
		   b.setCordinates(80, 80);
		   b.paint(g);
	}
}