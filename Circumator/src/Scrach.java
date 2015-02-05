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
	static int delay = 1000;
	
	public static void main(String args[])  throws Exception{
	m = new Model();
	m.readFile(args[0]);
	build();
	new Scrach();
	}
	
	public Scrach(){
		super(m.CircuitName);
		setSize(m.windowSize.getX() + 100, m.windowSize.getY() + 50);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		       {public void windowClosing(WindowEvent e)
		          {dispose(); System.exit(0);}
		       }
		     );
	}
	 
	/**Display the circuit on screen
	 * 
	 * */
	public void paint(Graphics g) {
		 	for (String IDw: m.wire.keySet())
			  m.wire.get(IDw).paintWInfo(g);
			for(String ID : m.comp.keySet())
				  m.comp.get(ID).paint(g);
			animate(g);
	 }
	 
	 public static void animate(Graphics g){
		 try{
		 Thread.sleep(delay);
		 }catch(InterruptedException e) {
				e.printStackTrace();
		 }
		 
		 for(int i = 0; i < m.comp.size(); i++){
			 try{
				for (LogicComponent a : gateWithDeph.get(i))
					a.pass(g);
				Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
	 }
	 
	 /**Builds and displays on LUI the gateWithDepth matrix
	  * This is later used in the simulation and animation of the circuit
	  * */
		public static void build(){
			//The size will be the number of components in worst case(Linear circuit)
			gateWithDeph = new ArrayList<ArrayList<LogicComponent>>();
			for(int i = 0; i < m.comp.size() + 4; i++)
				gateWithDeph.add(new ArrayList<LogicComponent>());
			
			ArrayDeque<WNode> readyNodes = new ArrayDeque<WNode>();
			//instanciate simulation
			for (WNode starting: m.start.keySet()){
				starting.setReady(true);
				readyNodes.add(starting);
			}
			m.clock.tick();
			
			WNode buffer;
			while (!readyNodes.isEmpty()){
				buffer = readyNodes.poll();
				if (buffer.isWStart() && buffer.hasWire()){//push signal accross the wire
					buffer.getWire().pushSignal();
					readyNodes.add(buffer.getWire().getEnd());
				}else if (buffer.isWEnd() && buffer.hasComponent() && buffer.getComponent().pushSignal()){ 
						readyNodes.removeAll(buffer.getComponent().getInputs());
						readyNodes.addAll(buffer.getComponent().getOutputs());
						int gateDelay = buffer.getComponent().gateDelay;
						while (gateDelay >= 0){
							gateWithDeph.get(buffer.getComponent().pathDeph + gateDelay).add(buffer.getComponent());
							gateDelay --;
							System.out.println();
						}
					}
			}
			for(int i = 0; i < gateWithDeph.size(); i++){
				System.out.print("" + i + " ");
				for (LogicComponent a : gateWithDeph.get(i))
					System.out.print(a.toString() + " ; ");
				System.out.println();
			}			
		}
}
