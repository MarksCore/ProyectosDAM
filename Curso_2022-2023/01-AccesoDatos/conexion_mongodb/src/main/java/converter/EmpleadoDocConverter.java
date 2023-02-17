package converter;

import model.Empleado;
import org.bson.Document;

import java.time.LocalDateTime;

public class EmpleadoDocConverter {
    /** Convertir objeto empleado a documento BSON (MongoDB).
     * @param empleado Objeto empleado
     * @return Documento BSON
     */
    public Document empleadoADoc(Empleado empleado) {
        Document document = null;

        if(empleado != null) {
            document = new Document();
            document.put("nombre", empleado.getNombre());
            document.put("edad", empleado.getEdad());
            document.put("contratoFijo", empleado.isContratoFijo());
            document.put("alta",empleado.getAlta());
            document.put("sueldo",empleado.getSueldo());
        }
        return document;
    }

    /**
     * Convertir objeto BSON (MongoDB) a objeto empleado.
     * @param document Documento BSON
     * @return Objeto empleado
     */
    public Empleado docAEmpleado(Document document) {
        Empleado empleado = null;

        if(document != null) {
            empleado = new Empleado();

            empleado.setNombre(document.getString(("nombre")));
            empleado.setEdad(document.getInteger("edad"));
            empleado.setContratoFijo(document.getBoolean("contratoFijo"));
            empleado.setAlta(document.getString("alta"));
            empleado.setSueldo(document.getDouble("sueldo"));
        }
        return empleado;
    }
}
