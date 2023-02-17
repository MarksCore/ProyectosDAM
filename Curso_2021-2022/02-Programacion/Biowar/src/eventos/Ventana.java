package eventos;

import javax.swing.*;
import java.awt.*;

// Creamos una clase "Ventana", que contrndrá los sucesivos eventos.
public class Ventana extends JFrame {

    // Declaramos e inicializamos variable "panel" para referenciar esta ventana.
    static int panel = 1;


    public Ventana() {

        setTitle("BIOWAR");
        setResizable(true);
//        setUndecorated(true);

        setBounds(0, 0, 1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon iconoFrame = new ImageIcon("src/img/RECURSOS/bouncericono.png");
        this.setIconImage(iconoFrame.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH));


        if (panel == 1) {
            MenuPrincipal panel1 = new MenuPrincipal();
            add(panel1);
        } else if (panel == 2) {
            AnadirJugadores panel2 = new AnadirJugadores();
            add(panel2);
        } else if (panel == 3) {
            ElegirPersonaje panel3 = new ElegirPersonaje();
            add(panel3);
        } else if (panel == 4) {
            Combate panel4 = new Combate();
            add(panel4);
        } else if (panel == 5) {
            FinRonda panel5 = new FinRonda();
            add(panel5);
        }

        setVisible(true);
    }
}
