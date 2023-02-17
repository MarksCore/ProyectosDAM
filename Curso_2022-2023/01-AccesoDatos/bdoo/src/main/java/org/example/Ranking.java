package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Ranking implements Serializable {

    private ArrayList<Jugador> mejoresJugadores = new ArrayList<Jugador>();

    public Ranking() {
    }

    public Ranking(ArrayList<Jugador> mejoresJugadores) {
        this.mejoresJugadores = mejoresJugadores;
    }

    public ArrayList<Jugador> getMejoresJugadores() {
        return mejoresJugadores;
    }

    public void setMejoresJugadores(ArrayList<Jugador> mejoresJugadores) {
        this.mejoresJugadores = mejoresJugadores;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "mejoresJugadores=" + mejoresJugadores +
                '}';
    }
}
