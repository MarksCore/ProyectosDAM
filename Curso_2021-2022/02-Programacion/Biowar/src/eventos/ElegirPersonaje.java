package eventos;

import diseno.RoundButton;
import fuentes.FuentesPersonalizadas;
import informacion.Info;
import objetos.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ElegirPersonaje extends JPanel implements ActionListener {
	private final JLabel lblBackgroundCharacters;
	private final JLabel lblTitleBouncer;
	private final JLabel lblBouncer;
	private final JLabel lblTitleBigSister;
	private final JLabel lblBigSister;
	private final JLabel lblTitleSujetoDelta;
	private final JLabel lblSujetoDelta;
	private final JLabel lblTitleLancero;
	private final JLabel lblLancero;
	private final JLabel lblTitleLittleSister;
	private final JLabel lblLittleSister;
	private final JLabel lblLittleSisterButton;

	private final JButton btnBouncer;
	private final JButton btnBigSister;
	private final JButton btnSujetoDelta;
	private final JButton btnLancero;
	private final JButton btnlittleSister;
	private final JButton btnIniciar;
	
	private final JTextArea jtJugadorSeleccionaPj;
	private final JTextArea jtSeleccionPersonaje;
	private final JTextArea jtEstadisticasPersonaje;
	
	private final String sndIniciar = "src/audio/sndiniciar.wav";
	private final float volumenBotonIniciar = 0.0f;
	private final String sndBotonPersonaje = "src/audio/sndbotonpersonaje.wav";
	private final float volumenBotonPersonaje = -7.0f;

	static ArrayList<Personaje> arrayPersonajes;
	static int posicion;
	static int[] arrayVidas;
	FuentesPersonalizadas tipoFuente;


	// CONSTRUCTOR
	public ElegirPersonaje() {
		setLayout(null);
		tipoFuente = new FuentesPersonalizadas();

		jtJugadorSeleccionaPj = new JTextArea();
		jtJugadorSeleccionaPj.setBounds(170, 290, 500, 50);
		jtJugadorSeleccionaPj.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 28));
		jtJugadorSeleccionaPj.setForeground(Color.WHITE);
		jtJugadorSeleccionaPj.setEditable(false);
		jtJugadorSeleccionaPj.setOpaque(false);
		add(jtJugadorSeleccionaPj);

		jtSeleccionPersonaje = new JTextArea();
		jtSeleccionPersonaje.setBounds(175, 350, 510, 300);
		jtSeleccionPersonaje.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 20));
		jtSeleccionPersonaje.setForeground(Color.WHITE);
		jtSeleccionPersonaje.setEditable(false);
		jtSeleccionPersonaje.setOpaque(false);
		add(jtSeleccionPersonaje);

		// JTextArea para mostrar estadísticas de cada tipo de personaje.
		jtEstadisticasPersonaje = new JTextArea();
		jtEstadisticasPersonaje.setBounds(870, 650, 320, 100);
		jtEstadisticasPersonaje.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		jtEstadisticasPersonaje.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 16));
		jtEstadisticasPersonaje.setForeground(Color.WHITE);
		jtEstadisticasPersonaje.setEditable(false);
		jtEstadisticasPersonaje.setOpaque(false);
		jtEstadisticasPersonaje.setVisible(false);
		add(jtEstadisticasPersonaje);


		// ELEMENTOS DE PERSONAJE BOUNCER.
		lblTitleBouncer = new JLabel("Bouncer");
		lblTitleBouncer.setBounds(975, 285, 200, 50);
		lblTitleBouncer.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 30));
		lblTitleBouncer.setForeground(Color.WHITE);
		lblTitleBouncer.setOpaque(false);
		lblTitleBouncer.setVisible(false);
		add(lblTitleBouncer);

		Image bouncerPj = new ImageIcon("src/img/personajes/bouncerpj.png").getImage();
		ImageIcon bouncerPjIcon = new ImageIcon(bouncerPj.getScaledInstance(320, 270, Image.SCALE_SMOOTH));
		lblBouncer = new JLabel();
		lblBouncer.setIcon(bouncerPjIcon);
		lblBouncer.setBounds(845, 340, 320, 270);
		lblBouncer.setOpaque(false);
		lblBouncer.setVisible(false);
		add(lblBouncer);

		Image bouncer = new ImageIcon("src/img/personajes/bigdaddy1.jpg").getImage();
		ImageIcon bouncerIcon = new ImageIcon(bouncer.getScaledInstance(176, 113, Image.SCALE_SMOOTH));
		btnBouncer = new JButton();
		btnBouncer.setIcon(bouncerIcon);
		btnBouncer.setBounds(1532, 160, 176, 113);
		btnBouncer.setBorderPainted(true);
		btnBouncer.setBorder(new LineBorder(Color.WHITE, 3, true));
		btnBouncer.addActionListener(this);
		add(btnBouncer);
		btnBouncer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBouncer.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnBouncer.setBorder(new LineBorder(Color.ORANGE, 3, true));
				lblTitleBouncer.setVisible(true);
				lblBouncer.setVisible(true);
				jtEstadisticasPersonaje.setText(Info.verInfoEstadisticas("Bouncer"));
				jtEstadisticasPersonaje.setVisible(true);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnBouncer.setBorder(new LineBorder(Color.WHITE, 3, true));
				lblTitleBouncer.setVisible(false);
				lblBouncer.setVisible(false);
				jtEstadisticasPersonaje.setVisible(false);
			}
		});


		// ELEMENTOS DE PERSONAJE BIG SISTER.
		lblTitleBigSister = new JLabel("Big Sister");
		lblTitleBigSister.setBounds(955, 285, 200, 50);
		lblTitleBigSister.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 30));
		lblTitleBigSister.setForeground(Color.WHITE);
		lblTitleBigSister.setVisible(false);
		add(lblTitleBigSister);

		Image bigSisterPj = new ImageIcon("src/img/personajes/bigsisterpj.png").getImage();
		ImageIcon bigSisterPjIcon = new ImageIcon(bigSisterPj.getScaledInstance(170, 285, Image.SCALE_SMOOTH));
		lblBigSister = new JLabel();
		lblBigSister.setIcon(bigSisterPjIcon);
		lblBigSister.setBounds(930, 340, 170, 285);
		lblBigSister.setOpaque(false);
		lblBigSister.setVisible(false);
		add(lblBigSister);

		Image bigSister = new ImageIcon("src/img/personajes/bigsister1.jpg").getImage();
		ImageIcon bigSisterIcon = new ImageIcon(bigSister.getScaledInstance(176, 113, Image.SCALE_SMOOTH));
		btnBigSister = new JButton();
		btnBigSister.setIcon(bigSisterIcon);
		btnBigSister.setBounds(1532, 303, 176, 113);
		btnBigSister.setBorderPainted(true);
		btnBigSister.setBorder(new LineBorder(Color.WHITE, 3, true));
		btnBigSister.addActionListener(this);
		add(btnBigSister);
		btnBigSister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBigSister.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnBigSister.setBorder(new LineBorder(Color.ORANGE, 3, true));
				lblTitleBigSister.setVisible(true);
				lblBigSister.setVisible(true);
				jtEstadisticasPersonaje.setText(Info.verInfoEstadisticas("BigSister"));
				jtEstadisticasPersonaje.setVisible(true);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnBigSister.setBorder(new LineBorder(Color.WHITE, 3, true));
				lblTitleBigSister.setVisible(false);
				lblBigSister.setVisible(false);
				jtEstadisticasPersonaje.setVisible(false);
			}
		});


		// ELEMENTOS DE PERSONAJE SUJETO DELTA.
		lblTitleSujetoDelta = new JLabel("Sujeto Delta");
		lblTitleSujetoDelta.setBounds(940, 285, 200, 50);
		lblTitleSujetoDelta.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 30));
		lblTitleSujetoDelta.setForeground(Color.WHITE);
		lblTitleSujetoDelta.setVisible(false);
		add(lblTitleSujetoDelta);

		Image sujetoDeltaPj = new ImageIcon("src/img/personajes/sujetodeltapj.png").getImage();
		ImageIcon sujetoDeltaPjIcon = new ImageIcon(sujetoDeltaPj.getScaledInstance(170, 290, Image.SCALE_SMOOTH));
		lblSujetoDelta = new JLabel();
		lblSujetoDelta.setIcon(sujetoDeltaPjIcon);
		lblSujetoDelta.setBounds(930, 340, 170, 290);
		lblSujetoDelta.setOpaque(false);
		lblSujetoDelta.setVisible(false);
		add(lblSujetoDelta);

		Image sujetoDelta = new ImageIcon("src/img/personajes/sujetodelta1.jpg").getImage();
		ImageIcon sujetoDeltaIcon = new ImageIcon(sujetoDelta.getScaledInstance(176, 113, Image.SCALE_SMOOTH));
		btnSujetoDelta = new JButton();
		btnSujetoDelta.setIcon(sujetoDeltaIcon);
		btnSujetoDelta.setBounds(1532, 450, 176, 113);
		btnSujetoDelta.setBorderPainted(true);
		btnSujetoDelta.setBorder(new LineBorder(Color.WHITE, 3, true));
		btnSujetoDelta.addActionListener(this);
		add(btnSujetoDelta);
		btnSujetoDelta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSujetoDelta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnSujetoDelta.setBorder(new LineBorder(Color.ORANGE, 3, true));
				lblTitleSujetoDelta.setVisible(true);
				lblSujetoDelta.setVisible(true);
				jtEstadisticasPersonaje.setText(Info.verInfoEstadisticas("SujetoDelta"));
				jtEstadisticasPersonaje.setVisible(true);

			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnSujetoDelta.setBorder(new LineBorder(Color.WHITE, 3, true));
				lblTitleSujetoDelta.setVisible(false);
				lblSujetoDelta.setVisible(false);
				jtEstadisticasPersonaje.setVisible(false);
			}
		});

		// ELEMENTOS DE PERSONAJE LANCER.
		lblTitleLancero = new JLabel("Lancer");
		lblTitleLancero.setBounds(975, 285, 200, 50);
		lblTitleLancero.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 30));
		lblTitleLancero.setForeground(Color.WHITE);
		lblTitleLancero.setVisible(false);
		add(lblTitleLancero);

		Image lanceroPj = new ImageIcon("src/img/personajes/lanceropj.png").getImage();
		ImageIcon lanceroPjIcon = new ImageIcon(lanceroPj.getScaledInstance(170, 290, Image.SCALE_SMOOTH));
		lblLancero = new JLabel();
		lblLancero.setIcon(lanceroPjIcon);
		lblLancero.setBounds(930, 340, 170, 290);
		lblLancero.setOpaque(false);
		lblLancero.setVisible(false);
		add(lblLancero);


		Image lancero = new ImageIcon("src/img/personajes/lancer1.png").getImage();
		ImageIcon lanceroIcon = new ImageIcon(lancero.getScaledInstance(176, 113, Image.SCALE_SMOOTH));
		btnLancero = new JButton();
		btnLancero.setIcon(lanceroIcon);
		btnLancero.setBounds(1532, 597, 176, 113);
		btnLancero.setBorderPainted(true);
		btnLancero.setBorder(new LineBorder(Color.WHITE, 3, true));
		btnLancero.addActionListener(this);
		add(btnLancero);
		btnLancero.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLancero.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnLancero.setBorder(new LineBorder(Color.ORANGE, 3, true));
				lblTitleLancero.setVisible(true);
				lblLancero.setVisible(true);
				jtEstadisticasPersonaje.setText(Info.verInfoEstadisticas("Lancer"));
				jtEstadisticasPersonaje.setVisible(true);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnLancero.setBorder(new LineBorder(Color.WHITE, 3, true));
				lblTitleLancero.setVisible(false);
				lblLancero.setVisible(false);
				jtEstadisticasPersonaje.setVisible(false);
			}
		});

		// ELEMENTOS DE PERSONAJE LITTLE SISTER.
		lblTitleLittleSister = new JLabel("Little Sister");
		lblTitleLittleSister.setBounds(935, 285, 200, 50);
		lblTitleLittleSister.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 30));
		lblTitleLittleSister.setForeground(Color.WHITE);
		lblTitleLittleSister.setVisible(false);
		add(lblTitleLittleSister);

		Image littleSisterPj = new ImageIcon("src/img/personajes/littlesisterpj.png").getImage();
		ImageIcon littleSisterPjIcon = new ImageIcon(littleSisterPj.getScaledInstance(170, 290, Image.SCALE_SMOOTH));
		lblLittleSister = new JLabel();
		lblLittleSister.setIcon(littleSisterPjIcon);
		lblLittleSister.setBounds(930, 340, 170, 290);
		lblLittleSister.setOpaque(false);
		lblLittleSister.setVisible(false);
		add(lblLittleSister);


		Image littleSister = new ImageIcon("src/img/personajes/littlesister2.png").getImage();
		ImageIcon littleSisterIcon = new ImageIcon(littleSister.getScaledInstance(150, 120, Image.SCALE_SMOOTH));
		lblLittleSisterButton = new JLabel();
		lblLittleSisterButton.setBounds(1509, 750, 200, 200);
		lblLittleSisterButton.setOpaque(false);
		lblLittleSisterButton.setIcon(littleSisterIcon);
		add(lblLittleSisterButton);

		btnlittleSister = new RoundButton();
		btnlittleSister.setBounds(1536, 768, 150, 150);
		btnlittleSister.addActionListener(this);
		btnlittleSister.setOpaque(false);
		add(btnlittleSister);
		btnlittleSister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnlittleSister.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnlittleSister.setBorder(new LineBorder(Color.ORANGE, 3, true));
				lblTitleLittleSister.setVisible(true);
				lblLittleSister.setVisible(true);
				jtEstadisticasPersonaje.setText(Info.verInfoEstadisticas("LittleSister"));
				jtEstadisticasPersonaje.setVisible(true);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnlittleSister.setBorder(new LineBorder(Color.WHITE, 3, true));
				lblTitleLittleSister.setVisible(false);
				lblLittleSister.setVisible(false);
				jtEstadisticasPersonaje.setVisible(false);
			}
		});

		btnIniciar = new JButton("Iniciar partida");
		btnIniciar.setBounds(555, 695, 300, 100);
		btnIniciar.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setContentAreaFilled(false);
		btnIniciar.setBorderPainted(false);
		btnIniciar.addActionListener(this);
		add(btnIniciar);
		btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnIniciar.setForeground(Color.ORANGE);

			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnIniciar.setForeground(Color.WHITE);
			}
		});

		Image backgroundPlayers = new ImageIcon("src/img/fondos/bgcharacters.png").getImage();
		ImageIcon backgroundPlayersicon = new ImageIcon(backgroundPlayers.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
		lblBackgroundCharacters = new JLabel();
		lblBackgroundCharacters.setBounds(0, -43, 1920, 1080);
		lblBackgroundCharacters.setIcon(backgroundPlayersicon);
		add(lblBackgroundCharacters);

		rellenarTextAreaJugador();

		arrayPersonajes = new ArrayList<Personaje>();
	}


	// MÉTODOS
	public void rellenarTextAreaJugador() {

		if (posicion < MenuPrincipal.nombresJugadores.size()) {
			String jugadorTextArea = "Jugador " + (posicion + 1) + ", elige tu personaje:"; 
			jtJugadorSeleccionaPj.setText(jugadorTextArea);

		}
	}

	public void rellenarTextAreaConfirmados() {

		StringBuilder seleccionTextArea = new StringBuilder();

		for (int i = 0; i < arrayPersonajes.size(); i++) {
			seleccionTextArea.append(arrayPersonajes.get(i).getNombre()).append(" (J").append(i + 1).append(") ha seleccionado ")
					.append(arrayPersonajes.get(i).descripcionTipo(arrayPersonajes.get(i))).append("\n");
		}
		jtSeleccionPersonaje.setText(seleccionTextArea.toString());
	}
	
	public static void reiniciarVidas() {
		arrayVidas = new int[arrayPersonajes.size()];
		
		for (int i = 0; i < arrayPersonajes.size(); i++) {
			arrayVidas[i] = arrayPersonajes.get(i).getVidas();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		do {
			try {
				if (e.getSource().equals(btnBouncer)) {
					MenuPrincipal.reproducirBoton(sndBotonPersonaje, volumenBotonPersonaje);
					arrayPersonajes.add(new Bouncer(MenuPrincipal.nombresJugadores.get(posicion), ""));
					rellenarTextAreaConfirmados();
					posicion++;
					rellenarTextAreaJugador();

				} else if (e.getSource().equals(btnBigSister)) {
					MenuPrincipal.reproducirBoton(sndBotonPersonaje, volumenBotonPersonaje);
					arrayPersonajes.add(new BigSister(MenuPrincipal.nombresJugadores.get(posicion), ""));
					rellenarTextAreaConfirmados();
					posicion++;
					rellenarTextAreaJugador();

				} else if (e.getSource().equals(btnSujetoDelta)) {
					MenuPrincipal.reproducirBoton(sndBotonPersonaje, volumenBotonPersonaje);
					arrayPersonajes.add(new SujetoDelta(MenuPrincipal.nombresJugadores.get(posicion), ""));
					rellenarTextAreaConfirmados();
					posicion++;
					rellenarTextAreaJugador();

				} else if (e.getSource().equals(btnLancero)) {
					MenuPrincipal.reproducirBoton(sndBotonPersonaje, volumenBotonPersonaje);
					arrayPersonajes.add(new Lancer(MenuPrincipal.nombresJugadores.get(posicion), ""));
					rellenarTextAreaConfirmados();
					posicion++;
					rellenarTextAreaJugador();

				} else if (e.getSource().equals(btnlittleSister)) {
					MenuPrincipal.reproducirBoton(sndBotonPersonaje, volumenBotonPersonaje);
					arrayPersonajes.add(new LittleSister(MenuPrincipal.nombresJugadores.get(posicion), ""));
					rellenarTextAreaConfirmados();
					posicion++;
					rellenarTextAreaJugador();
				}

			} catch(IndexOutOfBoundsException exc) {
				JOptionPane.showMessageDialog(null, "Todos los jugadores participantes ya han seleccionado personaje." + "\n" + "Haz click en 'Iniciar Partida'.", "Información", JOptionPane.INFORMATION_MESSAGE);
			}

		} while(posicion < arrayPersonajes.size());

		if (e.getSource().equals(btnIniciar)) {

			if(MenuPrincipal.nombresJugadores.size() != arrayPersonajes.size()) {
				JOptionPane.showMessageDialog(null, "Faltan jugadores por seleccionar personaje.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				MenuPrincipal.clipTheme.stop();
				MenuPrincipal.reproducirBoton(sndIniciar, volumenBotonIniciar);
				Combate.seAtaco = false;
				reiniciarVidas();
				
				JFrame frame3 = (JFrame) SwingUtilities.getWindowAncestor(this);
				frame3.remove(this);
				frame3.getContentPane().add(new Combate());
				frame3.setVisible(true);
			}
		}
	}
}
