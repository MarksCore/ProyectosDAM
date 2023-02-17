package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "aventuras")
public class Aventuras {

    ArrayList<Aventura> listaAventuras;

    public Aventuras() {
    }

    public Aventuras(ArrayList<Aventura> listaAventuras) {
        this.listaAventuras = listaAventuras;
    }

    public ArrayList<Aventura> getListaAventuras() {
        return listaAventuras;
    }

    @XmlElement(name = "aventura")
    public void setListaAventuras(ArrayList<Aventura> listaAventuras) {
        this.listaAventuras = listaAventuras;
    }

}
