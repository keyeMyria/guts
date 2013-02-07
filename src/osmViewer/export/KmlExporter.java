/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osmViewer.export;

import java.io.File;
import java.util.LinkedList;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import osmViewer.data.Waypoint;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author patrick
 */
public class KmlExporter {
    private static Text coords;
    
    public static void exportHistoryAsXML(Set<osmViewer.data.Waypoint> history) {
        
                Document doc;
               try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.newDocument();
		// kml element
		 
                coords = doc.createTextNode("");
                for (Waypoint wp : history) {
                    coords.appendData(wp.getLongitude() + "," + wp.getLatitude() + ",0 \n");
                }
                
                buildXML(doc);
                
              } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
              }
              
               
              
    } 
    
    private static void buildXML(Document doc) {
        try {
		Element rootElement = doc.createElement("kml");
                rootElement.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
                rootElement.setAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2");
                rootElement.setAttribute("xmlns:kml", "http://www.opengis.net/kml/2.2");
                rootElement.setAttribute("xmlns:atom", "http://www.w3.org/2005/Atom");
		doc.appendChild(rootElement);
 
		// Document element
		Element document = doc.createElement("Document");
		rootElement.appendChild(document);
                
                // Placemark element
                Element placemark = doc.createElement("Placemark");
                document.appendChild(placemark);
                
                // visibility Element
                Element visibility = doc.createElement("visibility");
                visibility.appendChild(doc.createTextNode("1"));
                placemark.appendChild(visibility);
                

                
                // Line String Element
                Element lineString = doc.createElement("LineString");
                placemark.appendChild(lineString);
                
                // extrude Element
                //Element extrude = doc.createElement("extrude");
                //extrude.appendChild(doc.createTextNode("1"));
                //lineString.appendChild(extrude);

                // tessellate Element
                Element tessellate = doc.createElement("tessellate");
                tessellate.appendChild(doc.createTextNode("1"));
                lineString.appendChild(tessellate);
                
                // coordinates Element
                Element coordinates = doc.createElement("coordinates");
                coordinates.appendChild(coords);
                lineString.appendChild(coordinates);
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("waypoints.kml"));
 
		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  
	  } catch (TransformerException tfe) {
              System.out.println("Error");
              tfe.printStackTrace();
	  }
    }
    
}
