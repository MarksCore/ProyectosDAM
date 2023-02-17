package diseno;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class RoundButton2 extends JButton {
	
	private Color color1;
	private Color color2;
	
	public RoundButton2() {
		
		color1 = new Color(200, 225, 200);
		color2 = new Color(31, 33, 30);
		setContentAreaFilled(false);
	}
	
	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if(getModel().isRollover()) { 	
			g2d.setColor(color1);
			g2d.setStroke(new BasicStroke(4.7F));
		} else {
			g2d.setColor(color2);
			g2d.setStroke(new BasicStroke(4.7F));
		}
//		g2d.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		g2d.drawOval(25, 25, 125, 125);
	}
	

}
