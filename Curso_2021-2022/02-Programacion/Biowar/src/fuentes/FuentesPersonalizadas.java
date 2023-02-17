package fuentes;

import java.awt.*;
import java.io.InputStream;

public class FuentesPersonalizadas {
    private Font font = null;
    public String bettyNoir = "bettynoir.ttf";
    public String broadway = "broadway.otf";
    public String centuryGothic = "century-gothic.ttf";
    public String itcAnnaStd = "itc-anna-std.otf";

    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
     * tamanio = float
     */
    public Font fuente(String fontName, int estilo, float tamanio) {
        try {
            //Se carga la fuente
            InputStream is = getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }

}
