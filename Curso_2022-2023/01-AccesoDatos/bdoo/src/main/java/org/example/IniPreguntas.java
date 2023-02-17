package org.example;

import java.util.ArrayList;

public class IniPreguntas {
    public static void main(String[] args) throws Exception {
        PreguntaDAO preguntaDAO = new PreguntaDAO();

        Ranking rankingJugadores = new Ranking();
        preguntaDAO.almacenarRanking(rankingJugadores);

        ArrayList<Opcion> opciones = new ArrayList<Opcion>();

        Opcion opcion1_1 = new Opcion("a) Innovación tecnológica.", true);
        Opcion opcion1_2 = new Opcion("b) Calidad tecnológica.", false);
        Opcion opcion1_3 = new Opcion("c) Productividad técnica.", false);
        Opcion opcion1_4 = new Opcion("d) Capacidad productiva.", false);

        opciones.add(opcion1_1);
        opciones.add(opcion1_2);
        opciones.add(opcion1_3);
        opciones.add(opcion1_4);

        Pregunta pregunta1 = new Pregunta("La introducción inicial de un cambio técnico en un producto o en un proceso se denomina:", opciones);
        preguntaDAO.almacenar(pregunta1);

        Opcion opcion2_1 = new Opcion("a) Costes técnicos.", false);
        Opcion opcion2_2 = new Opcion("b) Costes directos.", false);
        Opcion opcion2_3 = new Opcion("c) Costes sociales.", false);
        Opcion opcion2_4 = new Opcion("d) Costes indirectos.", true);

        opciones.clear();

        opciones.add(opcion2_1);
        opciones.add(opcion2_2);
        opciones.add(opcion2_3);
        opciones.add(opcion2_4);

        Pregunta pregunta2 = new Pregunta("Los costes que afectan a todo el proceso productivo y/o a varios productos se denominan:", opciones);
        preguntaDAO.almacenar(pregunta2);

        Opcion opcion3_1 = new Opcion("a) Rentabilidad económica.", false);
        Opcion opcion3_2 = new Opcion("b) Eficiencia.", false);
        Opcion opcion3_3 = new Opcion("c) Productividad.", true);
        Opcion opcion3_4 = new Opcion("d) Rendimiento productivo.", false);

        opciones.clear();

        opciones.add(opcion3_1);
        opciones.add(opcion3_2);
        opciones.add(opcion3_3);
        opciones.add(opcion3_4);

        Pregunta pregunta3 = new Pregunta("La relación entre la cantidad producida y los factores empleados se denomina:", opciones);
        preguntaDAO.almacenar(pregunta3);

        ArrayList<Pregunta> preguntas = preguntaDAO.listar();

        for(Pregunta pregunta : preguntas) {
            System.out.println(pregunta);
        }
    }
}
