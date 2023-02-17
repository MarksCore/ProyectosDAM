package eventos;

import datos.Datos;
import fuentes.FuentesPersonalizadas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JPanel implements ActionListener {
    private final JButton btnAtras;
    private final JLabel lblBackgroungCreditos;
    private final JLabel lblCreditos;
    private final JLabel lblLogoCreditos;
    private JLabel lblMarcoCreditos;
    private final JTextArea jtpRanking;
    private final JScrollPane scrollRanking;

    private final String rankingTheme = "src/audio/rankingtheme.wav";
    private final float volumenRankingTheme = -8.0f;

    FuentesPersonalizadas tipoFuente;

    public Ranking() {
        setLayout(null);
        tipoFuente = new FuentesPersonalizadas();

        lblCreditos = new JLabel("Ranking");
        lblCreditos.setBounds(765, 50, 400, 220);
        lblCreditos.setFont(tipoFuente.fuente(tipoFuente.itcAnnaStd, 0, 150));
        lblCreditos.setForeground(Color.darkGray);
        lblCreditos.setBackground(Color.darkGray);
        lblCreditos.setOpaque(false);
        add(lblCreditos);

        jtpRanking = new JTextArea();
        jtpRanking.setText(Datos.leerXML());
        jtpRanking.setBounds(760, 425, 320, 350);
        jtpRanking.setFont(tipoFuente.fuente(tipoFuente.broadway, 1, 18));
        jtpRanking.setForeground(Color.WHITE);
        jtpRanking.setBackground(Color.white);
        jtpRanking.setLineWrap(true);
        jtpRanking.setWrapStyleWord(true);
        jtpRanking.setEditable(false);
        jtpRanking.setOpaque(false);
        scrollRanking = new JScrollPane(jtpRanking, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRanking.setBounds(760, 425, 370, 350);
        scrollRanking.setOpaque(false);
        scrollRanking.getViewport().setOpaque(false);
        scrollRanking.setBorder(BorderFactory.createEmptyBorder());
        scrollRanking.setViewportBorder(BorderFactory.createEmptyBorder());
        add(scrollRanking);

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonHover, MenuPrincipal.volumenBotonHover);
                btnAtras.setForeground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
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
        ImageIcon marcoCreditosIcon = new ImageIcon(marcoCreditos.getScaledInstance(600, 700, Image.SCALE_SMOOTH));
        lblMarcoCreditos = new JLabel();
        lblMarcoCreditos.setBounds(637, 215, 600, 700);
        lblMarcoCreditos.setIcon(marcoCreditosIcon);
        lblMarcoCreditos.setOpaque(false);
        add(lblMarcoCreditos);

        Image marcoCreditosBack = new ImageIcon("src/img/RECURSOS/rankingbgcuadro.png").getImage();
        ImageIcon marcoCreditosBackIcon = new ImageIcon(marcoCreditosBack.getScaledInstance(600, 700, Image.SCALE_SMOOTH));
        lblMarcoCreditos = new JLabel();
        lblMarcoCreditos.setBounds(637, 215, 600, 700);
        lblMarcoCreditos.setIcon(marcoCreditosBackIcon);
        lblMarcoCreditos.setOpaque(false);
        add(lblMarcoCreditos);

        Image backgroundCreditos = new ImageIcon("src/img/fondos/bgranking.jpg").getImage();
        ImageIcon backgroundCreditosIcon = new ImageIcon(backgroundCreditos.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        lblBackgroungCreditos = new JLabel();
        lblBackgroungCreditos.setBounds(0, 0, 1920, 1080);
        lblBackgroungCreditos.setIcon(backgroundCreditosIcon);
        add(lblBackgroungCreditos);

        MenuPrincipal.reproducirBSO(rankingTheme, volumenRankingTheme);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(btnAtras)) {
            MenuPrincipal.reproducirBoton(MenuPrincipal.sndBotonAtras, MenuPrincipal.volumenBotonAtras);
            MenuPrincipal.clipTheme.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.getContentPane().add(new MenuPrincipal());
            frame.setVisible(true);
        }
    }
}
