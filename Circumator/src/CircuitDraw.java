
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import wireComponent.WNode;
import logicComponent.LogicComponent;

public class CircuitDraw extends JPanel implements ActionListener {
  Timer timer;
  Model m;
  ArrayList<ArrayList<LogicComponent>> gateWithDeph;
  ArrayList<WNode> startFilter;
  int delay = 2000;
  int animationStep;
  int animationBound;
  int clockCount;

  public CircuitDraw(String filename) throws Exception{
    timer = new Timer(delay, this);
    timer.setInitialDelay(1000);
    timer.start();
    m = new Model();
	m.readFile(filename);
	build();
	startFilter = new ArrayList<WNode>();
	for (WNode starting: m.start.keySet()){
		if (m.start.get(starting) != null){
			starting.setReady(true);
			startFilter.add(starting);
		}
	}
	animationBound = gateWithDeph.size() - 1;
	animationStep = animationBound - 1;
	clockCount = -1;
  }

  public void paint(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawString("this", animationStep*100, 10);
    if (animationStep == 0){
    	plainComponents(g);
    	values(g);
    }
    else if (animationStep == animationBound){
    	plainComponents(g);
    	m.clock.tick();
    	animationStep = -1;
    	clockCount = (clockCount + 1) % m.numberOfClockCycles;
    	nextCycleValues(clockCount);
    	values(g);
    }
    else {
    	frame(g2d, animationStep);
 	   	values(g);
    }
    
  }
  
  public void frame(Graphics g, int x){
	  plainComponents(g);
	  for (LogicComponent c : gateWithDeph.get(x)){
			if (c.pushSignal()){
				for(WNode o: c.getOutputs())
					if (o.isWStart() && o.hasWire())//push signal accross the wire
						o.getWire().pushSignal();
				c.pass(g);
			}
  	}
  }
  
  public void plainComponents(Graphics g){
	  for (String IDw: m.wire.keySet())
		  m.wire.get(IDw).paint(g);
    	for(String ID : m.comp.keySet())
			  m.comp.get(ID).paint(g);
  }
  
  public void values(Graphics g){
	  for (WNode n : m.node.values())
		  n.paintValue(g);
  }
  
  public void nextCycleValues(int clockCycle){
	  System.out.println(m.start.values().toString());
	  for (WNode n : startFilter)
		  n.getSignal().setValue(m.start.get(n).get(clockCycle));
  }
  
  public void build(){
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

  public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame("FontSizeAnimation");
    CircuitDraw c = new CircuitDraw(args[0]);
    frame.add(c);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(c.m.windowSize.getX() + 100, c.m.windowSize.getY() + 50);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    animationStep += 1;
    System.out.println("animation step: " + animationStep);
    repaint();
  }
}