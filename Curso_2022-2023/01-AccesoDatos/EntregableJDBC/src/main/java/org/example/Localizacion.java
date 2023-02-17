package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Location")
public class Localizacion {
    private ArrayList<Region> listaRegiones;

    public ArrayList<Region> getListaRegiones() {
        return listaRegiones;
    }

    @XmlElement(name = "CountryRegion")
    public void setListaRegiones(ArrayList<Region> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }
}
