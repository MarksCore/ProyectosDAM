package datos;

public class Datos_2 {
	
	private String nombre;
	private String id;
	private String tipoPersonaje;
	private String fecha;
	
	
	public Datos_2(String id, String nombre, String tipoPersonaje, String fecha) {
		
		this.id = id;
		this.nombre = nombre;
		this.tipoPersonaje = tipoPersonaje;
		this.fecha = fecha;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTipoPersonaje() {
		return tipoPersonaje;
	}


	public void setTipoPersonaje(String tipoPersonaje) {
		this.tipoPersonaje = tipoPersonaje;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
