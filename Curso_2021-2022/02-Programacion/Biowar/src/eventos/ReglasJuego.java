package eventos;

import fuentes.FuentesPersonalizadas;
import informacion.Info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReglasJuego extends JPanel implements ActionListener {
	private final JLabel lblBackgroundReglas;
	private final JLabel lblReglasDeJuego;

	private final JTextArea jtinformacion;
	
	private final JButton btnAtras;
	
	private final String reglasTheme = "src/audio/reglasost.wav";
	private final float volumenReglasTheme = 3.0f;
	private final Color colorTitulo;

    FuentesPersonalizadas tipoFuente;

	public ReglasJuego() {

		setLayout(null);
//		MenuPrincipal.clipTheme.isRunning();
		colorTitulo = new Color(0, 101, 71);
        tipoFuente = new FuentesPersonalizadas();
		
		lblReglasDeJuego = new JLabel("REGLAS DE JUEGO");
		lblReglasDeJuego.setBounds(777, 32, 363, 55);
		lblReglasDeJuego.setFont(tipoFuente.fuente(tipoFuente.bettyNoir,0,50));
		lblReglasDeJuego.setForeground(colorTitulo);
		lblReglasDeJuego.setBackground(Color.orange);
		lblReglasDeJuego.setOpaque(true);
		add(lblReglasDeJuego);
		
		jtinformacion = new JTextArea();
		jtinformacion.setText(Info.verReglasJuego());
		jtinformacion.setBounds(490, 230, 940, 570);
		jtinformacion.setFont(tipoFuente.fuente(tipoFuente.broadway,0,16));
		jtinformacion.setForeground(Color.orange);
		jtinformacion.setBackground(Color.black);
		jtinformacion.setLineWrap(true);
		jtinformacion.setWrapStyleWord(true);
		jtinformacion.setEditable(false);
		jtinformacion.setOpaque(false);
		add(jtinformacion);

		btnAtras = new JButton("Atrás");
		btnAtras.setBounds(675, 865, 120, 70);
		btnAtras.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd,0,50));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorderPainted(false);
		btnAtras.addActionListener(this);
		btnAtras.setOpaque(false);
		add(btnAtras);
		btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				btnAtras.setForeground(Color.ORANGE);
				
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnAtras.setForeground(Color.WHITE);
			}
		});
		
		Image backgroundReglas = new ImageIcon("src/img/fondos/fondoreglas.jpg").getImage();
		ImageIcon backgroundReglasIcon = new ImageIcon(backgroundReglas.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
		lblBackgroundReglas = new JLabel();
		lblBackgroundReglas.setBounds(0, -35, 1920, 1080);
		lblBackgroundReglas.setIcon(backgroundReglasIcon);
		add(lblBackgroundReglas);
		
		MenuPrincipal.reproducirBSO(reglasTheme, volumenReglasTheme);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnAtras)) {
			MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonAtras, MenuPrincipal.volumenBotonAtras);
			MenuPrincipal.clipTheme.stop();
			MenuPrincipal.volverMenu = false;
			Window wind = SwingUtilities.getWindowAncestor(this);
			wind.setVisible(false);

			Ventana.panel = 1;
			Ventana p = new Ventana();
		}

	}

}

