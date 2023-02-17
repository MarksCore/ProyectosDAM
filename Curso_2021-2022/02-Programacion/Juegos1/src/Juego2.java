import java.util.Scanner;

public class Juego2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		//Variables:
		//Variable "npartidas" para almacenar número de partidas a jugar introducido por teclado, "i" para contar iteraciones de partidas, "jugadaJ" para guardar número de opción del jugador introducido por teclado,
		//"jugadaCom" para almacenar número de opción generado aleatoriamente por ordenador, "victoriasJ" y "victoriasCom" para contar cantidad de victorias de jugador y de ordenador, respectivamente, y "retry" para almacenar
		// valor introducido por teclado, sea 1 o 0, para repetir o salir del juego, respectivamente.
		int npartidas = 0;
		int i = 1;
		int jugadaJ = 0;
		int jugadaCom = 0;
		int victoriasJ = 0;
		int victoriasCom = 0;
		int retry = 0;

		//Bucle do while: seguir ejecutando o no instrucciones del bucle, dependiendo de si el valor de "retry" es 1 para continuar el bucle ó 0 para finalizar.
		do {

			//Bucle do while anidado: asegurar que el número de partidas introducido por teclado es positivo.
			do {
				System.out.println("¡BIENVENIDO A PIEDRA, PAPEL, TIJERA, LAGARTO, SPOCK!");
				System.out.println("Introduzca el número de partidas que desee jugar:");
				npartidas = teclado.nextInt();
				if(npartidas <= 0) {
					System.out.println("El número de partidas introducido es incorrecto. por favor, entroduzca nuevamente un número de partidas.");
				}
			}while(npartidas <= 0);
			
			System.out.println("Va a jugar: "+ npartidas +" partida/s.");

			//Bucle do while anidado: repetir instrucciones mientras que la variable contadora de iteraciones "i" no alcance el número total de partidas "npartidas" introducido por teclado.
			do {

				//Bucle do while anidado: asegurar que el número de opción introducido por teclado que elija el jugador esté ubicado dentro del intervalo 1-5 con el fin de establecer estrategia de juego.
				do {
					System.out.println();
					System.out.println("PARTIDA "+ i);
					System.out.println("Elija:");
					System.out.println("1.- Piedra");
					System.out.println("2.- Papel");
					System.out.println("3.- Tijeras");
					System.out.println("4.- Lagarto");
					System.out.println("5.- Spock");
					jugadaJ = teclado.nextInt();
				}while(5 < jugadaJ || jugadaJ < 0);

				//Generar número de opción aleatorio de 1 a 5 para establecer estrategia de juego del ordenador.
				jugadaCom = (int)Math.floor(Math.random()*(5-1+1)+1);
				System.out.println("Comprobación de nº de estrategia escogida por Ordenador: "+ jugadaCom);

				//Condicional if contemplando todas las posibles combinaciones de opciones elegidas por jugador y ordenador, para así, por cada combinación, 
				//indicar la opción seleccionada por cada uno, determinar si hay o no ganador de la partida e incrementar o no contador de jugador u ordenador 
				//dependiendo de la concurrencia que se presente (victoria de una parte u otra o empate).
				if(jugadaJ == 1 && jugadaCom == 3){	
					System.out.println("Jugador ha escogido: Piedra.");
					System.out.println("Ordenador ha escogido: Tijeras.");
					System.out.println("Piedra aplasta a Tijeras.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 1 && jugadaCom == 4){
					System.out.println("Jugador ha escogido: Piedra.");
					System.out.println("Ordenador ha escogido: Lagarto.");
					System.out.println("Piedra aplasta a Lagarto.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 2 && jugadaCom == 1) {
					System.out.println("Jugador ha escogido: Papel.");
					System.out.println("Ordenador ha escogido: Piedra.");
					System.out.println("Papel cubre a Piedra.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 2 && jugadaCom == 5){
					System.out.println("Jugador ha escogido: Papel.");
					System.out.println("Ordenador ha escogido: Spock.");
					System.out.println("Papel desautoriza a Spock.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 3 && jugadaCom == 2) {
					System.out.println("Jugador ha escogido: Tijeras.");
					System.out.println("Ordenador ha escogido: Papel.");
					System.out.println("Tijeras cortan Papel.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 3 && jugadaCom == 4){
					System.out.println("Jugador ha escogido: Tijeras.");
					System.out.println("Ordenador ha escogido: Lagarto.");
					System.out.println("Tijeras decapitan Lagarto.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 4 && jugadaCom == 2) {
					System.out.println("Jugador ha escogido: Lagarto.");
					System.out.println("Ordenador ha escogido: Papel.");
					System.out.println("Lagarto devora Papel.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 4 && jugadaCom == 5) {
					System.out.println("Jugador ha escogido: Lagarto.");
					System.out.println("Ordenador ha escogido: Spock.");
					System.out.println("Lagarto envenena a Spock.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 5 && jugadaCom == 1) {
					System.out.println("Jugador ha escogido: Spock.");
					System.out.println("Ordenador ha escogido: Piedra.");
					System.out.println("Spock vaporiza Piedra.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 5 && jugadaCom == 3) {
					System.out.println("Jugador ha escogido: Spock.");
					System.out.println("Ordenador ha escogido: Tijeras.");
					System.out.println("Spock rompe Tijeras.");
					System.out.println("El ganador es: JUGADOR.");
					victoriasJ++;

				}else if(jugadaJ == 3 && jugadaCom == 1){	
					System.out.println("Jugador ha escogido: Tijeras.");
					System.out.println("Ordenador ha escogido: Piedra.");
					System.out.println("Piedra aplasta a Tijeras.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 4 && jugadaCom == 1){
					System.out.println("Jugador ha escogido: Lagarto.");
					System.out.println("Ordenador ha escogido: Piedra.");
					System.out.println("Piedra aplasta a Lagarto.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 1 && jugadaCom == 2) {
					System.out.println("Jugador ha escogido: Piedra.");
					System.out.println("Ordenador ha escogido: Papel.");
					System.out.println("Papel cubre a Piedra.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 5 && jugadaCom == 2){
					System.out.println("Jugador ha escogido: Spock.");
					System.out.println("Ordenador ha escogido: Papel.");
					System.out.println("Papel desautoriza a Spock.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 2 && jugadaCom == 3) {
					System.out.println("Jugador ha escogido: Papel.");
					System.out.println("Ordenador ha escogido: Tijeras.");
					System.out.println("Tijeras cortan Papel.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 4 && jugadaCom == 3){
					System.out.println("Jugador ha escogido: Lagarto.");
					System.out.println("Ordenador ha escogido: Tijeras.");
					System.out.println("Tijeras decapitan Lagarto.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 2 && jugadaCom == 4) {
					System.out.println("Jugador ha escogido: Papel.");
					System.out.println("Ordenador ha escogido: Lagarto.");
					System.out.println("Lagarto devora Papel.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 5 && jugadaCom == 4) {
					System.out.println("Jugador ha escogido: Spock.");
					System.out.println("Ordenador ha escogido: Lagarto.");
					System.out.println("Lagarto envenena a Spock.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 1 && jugadaCom == 5) {
					System.out.println("Jugador ha escogido: Piedra.");
					System.out.println("Ordenador ha escogido: Spock.");
					System.out.println("Spock vaporiza Piedra.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 3 && jugadaCom == 5) {
					System.out.println("Jugador ha escogido: Tijeras.");
					System.out.println("Ordenador ha escogido: Spock.");
					System.out.println("Spock rompe Tijeras.");
					System.out.println("El ganador es: ORDENADOR.");
					victoriasCom++;

				}else if(jugadaJ == 1 && jugadaCom == 1) {
					System.out.println("Jugador ha escogido: Piedra.");
					System.out.println("Ordenador ha escogido: Piedra.");
					System.out.println("Se produce empate.");
					System.out.println("NO hay ganador.");
				}

				else if(jugadaJ == 2 && jugadaCom == 2) {
					System.out.println("Jugador ha escogido: Papel.");
					System.out.println("Ordenador ha escogido: Papel.");
					System.out.println("Se produce empate.");
					System.out.println("NO hay ganador.");
				}

				else if(jugadaJ == 3 && jugadaCom == 3) {
					System.out.println("Jugador ha escogido: Tijeras.");
					System.out.println("Ordenador ha escogido: Tijeras.");
					System.out.println("Se produce empate.");
					System.out.println("NO hay ganador.");
				}

				else if(jugadaJ == 4 && jugadaCom == 4) {
					System.out.println("Jugador ha escogido: Lagarto.");
					System.out.println("Ordenador ha escogido: Lagarto.");
					System.out.println("Se produce empate.");
					System.out.println("NO hay ganador.");
				}

				else if(jugadaJ == 5 && jugadaCom == 5) {
					System.out.println("Jugador ha escogido: Spock.");
					System.out.println("Ordenador ha escogido: Spock.");
					System.out.println("Se produce empate.");
					System.out.println("NO hay ganador.");
				}

				i++;
			}while(i <= npartidas);
			
			//Salida por consola mostrando el resultado definitivo del número de partidas establecido por teclado, indicando número de victorias de jugador y ordenador, respectivamente.
			System.out.println();
			System.out.println("RESULTADO FINAL DE "+ npartidas + " PARTIDAS:");
			System.out.println("JUGADOR "+ victoriasJ +" vs "+ victoriasCom +" ORDENADOR");

			//Condicional if: comparar ambos valores de los contadores de victorias (jugador y ordenador) para determinar ganador del juego o empate, si se presenta el caso.
			if(victoriasJ > victoriasCom) {
				System.out.println("¡El ganador es: JUGADOR!");
			}else if(victoriasJ < victoriasCom) {
				System.out.println("¡El ganador es: ORDENADOR!");
			}else {
				System.out.println("EMPATE, NO hay GANADOR.");
			}

			//Reiniciar contador de partidas (para próximo juego comenzar en partida 1).
			i=1;
			
			//Reiniciar contador de victorias (para próximo juego comenzar en victorias de ambos a 0).
			victoriasJ=0;
			victoriasCom=0;
			
			//Bucle do while anidado: asegurar que el valor introducido para variable "retry" sea igual a 1 para reintentar juego o igual a 0 para finalizar el juego.
			do {
				System.out.println("Si quiere volver a intentarlo pulse 1. Si desea salir del juego, pulse 0.");
				retry = teclado.nextInt();
			}while(retry != 0 && retry !=1);
			System.out.println();

		}while(retry == 1);
		System.out.println("Ha salido del juego.");

	}

}


