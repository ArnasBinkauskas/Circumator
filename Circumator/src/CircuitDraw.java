import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import logicComponent.LogicComponent;
import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

public class CircuitDraw extends JFrame {
	
	public static void main(String args[]){
		ArrayList<ArrayList<Integer>> gateWithDeph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 10; i++){
			ArrayList<Integer> a = new ArrayList<Integer>();
			gateWithDeph.add(a);
			}
		gateWithDeph.get(1).add(666);
		gateWithDeph.get(1).add(12);
		gateWithDeph.get(1).add(18);
		gateWithDeph.get(5).add(6);
		gateWithDeph.get(2).add(14);
		for (ArrayList<Integer> a : gateWithDeph)
			for (Integer t:a)
				System.out.println(t);
		
			//new CircuitDraw();
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
		 
	}
}