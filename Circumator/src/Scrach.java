import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

import java.io.File;
import java.io.IOException;

public class Scrach extends JFrame {
	static Model m;
	
	public static void main(String args[])  throws IOException{
	m = new Model();
	m.readFile(args[0]);
	new Scrach();
	}
	
	public Scrach(){
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
		   for(String ID : m.comp.keySet()){
			   System.out.println(ID);
			   m.comp.get(ID).paint(g);
		   }
		   //for(String ID : m.comp.keySet())
			 //  m.comp.get(ID).paint(g);
	}
	
	
}
