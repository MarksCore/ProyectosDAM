package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;


public class Aventura implements Serializable {
    ArrayList<Escena> listaEscenas;
    private String titulo;

    public Aventura() {
    }

    public Aventura(ArrayList<Escena> listaEscenas, String titulo) {
        this.listaEscenas = listaEscenas;
        this.titulo = titulo;
    }

    public ArrayList<Escena> getListaEscenas() {
        return listaEscenas;
    }
    @XmlElement(name = "escena")
    public void setListaEscenas(ArrayList<Escena> listaEscenas) {
        this.listaEscenas = listaEscenas;
    }

    public String getTitulo() {
        return titulo;
    }
    @XmlAttribute(name = "titulo")
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Aventura{" +
                "listaEscenas=" + listaEscenas +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
