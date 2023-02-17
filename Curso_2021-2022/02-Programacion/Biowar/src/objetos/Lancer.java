package objetos;

public class Lancer extends Personaje{

	public Lancer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lancer(String nombre, String idEquipo, int vidas, int misiles) {
		super(nombre, idEquipo, vidas, misiles);
		this.vidas = vidas * 2;
		this.misiles = misiles / 5;

	}

	public Lancer(String nombre, String idEquipo) {
		super(nombre, idEquipo);
		this.vidas = 400;
		this.misiles = 10;
	}

	@Override
	public String atacar(int misiles, Personaje atacado) {

		String mensajeAtaqueNormal = getNombre() + " (Lancer) ha destinado " + misiles 
				+ " puntos de EVE para atacar a " + atacado.getNombre() + " (" + descripcionTipo(atacado) + ")." + "\n";
		String mensajeAtaque = "";

		String recogerCeros = "";
		
		setMisiles(getMisiles() - misiles);

		if(misiles == 0) {
			recogerCeros += recogerCeros;

		} else if(atacado instanceof LittleSister) {
			mensajeAtaque = mensajeAtaqueNormal + esquivarAtaque(atacado, misiles);
			
		} else {
			atacado.setVidas(atacado.getVidas() - misiles);
			mensajeAtaque = mensajeAtaqueNormal;
		}

		return mensajeAtaque;
	}

	@Override
	public void reiniciarMisiles(int contRondas) {

		setMisiles(10 * contRondas);

	} 
}




