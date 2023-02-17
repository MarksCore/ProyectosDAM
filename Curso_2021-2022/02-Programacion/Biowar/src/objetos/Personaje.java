package objetos;
import java.text.DecimalFormat;
import java.util.ArrayList;

import informacion.Info;
import interfaces.InterfazTipoEquipo;

public abstract class Personaje implements InterfazTipoEquipo {

	// Atributos.
	protected int vidas;
	protected int misiles;
	protected String nombre;
	protected String idEquipo;
	protected boolean opcionLonginus;
	//	private int numEquipo = 0;
	//	public static int idEquipo = 0;

	// Constructores.
	public Personaje() {

	}

	public Personaje(String nombre, String idEquipo) {
		this.nombre = nombre;
		this.idEquipo = idEquipo;
		this.opcionLonginus = true;
		this.vidas = 200;
		this.misiles = 50;
		//		}

	}

	// Constructor para opción de configuración (no se utiliza).
	public Personaje(String nombre, String idEquipo, int vidas, int misiles) {
		this.nombre = nombre;
		this.idEquipo = idEquipo;
		this.opcionLonginus = true;
		this.vidas = vidas;
		this.misiles = misiles;
		//		}
	}


	// Métodos get y set
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getMisiles() {
		return misiles;
	}

	public void setMisiles(int misiles) {
		this.misiles = misiles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public boolean isOpcionLonginus() {
		return opcionLonginus;
	}

	public void setOpcionLonginus(boolean opcionLonginus) {
		this.opcionLonginus = opcionLonginus;
	}


	// MÉTODOS
	/* Método que recibe el objeto de clase Personaje en turno, un número de misiles y uno de los objetos de clase Personaje
	 * diferente a este primero, es decir, de los que no están en turno, escogidos como opción de ataque.
	 * El jugador en turno es el que realiza la acción de atacar y el jugador seleccionado como opción es el que recibirá el ataque.
	 * Cuando el ataque ocurra, este método restará del número de misiles total del jugador atacante, el número de misiles recibidos
	 * como parámetro de entrada y, a su vez, este último número de misiles restará un número de vidas al jugador atacado.
	 * Dependiendo de la confluencia de las subclases de Personaje (atacante y atacado), este último número será de mayor o de menor cuantía. 
	 * El método devolverá una cadena de texto para reflejar el hecho en el historial de sucesos al final de cada ronda.*/

	public abstract String atacar(int misiles, Personaje atacado);


	/* Método que recibe el objeto de clase Personaje y el número de misiles restantes del jugador que se encuentra en turno.
	 * Si el jugador opta por defenderse en el turno, se le descontarán todos los misiles restantes hasta el total disponible por ronda.
	 * De la cantidad de misiles restantes se sumarán solo la midad de esta en forma de puntos de vida.
	 * El método devolverá una cadena de texto para reflejar el hecho en el historial de sucesos al final de cada ronda.*/
	public static String defender(Personaje atacante, int misilesRestantes) {

		atacante.setMisiles(atacante.getMisiles() - misilesRestantes);
		atacante.setVidas(atacante.getVidas() + (misilesRestantes / 2));

		return atacante.getNombre() + " se ha defendido utilizando " + misilesRestantes + " (" + (misilesRestantes / 2) + ")" + " puntos de EVE." + "\n";
	}


	/* Método que recibe el ArrayList de objetos de clase Equipo y el número de misiles totales disponibles por equipo y por ronda.
	 * Se recorre el ArrayList para reestablecer el número inicial de misiles por ronda para cada objeto de la clase Equipo.
	 * También se reinicia la posibilidad de utilizar el Proyectil de Longinus.*/
	public abstract void reiniciarMisiles(int contRondas);


	/* Método que recibe el objeto de la clase Equipo que será atacado y un número de misiles como parámetros de entrada.
	 * Se generará aleatoriamente un valor que será 1 o 2 por cada misil recibido. Si el valor generado es igual a 1,
	 * se restará 1 punto de vida al equipo atacado. Si el valor generado es igual a 2, el misil no afectará a la vida.
	 * Unos contadores llevarán la cuenta de los misiles recibidos y los misiles esquivados.
	 * El método devolverá una cadena de texto para reflejar el hecho en el historial de sucesos al final de cada ronda.*/
	public static String esquivarAtaque(Personaje atacado, int misiles) {

		float contEsquivados = 0;
		float contRecibidos = 0;
		float sumaAtaque = 0;
		float porcentajeRecibidos = 0;
		float porcentajeEsquivados = 0;

		int contCeros = 0;

		if(misiles == 0) {
			contCeros += contCeros;

		} else {
			for(int i = 0; i < misiles; i++) {
				int esquivar = (int) Math.floor(Math.random()*(2-1+1)+1);

				if(esquivar == 1) {
					atacado.setVidas(atacado.getVidas() - 1);	
					contRecibidos++;
				} else {
					contEsquivados++;
				}
			}
		}
		
		sumaAtaque = contRecibidos + contEsquivados;
		porcentajeRecibidos = (contRecibidos/sumaAtaque)*100;
		porcentajeEsquivados = (contEsquivados/sumaAtaque)*100;
		
		DecimalFormat formato1 = new DecimalFormat("#");
		
		return atacado.getNombre() + " (Little Sister) ha esquivado un " + formato1.format(porcentajeEsquivados) + " % y ha recibido un " 
		+ formato1.format(porcentajeRecibidos) + " % del daño." + "\n" + "\n";
	}

	/* Método para indicar el tipo del equipo mediante un String dependiendo del valor recibido del parámetro de entrada tipo.*/
	public String descripcionTipo(Personaje pj) {

		String descripcion = "";

		if(pj instanceof Bouncer) {
			descripcion = "Bouncer";
		} else if(pj instanceof SujetoDelta) {
			descripcion = "Sujeto Delta";
		} else if(pj instanceof BigSister) {
			descripcion = "Big Sister";
		} else if(pj instanceof Lancer) {
			descripcion = "Lancer";
		} else if(pj instanceof LittleSister) {
			descripcion = "Little Sister";
		}

		return descripcion;
	}

	public static String mostrarIconoPersonaje(Personaje pj) {

		String iconoPersonaje = "";

		if(pj instanceof Bouncer) {
			iconoPersonaje = "src/img/personajes/bouncericono.png";
		} else if(pj instanceof BigSister) {
			iconoPersonaje = "src/img/personajes/bigsistericono.png";
		} else if(pj instanceof SujetoDelta) {
			iconoPersonaje = "src/img/personajes/sujetodeltaicono.png";
		} else if(pj instanceof Lancer) {
			iconoPersonaje = "src/img/personajes/lancericono.png";
		} else if(pj instanceof LittleSister) {
			iconoPersonaje = "src/img/personajes/littlesistericono.png";
		}

		return iconoPersonaje;
	}

	/* Función que recorre el ArrayList de equipos, elimina elementos y devuelve tamaño. Los equipos que dispongan de cero vidas, serán eliminados del mismo.
	 * Esta función recibe como parámetro de entrada un ArrayList y devuelve un entero que supone el número de equipos todavía en juego.
	 * También indica por consola el equipo que ha sido eliminado.*/
	//	public static int comprobarEquiposVivos(ArrayList<Personaje> arrayEquipos) {
	//
	//		for(int i = 0; i < arrayEquipos.size(); i++) {
	//			if(arrayEquipos.get(i).getVidas() <= 0) {
	//				System.out.println("El equipo " + arrayEquipos.get(i).getNombre() + " ha sido eliminado.");
	//				arrayEquipos.remove(i);
	//			}
	//		}
	//
	//		return arrayEquipos.size();
	//	}
	//	
	public static String comprobarEquiposVivos(ArrayList<Personaje> arrayEquipos) {

		String equipoDerrotados = "";
		for(int i = 0; i < arrayEquipos.size(); i++) {
			if(arrayEquipos.get(i).getVidas() <= 0) {
				equipoDerrotados += "El jugador " + arrayEquipos.get(i).getNombre() + " ha sido eliminado." + "\n";
				arrayEquipos.remove(i);
				i = -1;
			}
		}

		return equipoDerrotados;
	}

	/* Función que recorre el ArrayList de equipos y muestra la vida de cada uno de ellos.*/
	public static String resumenVidas(ArrayList<Personaje> arrayEquipos) {

		String resumenVidas = "";

		for(int i = 0; i < arrayEquipos.size(); i++) {
			if(arrayEquipos.get(i).getVidas() > 0) {
				resumenVidas += "El jugador '" + arrayEquipos.get(i).getNombre() + "' posee " + arrayEquipos.get(i).getVidas() + " puntos de salud." + "\n";
			}
		}	

		return resumenVidas;
	}

	/* Función destinada a mostrar el nombre del único superviviente final del juego, el ganador. Se toma el resultado de la
	 * función "comprobarEquiposVivos" para determinar si se dan las circunstancias para que haya ganador o no (1 equipo superviviente).
	 * También se muestra por consola el número de rondas que este ha superado.*/
		public static String mostrarGanador(ArrayList<Personaje> arrayEquipos, int contRondas) {
	
			String ganador = "";
	
			if(arrayEquipos.size() == 1) {
				ganador = "El ganador de la partida es el equipo: " + arrayEquipos.get(0).getNombre() + "\n" +
						"Ha sobrevivido " + contRondas + " rondas en total." + "\n" + "\n";	
			} else if(arrayEquipos.size() == 0) {
				ganador =  "Todos los equipos han sido eliminados. No hay ganador." + "\n" + "\n";
			}
			return ganador;
		}
		
		public static String mostrarImagenGanador(Personaje pj) {

			String imagenGanador = "";

			if(pj instanceof Bouncer) {
				imagenGanador = "src/img/personajes/bigdaddy1.jpg";
			} else if(pj instanceof BigSister) {
				imagenGanador = "src/img/personajes/bigsister1.jpg";
			} else if(pj instanceof SujetoDelta) {
				imagenGanador = "src/img/personajes/sujetodelta1.jpg";
			} else if(pj instanceof Lancer) {
				imagenGanador = "src/img/personajes/lancer1.png";
			} else if(pj instanceof LittleSister) {
				imagenGanador = "src/img/personajes/littlesister1.jpg";
			}

			return imagenGanador;
		}

}
