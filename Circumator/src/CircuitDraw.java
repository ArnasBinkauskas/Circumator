
import java.awt.BorderLayout;
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
import javax.swing.JButton;

import wireComponent.WNode;
import logicComponent.LogicComponent;

public class CircuitDraw extends JPanel implements ActionListener {
  static Timer timer;
  Model m;
  ArrayList<WNode> startFilter;
  private static final String START = "Start";
  private static final String STOP = "Stop";
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
	startFilter = new ArrayList<WNode>();
	for (WNode starting: m.start.keySet()){
		if (m.start.get(starting) != null){
			starting.setReady(true);
			startFilter.add(starting);
		}
	}
	animationBound = m.gateWithDeph.size() - 1;
	animationStep = 0;
	clockCount = -1;
  }

  public void paint(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawString("" + animationStep, animationStep*40, 10);
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
    	for(String ID : m.comp.keySet())
			  m.comp.get(ID).resetAnimation();
    	values(g);			  
    }
    else {
    	frame(g2d, animationStep);
 	   	values(g);
    }
    
  }
  
  public void frame(Graphics g, int x){
	  plainComponents(g);
	  for (LogicComponent c : m.gateWithDeph.get(x)){
			if (c.animate(g)){
				for(WNode o: c.getOutputs())
					if (o.isWStart() && o.hasWire())//push signal accross the wire
						o.getWire().pushSignal();
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
	  //System.out.println(m.start.values().toString());
	  for (WNode n : startFilter)
		  n.getSignal().setValue(m.start.get(n).get(clockCycle));
  }
  
 

  public static void main(String[] args) throws Exception {
	JPanel contentPane = new JPanel(new BorderLayout());
	CircuitDraw c = new CircuitDraw(args[0]);
    JButton run = new JButton(STOP);
    run.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (STOP.equals(cmd)) {
                timer.stop();
                run.setText(START);
            } else {
                timer.start();
                run.setText(STOP);
            }
        }
    });
    contentPane.add(run, BorderLayout.NORTH);
    contentPane.add(c, BorderLayout.CENTER);
    JFrame frame = new JFrame("FontSizeAnimation");
    frame.add(contentPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(c.m.windowSize.getX() + 100, c.m.windowSize.getY() + 100);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    animationStep += 1;
   // System.out.println("animation step: " + animationStep);
    repaint();
  }
}