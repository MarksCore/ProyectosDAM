package informacion;


public class Info {

	public static String verReglasJuego() {

		String reglasJuego = "- Cada nueva partida estará compuesta inicialmente por 3 jugadores como mínimo y 10 como máximo." + "\n" + "\n" +
		
				"- Al inicio, cada jugador deberá escoger un nombre y un tipo de personaje, el cual proporcionará ciertas ventajas y desventajas con respecto a los personajes de los jugadores contrincantes." + "\n" + "\n" +

				"- Todo jugador dispondrá de un nivel de EVE  por turno  (puntos de acción) y un nivel de salud por partida (puntos de vida)." + "\n" + "\n" +

				"- Durante la partida se jugarán sucesivas rondas y estas serán de carácter eliminatorio." + "\n" + "\n" +

				"- Cada ronda se dividirá en consecutivos turnos en los cuales cada jugador no eliminado tendrá dos opciones, atacar o defender, lo cual consumirá puntos de EVE." + "\n" + "\n" +

				"- Si un equipo opta por atacar, primeramente tendrá que seleccionar a uno de los posibles jugadores enemigos. A continuación, deberá teclear el número de puntos de EVE con los que ejecutar el ataque." + "\n" + "\n" +

				"- Un jugador podrá realizar varios ataques al mismo o a diferentes objetivos enemigos durante su turno." + "\n" + "\n" +

				"- Si un equipo opta por defenderse se sumará la mitad de los puntos de EVE disponibles a su nivel de vida." + "\n" + "\n" +

				"- Un equipo será eliminado siempre que su número de puntos de vida alcance el valor cero." + "\n" + "\n" +

				"- Las estrategias llevadas a cabo durante cada ronda (ataque o defensa), ademas del jugador que haya sido eliminado, si se presenta ese caso, se podrán visualizar al final de las mismas." + "\n" +  "\n" +

				"- El juego finalizará cuando solo permanezca un equipo con puntos de vida, este último será el ganador." + "\n" + "\n" +

				"- Si todos los jugadores son eliminados, no habrá supervivientes, por tanto, no existirá ganador.";

		return reglasJuego;
	}

	public static String verCreditos() {

		String creditos = "Versión: A1.0" + "\n" + "\n" +
				"Contacto: marcos_91cp@hotmail.com" + "\n" + "\n" +
				"Autores: Marcos Cerviño Pardo";

		return creditos;
	}


	public static String verInfoEstadisticas(String tipoPersonaje) {

		String infoEstadisticas = "";

		if(tipoPersonaje.equals("Bouncer")) {
			infoEstadisticas = "                   Daño a Big Sister (x2)" + "\n" + "                Daño a Sujeto Delta (/2)";
		} else if(tipoPersonaje.equals("BigSister")) {
			infoEstadisticas = "                Daño a Sujeto Delta (x2)" + "\n" + "                   Daño a Bouncer (/2)";
		} else if(tipoPersonaje.equals("SujetoDelta")) {
			infoEstadisticas = "                   Daño a Bouncer (x2)" + "\n" + "                   Daño a Big Sister (/2)";
		} else if(tipoPersonaje.equals("Lancer")) {
			infoEstadisticas = " Puntos de EVE al inicio (+10 por ronda)" + "\n" + "                    Vida al inicio (x2)";
		} else if(tipoPersonaje.equals("LittleSister")) {
			infoEstadisticas = "       Probabilidad de esquivar (50%)" + "\n" + "                     Vida al inicio (/2)";
		}

		return infoEstadisticas;

	}

}


