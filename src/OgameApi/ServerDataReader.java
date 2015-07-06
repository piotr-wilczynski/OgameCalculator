/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package OgameApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
