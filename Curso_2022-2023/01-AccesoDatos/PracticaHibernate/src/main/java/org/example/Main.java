package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HttpURLConnection connection = null;
        OutputStreamWriter wr = null;
        String line = null;

        try {
            // LLamar a API
            URL serverAddress = new URL("https://tenders.guru/api/es/tenders");
            connection = (HttpURLConnection) serverAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);
            connection.connect();

            // Leer resultados API.
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            // Formatear resultados API
            JsonObject objetoJson = new Gson().fromJson(sb.toString(), JsonObject.class);
            JsonArray jsonArray = objetoJson.getAsJsonArray("data");

            // Insertar resultados API en base de datos y consultarlos por consola.
            insertarObjetos(jsonArray);
            consultarRegistros();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    // Recibimos un JsonArray para recorrer cada JsonElement del mismo, transformarlo en un objeto de la clase correspondiente y almacenarlo en la base de datos.
    public static void insertarObjetos(JsonArray jsonArray) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        try {
            // Recorremos cada elemento del JsonArray de licitaciones y lo convertimos en un objeto Licitacion para almacenarlo en la base de datos.
            for (JsonElement jsonElementLicitacion : jsonArray) {
                Licitacion licitacion = devolverLicitacion(jsonElementLicitacion);
                session.save(licitacion);

                // Recorremos cada elemento del JsonArray de adjudicaciones y lo convertimos en un objeto Adjudicacion para almacenarlo en la base de datos.
                for (JsonElement jsonElementAdjudicacion : jsonElementLicitacion.getAsJsonObject().get("awarded").getAsJsonArray()) {
                    Adjudicacion adjudicacion = devolverAdjudicacion(jsonElementAdjudicacion, licitacion.getId());
                    session.save(adjudicacion);

                    // Recorremos cada elemento del JsonArray de proveedores y lo convertimos en un objeto Proveedor para almacenarlo en la base de datos.
                    for (JsonElement jsonElementProveedor : jsonElementAdjudicacion.getAsJsonObject().get("suppliers").getAsJsonArray()) {
                        Proveedor proveedor = devolverProveedor(jsonElementProveedor, adjudicacion.getId());
                        session.save(proveedor);
                    }
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        session.close();
    }

    // Convertimos cada JsonElement de licitacion en un objeto JsonObject para poder acceder a sus propiedades.
    // Recogemos los valores de las propiedades de cada JsonObject y los seteamos en los atributos de un objeto de la clase Licitacion.
    // Devolvemos el objeto Licitacion.
    public static Licitacion devolverLicitacion(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Licitacion licitacion = new Licitacion();
        licitacion.setIdApi(jsonObject.get("id").getAsString());
        licitacion.setFecha(jsonObject.get("date").getAsString());
        if (jsonObject.get("deadline_date") != null) {
            licitacion.setFechaLimite(jsonObject.get("deadline_date").getAsString());
        }
        if (jsonObject.get("deadline_length_days") != null) {
            licitacion.setDiasFechaLimite(jsonObject.get("deadline_length_days").getAsString());
        }
        licitacion.setTitulo(jsonObject.get("title").getAsString());
        licitacion.setCategory(jsonObject.get("category").getAsString());
        licitacion.setSid(jsonObject.get("sid").getAsString());
        licitacion.setUrl(jsonObject.get("src_url").getAsString());

        return licitacion;
    }

    // Convertimos cada JsonElement de adjudicacion en un objeto JsonObject para poder acceder a sus propiedades.
    // Recogemos los valores de las propiedades de cada JsonObject y los seteamos en los atributos de un objeto de la clase Adjudicacion.
    // Devolvemos el objeto Adjudicacion.
    public static Adjudicacion devolverAdjudicacion(JsonElement jsonElement, int idLicitacion) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Adjudicacion adjudicacion = new Adjudicacion();
        adjudicacion.setFechaAdjudicacion(jsonObject.get("date").getAsString());
        adjudicacion.setValueForTwo(jsonObject.get("value_for_two").getAsInt());
        adjudicacion.setIdProveedor(jsonObject.get("suppliers_id").getAsString());
        adjudicacion.setValueForThree(jsonObject.get("value_for_three").getAsInt());
        adjudicacion.setRecuento(jsonObject.get("count").getAsString());
        adjudicacion.setRecuentoOfertas(jsonObject.get("offers_count").getAsInt());
        adjudicacion.setValueForOne(jsonObject.get("value_for_one").getAsInt());
        adjudicacion.setNombreProveedor(jsonObject.get("suppliers_name").getAsString());
        adjudicacion.setValor(jsonObject.get("value").getAsString());
        adjudicacion.setIdLicitacion(idLicitacion);

        return adjudicacion;
    }

    // Convertimos cada JsonElement de proveedor en un objeto JsonObject para poder acceder a sus propiedades.
    // Recogemos los valores de las propiedades de cada JsonObject y los seteamos en los atributos de un objeto de la clase Proveedor.
    // Devolvemos el objeto Proveedor.
    public static Proveedor devolverProveedor(JsonElement jsonElement, int idAdjudicacion) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(jsonObject.get("name").getAsString());
        proveedor.setIdProveedor(jsonObject.get("id").getAsString());
        proveedor.setSlugProveedor(jsonObject.get("slug").getAsString());
        proveedor.setIdAdjudicacion(idAdjudicacion);

        return proveedor;
    }

    // Consultamos los registros de la base de datos y los mostramos por consola en formato objeto Licitacion.
    public static void consultarRegistros() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<Licitacion> query = session.createQuery("from Licitacion", Licitacion.class);
        List<Licitacion> licitaciones = query.list();

        for (Licitacion licitacion : licitaciones) {
            System.out.println(licitacion + "\n");
        }

        transaction.commit();
        session.close();
    }
}
