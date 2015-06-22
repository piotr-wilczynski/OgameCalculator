/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OgameApi;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl;
import com.sun.xml.internal.stream.XMLInputFactoryImpl;
import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Piotr
 */
public class ServerDataReader {

    private String serverUrl;

    public ServerDataReader(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void readData() throws MalformedURLException, IOException, JAXBException {
        String url = serverUrl + "/api/serverData.xml";
        InputStream input = new URL(url).openStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(ServerData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ServerData data = (ServerData) jaxbUnmarshaller.unmarshal(input);
        System.out.println(data.getName()+" "+data.getSpeedFleet());
    }

}
