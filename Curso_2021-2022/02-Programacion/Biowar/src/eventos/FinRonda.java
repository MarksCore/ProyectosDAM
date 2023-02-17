package eventos;

import fuentes.FuentesPersonalizadas;
import objetos.Personaje;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinRonda extends JPanel implements ActionListener {
	private final JLabel lblBackgroundFinRonda;
	private final JLabel lblCuadroFinRonda;
	private final JLabel lblCuadroResumenVidas;


	private final JTextArea jtHistorialRonda;
	private final JTextArea jtResumenVidas;

	private final JLabel lblFinRonda;

	private final JScrollPane scrollHistorial;
	private final JScrollPane scrollResumenVidas;

	private final JButton btnContinuar;
	
	static String sndConfirmarRonda = "src/audio/sndconfirmarronda.wav";
	static float volumenBotonConfirmarRonda = 0.0f;
	static String sndFinRonda = "src/audio/sndfinronda.wav";
	static float volumenFinRonda = 0.0f;

    FuentesPersonalizadas tipoFuente;

	public FinRonda() {
        setLayout(null);
        tipoFuente = new FuentesPersonalizadas();

		lblFinRonda = new JLabel();
		lblFinRonda.setText("Fin de ronda " + Combate.contRondas);
		lblFinRonda.setBounds(583, 115, 750, 190);
        lblFinRonda.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 1, 90));
        lblFinRonda.setForeground(Color.ORANGE);
        lblFinRonda.setHorizontalAlignment(JLabel.CENTER);
        lblFinRonda.setVerticalAlignment(JLabel.CENTER);
		lblFinRonda.setOpaque(false);
		add(lblFinRonda);

		Image cuadroFinRonda = new ImageIcon("src/img/otros/cuadrofinronda2.png").getImage();
		ImageIcon cuadroFinRondaIcon = new ImageIcon(cuadroFinRonda.getScaledInstance(750, 150, Image.SCALE_SMOOTH));
		lblCuadroFinRonda = new JLabel();
		lblCuadroFinRonda.setBounds(583, 115, 750, 140);
		lblCuadroFinRonda.setIcon(cuadroFinRondaIcon);
		add(lblCuadroFinRonda);

		jtHistorialRonda = new JTextArea();
		jtHistorialRonda.setText(Combate.resultadoRonda);
		jtHistorialRonda.setBounds(612, 349, 698, 370);
		jtHistorialRonda.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 18));
		jtHistorialRonda.setForeground(Color.WHITE);
		jtHistorialRonda.setBackground(new Color (0, 0, 0));
		jtHistorialRonda.setLineWrap(true);
		jtHistorialRonda.setWrapStyleWord(true);
		jtHistorialRonda.setEditable(false);
		jtHistorialRonda.setOpaque(true);
		scrollHistorial = new JScrollPane(jtHistorialRonda, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollHistorial.setBounds(612, 349, 698, 370);
		scrollHistorial.setBorder(new LineBorder(Color.darkGray, 3, false));
		add(scrollHistorial);

		jtResumenVidas = new JTextArea();
		jtResumenVidas.setText(Personaje.resumenVidas(ElegirPersonaje.arrayPersonajes));
		jtResumenVidas.setBounds(650, 850, 630, 125);
		jtResumenVidas.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 18));
		jtResumenVidas.setForeground(Color.WHITE);
		jtResumenVidas.setBackground(new Color (0, 0, 0));
		jtResumenVidas.setLineWrap(true);
		jtResumenVidas.setWrapStyleWord(true);
		jtResumenVidas.setEditable(false);
		jtResumenVidas.setOpaque(true);

		scrollResumenVidas = new JScrollPane(jtResumenVidas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollResumenVidas.setBounds(650, 850, 630, 125);
		scrollResumenVidas.setBorder(new LineBorder(Color.darkGray, 3, false));

		add(scrollResumenVidas);


		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(1350, 870, 200, 100);
		btnContinuar.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setContentAreaFilled(false);
		btnContinuar.setBorderPainted(false);
		btnContinuar.addActionListener(this);
		add(btnContinuar);
		btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnContinuar.setForeground(Color.ORANGE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnContinuar.setForeground(Color.WHITE);
			}
		});

		Image cuadroResumenVidas = new ImageIcon("src/img/otros/cuadroresumenvidas.png").getImage();
		ImageIcon cuadroResumenVidasIcon = new ImageIcon(cuadroResumenVidas.getScaledInstance(698, 200, Image.SCALE_SMOOTH));
		lblCuadroResumenVidas = new JLabel();
		lblCuadroResumenVidas.setBounds(612, 815, 698, 200);
		lblCuadroResumenVidas.setIcon(cuadroResumenVidasIcon);
		lblCuadroResumenVidas.setOpaque(false);
		add(lblCuadroResumenVidas);


		Image backgroundFinRonda = new ImageIcon("src/img/fondos/bgplayers2.png").getImage();
		ImageIcon backgroundFinRondaIcon = new ImageIcon(backgroundFinRonda.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
		lblBackgroundFinRonda = new JLabel();
		lblBackgroundFinRonda.setBounds(0, 0, 1920, 1080);
		lblBackgroundFinRonda.setIcon(backgroundFinRondaIcon);
		add(lblBackgroundFinRonda);
		
		MenuPrincipal.reproducirBoton(sndFinRonda, volumenFinRonda);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnContinuar)) {

			MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
			
			Combate.resultadoRonda = "";

			Combate.posicion = 0;

			Combate.contRondas++;
			
			ElegirPersonaje.reiniciarVidas();

			// Reiniciar puntos de ataque de cada objeto de cada subclase de Personaje.
			for(int i = 0; i < ElegirPersonaje.arrayPersonajes.size(); i++) {
				ElegirPersonaje.arrayPersonajes.get(i).reiniciarMisiles(Combate.contRondas);
			}

			if(ElegirPersonaje.arrayPersonajes.size() > 1) {
				MenuPrincipal.reproducirBoton(sndConfirmarRonda, volumenBotonConfirmarRonda);
				Combate.seAtaco = false;
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
				frame.remove(this);
				frame.getContentPane().add(new Combate());
				frame.setVisible(true);
			} else {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
				frame.remove(this);
				frame.getContentPane().add(new FinJuego());
				frame.setVisible(true);
			}
		}
	}
}


