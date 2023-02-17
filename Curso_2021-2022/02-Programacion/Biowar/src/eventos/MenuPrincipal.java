package eventos;

import fuentes.FuentesPersonalizadas;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPrincipal extends JPanel implements ActionListener {
    private JLabel lblBackgroundMain;
    private JLabel lblLogo;
    private JLabel lblMenu;

    private JButton btnInutil;
    private JButton btnJugar;
    private JButton btnReglas;
    private JButton btnCreditos;
    private JButton btnRanking;
    private JButton btnSalir;

    static AudioInputStream audioTheme;
    static Clip clipTheme;
    static AudioInputStream audioClick;
    static Clip clipClick;

    private String mainTheme = "src/audio/main_theme.wav";
    static float volumenMainTheme = -15.0f;
    static String sndBotonesPricipal = "src/audio/sndbotonesprincipal.wav";
    static float volumenBotonesPricipal = 0.0f;
    static String sndBotonAtras = "src/audio/sndbotonatras.wav";
    static float volumenBotonAtras = 0.0f;
    static String sndBotonHover = "src/audio/sndbotonhover.wav";
    static float volumenBotonHover = 0.0f;

    static boolean volverMenu = false;
    static ArrayList<String> nombresJugadores;    // "nombresJugadores" es static para poder utilizarlo en otras clases e incrementar posiciones.
    FuentesPersonalizadas tipoFuente;

    public MenuPrincipal() {
        setLayout(null);
        tipoFuente = new FuentesPersonalizadas();

        btnInutil = new JButton("");
        btnInutil.setBounds(0, 0, 0, 0);
        add(btnInutil);

        // BOTONES MENÚ PRINCIPAL.
        btnJugar = new JButton("Jugar");
        btnJugar.setBounds(902, 490, 106, 65);
        btnJugar.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 40));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setContentAreaFilled(false);
        btnJugar.setBorderPainted(false);
        btnJugar.addActionListener(this);
        add(btnJugar);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnJugar.setForeground(Color.ORANGE);
                reproducirBoton(sndBotonHover, volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnJugar.setForeground(Color.WHITE);
            }
        });

        btnReglas = new JButton("Reglas de juego");
        btnReglas.setBounds(850, 555, 213, 65);
        btnReglas.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 40));
        btnReglas.setForeground(Color.WHITE);
        btnReglas.setContentAreaFilled(false);
        btnReglas.setBorderPainted(false);
        btnReglas.addActionListener(this);
        add(btnReglas);
        btnReglas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReglas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReglas.setForeground(Color.ORANGE);
                reproducirBoton(sndBotonHover, volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReglas.setForeground(Color.WHITE);
            }
        });

        btnCreditos = new JButton("Créditos");
        btnCreditos.setBounds(856, 620, 200, 65);
        btnCreditos.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 40));
        btnCreditos.setForeground(Color.WHITE);
        btnCreditos.setContentAreaFilled(false);
        btnCreditos.setBorderPainted(false);
        btnCreditos.addActionListener(this);
        add(btnCreditos);
        btnCreditos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreditos.setForeground(Color.ORANGE);
                reproducirBoton(sndBotonHover, volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreditos.setForeground(Color.WHITE);
            }
        });

        btnRanking = new JButton("Ranking");
        btnRanking.setBounds(856, 685, 200, 65);
        btnRanking.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 40));
        btnRanking.setForeground(Color.WHITE);
        btnRanking.setContentAreaFilled(false);
        btnRanking.setBorderPainted(false);
        btnRanking.addActionListener(this);
        add(btnRanking);
        btnRanking.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRanking.setForeground(Color.ORANGE);
                reproducirBoton(sndBotonHover, volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRanking.setForeground(Color.WHITE);
            }
        });

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(857, 750, 200, 65);
        btnSalir.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 40));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(this);
        add(btnSalir);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir.setForeground(Color.ORANGE);
                reproducirBoton(sndBotonHover, volumenBotonHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir.setForeground(Color.WHITE);
            }
        });

        // RECUADRO MENU PRINCIPAL.
        Image menu = new ImageIcon("src/img/otros/cuadro_menu2.png").getImage();
        ImageIcon menuIcon = new ImageIcon(menu.getScaledInstance(500, 550, Image.SCALE_SMOOTH));
        lblMenu = new JLabel();
        lblMenu.setBounds(705, 350, 500, 600);
        lblMenu.setIcon(menuIcon);
        lblMenu.setOpaque(false);
        add(lblMenu);

        // LOGO BIOWAR.
        Image logo = new ImageIcon("src/img/otros/logo7.jpg").getImage();
        ImageIcon logoIcon = new ImageIcon(logo.getScaledInstance(750, 250, Image.SCALE_SMOOTH));
        lblLogo = new JLabel();
        lblLogo.setBounds(585, 50, 750, 250);
        lblLogo.setIcon(logoIcon);
        lblLogo.setOpaque(false);
        //		lblMenu.setHorizontalAlignment(JLabel.CENTER);
        //		add(lblLogo, BorderLayout.CENTER);
        add(lblLogo);

        // IMAGEN DE FONDO.
        Image backgroundMain = new ImageIcon("src/img/fondos/bgmain.png").getImage();
        ImageIcon backgroundMainIcon = new ImageIcon(backgroundMain.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        lblBackgroundMain = new JLabel();
        lblBackgroundMain.setBounds(0, 0, 1920, 1080);
        lblBackgroundMain.setIcon(backgroundMainIcon);
        add(lblBackgroundMain);

        if (!volverMenu) {
            reproducirBSO(mainTheme, volumenMainTheme);
        }

        nombresJugadores = new ArrayList<String>();


    }

    // MÉTODOS.
    // Método para reproducir música de fondo en pantalla principal.
    public static void reproducirBSO(String sonido, float volumen) {
        try {
            try {
                audioTheme = AudioSystem.getAudioInputStream(new File(sonido).getAbsoluteFile());
                AudioFormat af = audioTheme.getFormat();
                clipTheme = AudioSystem.getClip();
                DataLine.Info info = new DataLine.Info(Clip.class, af);

                Line line1 = AudioSystem.getLine(info);

                if (!line1.isOpen()) {
                    clipTheme.open(audioTheme);
                    clipTheme.loop(Clip.LOOP_CONTINUOUSLY);
                    clipTheme.start();
                }

                FloatControl gainControl = (FloatControl) clipTheme.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volumen);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println("Error al reproducir el audio.");
            }
        } catch (Exception exc) {
            System.out.println("No hay salida de audio.");
        }
    }

    public static void reproducirBoton(String sonido, float volumen) {
        try {
            try {
                audioClick = AudioSystem.getAudioInputStream(new File(sonido).getAbsoluteFile());
                clipClick = AudioSystem.getClip();
                clipClick.open(audioClick);
                clipClick.start();

                FloatControl gainControl = (FloatControl) clipClick.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volumen);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println("Error al reproducir el audio.");
            }
        } catch (Exception exc) {
            System.out.println("No hay salida de audio.");
        }
    }

    // Métodos de acción para botones.
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(btnJugar)) {
            reproducirBoton(sndBotonesPricipal, volumenBotonesPricipal);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new AnadirJugadores());
            frame.setVisible(true);

        } else if (e.getSource().equals(btnReglas)) {
            reproducirBoton(sndBotonesPricipal, volumenBotonesPricipal);
            MenuPrincipal.clipTheme.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new ReglasJuego());
            frame.setVisible(true);

        } else if (e.getSource().equals(btnCreditos)) {
            reproducirBoton(sndBotonesPricipal, volumenBotonesPricipal);
            MenuPrincipal.clipTheme.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new Creditos());
            frame.setVisible(true);

        } else if (e.getSource().equals(btnRanking)) {
            reproducirBoton(sndBotonesPricipal, volumenBotonesPricipal);
            MenuPrincipal.clipTheme.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new Ranking());
            frame.setVisible(true);

        } else if (e.getSource().equals(btnSalir)) {
            reproducirBoton(sndBotonesPricipal, volumenBotonesPricipal);
            System.exit(ABORT);
        }
    }
}
