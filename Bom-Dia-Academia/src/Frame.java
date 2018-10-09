import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Frame {

	private JFrame frame;
	private Panels panels;
	
	public Frame() {
		
		frame = new JFrame("Bom Dia Academia");
		
		panels = new Panels(frame);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		addComponents();
		
	}
	
	private void addComponents() {
		
		panels.loginPanel();
	}

	public void init() {
				
		frame.setSize(375, 265);
		centerFrame();
		frame.setVisible(true);
	}
	
	public void centerFrame() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
	}
}
