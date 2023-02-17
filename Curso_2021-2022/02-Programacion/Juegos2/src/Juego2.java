import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego2 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		// Variables de switch.
		int opcion = 0;
		int config = 0;
		
		// L�mites superiores predeterminados para juego.
		int numInt = 5;
		int numMax = 10;
		
		
		int numOculto = 0;
		int numApostado = -1;
		int intentosCont = 0;
		boolean numCheck = false;
		

		do {
			do {
				try {
					System.out.println("MEN� PRINCIPAL");
					System.out.println("1.- Configurar");
					System.out.println("2.- Jugar");
					System.out.println("3.- Salir");
					opcion = teclado.nextInt();
					if(!(opcion == 1 || opcion == 2 || opcion == 3)) {
						System.out.println("La opci�n seleccionada no existe.");
					}
				} catch(InputMismatchException exc) {
					System.out.println("El tipo de dato introducido no es v�lido.");
					teclado.next();
				}
			}while(!(opcion == 1 || opcion == 2 || opcion == 3));

			
			switch(opcion) {

			case 1:
				do {
					try {
						System.out.println("---CONFIGURAR---");
						System.out.println("Selecciona el tipo de configuraci�n:");
						System.out.println("1.- Configuraci�n predeterminada.");
						System.out.println("2.- Configuraci�n manual.");
						config = teclado.nextInt();
						if(!(config == 1 || config == 2)) {
							System.out.println("La opci�n seleccionada no existe.");
						}
					} catch(InputMismatchException exc) {
						System.out.println("El tipo de dato introducido no es v�lido.");
						teclado.next();
					}
				} while(!(config == 1 || config == 2) || config == 0);

				/* Switch que consta de 2 opciones, en caso de querer configurar el n�mero de intentos y el n�mero m�ximo, 
				 * la primera de ellas establece los valores predeterminados y la segunda permite fijar unos valores manualmente.*/
				switch (config) {

				case 1:
					numInt = 5;
					numMax = 10;

					break;

				case 2:
					do {
						try {
							System.out.println("Establece el n�mero m�ximo permitido de intentos:");
							numInt = teclado.nextInt();
							if(numInt <= 0 ) {
								System.out.println("Error. Has seleccionado 0 o un n�mero negativo de intentos.");
							}
							numCheck = true;
						} catch(InputMismatchException exc) {
							System.out.println("El tipo de dato introducido no es v�lido.");
							teclado.next();
						}
					} while(numInt <= 0 || !numCheck);

					numCheck = false;

					do {
						try {
							System.out.println("Establece el n�mero m�ximo que generar� la m�quina:");
							numMax = teclado.nextInt();
							if(numMax <= 0 ) {
								System.out.println("Error. Has seleccionado 0 o un n�mero negativo. Debes introducir un n�mero positivo.");
							}
							numCheck = true;
						} catch(InputMismatchException exc) {
							System.out.println("El tipo de dato introducido no es v�lido.");
							teclado.next();
						}
					} while(numMax < 0 || !numCheck); 

					break;
				}

				break;

			case 2:
				System.out.println("---JUGAR---");
				numOculto = (int) Math.floor(Math.random()*(numMax-0+1)+0);
				System.out.println("La m�quina ha generado un n�mero oculto (" + numOculto + ").");

				do {
					do {
						try {
							System.out.println("�Cu�l crees que es el n�mero oculto?");
							numApostado = teclado.nextInt();
							if(numMax < numApostado || numApostado < 0) {
								System.out.println("Intento fallido. El n�mero introducido se encuentra fuera del intervalo permitido (0 - " + numMax + ").");
							} 
						} catch(InputMismatchException exc) {
							System.out.println("Intento fallido. El tipo de dato introducido no es v�lido.");
							teclado.next();
						}
					} while((numMax < numApostado || numApostado < 0));

					// Condicional que llama la funci�n mayorMenor solo en el caso de no acertar el n�mero oculto.
					if(!comprobarAcierto(numApostado, numOculto)) {
						mayorMenor(numApostado, numOculto);
					}

					intentosCont++;

					// Si a�n quedan intentos restantes, el bucle continuar�, a excepci�n de que el jugador acierte el n�mero oculto (true).
				} while(!comprobarIntentos(intentosCont, numInt, numApostado, numOculto)); 

				intentosCont = 0;	// Se reinician intentos en cada partida.

				System.out.println();
				
				break;

			case 3:
				System.out.println("Has salido del juego.");

				break;

			default:
				System.out.println("No existe la opci�n seleccionada.");
			}

		} while(opcion != 3); 
		
		teclado.close();
	}


	/* Funci�n que recibe el n�mero introducido y el oculto generado por la m�quina y compara si son iguales.
	 * Si lo son devuelve un valor booleano true.
	 * Si no lo son devuelve un valor booleano false. */
	private static boolean comprobarAcierto(int numApostado, int numOculto) {

		boolean victoria = false;

		if(numApostado == numOculto) {
			victoria = true;
		}

		return victoria;
	}

	/* Funci�n que comprueba si el valor introducido es mayor o menor al n�mero oculto y
	 * muestra un mensaje por consola con respecto a ello.*/
	private static void mayorMenor(int numApostado, int numOculto) {

		if(numApostado < numOculto) {
			System.out.println("PISTA: el n�mero oculto es MAYOR que el n�mero introducido.");
		}else if(numApostado > numOculto) {
			System.out.println("PISTA: el n�mero oculto es MENOR que el n�mero introducido.");
		}
	}

	/* Funci�n que recibe el n�mero de intentos realizados, el n�mero de intentos m�ximos, el valor introducido y el n�mero oculto generado.
	 * Comprueba si los intentos realizados son iguales al n�mero de intentos m�ximos para determinar si el jugador pierde el juego.
	 * En otro caso, toma el valor de la funcion "comprobarAcierto" y, si este es true, el resultado es que el jugador gana el juego y
	 * se cuentan los intentos realizados hasta acertar el n�mero oculto.
	 * En caso de no acertar el n�mero oculto, se le resta al m�ximo de intentos el n�mero de intentos realizados para determinar
	 * los intentos restantes.*/
	private static boolean comprobarIntentos(int intentosCont, int numInt, int numApostado, int numOculto) {

		if (comprobarAcierto(numApostado, numOculto)) {
			System.out.println("�ltimo n�mero introducido: " + numApostado);
			System.out.println("N�mero oculto: " + numOculto);
			System.out.println("�HAS GANADO! Para ello has necesitado " + intentosCont + " intentos.");
			return true;
		} else if(!comprobarAcierto(numApostado, numOculto)) {
			System.out.println("Te quedan " + (numInt - intentosCont) + " intentos.");
			System.out.println();
			if(intentosCont == numInt) {
				System.out.println("�ltimo n�mero introducido: " + numApostado);
				System.out.println("N�mero oculto: " + numOculto);
				System.out.println("HAS PERDIDO, se han consumido todos tus intentos.");
				return true;
			}
		}

		return false;
	}

}




