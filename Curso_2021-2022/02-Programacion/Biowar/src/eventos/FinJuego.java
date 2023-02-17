package eventos;

import datos.Datos;
import datos.Datos_2;
import fuentes.FuentesPersonalizadas;
import objetos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinJuego extends JPanel implements ActionListener{
	private final JLabel lblBackgroundFinJuego;
	private final JLabel lblGanador;
	private final JLabel lblTituloGanador;
	private final JLabel lblcajaGanador;
	private final JLabel lblcajaEstadisticas;

	private final JLabel lblNombreGanador;

	private final JButton btnVolver;

	private final String finJuegoTheme = "src/audio/finjuegotheme.wav";
	private final float volumenFinJuego = 3.0f;
	private final String finJuegoSadTheme = "src/audio/finjuegosadtheme.wav";
	private final float volumenFinJuegoSad = 0.0f;

	private int idGanador;
	private String fechaRegistro = "";
	private final Image ganador;
	FuentesPersonalizadas tipoFuente;

	public FinJuego() {
		setLayout(null);
		tipoFuente = new FuentesPersonalizadas();

		lblTituloGanador = new JLabel("GANADOR");
		lblTituloGanador.setBounds(775, 135, 500, 120);
		lblTituloGanador.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 0, 100));
		lblTituloGanador.setForeground(Color.WHITE);
		lblTituloGanador.setOpaque(false);
		add(lblTituloGanador);

		lblNombreGanador = new JLabel();
		if(ElegirPersonaje.arrayPersonajes.size() == 0) {
			lblNombreGanador.setText("Nadie ha sobrevivido.");
		} else {
			lblNombreGanador.setText(ElegirPersonaje.arrayPersonajes.get(0).getNombre());
		}
		lblNombreGanador.setBounds(555, 850, 811, 110);
		lblNombreGanador.setFont(tipoFuente.fuente(tipoFuente.bettyNoir, 1, 50));
		lblNombreGanador.setForeground(Color.ORANGE);
		lblNombreGanador.setHorizontalAlignment(JLabel.CENTER);
		lblNombreGanador.setOpaque(false);
		add(lblNombreGanador);

		if(ElegirPersonaje.arrayPersonajes.size() == 0) {
			ganador = new ImageIcon("src/img/personajes/bigdaddydead.jpg").getImage();
			MenuPrincipal.reproducirBSO(finJuegoSadTheme, volumenFinJuegoSad);
		} else {
			ganador = new ImageIcon(Personaje.mostrarImagenGanador(ElegirPersonaje.arrayPersonajes.get(0))).getImage();
			MenuPrincipal.reproducirBSO(finJuegoTheme, volumenFinJuego);
		}
		ImageIcon ganadorIcon = new ImageIcon(ganador.getScaledInstance(811, 447, Image.SCALE_SMOOTH));
		lblGanador = new JLabel();
		lblGanador.setIcon(ganadorIcon);
		lblGanador.setBounds(555, 310, 811, 447);
		lblGanador.setVisible(true);
		add(lblGanador);

		btnVolver = new JButton("Volver a menú");
		btnVolver.setBounds(200, 870, 250, 100);
		btnVolver.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.addActionListener(this);
		add(btnVolver);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) 
			{
				MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
				btnVolver.setForeground(Color.ORANGE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) 
			{
				btnVolver.setForeground(Color.WHITE);
			}
		});


		Image cajaGanador = new ImageIcon("src/img/otros/cajaganador3.png").getImage();
		ImageIcon cajaGanadorIcon = new ImageIcon(cajaGanador.getScaledInstance(1100, 300, Image.SCALE_SMOOTH));
		lblcajaGanador = new JLabel();
		lblcajaGanador.setBounds(409, 0, 1100, 300);
		lblcajaGanador.setIcon(cajaGanadorIcon);
		add(lblcajaGanador);

		Image cajaEstadisticas = new ImageIcon("src/img/otros/cajafinjuego.png").getImage();
		ImageIcon cajaEstadisticasIcon = new ImageIcon(cajaEstadisticas.getScaledInstance(930, 200, Image.SCALE_SMOOTH));
		lblcajaEstadisticas = new JLabel();
		lblcajaEstadisticas.setBounds(498, 780, 933, 200);
		lblcajaEstadisticas.setIcon(cajaEstadisticasIcon);
		add(lblcajaEstadisticas);

		Image backgroundFinJuego = new ImageIcon("src/img/fondos/bgfinjuego.png").getImage();
		ImageIcon backgroundFinJuegoIcon = new ImageIcon(backgroundFinJuego.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
		lblBackgroundFinJuego = new JLabel();
		lblBackgroundFinJuego.setBounds(0, 0, 1920, 1080);
		lblBackgroundFinJuego.setIcon(backgroundFinJuegoIcon);
		add(lblBackgroundFinJuego);

		if(ElegirPersonaje.arrayPersonajes.size() > 0) {
			for(int i = 0; i < MenuPrincipal.nombresJugadores.size(); i++) {
				if(MenuPrincipal.nombresJugadores.get(i).equals(ElegirPersonaje.arrayPersonajes.get(0).getNombre())) {
					idGanador = MenuPrincipal.nombresJugadores.indexOf(MenuPrincipal.nombresJugadores.get(i)) + 1;
				}
			}

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			fechaRegistro = dtf.format(LocalDateTime.now());

			Datos_2 datos = new Datos_2(String.valueOf(idGanador), ElegirPersonaje.arrayPersonajes.get(0).getNombre(), descripcionTipo(ElegirPersonaje.arrayPersonajes.get(0)), fechaRegistro);
			Datos.listaDatos.add(datos);
			Datos.guardarXML(Datos.listaDatos);
		}
	}

	public String descripcionTipo(Personaje pj) {
		String descripcion = "";

		if(pj instanceof Bouncer) {
			descripcion = "Bouncer";
		} else if(pj instanceof SujetoDelta) {
			descripcion = "Sujeto Delta";
		} else if(pj instanceof BigSister) {
			descripcion = "Big Sister";
		} else if(pj instanceof Lancer) {
			descripcion = "Lancer";
		} else if(pj instanceof LittleSister) {
			descripcion = "Little Sister";
		}

		return descripcion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			MenuPrincipal.reproducirBoton(FinRonda.sndConfirmarRonda, FinRonda.volumenBotonConfirmarRonda);
			MenuPrincipal.volverMenu = false;
			MenuPrincipal.clipTheme.stop();

			MenuPrincipal.nombresJugadores.clear();
			ElegirPersonaje.arrayPersonajes.clear();
			Combate.contRondas = 0;
			ElegirPersonaje.posicion = 0;

			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			frame.remove(this);
			frame.getContentPane().add(new MenuPrincipal());
			frame.setVisible(true);
		}

	}

}
