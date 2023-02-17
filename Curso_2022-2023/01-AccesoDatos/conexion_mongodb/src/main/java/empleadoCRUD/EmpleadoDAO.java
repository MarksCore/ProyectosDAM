package empleadoCRUD;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import converter.EmpleadoDocConverter;
import model.Empleado;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.eq;

public class EmpleadoDAO {
    EmpleadoDocConverter empleadoDocConverter = new EmpleadoDocConverter();

    /**
     * Consultar datos de un empleado de la base de datos e imprimir por consola.
     * @param mongoCollection Colección de documentos de la base de datos
     * @param nombre Nombre del empleado a consultar
     * @return Objeto empleado que se consulta
     */
    public Empleado consultarEmpleado(MongoCollection<Document> mongoCollection, String nombre) {
        Document document = mongoCollection.find(Filters.eq("nombre", nombre)).first();
        return empleadoDocConverter.docAEmpleado(document);
    }

    /**
     * Obtener empleados de la base de datos.
     * @param mongoCollection Colección de documentos de la base de datos
     * @return Lista de empleados
     */
    public List<Empleado> listarEmpleados(MongoCollection<Document> mongoCollection) {
        List<Empleado> empleados = new ArrayList<Empleado>();
        List<Document> documents = mongoCollection.find().into(new ArrayList<Document>());

        Empleado empleado;

        for (Document document : documents) {
            empleado = empleadoDocConverter.docAEmpleado(document);
            empleados.add(empleado);
        }
        return empleados;
    }

    /**
     * Insertar empleado en la base de datos
     * @param mongoCollection Colección de documentos de la base de datos
     * @param empleado Objeto empleado a insertar
     */
    public void insertarEmpleado(MongoCollection<Document> mongoCollection, Empleado empleado) {
        Document document = empleadoDocConverter.empleadoADoc(empleado);
        if (consultarEmpleado(mongoCollection, empleado.getNombre()) == null) {
            mongoCollection.insertOne(document);
        } else {
            System.out.println("Error: No es posible insertar el documento " + empleado.getNombre() + ", ya existe.");
        }
    }

    /**
     * Actualizar datos de un empleado en base de datos.
     * @param mongoCollection Colección de documentos de la base de datos
     * @param empleado Objeto empleado a actualizar
     */
    public void actualizarEmpleado(MongoCollection<Document> mongoCollection, Empleado empleado) {
        Document document = empleadoDocConverter.empleadoADoc(empleado);

        try {
            mongoCollection.replaceOne(Filters.eq("nombre", empleado.getNombre()), document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Eliminar empleado de la base de datos.
     * @param mongoCollection Colección de documentos de la base de datos
     * @param nombre Atributo nombre del documento de la colección a eliminar
     */
    public void elmiminarEmpleado(MongoCollection<Document> mongoCollection, String nombre) {
        try {
            mongoCollection.deleteOne(Filters.eq("nombre", nombre));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
