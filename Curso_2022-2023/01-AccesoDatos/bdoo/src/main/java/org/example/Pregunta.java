package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Pregunta implements Serializable {
    private String texto;
    private ArrayList<Opcion> opciones;

    public Pregunta() {
    }

    public Pregunta( String texto, ArrayList<Opcion> opciones) {
        this.texto = texto;
        this.opciones = opciones;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "texto='" + texto + '\'' +
                ", opciones=" + opciones +
                '}';
    }
}
