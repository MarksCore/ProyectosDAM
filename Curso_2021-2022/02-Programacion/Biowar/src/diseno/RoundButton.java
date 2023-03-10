package diseno;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class RoundButton extends JButton {

	public RoundButton() {

		//		setBackground(Color.black);
		//		setFocusable(false);

		/*
	     These statements enlarge the button so that it 
	     becomes a circle rather than an oval.
		 */
		//		Dimension size = getPreferredSize();
		//		size.width = size.height = Math.max(size.width, size.height);
		//		setPreferredSize(size);

		/*
	     This call causes the JButton not to paint the background.
	     This allows us to paint a round background.
		 */
		setContentAreaFilled(false);
	}

	//	protected void paintComponent(Graphics g) {
	//		if (getModel().isRollover()) {
	//			g.setColor(Color.gray);
	//		} else {
	//			g.setColor(getBackground());
	//		}
	//		g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
	//
	//		super.paintComponent(g);
	//	}

	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if(getModel().isRollover()) { 	
			g2d.setColor(Color.ORANGE);
			g2d.setStroke(new BasicStroke(3.5F));
		} else {
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.5F));
		}
//		g2d.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		g2d.drawOval(25, 25, 113, 100);
	}

	// Hit detection.
//	Shape shape;

//	public boolean contains(int x, int y) {
//		// If the button has changed size,  make a new shape object.
//		if (shape == null || !shape.getBounds().equals(getBounds())) {
//			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
//		}
//		return shape.contains(x, y);
//	}

	//	  public static void main(String[] args) {
	//	 
	//	    JFrame.setDefaultLookAndFeelDecorated(true);
	//	    JFrame frame = new JFrame("Rounded Button Example");
	//	    frame.setLayout(new FlowLayout());
	//	 
	//	    JButton b1 = new RoundButton("B1");
	//	    JButton b2 = new RoundButton("B2");
	//	 
	//	    frame.add(b1);
	//	    frame.add(b2);
	//	 
	//	    frame.setSize(300, 150);
	//	    frame.setVisible(true);
	//	  }
}
