package eventos;

import diseno.RoundButton2;
import fuentes.FuentesPersonalizadas;
import objetos.Personaje;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Combate extends JPanel implements ActionListener {
    private final JLabel lblBackgroundFight;
    private JLabel lblMarcoPerfilJugador;
    private final JLabel lblMarcoBotonAtacar;
    private final JLabel lblMarcoBotonDefender;
    private final JLabel lblAtacar;
    private final JLabel lblBotonAtacar;
    private final JLabel lblDefender;
    private final JLabel lblBotondefender;
    private final JLabel lblBarraVida;
    private final JLabel lblBarraEve;
    private final JLabel lblIconoPersonaje;
    private final JLabel lblSeleccionAtaque;
    private final JLabel lblPuntosAtaque;

    private final JButton btnAtacar;
    private final JButton btnDefender;

    private final JComboBox<String> jcbSeleccionAtaque;

    private final JTextField jfPuntosAtaque;

    private final JLabel lblPuntosVida;
    private final JLabel lblPuntosAtaqueRestantes;

    private final JLabel lblNombreJugador;
    private final JLabel lblTipoPersonaje;

    private final String sonidoAtacar = "src/audio/sonidoatacar.wav";
    private final float volumenBotonAtacar = 0.0f;
    private final String sonidoDefender = "src/audio/sonidodefender.wav";
    private final float volumenBotonDefender = -5.0f;
    private final String combateTheme = "src/audio/combatetheme.wav";
    private final float volumenCombateTheme = -10.0f;

    static boolean seAtaco = false;

    private final Color color1;

    static String resultadoRonda = "";
    static int posicion;
    static int contRondas = 1;

    FuentesPersonalizadas tipoFuente;

    public Combate() {
        setLayout(null);
        tipoFuente = new FuentesPersonalizadas();

        color1 = new Color(200, 225, 200);

        Image iconoPersonaje = new ImageIcon(Personaje.mostrarIconoPersonaje(ElegirPersonaje.arrayPersonajes.get(posicion))).getImage();
        ImageIcon iconoPersonajeIcon = new ImageIcon(iconoPersonaje.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        lblIconoPersonaje = new JLabel();
        lblIconoPersonaje.setBounds(724, 85, 100, 100);
        lblIconoPersonaje.setIcon(iconoPersonajeIcon);
        lblIconoPersonaje.setOpaque(false);
        add(lblIconoPersonaje);

        Image marcoPerfiljugador = new ImageIcon("src/img/otros/marcoperfil.png").getImage();
        ImageIcon marcoPerfiljugadorIcon = new ImageIcon(marcoPerfiljugador.getScaledInstance(220, 170, Image.SCALE_SMOOTH));
        lblMarcoPerfilJugador = new JLabel();
        lblMarcoPerfilJugador.setBounds(685, 50, 220, 170);
        lblMarcoPerfilJugador.setIcon(marcoPerfiljugadorIcon);
        lblMarcoPerfilJugador.setOpaque(false);
        add(lblMarcoPerfilJugador);

        lblPuntosVida = new JLabel();
        lblPuntosVida.setText(String.valueOf((ElegirPersonaje.arrayVidas[posicion])));
        lblPuntosVida.setBounds(925, 70, 60, 57);
        lblPuntosVida.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 35));
        lblPuntosVida.setForeground(Color.WHITE);
        lblPuntosVida.setBackground(Color.BLACK);
        lblPuntosVida.setHorizontalAlignment(JLabel.CENTER);
        lblPuntosVida.setVerticalAlignment(JLabel.CENTER);
        lblPuntosVida.setOpaque(true);
        add(lblPuntosVida);


        Image barraVida = new ImageIcon("src/img/otros/barravida2.png").getImage();
        ImageIcon barraVidaIcon = new ImageIcon(barraVida.getScaledInstance(200, 70, Image.SCALE_SMOOTH));
        lblBarraVida = new JLabel();
        lblBarraVida.setBounds(840, 62, 200, 70);
        lblBarraVida.setIcon(barraVidaIcon);
        lblBarraVida.setOpaque(false);
        add(lblBarraVida);

        lblPuntosAtaqueRestantes = new JLabel();
        lblPuntosAtaqueRestantes.setText(String.valueOf(ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles()));
        lblPuntosAtaqueRestantes.setBounds(925, 145, 60, 57);
        lblPuntosAtaqueRestantes.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 35));
        lblPuntosAtaqueRestantes.setForeground(Color.WHITE);
        lblPuntosAtaqueRestantes.setBackground(Color.BLACK);
        lblPuntosAtaqueRestantes.setHorizontalAlignment(JLabel.CENTER);
        lblPuntosAtaqueRestantes.setVerticalAlignment(JLabel.CENTER);
        lblPuntosAtaqueRestantes.setOpaque(true);
        add(lblPuntosAtaqueRestantes);

        Image barraAdam = new ImageIcon("src/img/otros/barraadam2.png").getImage();
        ImageIcon barraAdamIcon = new ImageIcon(barraAdam.getScaledInstance(200, 70, Image.SCALE_SMOOTH));
        lblBarraEve = new JLabel();
        lblBarraEve.setBounds(840, 138, 200, 70);
        lblBarraEve.setIcon(barraAdamIcon);
        lblBarraEve.setOpaque(false);
        add(lblBarraEve);

        lblNombreJugador = new JLabel();
        lblNombreJugador.setText(ElegirPersonaje.arrayPersonajes.get(posicion).getNombre());
        lblNombreJugador.setBounds(1060, 70, 240, 50);
        lblNombreJugador.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 26));
        lblNombreJugador.setForeground(Color.WHITE);
        lblNombreJugador.setHorizontalAlignment(JLabel.RIGHT);
        lblNombreJugador.setVerticalAlignment(JLabel.CENTER);
        lblNombreJugador.setOpaque(false);
        add(lblNombreJugador);

        lblTipoPersonaje = new JLabel();
        lblTipoPersonaje.setText(ElegirPersonaje.arrayPersonajes.get(posicion).descripcionTipo(ElegirPersonaje.arrayPersonajes.get(posicion)));
        lblTipoPersonaje.setBounds(1172, 150, 125, 40);
        lblTipoPersonaje.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 16));
        lblTipoPersonaje.setForeground(Color.ORANGE);
        lblTipoPersonaje.setHorizontalAlignment(JLabel.RIGHT);
        lblTipoPersonaje.setVerticalAlignment(JLabel.CENTER);
        lblTipoPersonaje.setOpaque(false);
        add(lblTipoPersonaje);

        Image cuadroJugador = new ImageIcon("src/img/otros/cuadrojugador.png").getImage();
        ImageIcon cuadroJugadorIcon = new ImageIcon(cuadroJugador.getScaledInstance(720, 260, Image.SCALE_SMOOTH));
        lblMarcoPerfilJugador = new JLabel();
        lblMarcoPerfilJugador.setBounds(650, 5, 720, 260);
        lblMarcoPerfilJugador.setIcon(cuadroJugadorIcon);
        lblMarcoPerfilJugador.setOpaque(false);
        add(lblMarcoPerfilJugador);

        lblSeleccionAtaque = new JLabel("Objetivo");
        lblSeleccionAtaque.setBounds(975, 332, 150, 40);
        lblSeleccionAtaque.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 26));
        lblSeleccionAtaque.setForeground(Color.WHITE);
        lblSeleccionAtaque.setOpaque(false);
        add(lblSeleccionAtaque);

        jcbSeleccionAtaque = new JComboBox<String>();
        jcbSeleccionAtaque.setBounds(1060, 325, 200, 35);
        jcbSeleccionAtaque.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 20));
        jcbSeleccionAtaque.setBorder(new LineBorder(Color.darkGray, 3, false));
        for (int i = 0; i < ElegirPersonaje.arrayPersonajes.size(); i++) {
            if (!ElegirPersonaje.arrayPersonajes.get(i).getNombre().equals(ElegirPersonaje.arrayPersonajes.get(posicion).getNombre())) {
                jcbSeleccionAtaque.addItem(ElegirPersonaje.arrayPersonajes.get(i).getNombre());
            }
        }
        add(jcbSeleccionAtaque);

        lblPuntosAtaque = new JLabel("Puntos de EVE para ataque");
        lblPuntosAtaque.setBounds(975, 382, 250, 40);
        lblPuntosAtaque.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 26));
        lblPuntosAtaque.setForeground(Color.WHITE);
        lblPuntosAtaque.setOpaque(false);
        add(lblPuntosAtaque);

        jfPuntosAtaque = new JTextField();
        jfPuntosAtaque.setBounds(1220, 380, 40, 30);
        jfPuntosAtaque.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 1, 20));
        jfPuntosAtaque.setBorder(new LineBorder(Color.darkGray, 3, false));
        jfPuntosAtaque.setOpaque(true);
        add(jfPuntosAtaque);


        // ELEMENTOS DE BOTÓN ATAQUE.
        lblAtacar = new JLabel("Ataque");
        lblAtacar.setBounds(806, 403, 175, 40);
        lblAtacar.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 34));
        lblAtacar.setForeground(Color.WHITE);
        lblAtacar.setOpaque(false);
        add(lblAtacar);

        Image botonAtacarImagen = new ImageIcon("src/img/botones/botonAtacar.png").getImage();
        ImageIcon botonAtacarImagenIcon = new ImageIcon(botonAtacarImagen.getScaledInstance(123, 123, Image.SCALE_SMOOTH));
        lblBotonAtacar = new JLabel();
        lblBotonAtacar.setBounds(779, 329, 123, 123);
        lblBotonAtacar.setOpaque(false);
        lblBotonAtacar.setIcon(botonAtacarImagenIcon);
        add(lblBotonAtacar);

        btnAtacar = new RoundButton2();
        btnAtacar.setBounds(753, 303, 160, 160);
        btnAtacar.setOpaque(false);
        btnAtacar.addActionListener(this);
        add(btnAtacar);
        btnAtacar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtacar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
                lblAtacar.setForeground(color1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAtacar.setForeground(Color.WHITE);
            }
        });

        Image marcoBotonAtacar = new ImageIcon("src/img/botones/marcobotonatacar2.png").getImage();
        ImageIcon marcoBotonAtacarIcon = new ImageIcon(marcoBotonAtacar.getScaledInstance(182, 182, Image.SCALE_SMOOTH));
        lblMarcoBotonAtacar = new JLabel();
        lblMarcoBotonAtacar.setBounds(750, 300, 182, 182);
        lblMarcoBotonAtacar.setIcon(marcoBotonAtacarIcon);
        lblMarcoBotonAtacar.setOpaque(false);
        add(lblMarcoBotonAtacar);


        // ELEMENTOS DE BOTÓN DEFENSA.
        lblDefender = new JLabel("Defensa");
        lblDefender.setBounds(987, 587, 175, 40);
        lblDefender.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 34));
        lblDefender.setForeground(Color.WHITE);
        lblDefender.setOpaque(false);
        add(lblDefender);

        Image botonDefenderImagen = new ImageIcon("src/img/botones/botonDefender.png").getImage();
        ImageIcon botonDefenderImagenIcon = new ImageIcon(botonDefenderImagen.getScaledInstance(123, 123, Image.SCALE_SMOOTH));
        lblBotondefender = new JLabel();
        lblBotondefender.setBounds(963, 513, 123, 123);
        lblBotondefender.setOpaque(false);
        lblBotondefender.setIcon(botonDefenderImagenIcon);
        add(lblBotondefender);

        btnDefender = new RoundButton2();
        btnDefender.setBounds(937, 488, 160, 160);
        btnDefender.setOpaque(false);
        btnDefender.addActionListener(this);
        add(btnDefender);
        btnDefender.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDefender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
                lblDefender.setForeground(color1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDefender.setForeground(Color.WHITE);
            }
        });

        Image marcoBotonDefender = new ImageIcon("src/img/botones/marcobotondefender.png").getImage();
        ImageIcon marcoBotonDefenderIcon = new ImageIcon(marcoBotonDefender.getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        lblMarcoBotonDefender = new JLabel();
        lblMarcoBotonDefender.setBounds(950, 500, 150, 150);
        lblMarcoBotonDefender.setIcon(marcoBotonDefenderIcon);
        lblMarcoBotonDefender.setOpaque(false);
        add(lblMarcoBotonDefender);


        Image backgroundFight = new ImageIcon("src/img/fondos/bgfight2.jpg").getImage();
        ImageIcon backgroundFightIcon = new ImageIcon(backgroundFight.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        lblBackgroundFight = new JLabel();
        lblBackgroundFight.setBounds(0, 0, 1920, 1080);
        lblBackgroundFight.setIcon(backgroundFightIcon);
        add(lblBackgroundFight);

        if (!seAtaco) {
            MenuPrincipal.reproducirBSO(combateTheme, volumenCombateTheme);
        }
    }


    // MÉTODOS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAtacar)) {
            int numPosicion = 0;
            boolean datoErroneo;
            int puntosAtaque = 0;

            // Obtener posición en el ArrayList "arrayPersonajes" del jugador seleccionado para el ataque en la ComboBox a través de su nombre.
            for (int i = 0; i < ElegirPersonaje.arrayPersonajes.size(); i++) {
                if (ElegirPersonaje.arrayPersonajes.get(i).getNombre().equals(jcbSeleccionAtaque.getSelectedItem())) {
                    numPosicion = i;
                }
            }

            // Control de entrada de número de puntos de ataque.
            do {
                try {
                    datoErroneo = false;
                    puntosAtaque = Integer.parseInt(jfPuntosAtaque.getText());
                    if (puntosAtaque > ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles()) {
                        JOptionPane.showMessageDialog(null, "El número de puntos de ataque introducido es superior al total de los que dispones.", "Error", JOptionPane.ERROR_MESSAGE);
                        jfPuntosAtaque.setText("0");
                    } else if (puntosAtaque < 0) {
                        JOptionPane.showMessageDialog(null, "Introduce un numero de puntos de ataque positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        jfPuntosAtaque.setText("0");
                    } else if (0 < puntosAtaque && puntosAtaque <= ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles()) {
                        MenuPrincipal.reproducirBoton(sonidoAtacar, volumenBotonAtacar);
                        jfPuntosAtaque.setText("0");
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null, "El dato introducido no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    datoErroneo = true;
                    jfPuntosAtaque.setText("0");
                }

            } while (datoErroneo || ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles() < puntosAtaque || puntosAtaque < 0);

            // Llamamos a método abstract "atacar" con los puntos de ataque y el objetivo para realizar la acción sobre el jugador atacado.
            resultadoRonda += ElegirPersonaje.arrayPersonajes.get(posicion).atacar(puntosAtaque, ElegirPersonaje.arrayPersonajes.get(numPosicion));
            lblPuntosAtaqueRestantes.setText(String.valueOf(ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles()));

            seAtaco = true;

			/* Ataque o defensa del jugador en turno hasta que sus puntos de ataque sean 0.
			Si es así, sumamos una posición al ArrayList "arrayPersonajes" para seleccionar siguiente jugador y actualizamos pantalla. */
            if (ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles() == 0) {
                posicion++;

                if (posicion == ElegirPersonaje.arrayPersonajes.size()) {
                    // Eliminar equipos con puntos de vida a 0 y añadir mensaje a historial de ronda indicando el jugador eliminado.
                    resultadoRonda += Personaje.comprobarEquiposVivos(ElegirPersonaje.arrayPersonajes);

                    MenuPrincipal.clipTheme.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.remove(this);
                    frame.getContentPane().add(new FinRonda());
                    frame.setVisible(true);
                } else {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.remove(this);
                    frame.getContentPane().add(new Combate());
                    frame.setVisible(true);
                }
            }
        } else if (e.getSource().equals(btnDefender)) {
            MenuPrincipal.reproducirBoton(sonidoDefender, volumenBotonDefender);
            resultadoRonda += Personaje.defender(ElegirPersonaje.arrayPersonajes.get(posicion), ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles());
            lblPuntosVida.setText(String.valueOf(ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles()));
            seAtaco = true;

            if (ElegirPersonaje.arrayPersonajes.get(posicion).getMisiles() == 0) {
                posicion++;

                if (posicion == ElegirPersonaje.arrayPersonajes.size()) {
                    resultadoRonda += Personaje.comprobarEquiposVivos(ElegirPersonaje.arrayPersonajes);
                    MenuPrincipal.clipTheme.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.remove(this);
                    frame.getContentPane().add(new FinRonda());
                    frame.setVisible(true);
                } else {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.remove(this);
                    frame.getContentPane().add(new Combate());
                    frame.setVisible(true);
                }
            }
        }
    }
}
