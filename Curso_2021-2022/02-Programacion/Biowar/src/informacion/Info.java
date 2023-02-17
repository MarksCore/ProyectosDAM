package informacion;


public class Info {

	public static String verReglasJuego() {

		String reglasJuego = "- Cada nueva partida estar� compuesta inicialmente por 3 jugadores como m�nimo y 10 como m�ximo." + "\n" + "\n" +
		
				"- Al inicio, cada jugador deber� escoger un nombre y un tipo de personaje, el cual proporcionar� ciertas ventajas y desventajas con respecto a los personajes de los jugadores contrincantes." + "\n" + "\n" +

				"- Todo jugador dispondr� de un nivel de EVE  por turno  (puntos de acci�n) y un nivel de salud por partida (puntos de vida)." + "\n" + "\n" +

				"- Durante la partida se jugar�n sucesivas rondas y estas ser�n de car�cter eliminatorio." + "\n" + "\n" +

				"- Cada ronda se dividir� en consecutivos turnos en los cuales cada jugador no eliminado tendr� dos opciones, atacar o defender, lo cual consumir� puntos de EVE." + "\n" + "\n" +

				"- Si un equipo opta por atacar, primeramente tendr� que seleccionar a uno de los posibles jugadores enemigos. A continuaci�n, deber� teclear el n�mero de puntos de EVE con los que ejecutar el ataque." + "\n" + "\n" +

				"- Un jugador podr� realizar varios ataques al mismo o a diferentes objetivos enemigos durante su turno." + "\n" + "\n" +

				"- Si un equipo opta por defenderse se sumar� la mitad de los puntos de EVE disponibles a su nivel de vida." + "\n" + "\n" +

				"- Un equipo ser� eliminado siempre que su n�mero de puntos de vida alcance el valor cero." + "\n" + "\n" +

				"- Las estrategias llevadas a cabo durante cada ronda (ataque o defensa), ademas del jugador que haya sido eliminado, si se presenta ese caso, se podr�n visualizar al final de las mismas." + "\n" +  "\n" +

				"- El juego finalizar� cuando solo permanezca un equipo con puntos de vida, este �ltimo ser� el ganador." + "\n" + "\n" +

				"- Si todos los jugadores son eliminados, no habr� supervivientes, por tanto, no existir� ganador.";

		return reglasJuego;
	}

	public static String verCreditos() {

		String creditos = "Versi�n: A1.0" + "\n" + "\n" +
				"Contacto: marcos_91cp@hotmail.com" + "\n" + "\n" +
				"Autores: Marcos Cervi�o Pardo";

		return creditos;
	}


	public static String verInfoEstadisticas(String tipoPersonaje) {

		String infoEstadisticas = "";

		if(tipoPersonaje.equals("Bouncer")) {
			infoEstadisticas = "                   Da�o a Big Sister (x2)" + "\n" + "                Da�o a Sujeto Delta (/2)";
		} else if(tipoPersonaje.equals("BigSister")) {
			infoEstadisticas = "                Da�o a Sujeto Delta (x2)" + "\n" + "                   Da�o a Bouncer (/2)";
		} else if(tipoPersonaje.equals("SujetoDelta")) {
			infoEstadisticas = "                   Da�o a Bouncer (x2)" + "\n" + "                   Da�o a Big Sister (/2)";
		} else if(tipoPersonaje.equals("Lancer")) {
			infoEstadisticas = " Puntos de EVE al inicio (+10 por ronda)" + "\n" + "                    Vida al inicio (x2)";
		} else if(tipoPersonaje.equals("LittleSister")) {
			infoEstadisticas = "       Probabilidad de esquivar (50%)" + "\n" + "                     Vida al inicio (/2)";
		}

		return infoEstadisticas;

	}

}


