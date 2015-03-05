import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Circumator {
	 private static final String START = "Start";
	 private static final String STOP = "Stop";
	  static Timer timer;
	 
	 public static void main(String[] args) throws Exception {
		 	timer = new Timer(0, null);
			JPanel contentPane = new JPanel(new BorderLayout());
			CircuitDraw c = new CircuitDraw(args[0]);
		    JButton run = new JButton(STOP);
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
		    contentPane.add(run, BorderLayout.NORTH);
		    contentPane.add(c, BorderLayout.CENTER);
		    JFrame frame = new JFrame("FontSizeAnimation");
		    frame.add(contentPane);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(c.m.windowSize.getX() + 100, c.m.windowSize.getY() + 100);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		  }
}
