import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego2 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		// Variables de switch.
		int opcion = 0;
		int config = 0;
		
		// Límites superiores predeterminados para juego.
		int numInt = 5;
		int numMax = 10;
		
		
		int numOculto = 0;
		int numApostado = -1;
		int intentosCont = 0;
		boolean numCheck = false;
		

		do {
			do {
				try {
					System.out.println("MENÚ PRINCIPAL");
					System.out.println("1.- Configurar");
					System.out.println("2.- Jugar");
					System.out.println("3.- Salir");
					opcion = teclado.nextInt();
					if(!(opcion == 1 || opcion == 2 || opcion == 3)) {
						System.out.println("La opción seleccionada no existe.");
					}
				} catch(InputMismatchException exc) {
					System.out.println("El tipo de dato introducido no es válido.");
					teclado.next();
				}
			}while(!(opcion == 1 || opcion == 2 || opcion == 3));

			
			switch(opcion) {

			case 1:
				do {
					try {
						System.out.println("---CONFIGURAR---");
						System.out.println("Selecciona el tipo de configuración:");
						System.out.println("1.- Configuración predeterminada.");
						System.out.println("2.- Configuración manual.");
						config = teclado.nextInt();
						if(!(config == 1 || config == 2)) {
							System.out.println("La opción seleccionada no existe.");
						}
					} catch(InputMismatchException exc) {
						System.out.println("El tipo de dato introducido no es válido.");
						teclado.next();
					}
				} while(!(config == 1 || config == 2) || config == 0);

				/* Switch que consta de 2 opciones, en caso de querer configurar el número de intentos y el número máximo, 
				 * la primera de ellas establece los valores predeterminados y la segunda permite fijar unos valores manualmente.*/
				switch (config) {

				case 1:
					numInt = 5;
					numMax = 10;

					break;

				case 2:
					do {
						try {
							System.out.println("Establece el número máximo permitido de intentos:");
							numInt = teclado.nextInt();
							if(numInt <= 0 ) {
								System.out.println("Error. Has seleccionado 0 o un número negativo de intentos.");
							}
							numCheck = true;
						} catch(InputMismatchException exc) {
							System.out.println("El tipo de dato introducido no es válido.");
							teclado.next();
						}
					} while(numInt <= 0 || !numCheck);

					numCheck = false;

					do {
						try {
							System.out.println("Establece el número máximo que generará la máquina:");
							numMax = teclado.nextInt();
							if(numMax <= 0 ) {
								System.out.println("Error. Has seleccionado 0 o un número negativo. Debes introducir un número positivo.");
							}
							numCheck = true;
						} catch(InputMismatchException exc) {
							System.out.println("El tipo de dato introducido no es válido.");
							teclado.next();
						}
					} while(numMax < 0 || !numCheck); 

					break;
				}

				break;

			case 2:
				System.out.println("---JUGAR---");
				numOculto = (int) Math.floor(Math.random()*(numMax-0+1)+0);
				System.out.println("La máquina ha generado un número oculto (" + numOculto + ").");

				do {
					do {
						try {
							System.out.println("¿Cuál crees que es el número oculto?");
							numApostado = teclado.nextInt();
							if(numMax < numApostado || numApostado < 0) {
								System.out.println("Intento fallido. El número introducido se encuentra fuera del intervalo permitido (0 - " + numMax + ").");
							} 
						} catch(InputMismatchException exc) {
							System.out.println("Intento fallido. El tipo de dato introducido no es válido.");
							teclado.next();
						}
					} while((numMax < numApostado || numApostado < 0));

					// Condicional que llama la función mayorMenor solo en el caso de no acertar el número oculto.
					if(!comprobarAcierto(numApostado, numOculto)) {
						mayorMenor(numApostado, numOculto);
					}

					intentosCont++;

					// Si aún quedan intentos restantes, el bucle continuará, a excepción de que el jugador acierte el número oculto (true).
				} while(!comprobarIntentos(intentosCont, numInt, numApostado, numOculto)); 

				intentosCont = 0;	// Se reinician intentos en cada partida.

				System.out.println();
				
				break;

			case 3:
				System.out.println("Has salido del juego.");

				break;

			default:
				System.out.println("No existe la opción seleccionada.");
			}

		} while(opcion != 3); 
		
		teclado.close();
	}


	/* Función que recibe el número introducido y el oculto generado por la máquina y compara si son iguales.
	 * Si lo son devuelve un valor booleano true.
	 * Si no lo son devuelve un valor booleano false. */
	private static boolean comprobarAcierto(int numApostado, int numOculto) {

		boolean victoria = false;

		if(numApostado == numOculto) {
			victoria = true;
		}

		return victoria;
	}

	/* Función que comprueba si el valor introducido es mayor o menor al número oculto y
	 * muestra un mensaje por consola con respecto a ello.*/
	private static void mayorMenor(int numApostado, int numOculto) {

		if(numApostado < numOculto) {
			System.out.println("PISTA: el número oculto es MAYOR que el número introducido.");
		}else if(numApostado > numOculto) {
			System.out.println("PISTA: el número oculto es MENOR que el número introducido.");
		}
	}

	/* Función que recibe el número de intentos realizados, el número de intentos máximos, el valor introducido y el número oculto generado.
	 * Comprueba si los intentos realizados son iguales al número de intentos máximos para determinar si el jugador pierde el juego.
	 * En otro caso, toma el valor de la funcion "comprobarAcierto" y, si este es true, el resultado es que el jugador gana el juego y
	 * se cuentan los intentos realizados hasta acertar el número oculto.
	 * En caso de no acertar el número oculto, se le resta al máximo de intentos el número de intentos realizados para determinar
	 * los intentos restantes.*/
	private static boolean comprobarIntentos(int intentosCont, int numInt, int numApostado, int numOculto) {

		if (comprobarAcierto(numApostado, numOculto)) {
			System.out.println("Último número introducido: " + numApostado);
			System.out.println("Número oculto: " + numOculto);
			System.out.println("¡HAS GANADO! Para ello has necesitado " + intentosCont + " intentos.");
			return true;
		} else if(!comprobarAcierto(numApostado, numOculto)) {
			System.out.println("Te quedan " + (numInt - intentosCont) + " intentos.");
			System.out.println();
			if(intentosCont == numInt) {
				System.out.println("Último número introducido: " + numApostado);
				System.out.println("Número oculto: " + numOculto);
				System.out.println("HAS PERDIDO, se han consumido todos tus intentos.");
				return true;
			}
		}

		return false;
	}

}




