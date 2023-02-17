package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        String letraAcierto = "";
        String letraSeleccionada;
        int numJugadores = 0;
        boolean datoCorrecto;

        PreguntaDAO preguntaDAO = new PreguntaDAO();
        ArrayList<Pregunta> preguntas = preguntaDAO.listar();

        Ranking rankingJugadores = preguntaDAO.obtenerRanking() == null ? new Ranking() : preguntaDAO.obtenerRanking();

        do {
            try {
                System.out.println("Introduce el número de jugadores:");
                numJugadores = teclado.nextInt();
                if (numJugadores <= 0) {
                    System.out.println("Error: número de jugadores menor o igual a cero.\n");
                }
                datoCorrecto = true;
            } catch (Exception e) {
                datoCorrecto = false;
                System.out.println("El dato introducido no es válido.\n");
                teclado.nextLine();
            }
        } while (!datoCorrecto || numJugadores <= 0);

        for (int jug = 0; jug < numJugadores; jug++) {
            teclado.nextLine();
            System.out.println("Jugador " + (jug + 1) + ", introduce tu nombre:");
            Jugador jugador = new Jugador();
            jugador.setNombre(teclado.nextLine());

            Collections.shuffle(preguntas);

            for (int i = 0; i < preguntas.size(); i++) {
                System.out.println("\nPregunta " + (i + 1) + ": " + preguntas.get(i).getTexto());

                for (int j = 0; j < preguntas.get(i).getOpciones().size(); j++) {
                    System.out.println(preguntas.get(i).getOpciones().get(j).getTexto());
                    if (preguntas.get(i).getOpciones().get(j).isAcierto()) {
                        letraAcierto = preguntas.get(i).getOpciones().get(j).getTexto().substring(0, 1);
                    }
                }
                //System.out.println(letraAcierto);
                do {
                    System.out.println("\nIntroduce tu respuesta:");
                    letraSeleccionada = teclado.next();

                    if (!(letraSeleccionada.equals("a") || letraSeleccionada.equals("b") ||
                            letraSeleccionada.equals("c") || letraSeleccionada.equals("d"))) {
                        System.out.println("No existe la respuesta introducida.");
                    }

                } while (!(letraSeleccionada.equals("a") || letraSeleccionada.equals("b") ||
                        letraSeleccionada.equals("c") || letraSeleccionada.equals("d")));

                if (letraSeleccionada.equals(letraAcierto)) {
                    jugador.setPuntuacion(jugador.getPuntuacion() + 1);
                    System.out.println("Has acertado");
                } else {
                    System.out.println("Has fallado.");
                }
            }
            System.out.println("Puntuación total: " + jugador.getPuntuacion() + "\n");

            rankingJugadores.getMejoresJugadores().add(jugador);
        }
        preguntaDAO.actualizarRanking(rankingJugadores);
    }
}
