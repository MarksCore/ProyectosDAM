package org.example;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;

public class PreguntaDAO {

    String ruta = ".\\database\\preguntas.db";

    public void almacenar(Pregunta pregunta) throws Exception {
        ODB odb = null;

        try {
            odb = ODBFactory.open(ruta);
            odb.store(pregunta);

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public ArrayList<Pregunta> listar() throws Exception {
        ODB odb = null;

        try {
            odb = ODBFactory.open(ruta);
            Objects preguntas = odb.getObjects(Pregunta.class);

            return (ArrayList<Pregunta>) preguntas;

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public Ranking obtenerRanking() throws Exception {
        ODB odb = null;

        try {
            odb = ODBFactory.open(ruta);
            Objects rankings = odb.getObjects(Ranking.class);
            Ranking ranking = null;

            while(rankings.hasNext()){
                ranking = (Ranking) rankings.next();
            }
            return ranking;

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public void almacenarRanking(Ranking ranking) throws Exception {
        ODB odb = null;

        try {
            odb = ODBFactory.open(ruta);
            odb.store(ranking);

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }

    public void actualizarRanking(Ranking ranking) throws Exception {
        ODB odb = null;

        try {
            odb = ODBFactory.open(ruta);
            Objects rankings = odb.getObjects(Ranking.class);
            Ranking rankingActualizado = null;

            while(rankings.hasNext()){
                rankingActualizado = (Ranking) rankings.next();
            }

            if(!ranking.getMejoresJugadores().isEmpty()) {
                rankingActualizado.setMejoresJugadores(ranking.getMejoresJugadores());
            }

            odb.store(rankingActualizado);

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
