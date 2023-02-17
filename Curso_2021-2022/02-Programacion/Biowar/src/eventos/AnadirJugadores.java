package eventos;

import fuentes.FuentesPersonalizadas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnadirJugadores extends JPanel implements ActionListener {
    private final JLabel lblBackgroundJugadores;
    private final JLabel lblNombreJugador;
    private final JLabel lblConfirmBoxJugadores;

    private final JTextField jfNombreJugador;
    private final JTextArea jtNombreJugador;

    private final JButton btnAnadirJugador;
    private final JButton btnEliminarJugador;
    private final JButton btnAtras;
    private final JButton btnConfirmar;

    private final String sndAnadir = "src/audio/sndanadir.wav";
    private final float volumenBotonAnadir = 0.0f;
    private final String sndEliminar = "src/audio/sndeliminar.wav";
    private final float volumenBotonEliminar = 0.0f;

    private static int numJugador;
    FuentesPersonalizadas tipoFuente;

    // CONSTRUCTOR
    public AnadirJugadores() {
        setLayout(null);
        tipoFuente = new FuentesPersonalizadas();

        lblNombreJugador = new JLabel("Introduce tu nombre de jugador:");
        lblNombreJugador.setBounds(715, 125, 500, 30);
        lblNombreJugador.setFont(tipoFuente.fuente(tipoFuente.bettyNoir,0,34));
        lblNombreJugador.setForeground(Color.WHITE);
        lblNombreJugador.setOpaque(false);
        add(lblNombreJugador);

        jfNombreJugador = new JTextField();
        jfNombreJugador.setBounds(715, 165, 495, 30);
        jfNombreJugador.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 26));
        jfNombreJugador.setForeground(Color.WHITE);
        jfNombreJugador.setBackground(Color.BLACK);
        jfNombreJugador.setOpaque(true);
        add(jfNombreJugador);

        btnAnadirJugador = new JButton("Añadir jugador");
        btnAnadirJugador.setBounds(740, 210, 175, 40);
        btnAnadirJugador.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 28));
        btnAnadirJugador.setForeground(Color.WHITE);
        btnAnadirJugador.setContentAreaFilled(false);
        btnAnadirJugador.setBorderPainted(false);
        btnAnadirJugador.addActionListener(this);
        btnAnadirJugador.setOpaque(false);
        add(btnAnadirJugador);
        btnAnadirJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAnadirJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAnadirJugador.setForeground(Color.ORANGE);
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAnadirJugador.setForeground(Color.WHITE);
            }
        });

        btnEliminarJugador = new JButton("Eliminar jugador");
        btnEliminarJugador.setBounds(1005, 210, 175, 40);
        btnEliminarJugador.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 28));
        btnEliminarJugador.setForeground(Color.WHITE);
        btnEliminarJugador.setContentAreaFilled(false);
        btnEliminarJugador.setBorderPainted(false);
        btnEliminarJugador.addActionListener(this);
        btnEliminarJugador.setOpaque(false);
        add(btnEliminarJugador);
        btnEliminarJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminarJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarJugador.setForeground(Color.ORANGE);
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarJugador.setForeground(Color.WHITE);
            }
        });

        jtNombreJugador = new JTextArea();
        jtNombreJugador.setBounds(612, 349, 698, 370);
        jtNombreJugador.setFont(tipoFuente.fuente(tipoFuente.centuryGothic, 0, 28));
        jtNombreJugador.setForeground(Color.WHITE);
        jtNombreJugador.setBackground(new Color(0, 0, 0));
        jtNombreJugador.setBorder(new LineBorder(Color.darkGray, 3, true));
        jtNombreJugador.setEditable(false);
        jtNombreJugador.setOpaque(true);
        add(jtNombreJugador);

        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(350, 870, 150, 100);
        btnAtras.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setBorderPainted(false);
        btnAtras.addActionListener(this);
        add(btnAtras);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtras.setForeground(Color.ORANGE);
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtras.setForeground(Color.WHITE);
            }
        });

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(1350, 870, 200, 100);
        btnConfirmar.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 50));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(this);
        add(btnConfirmar);
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmar.setForeground(Color.ORANGE);
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmar.setForeground(Color.WHITE);
            }
        });

        Image confirmBoxJugadores = new ImageIcon("src/img/otros/bgboxplayers.png").getImage();
        ImageIcon confirmBoxJugadoresIcon = new ImageIcon(confirmBoxJugadores.getScaledInstance(783, 275, Image.SCALE_SMOOTH));
        lblConfirmBoxJugadores = new JLabel();
        lblConfirmBoxJugadores.setBounds(568, 5, 1050, 280);
        lblConfirmBoxJugadores.setIcon(confirmBoxJugadoresIcon);
        add(lblConfirmBoxJugadores);


        Image backgroundPlayers = new ImageIcon("src/img/fondos/bgplayers1.png").getImage();
        ImageIcon backgroundPlayersIcon = new ImageIcon(backgroundPlayers.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        lblBackgroundJugadores = new JLabel();
        lblBackgroundJugadores.setBounds(0, 0, 1920, 1080);
        lblBackgroundJugadores.setIcon(backgroundPlayersIcon);
        add(lblBackgroundJugadores);

        rellenarTextArea();
    }


    // MÉTODOS
    public void rellenarTextArea() {

        String nombresTextArea = "";
        for (int i = 0; i < MenuPrincipal.nombresJugadores.size(); i++) {
            nombresTextArea += " Jugador " + (i + 1) + " .- " + MenuPrincipal.nombresJugadores.get(i) + "\n";
        }

        jtNombreJugador.setText(nombresTextArea);
        jfNombreJugador.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /* Se añade nombre de jugador a ArrayList "nombresJugadores" ubicado en la clase "MenuPrincipal" cuando no se cumpla alguna de las condiciones de error.
         * Al añadir cada nuevo nombre la variable contador "numJugador" se incrementa para variar posición.*/
        if (e.getSource().equals(btnAnadirJugador)) {
            String nombre = jfNombreJugador.getText();

            if (nombre.equals("")) {
                JOptionPane.showMessageDialog(null, "No se ha introducido ningún nombre. Por favor, introduce un nombre de jugador.", "Error", JOptionPane.ERROR_MESSAGE);
                jfNombreJugador.setText("");
            } else if (nombre.length() > 15) {
                JOptionPane.showMessageDialog(null, "Introduce un nombre de jugador que no supere una extensión de 15 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
                jfNombreJugador.setText("");
            } else {
                if (MenuPrincipal.nombresJugadores.contains(nombre)) {
                    JOptionPane.showMessageDialog(null, "El nombre de jugador ya existe. Por favor, introduce otro nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                    jfNombreJugador.setText("");
                } else {
                    MenuPrincipal.reproducirBoton(sndAnadir, volumenBotonAnadir);
                    MenuPrincipal.nombresJugadores.add(nombre);
                    rellenarTextArea();
                    numJugador++;
                }
            }

        } else if (e.getSource().equals(btnEliminarJugador)) {
            if (MenuPrincipal.nombresJugadores.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Jugadores no registrados. No es posible eliminar jugador.", "Error", JOptionPane.ERROR_MESSAGE);
                jfNombreJugador.setText("");
            } else {
                MenuPrincipal.reproducirBoton(sndEliminar, volumenBotonEliminar);
                numJugador--;
                MenuPrincipal.nombresJugadores.remove(MenuPrincipal.nombresJugadores.get(numJugador));
                rellenarTextArea();
            }

        } else if (e.getSource().equals(btnConfirmar)) {
            if (10 < MenuPrincipal.nombresJugadores.size() || MenuPrincipal.nombresJugadores.size() >= 3) {
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonesPricipal, MenuPrincipal.volumenBotonesPricipal);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.remove(this);
                frame.getContentPane().add(new ElegirPersonaje());
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Número de jugadores incorrecto. Debes añadir 3 jugadores como mínimo y 10 como máximo.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource().equals(btnAtras)) {
            MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonAtras, 0.0f);
            MenuPrincipal.volverMenu = true;
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new MenuPrincipal());
            frame.setVisible(true);
        }
    }
}
