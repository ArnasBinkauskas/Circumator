import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Circumator {
	 private static final String START = "Start";
	 private static final String STOP = "Stop";
	 
	 public static void main(String[] args) throws Exception {
			JPanel contentPane = new JPanel(new BorderLayout());
			JFileChooser fc = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir"));
			fc.setCurrentDirectory(workingDirectory);
			fc.showOpenDialog(null);
			final CircuitDraw c = new CircuitDraw(fc.getSelectedFile().getAbsolutePath());
		    final JButton run = new JButton(START);
		    final JSlider speedSlider = new JSlider();
		    speedSlider.setMinimum(200);
		    speedSlider.setMaximum(1500);
		    speedSlider.setValue(600);
		    speedSlider.setMajorTickSpacing(100);
		    speedSlider.setPaintTicks(true);
		    
		    JPanel controlPanel = new JPanel(new BorderLayout());

		    c.timer.stop();
		    run.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            String cmd = e.getActionCommand();
		            if (STOP.equals(cmd)) {
		                c.timer.stop();
		                run.setText(START);
		            } else {
		                c.timer.start();
		                run.setText(STOP);
		            }
		        }
		    });
		    
		    speedSlider.addChangeListener(new ChangeListener() {
		        @Override
		        public void stateChanged(ChangeEvent ce) {
		            c.timer.setDelay(speedSlider.getValue());
		            
		        }
		    });
		    
		    controlPanel.add(run, BorderLayout.CENTER);
		    controlPanel.add(speedSlider, BorderLayout.EAST);
		    JPanel controlPanelW = new JPanel(new BorderLayout());
		    controlPanelW.add(controlPanel, BorderLayout.WEST);
		    contentPane.add(controlPanelW, BorderLayout.NORTH);
		    contentPane.add(c, BorderLayout.CENTER);
		    JFrame frame = new JFrame("Circumator");
		    frame.add(contentPane);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(c.m.windowSize.getX() + 100, c.m.windowSize.getY() + 100);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		  }
}
