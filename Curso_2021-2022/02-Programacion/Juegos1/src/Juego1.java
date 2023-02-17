import java.util.Scanner;

public class Juego1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		//Variables: 
		//Variable "nrondas" para almacenar número de rondas introducido por teclado, "i" para contar iteraciones de ronda, "apuestaJ" para almacenar valor de apuesta de Jugador,
		//"piedrasJ" para almacenar valor de piedras cogidas por Jugador, "piedrasTotales" para almacenar valor de  la suma total de piedras cogidas por Jugador y Ordenador, "victoriasJ" y 
		//"victoriasCom" para almacenar valor de contador de victorias de ambos jugadores, respectivamente, y "volver_a_jugar" para guardar número de opción para continuar bucle,
		//repitiendo juego o finalizar bucle.
		int nrondas = 0;
		int i = 1;
		int apuestaJ = 0;
		int piedrasJ = 0;
		int piedrasTotales = 0;
		int victoriasJ = 0;
		int victoriasCom= 0;
		int volver_a_jugar = 0;

		//Bucle do while para repetir juego o no según valor introducido por teclado para variable "volver_a_jugar" al final de la partida (1 = volver a jugar ó 0 = salir del juego).
		do {

			//Bucle do while para repetir instrucciones hasta que contador de iteraciones "i" alcance el valor de la variable "nrondas" de número de rondas introducido por teclado.
			do{
				System.out.println("¡BIENVENIDO AL JUEGO DE LAS 3 PIEDRAS!");
				System.out.println("Jugador, ¿cuántas rondas se atreve a jugar?");
				nrondas = teclado.nextInt();
				if(nrondas <= 0) {
					System.out.println();
					System.out.println("Introduzca un número de rondas correcto.");
					System.out.println();
				}
			}while(nrondas <= 0);
			System.out.println("Va a jugar: "+ nrondas+ " ronda/s.");
			System.out.println();

			//Bucle do while para repetir rondas hasta número de rondas totales introducido por teclado.
			do {
				System.out.println("RONDA "+ i);

				//Bucle do while para asegurar que Jugador introduce número correcto de apuesta (entre 0 y 6).
				do {
					System.out.println("Jugador, ¿cuánto está dispuesto a apostar?");
					apuestaJ = teclado.nextInt();
					if(6 < apuestaJ || apuestaJ < 0) {
						System.out.println("Ha introducido un número incorrecto de apuesta, elija un número de 0 a 6.");
						System.out.println();
					}
				}while(6 < apuestaJ || apuestaJ < 0);

				//Bucle do while para asegurar que Jugador introduce número correcto de piedras (entre 0 y 3).
				do{
					System.out.println("Jugador, ¿cuántas piedras ha cogido?");
					piedrasJ = teclado.nextInt();
					if(3 < piedrasJ || piedrasJ <0) {
						System.out.println("Ha introducido un número incorrecto de piedras, elija un número de 0 a 3.");
						System.out.println();
					}
				}while(3 < piedrasJ || piedrasJ < 0);

				//Generar número aleatorio para apuesta de Ordenador (entre 0 y 6).
				int apuestaCom = (int) Math.floor(Math.random()*(6+1));
				System.out.println("Ordenador apuesta: "+ apuestaCom);

				//Generar número aleatorio para cantidad de piedras de Ordenador (entre 0 y 3).
				int piedrasCom = (int) Math.floor(Math.random()*(3+1));
				System.out.println("Ordenador ha cogido: "+ piedrasCom);
				System.out.println();

				//Salida por consola indicando la suma total de piedras cogidas por Jugaror y Ordenador.
				System.out.println("La suma total de piedras cogidas por Jugador("+ piedrasJ +") y Ordenador("+ piedrasCom +") es: "+ (piedrasTotales = piedrasJ + piedrasCom));

				//Condicional if para determinar quién iguala con su apuesta (Jugador u Ordenador, ambos o ninguno) la suma total de piedras cogidas por ambos.
				//Si la apuesta de uno de ellos es igual a la suma total de piedras cogidas por ambos, este gana la ronda y suma victoria.
				//Si la apuesta de ambos es igual a la suma total de piedras cogidas, se produce empate, no suma victoria ninguno.
				//Si la apuesta de ninguno de ellos es igual a la suma total de piedras cogidas, no hay ganador, no suma victoria ninguno.
				if(apuestaJ == piedrasTotales && apuestaCom != piedrasTotales) {
					System.out.println("RESULTADO RONDA "+ i +": Gana Jugador apostando "+ apuestaJ +" piedras vs "+ apuestaCom +" piedras apostadas por Ordenador.");
					victoriasJ++;
				}else if(apuestaCom == piedrasTotales && apuestaJ != piedrasTotales) {
					System.out.println("RESULTADO RONDA "+ i +": Gana Ordenador apostando "+ apuestaCom +" piedras vs "+ apuestaJ +" piedras apostadas por Jugador.");
					victoriasCom++;
				}else if(apuestaJ == piedrasTotales && apuestaCom == piedrasTotales) {
					System.out.println("RESULTADO RONDA "+ i +": Se ha producido un EMPATE entre Jugador y Ordenador con "+ apuestaJ +" vs "+ apuestaCom +" piedras.");
				}else {
					System.out.println("RESULTADO RONDA "+ i +": NO hay ganador, ambas apuestas: Jugador("+ apuestaJ +") vs Ordenador("+ apuestaCom+ ") no coinciden con el total de piedras cogidas: "+ piedrasTotales +".");
				}
				System.out.println();
				i++;
			}while(i <= nrondas);

			//Salida por consola informando del resultado en victorias de la partida compuesta por n rondas.
			System.out.println("RESULTADO FINAL DE LA PARTIDA DE "+ nrondas +" RONDAS.");
			System.out.println("JUGADOR "+ victoriasJ +" vs "+ victoriasCom +" ORDENADOR");

			//Condicional if para comparar contadores de victorias entre Jugador y Ordenador.
			//Si valor de contador de victorias de jugador "victoriasJ" es superior a valor de contador de victorias "victoriasCom", Jugador gana la partida y viceversa.
			//Si ambos valores de contadores de victorias son iguales y distintos de cero, se produce empate (no hay ganador).
			//En los demás casos, no hay ganador (ambos valores de contadores de victorias son cero).
			if(victoriasJ > victoriasCom) {
				System.out.println("¡Ha ganado JUGADOR! :^)");
			}else if(victoriasJ < victoriasCom) {
				System.out.println("¿Ha ganado ORDENADOR? No me lo puedo creer... :^(");
			}else if(victoriasJ == victoriasCom && victoriasJ != 0 && victoriasCom != 0) {
				System.out.println("EMPATE. El juego está reñido :^O");
			}else {
				System.out.println("NO hay GANADOR. No desista, siga intentándolo :^D");
			}

			//Reiniciar contador de partidas (para empezar próximo juego en ronda 1)
			i=1;

			//Reiniciar victorias (para empezar próximo juego con victorias de ambos a 0)
			victoriasJ = 0;
			victoriasCom = 0;

			//Se establece bucle do while para que el usuario únicamente introduzca 1 ó 0, para repetir o salir del juego, respectivamente. 
			//Salida por consola mostrando las opciones de volver a jugar o salir del juego.
			do {
				System.out.println();
				System.out.println("¿Quiere volver a intentarlo?");
				System.out.println("Pulse 1 para volver a intentarlo.");
				System.out.println("Pulse 0 para salir.");
				volver_a_jugar = teclado.nextInt();
			}while(volver_a_jugar != 0 && volver_a_jugar != 1);
			System.out.println();

		}while(volver_a_jugar == 1);
		System.out.println("Ha salido del juego.");

	}

}
