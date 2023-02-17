package org.example;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws JAXBException {

        Scanner teclado = new Scanner(System.in);
        int escena = 1;
        int opcion = 0;
        boolean finalPartida = false;
        int selectorAventura;
        String ruta;

        ArrayList<Aventura> aventuras = new ArrayList<Aventura>();
        aventuras = leerXML(".\\xml\\aventuras.xml");
        System.out.println("Selecciona una aventura: ");
        for (int i = 1; i <= aventuras.size(); i++) {
            System.out.println(i + ".- " + aventuras.get(i-1).getTitulo());
        }
        selectorAventura = teclado.nextInt();
        Aventura aventura = aventuras.get(selectorAventura - 1);
        System.out.println(aventura.getTitulo());

        // Imprimir el XML.
        //System.out.println(escenas);

        do {
            escena = mostrarEscena(aventura.getListaEscenas(), escena);
            // System.out.println("Escena: " + escena);
            opcion = teclado.nextInt();
            escena = comprobarResultadoOpcion(aventura.getListaEscenas(), escena, opcion);

            if (aventura.getListaEscenas().get(escena - 1).getOpciones().isEmpty()) {
                finalPartida = true;
            }
        } while (!finalPartida);

        System.out.println(aventura.getListaEscenas().get(escena - 1).getTexto());
    }

    private static ArrayList<Aventura> leerXML(String ruta) throws JAXBException {
        File file = new File(ruta);
        ArrayList<Aventura> aventuras = new ArrayList<Aventura>();

        if (file.exists()) {
            JAXBContext jabxContext = JAXBContext.newInstance(Aventuras.class);
            Unmarshaller unmarshaller = jabxContext.createUnmarshaller();

            Aventuras listaAventuras = (Aventuras) unmarshaller.unmarshal(file);
            aventuras = (listaAventuras != null) ? listaAventuras.getListaAventuras() : new ArrayList<Aventura>();
        } else {
            System.out.println("No existe el fichero indicado.");
        }
        return aventuras;
    }

    private static int mostrarEscena(ArrayList<Escena> escenas, int escena) {
        for (int i = 0; i < escenas.size(); i++) {
            if (escena == escenas.get(i).getCodigo()) {
                escena = i;
                System.out.println(escenas.get(i).getTexto());
                for (int j = 0; j < escenas.get(i).getOpciones().size(); j++) {
                    System.out.println(escenas.get(i).getOpciones().get(j).getId() + ".- " + escenas.get(i).getOpciones().get(j).getTexto());
                }
            }
        }
        return escena;
    }

    private static int comprobarResultadoOpcion(ArrayList<Escena> escenas, int escena, int opcion) {
        int escenaSiguiente = 0;

        for (int i = 0; i < escenas.get(escena).getOpciones().size(); i++) {
            if (opcion == escenas.get(escena).getOpciones().get(i).getId()) {
                escenaSiguiente = escenas.get(escena).getOpciones().get(i).getResultado();
            }
        }

        return escenaSiguiente;
    }
}