package org.example;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import empleadoCRUD.EmpleadoDAO;
import model.Empleado;
import org.bson.Document;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        MongoClient mongoClient = null;

        try {
            // Conexión con base de datos.
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("empresa");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("empleados");

            // Insertar empleados en base de datos.
            empleadoDAO.insertarEmpleado(mongoCollection, new Empleado("John Wick", 35, true, "25/10/2020", 50000.50));
            empleadoDAO.insertarEmpleado(mongoCollection, new Empleado("Billie Joe", 40, true, "20/08/2018", 75000.25));

            // Listar empleados de la base de datos.
            List<Empleado> empleados = empleadoDAO.listarEmpleados(mongoCollection);
            System.out.println("\nLISTA DE EMPLEADOS\n------------------");
            for (Empleado empleado : empleados) {
                System.out.println(empleado.toString());
            }

            // Modificar datos de un empleado.
            empleadoDAO.actualizarEmpleado(mongoCollection, new Empleado("John Wick", 35, true, "25/10/2016", 100000.50));

            // Consultar empleado.
            Empleado empleadoConsultado = empleadoDAO.consultarEmpleado(mongoCollection, "John Wick");
            System.out.println("\nEmpleado consultado -> " + empleadoConsultado.toString());

            // Eliminar empleado.
            empleadoDAO.elmiminarEmpleado(mongoCollection,"Billie Joe");

            // Listar empleados de la base de datos tras modificaciones y eliminaciones.
            empleados = empleadoDAO.listarEmpleados(mongoCollection);
            System.out.println("\nLISTA DE EMPLEADOS\n------------------");
            for (Empleado empleado : empleados) {
                System.out.println(empleado.toString());
            }
        } catch (Exception e) {
            System.out.println("Error de conexion" + e.getCause().getMessage());
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
