package objetos;

public class SujetoDelta extends Personaje {

	public SujetoDelta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SujetoDelta(String nombre, String idEquipo, int vidas, int misiles) {
		super(nombre, idEquipo, vidas, misiles);
		// TODO Auto-generated constructor stub
	}

	public SujetoDelta(String nombre, String idEquipo) {
		super(nombre, idEquipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String atacar(int misiles, Personaje atacado) {

		String mensajeAtaqueNormal = getNombre() + " (Sujeto Delta) ha destinado " + misiles 
				+ " puntos de EVE para atacar a " + atacado.getNombre() + " (" + descripcionTipo(atacado) + ")." + "\n";
		String muyEfectivo = "Es muy efectivo, causa " + (misiles * 2) + " de daño." + "\n";
		String pocoEfectivo = "Es poco efectivo, causa " + (misiles / 2) + " de daño." + "\n";
		String mensajeAtaque = "";
		
		String recogerCeros = "";

		setMisiles(getMisiles() - misiles);

		if(misiles == 0) {
			recogerCeros += recogerCeros;

		} else if(atacado instanceof Bouncer) {
			Bouncer rojo = (Bouncer) atacado;
			rojo.setVidas(rojo.getVidas() - (misiles * 2));
			mensajeAtaque = mensajeAtaqueNormal + muyEfectivo;

		} else if(atacado instanceof BigSister) {
			BigSister verde = (BigSister) atacado;
			verde.setVidas(verde.getVidas() - (misiles / 2));
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
