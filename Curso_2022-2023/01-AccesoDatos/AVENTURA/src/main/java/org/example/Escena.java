package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;

public class Escena implements Serializable {

    private int codigo;
    private String texto;
    private ArrayList<Opcion> opciones = new ArrayList<Opcion>();

    public Escena() {
    }

    public Escena(int codigo, String texto, ArrayList<Opcion> opciones) {
        this.codigo = codigo;
        this.texto = texto;
        this.opciones = opciones;
    }

    public int getCodigo() {
        return codigo;
    }

    @XmlAttribute(name = "codigo")
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTexto() {
        return texto;
    }

    @XmlElement(name="texto")
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    @XmlElement(name = "opcion")
    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    @Override
    public String toString() {
        return "Escena{" +
                "codigo=" + codigo +
                ", texto='" + texto + '\'' +
                ", opciones=" + opciones +
                '}';
    }
}
