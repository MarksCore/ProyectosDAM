package eventos;

import fuentes.FuentesPersonalizadas;
import informacion.Info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Creditos extends JPanel implements ActionListener {
	private final JButton btnAtras;
	
	private final JLabel lblBackgroungCreditos;
	private final JLabel lblCreditos;
	private final JLabel lblLogoCreditos;
	private JLabel lblMarcoCreditos;
	private final JTextArea jtInfoCreditos;
	
	private final String creditosTheme = "src/audio/creditostheme.wav";
	private final float volumenCreditosTheme = -4.0f;

	FuentesPersonalizadas tipoFuente;

	public Creditos() {
		setLayout(null);
		tipoFuente = new FuentesPersonalizadas();
		
		lblCreditos = new JLabel("Créditos");
		lblCreditos.setBounds(775, 50, 400, 220);
		lblCreditos.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 150));
		lblCreditos.setForeground(Color.WHITE);
		lblCreditos.setBackground(Color.darkGray);
		lblCreditos.setOpaque(false);
		add(lblCreditos);

		jtInfoCreditos = new JTextArea();
		jtInfoCreditos.setText(Info.verCreditos());
		jtInfoCreditos.setBounds(655, 455, 700, 550);
		jtInfoCreditos.setFont(new Font("Broadway", Font.PLAIN, 26));
		jtInfoCreditos.setFont(tipoFuente.fuente(tipoFuente.broadway, 0, 26));
		jtInfoCreditos.setForeground(Color.white);
		jtInfoCreditos.setBackground(Color.black);
		jtInfoCreditos.setLineWrap(true);
		jtInfoCreditos.setWrapStyleWord(true);
		jtInfoCreditos.setEditable(false);
		jtInfoCreditos.setOpaque(false);
		add(jtInfoCreditos);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setBounds(200, 870, 150, 100);
		btnAtras.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorderPainted(false);
		btnAtras.addActionListener(this);
		add(btnAtras);
		btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnAtras.setForeground(Color.ORANGE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnAtras.setForeground(Color.WHITE);
			}
		});
		
		Image logoCreditos = new ImageIcon("src/img/otros/mainlogo.png").getImage();
		ImageIcon logoCreditosIcon = new ImageIcon(logoCreditos.getScaledInstance(250, 100, Image.SCALE_SMOOTH));
		lblLogoCreditos = new JLabel();
		lblLogoCreditos.setBounds(815, 300, 250, 100);
		lblLogoCreditos.setIcon(logoCreditosIcon);
		lblLogoCreditos.setOpaque(false);
		add(lblLogoCreditos);
		
		Image marcoCreditos = new ImageIcon("src/img/otros/marcocreditos2.png").getImage();
		ImageIcon marcoCreditosIcon = new ImageIcon(marcoCreditos.getScaledInstance(900, 620, Image.SCALE_SMOOTH));
		lblMarcoCreditos = new JLabel();
		lblMarcoCreditos.setBounds(480, 225, 900, 620);
		lblMarcoCreditos.setIcon(marcoCreditosIcon);
		lblMarcoCreditos.setOpaque(false);
		add(lblMarcoCreditos);
		
		Image marcoCreditosBack = new ImageIcon("src/img/otros/marcocreditosback2.png").getImage();
		ImageIcon marcoCreditosBackIcon = new ImageIcon(marcoCreditosBack.getScaledInstance(900, 620, Image.SCALE_SMOOTH));
		lblMarcoCreditos = new JLabel();
		lblMarcoCreditos.setBounds(480, 225, 900, 620);
		lblMarcoCreditos.setIcon(marcoCreditosBackIcon);
		lblMarcoCreditos.setOpaque(false);
		add(lblMarcoCreditos);

		Image backgroundCreditos = new ImageIcon("src/img/fondos/bg6.jpg").getImage();
		ImageIcon backgroundCreditosIcon = new ImageIcon(backgroundCreditos.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
		lblBackgroungCreditos = new JLabel();
		lblBackgroungCreditos.setBounds(0, 0, 1920, 1080);
		lblBackgroungCreditos.setIcon(backgroundCreditosIcon);
		add(lblBackgroungCreditos);
		
		MenuPrincipal.reproducirBSO(creditosTheme, volumenCreditosTheme);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAtras)) {
			MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonAtras, MenuPrincipal.volumenBotonAtras);
			MenuPrincipal.clipTheme.stop();
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			frame.remove(this);
			frame.getContentPane().add(new MenuPrincipal());
			frame.setVisible(true);
		}
	}
}
