package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws JAXBException, SQLException {
        String ruta = ".\\xml\\es.xml";

        Localizacion localizacion = realizarLecturaXML(ruta);
        insertarCiudadIndividual(localizacion);
        insertarLoteCiudades(localizacion);

        // LEER XML
//        for (Region region : localizacion.getListaRegiones()) {
//            System.out.println(region);
//            if (region.getStates() != null) {
//                for (Estado estado : region.getStates()) {
//                    if (estado.getListaCiudades() != null) {
//                        for (Ciudad city : estado.getListaCiudades()) {
//                            System.out.println(city);
//                        }
//                    }
//                }
//            }
//        }
    }

    private static Localizacion realizarLecturaXML(String ruta) throws JAXBException {
        File file = new File(ruta);

        JAXBContext jaxbContext = JAXBContext.newInstance(Localizacion.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Localizacion localizacion = (Localizacion) unmarshaller.unmarshal(file);

        return localizacion;
    }

    public static void insertarCiudadIndividual(Localizacion localizacion) throws SQLException {
        long tiempoInicio = System.currentTimeMillis();
        Connection connection = Conexion.obtenerConexion();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Ciudades (nombre, codigo) VALUES (?, ?)");
            for (Region regiones : localizacion.getListaRegiones()) {
                if (regiones.getStates() != null) {
                    for (Estado estado : regiones.getStates()) {
                        if (estado.getListaCiudades() != null) {
                            for (Ciudad city : estado.getListaCiudades()) {
                                preparedStatement.setString(1, city.getName());
                                preparedStatement.setString(2, city.getCode());
                                preparedStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
            long tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            System.out.println("Tiempo de inserción individual: " + tiempoFinal + " ms");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    public static void insertarLoteCiudades(Localizacion localizacion) throws SQLException {
        long tiempoInicio = System.currentTimeMillis();
        Connection connection = Conexion.obtenerConexion();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO Ciudades (nombre, codigo) VALUES (?, ?)");
            for (Region regiones : localizacion.getListaRegiones()) {
                if (regiones.getStates() != null) {
                    for (Estado estado : regiones.getStates()) {
                        if (estado.getListaCiudades() != null) {
                            for (Ciudad city : estado.getListaCiudades()) {
                                preparedStatement.setString(1, city.getName());
                                preparedStatement.setString(2, city.getCode());
                                preparedStatement.addBatch();
                            }
                        }
                    }
                }
            }
            preparedStatement.executeBatch();
            long tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            System.out.println("Tiempo de inserción por lotes: " + tiempoFinal + " ms");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}