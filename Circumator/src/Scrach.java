import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import logicComponent.*;
import logicComponent.oneBit.primitive.*;
import wireComponent.*;
import wireComponent.Point;

import java.io.File;
import java.io.IOException;

public class Scrach extends JFrame {
	static Model m;
	static ArrayList<ArrayList<LogicComponent>> gateWithDeph;
	static int delay = 500;
	
	public static void main(String args[])  throws Exception{
	m = new Model();
	m.readFile(args[0]);
	simulate();
	new Scrach();
	}
	
	public Scrach(){
		super("Attempt to draw!");
		setSize(1100,250);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		       {public void windowClosing(WindowEvent e)
		          {dispose(); System.exit(0);}
		       }
		     );
	}
	
	public static void simulate(){
		//The size will be the number of components in worst case(Linear circuit)
		gateWithDeph = new ArrayList<ArrayList<LogicComponent>>();
		for(int i = 0; i < m.comp.size(); i++)
			gateWithDeph.add(new ArrayList<LogicComponent>());
		
		ArrayDeque<WNode> readyNodes = new ArrayDeque<WNode>();
		//instanciate simulation
		for (WNode starting: m.start){
			starting.setReady(true);
			readyNodes.add(starting);
		}

		WNode buffer;
		while (!readyNodes.isEmpty()){
			try {
				System.out.println(readyNodes.toString());
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer = readyNodes.poll();
			if (buffer.isWStart() && buffer.hasWire()){//push signal accross the wire
				buffer.getWire().pushSignal();
				readyNodes.add(buffer.getWire().getEnd());
			}else if (buffer.isWEnd() && buffer.hasComponent() && buffer.getComponent().pushSignal()){ 
					readyNodes.removeAll(buffer.getComponent().getInputs());
					readyNodes.addAll(buffer.getComponent().getOutputs());
					gateWithDeph.get(buffer.getComponent().pathDeph).add(buffer.getComponent());
				}
		}
		
		for(int i = 0; i < m.comp.size(); i++){
			System.out.print("" + i + " ");
			for (LogicComponent a : gateWithDeph.get(i))
				System.out.print(a.toString() + " ; ");
			System.out.println();
		}			
	}
	 
	 public void paint(Graphics g) {
		 
		 	for (String IDw: m.wire.keySet()){
			  m.wire.get(IDw).paintWInfo(g);
			/*  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}*/
		 	}
			  for(String ID : m.comp.keySet()){
				  //System.out.println(ID);
				  m.comp.get(ID).paint(g);
		 	}
			 
	 }
	
	
}
