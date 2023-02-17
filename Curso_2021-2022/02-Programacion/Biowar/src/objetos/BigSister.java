package objetos;

public class BigSister extends Personaje {

	public BigSister() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigSister(String nombre, String idEquipo, int vidas, int misiles) {
		super(nombre, idEquipo, vidas, misiles);
		// TODO Auto-generated constructor stub
	}

	public BigSister(String nombre, String idEquipo) {
		super(nombre, idEquipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String atacar(int misiles, Personaje atacado) {

		String mensajeAtaqueNormal = getNombre() + " (Big Sister) ha destinado " + misiles 
				+ " puntos de EVE para atacar a " + atacado.getNombre() + " (" + descripcionTipo(atacado) + ")." + "\n";
		String muyEfectivo = "Es muy efectivo, causa " + (misiles * 2) + " de da�o." + "\n";
		String pocoEfectivo = "Es poco efectivo, causa " + (misiles / 2) + " de da�o." + "\n";
		String mensajeAtaque = "";
		
		String recogerCeros = "";

		setMisiles(getMisiles() - misiles);

		if(misiles == 0) {
			recogerCeros += recogerCeros;

		} else if(atacado instanceof SujetoDelta) {
			SujetoDelta azul = (SujetoDelta) atacado;
			azul.setVidas(azul.getVidas() - (misiles * 2));
			mensajeAtaque = mensajeAtaqueNormal + muyEfectivo;

		} else if(atacado instanceof Bouncer) {
			Bouncer rojo = (Bouncer) atacado;
			rojo.setVidas(rojo.getVidas() - (misiles / 2));
			mensajeAtaque = mensajeAtaqueNormal + pocoEfectivo;

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

		setMisiles(50);

	}
}
