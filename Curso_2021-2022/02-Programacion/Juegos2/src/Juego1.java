import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego1 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		int modoJuego = 0;

		// Variables acumulativas, su valor se incrementa o reduce turno tras turno.
		int canicasTotalesJ1 = 10;	
		int canicasTotalesJ2 = 10;
		int canicasTotalesJug = 0;
		int canicasTotalesOp = 0;
		String parImpar = "";
		int parImparCom = 0;

		// Variables temporales, se reinicia su valor en cada turno.
		int canicasGuardadas = -1;
		int canicasApostadas = -1;

		// Variables identificadoras de turno actual y de roles (Jugador y Oponente)
		int turnoJugador = 0;
		int numJugador = 0;
		int numOponente = 0;

		String repetir = "";
		String volverSalir = "";

		/* Si el valor de "turnoJugador" es par, será turno del Jugador 1. Si es impar, será turno del Jugador 2.
		 * 
		 * El Jugador 1 será siempre el que empiece el juego, por tanto, desempeñará el rol de Jugador en el primer turno y los
		 * siguientes que sean pares y su acción se corresponderá con la de guardar un número de canicas. 
		 * En el turno a continuación (impar) y siguientes impares ejercerá el rol de Oponente, por tanto,
		 * su acción será la de apostar un número de canicas y dar una respuesta de si el número de canicas guardado por
		 * Jugador es "par" o "impar". 
		 * 
		 * Lo mismo se aplica al Jugador 2 de manera inversa.*/

		System.out.println("¡BIENVENIDO AL JUEGO DE LAS CANICAS!");
		do {
			do {
				try {
					System.out.println("MENÚ PRINCIPAL");
					System.out.println("Elige modo de juego o salir del juego:");
					System.out.println("1.- Jugador contra jugador (PvP)");
					System.out.println("2.- Jugador contra la máquina (PvE)");
					System.out.println("3.- Salir");
					modoJuego = teclado.nextInt();

					if(modoJuego != 1 && modoJuego != 2 && modoJuego != 3) {
						System.out.println("La opción seleccionada no existe.");
					}
				}catch(InputMismatchException exc) {
					System.out.println("Error en la entrada de datos.");
					teclado.next();
				}
			} while(!(modoJuego == 1 || modoJuego == 2 || modoJuego == 3));

			switch(modoJuego) {

			case 1:
				do {
					System.out.println("Has seleccionado el modo PvP, ¡que empiece el juego!");
					do {
						if(turnoJugador % 2 == 0) {
							numJugador = 1;							
							numOponente = 2;						
							canicasTotalesJug = canicasTotalesJ1;	// Jugador 1 toma el rol de Jugador. Su número de canicas pasa a ser el de Jugador.
							canicasTotalesOp = canicasTotalesJ2;	// Jugador 2 toma el rol de Oponente. Su número de canicas pasa a ser el de Oponente.
						} else if(turnoJugador % 2 != 0) {
							numJugador = 2;
							numOponente = 1;
							canicasTotalesJug = canicasTotalesJ2;	// Jugador 2 toma el rol de Jugador. Su número de canicas pasa a ser el de Jugador.
							canicasTotalesOp = canicasTotalesJ1;	// Jugador 1 toma el rol de Oponente. Su número de canicas pasa a ser el de Oponente.
						}

						System.out.println("Turno del Jugador " + numJugador);
						do {
							try {
								System.out.println("Jugador " + numJugador + ", introduce el número de canicas que quieras guardar:");
								canicasGuardadas = teclado.nextInt();
								if(canicasTotalesJug < canicasGuardadas|| canicasGuardadas < 0) {
									System.out.println("Cantidad incorrecta. El número de canicas a guardar es superior a 10 o inferior a cero.");
								}
							} catch(InputMismatchException exc) {
								System.out.println("El tipo de dato introducido no es válido.");
								teclado.next();
							}
						}while(canicasTotalesJug < canicasGuardadas || canicasGuardadas < 0);

						do {
							try {
								System.out.println("Jugador " + numOponente + ", introduce tu apuesta en número de canicas:");
								canicasApostadas = teclado.nextInt();
								if(canicasTotalesOp < canicasApostadas || canicasApostadas < 0) {
									System.out.println("Apuesta incorrecta. El número de canicas apostadas es superior al total que tienes o inferior a cero.");
								}
							} catch(InputMismatchException exc) {
								System.out.println("El tipo de dato introducido no es válido.");
								teclado.next();
							}
						} while(canicasTotalesOp < canicasApostadas || canicasApostadas < 0);

						do {
							System.out.println("Jugador " + numOponente + ", ¿Crees que el número de canicas guardadas por el Jugador 1 es par o impar? Teclea par o impar para apostar:");
							parImpar = teclado.next();
							parImpar = parImpar.toLowerCase();
							if(!(parImpar.equals("par") || parImpar.equals("impar"))) {
								System.out.println("Error. Debes teclear la opción par o impar.");
							}
						} while(!(parImpar.equals("par") || parImpar.equals("impar")));


						/* Condicional if para imprimir por consola si el oponente ha acertado o fallado en su apuesta, 
						 * considerando el valor devuelto por la función "comprobarNumParImpar".
						 * Si esta devuelve true, entonces, el oponente gana su apuesta, en caso contrario, este pierde.*/
						if(comprobarNumParImpar(canicasGuardadas, parImpar)) {
							System.out.println("Jugador " + numOponente + " ha ganado su apuesta, se sumará el número de canicas que ha apostado al total de sus canicas.");
						} else if(!comprobarNumParImpar(canicasGuardadas, parImpar)) {
							System.out.println("Jugador " + numOponente + " ha perdido su apuesta, se restará el número de canicas que ha apostado al total de sus canicas.");
						}

						System.out.println();

						if(numJugador == 1) {
							canicasTotalesJ1 = resultadoJugador(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("Jugador " + numJugador + " tiene " + (canicasTotalesJ1) + " canicas en total.");
							canicasTotalesJ2 = resultadoOponente(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("Jugador " + numOponente + " tiene " + (canicasTotalesJ2) + " canicas en total.");
						} else if (numJugador == 2) {
							canicasTotalesJ1 = resultadoOponente(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("Jugador " + numOponente + " tiene " + (canicasTotalesJ1) + " canicas en total.");
							canicasTotalesJ2 = resultadoJugador(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("Jugador " + numJugador + " tiene " + (canicasTotalesJ2) + " canicas en total.");
						}

						turnoJugador++;

						// Se reinician variables temporables por cada turno.
						canicasGuardadas = -1;
						canicasApostadas = -1;

						System.out.println();

					} while(!comprobarFinalJuego(canicasTotalesJ1, canicasTotalesJ2));

					// Se reinician variables acumulativas por cada partida para partir de 10 canicas cada jugador.
					turnoJugador = 0;
					canicasTotalesJ1 = 10;
					canicasTotalesJ2 = 10;

					do {
						System.out.println("Fin del juego, ¿quiere repetir este modo de juego?");
						System.out.println("Sí = y");
						System.out.println("No = n");
						repetir = teclado.next();
						repetir = repetir.toLowerCase();
						if(!(repetir.equals("y") || repetir.equals("n"))) {
							System.out.println("Error en la entrada de datos. Introduce 'y' o 'n' por teclado.");
						}
					} while(!(repetir.equals("y") || repetir.equals("n")));

				} while(repetir.equals("y"));

				break;

			case 2:
				do {
					System.out.println("Has seleccionado el modo PvE, ¡que empiece el juego!");
					do {
						if(turnoJugador % 2 == 0) {
							numJugador = 1;
							numOponente = 2;
							canicasTotalesJug = canicasTotalesJ1;
							canicasTotalesOp = canicasTotalesJ2;
						} else if(turnoJugador % 2 != 0) {
							numJugador = 2;
							numOponente = 1;
							canicasTotalesJug = canicasTotalesJ2;
							canicasTotalesOp = canicasTotalesJ1;
						}

						if(numJugador == 1) {
							System.out.println("Turno del Jugador");
							do {
								try {
									System.out.println("Jugador, introduce el número de canicas que quieras guardar:");
									canicasGuardadas = teclado.nextInt();
									if(canicasTotalesJug < canicasGuardadas|| canicasGuardadas < 0) {
										System.out.println("Cantidad incorrecta. El número de canicas a guardar es superior a 10 o inferior a cero.");
									}
								} catch(InputMismatchException exc) {
									System.out.println("El tipo de dato introducido no es válido.");
									teclado.next();
								}

							}while(canicasTotalesJug < canicasGuardadas || canicasGuardadas < 0);

							do {
								canicasApostadas = (int) Math.floor(Math.random()*(20-0+1)+0);
							} while(canicasTotalesOp < canicasApostadas);
							System.out.println("La Máquina apostó " + canicasApostadas + " canicas.");

							parImparCom = (int) Math.floor(Math.random()*(1-0+1)+0);
							if(parImparCom == 0) {
								parImpar = "par";
							} else if(parImparCom == 1) {
								parImpar = "impar";
							}
							System.out.println("La Máquina ha elegido " + parImpar + ".");

						} else if(numJugador == 2) {
							System.out.println("Turno de la Máquina");

							do {
								canicasGuardadas = (int) Math.floor(Math.random()*(20-0+1)+0);
							} while(canicasTotalesJug < canicasGuardadas);
							System.out.println("La Máquina guardó un número de canicas (" + canicasGuardadas + ")");

							do {
								try {
									System.out.println("Jugador, introduce tu apuesta en número de canicas:");
									canicasApostadas = teclado.nextInt();
									if(canicasTotalesOp < canicasApostadas || canicasApostadas < 0) {
										System.out.println("Apuesta incorrecta. El número de canicas apostadas es superior al total que tienes o inferior a cero.");
									}
								} catch(InputMismatchException exc) {
									System.out.println("El tipo de dato introducido no es válido.");
									teclado.next();
								}
							} while(canicasTotalesOp < canicasApostadas || canicasApostadas < 0);

							do {
								System.out.println("Jugador, ¿Crees que el número de canicas guardadas por la Máquina es par o impar? Teclea par o impar para apostar:");
								parImpar = teclado.next();
								parImpar = parImpar.toLowerCase();
								if(!(parImpar.equals("par") || parImpar.equals("impar"))) {
									System.out.println("Error. Debes teclear la opción par o impar.");
								}
							} while(!(parImpar.equals("par") || parImpar.equals("impar")));
						}

						/* Condicional if para imprimir por consola si el oponente ha acertado o fallado en su apuesta, 
						 * considerando el valor devuelto por la función "comprobarNumParImpar"
						 * Si esta devuelve true, entonces, el oponente gana su apuesta, en caso contrario, este pierde.*/
						if(comprobarNumParImpar(canicasGuardadas, parImpar)) {
							if(numJugador == 1) {
								System.out.println("La Máquina ha ganado su apuesta, se sumará el número de canicas que ha apostado al total de sus canicas.");
							} else if(numJugador == 2) {
								System.out.println("El Jugador ha ganado su apuesta, se sumará el número de canicas que ha apostado al total de sus canicas.");
							}

						} else if(!comprobarNumParImpar(canicasGuardadas, parImpar)) {
							if(numJugador == 1) {
								System.out.println("La Máquina ha perdido su apuesta, se restará el número de canicas que ha apostado al total de sus canicas.");
							} else if(numJugador == 2) {
								System.out.println("El Jugador ha perdido su apuesta, se restará el número de canicas que ha apostado al total de sus canicas.");
							}

						}

						System.out.println();

						if(numJugador == 1) {
							canicasTotalesJ1 = resultadoJugador(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("El Jugador tiene " + (canicasTotalesJ1) + " canicas en total.");
							canicasTotalesJ2 = resultadoOponente(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("La Máquina tiene " + (canicasTotalesJ2) + " canicas en total.");
						} else if (numJugador == 2) {
							canicasTotalesJ1 = resultadoOponente(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("El Jugador tiene " + (canicasTotalesJ1) + " canicas en total.");
							canicasTotalesJ2 = resultadoJugador(canicasGuardadas, parImpar, canicasTotalesJug, canicasTotalesOp, canicasApostadas);
							System.out.println("La Máquina tiene " + (canicasTotalesJ2) + " canicas en total.");
						}

						turnoJugador++;

						canicasGuardadas = -1;
						canicasApostadas = -1;

						System.out.println();

					} while(!comprobarFinalJuego(canicasTotalesJ1, canicasTotalesJ2));

					turnoJugador = 0;
					canicasTotalesJ1 = 10;
					canicasTotalesJ2 = 10;

					do {
						System.out.println("Fin del juego, ¿quiere repetir este modo de juego?");
						System.out.println("Sí = y");
						System.out.println("No = n");
						repetir = teclado.next();
						repetir = repetir.toLowerCase();
						if(!(repetir.equals("y") || repetir.equals("n"))) {
							System.out.println("Error en la entrada de datos. Introduce 'y' o 'n' por teclado.");
						}
					} while(!(repetir.equals("y") || repetir.equals("n")));

				} while (repetir.equals("y"));

				break;

			case 3:
				System.out.println("Has salido del juego.");

				break;

			default:
				System.out.println("La opción seleccionada no existe.");

			}

			do {
				System.out.println("Para volver al menú principal teclea volver.");
				System.out.println("Para salir definitivamente de la aplicación teclea salir.");
				volverSalir = teclado.next();
				volverSalir = volverSalir.toLowerCase();
				if(!(volverSalir.equals("volver") || volverSalir.equals("salir"))) {
					System.out.println("Error en la entrada de datos. Introduce 'volver' o 'salir' por teclado.");
				}
			} while(!(volverSalir.equals("volver") || volverSalir.equals("salir")));

		} while(volverSalir.equals("volver"));
		
		System.out.println("Has salido de la aplicación.");

		teclado.close();
	}


	/* Función que recibe como parámetros de entrad a el número de canicas que se guarda el jugador y la respuesta "par" o "impar" de su oponente.
	 * Su finalidad es comprobar si el número de canicas guardadas por el jugador es par o impar y si se produce coincidencia con la respuesta del oponente.
	 * Si se da coincidencia, es decir, si el número de canicas guardadas por el jugador es par (impar)y su oponente introduce "par" ("impar") como respuesta,
	 * se considera acierto y la función devuelve un valor booleano "true". 
	 * En cualquier otro caso, no se produce coincidencia, se considerará fallo, y el valor booleano arrojado por la función será "false".*/
	private static boolean comprobarNumParImpar(int canicasGuardadas, String parOImpar) {

		boolean aciertoParImpar = false;

		if(canicasGuardadas % 2 == 0 && parOImpar.equals("par")) {
			aciertoParImpar = true;
		} else if(canicasGuardadas % 2 != 0 && parOImpar.equals("impar")) {
			aciertoParImpar = true;
		}

		return aciertoParImpar;
	}

	/* Función que toma la salida de la función "comprobarNumParImpar" y calcula el número total de canicas del participante con el rol de Jugador en ese turno.
	 * El resultado devuelto por la función dependerá de si el oponente ha acertado (Jugador restará canicas) o ha fallado (Jugador sumará canicas).*/
	private static int resultadoJugador(int canicasGuardadas, String parOImpar, int canicasTotalesJug, int canicasTotalesOp, int canicasApostadas) {

		if(comprobarNumParImpar(canicasGuardadas, parOImpar)) {
			canicasTotalesJug = canicasTotalesJug - canicasApostadas;
		} else if(!comprobarNumParImpar(canicasGuardadas, parOImpar)) {
			canicasTotalesJug = canicasTotalesJug + canicasApostadas;
		}

		return canicasTotalesJug;
	}

	/* Función que toma la salida de la función "comprobarNumParImpar" y calcula el número total de canicas del participante con el rol de Oponente en ese turno,
	 * El resultado devuelto por la función dependerá de si este ha acertado (Oponente sumará canicas) o ha fallado (Oponente restará canicas).*/
	private static int resultadoOponente(int canicasGuardadas, String parOImpar, int canicasTotalesJug, int canicasTotalesOp, int canicasApostadas) {

		if(comprobarNumParImpar(canicasGuardadas, parOImpar)) {
			canicasTotalesOp = canicasTotalesOp + canicasApostadas;
		} else if(!comprobarNumParImpar(canicasGuardadas, parOImpar)) {
			canicasTotalesOp = canicasTotalesOp - canicasApostadas;
		}

		return canicasTotalesOp;
	}

	/* Función que comprueba cuando se produce el final de la partida, es decir, cuando uno de los jugadores posee todas las canicas del otro,
	 * o lo que es lo mismo, 20 canicas (10 canicas Jugador + 10 canicas Oponente).
	 * La función indicará por consola el ganador de la partida y devolverá un valor booleano true en cuanto se cumpla lo descrito anteriormente.D */
	private static boolean comprobarFinalJuego(int canicasJ1, int canicasJ2) {

		boolean finalPartida = false;

		if(canicasJ1 >= 20) {
			System.out.println("Jugador 1 es el GANADOR.");
			System.out.println("Jugador 2 / Máquina ELIMINADO.");
			finalPartida = true;
		} else if(canicasJ2 >= 20) {
			System.out.println("Jugador 2 / Máquina es el GANADOR.");
			System.out.println("Jugador 1 ELIMINADO.");
			finalPartida = true;
		}

		return finalPartida;
	}

}
