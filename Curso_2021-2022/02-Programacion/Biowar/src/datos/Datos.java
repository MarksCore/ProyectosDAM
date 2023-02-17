package datos;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Datos {

	public static ArrayList<Datos_2> listaDatos = new  ArrayList<Datos_2>();


	public static void guardarXML(ArrayList<Datos_2> listaDatos) {

		leerXML();

		File archivo = new File("src/xml/ranking.xml");

		try {
			//crear estructura del xml
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// elemento raiz: fichero
			Document doc = docBuilder.newDocument();
			Element elemento = doc.createElement("fichero");
			doc.appendChild(elemento);

			for (int i = 0; i <listaDatos.size(); i++) {

				Element equipo = doc.createElement("jugador");
				elemento.appendChild(equipo);

				// atributo para el nodo equipo
				Attr attr = doc.createAttribute("id");
				attr.setValue(listaDatos.get(i).getId());
				equipo.setAttributeNode(attr);

				//nombreEquipo
				Element nombreJugador = doc.createElement("nombreJugador");
				nombreJugador.appendChild(doc.createTextNode(listaDatos.get(i).getNombre()));
				equipo.appendChild(nombreJugador);

				// tipoPersonaje
				Element tipoPersonaje= doc.createElement("tipoPersonaje");
				tipoPersonaje.appendChild(doc.createTextNode(listaDatos.get(i).getTipoPersonaje()));
				equipo.appendChild(tipoPersonaje);
				
				// fechaJuego
				Element fechaJuego = doc.createElement("fechaJuego");
				fechaJuego.appendChild(doc.createTextNode(listaDatos.get(i).getFecha()));
				equipo.appendChild(fechaJuego);
				
				
			}
			// escribimos el contenido en un archivo .xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(archivo);

			transformer.transform(source, result);

			System.out.println("Fichero guardado");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	} 

	public static String leerXML() {
		
		listaDatos = new  ArrayList<Datos_2>();
		File archivo = new File("src/xml/ranking.xml");
		String ranking = "";
		try {
			
			//cargar el fichero
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();


			//recorrer el fichero XML
			NodeList nList = document.getElementsByTagName("jugador");
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				//comprobamos si el elemento tiene elemento
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					//escogemos un elemento del xml
					Element eElement = (Element) nNode;

					//leemos el atributo
					String numJugador = eElement.getAttribute("id");

					//leemos los elementos
					String nombreJugador = eElement.getElementsByTagName("nombreJugador").item(0).getTextContent();
					String tipoPersonaje = eElement.getElementsByTagName("tipoPersonaje").item(0).getTextContent();
					String fechaJuego = eElement.getElementsByTagName("fechaJuego").item(0).getTextContent();

					listaDatos.add(new Datos_2(numJugador, nombreJugador, tipoPersonaje, fechaJuego));

				}
			}
			
			for(int i = 0; i < listaDatos.size(); i++) {
				
				ranking += ("Nº jugador: " + listaDatos.get(i).getId() + "\n" + "Nombre: " + listaDatos.get(i).getNombre() + "\n" +
						"Tipo de personaje: " + listaDatos.get(i).getTipoPersonaje() + "\n" + "Fecha de partida: " + listaDatos.get(i).getFecha()+ "\n" + "\n" );
			}

		}catch (Exception e) {
			System.out.println("Error lectura de fichero");
		}
		
		return ranking;
	}


}
