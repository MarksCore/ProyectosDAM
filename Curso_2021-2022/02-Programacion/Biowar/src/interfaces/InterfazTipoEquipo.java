package interfaces;

import objetos.Personaje;

public interface InterfazTipoEquipo {

	public String atacar(int misiles, Personaje atacado);
	
//	public String defender(Equipo atacante, int misilesRestantes);
	
//	public String utilizarProyectilLonginus(Equipo atacante, Equipo atacado);
	
	public void reiniciarMisiles(int contRondas);
	
//	public String esquivarAtaque(Equipo atacado, int misiles);
	
	public String descripcionTipo(Personaje equipo);
	
//	public int comprobarEquiposVivos(ArrayList<Equipo> arrayEquipos);
	
//	public void resumenVidas(ArrayList<Equipo> arrayEquipos);
	
//	public String mostrarGanador(ArrayList<Equipo> arrayEquipos, int contRondas);
	
}
