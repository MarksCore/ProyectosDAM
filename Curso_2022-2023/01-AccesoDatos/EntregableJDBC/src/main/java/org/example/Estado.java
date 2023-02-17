package org.example;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Estado {

    private ArrayList<Ciudad> listaCiudades;

    public ArrayList<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    @XmlElement(name = "City")
    public void setListaCiudades(ArrayList<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }
}
