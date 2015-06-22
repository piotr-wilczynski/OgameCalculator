package OgameApi;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class OgameApi {

    private static OgameApi ogameApi = new OgameApi();

    private Universes universes;
    private ServerData serverData;
    private Players players;
    private Universe universe;
    private PlayerData playerData;
    private Localization localization;

    public Universes getUniverses(String lang) {
        universes = null;
        int i = 1;
        while (universes == null) {
            final String url = "http://s" + i + "-" + lang + ".ogame.gameforge.com/api/universes.xml";
            try {
                universes = readData(url, Universes.class);
            } catch (FileNotFoundException | MalformedURLException | JAXBException ex) {
                //No server found
            }

            if (i >= 300) {
                break;
            }
            i++;
        }
        return universes;
    }

    public ServerData getServerData(Universes.Universe universe) throws MalformedURLException, JAXBException, FileNotFoundException {
        return serverData = readData(universe.href+"/api/serverData.xml", ServerData.class);        
    }    

    public Players getPlayers(Universes.Universe universe) throws MalformedURLException, JAXBException, FileNotFoundException {
        return players = readData(universe.href+"/api/players.xml", Players.class);
    }

    public PlayerData getPlayerData(Universes.Universe universe,Players.Player player) throws MalformedURLException, JAXBException, FileNotFoundException {
        return playerData = readData(universe.href+"/api/playerData.xml?id="+player.id, PlayerData.class);
    }   

    public Universe getUniverse(Universes.Universe universe)  throws MalformedURLException, JAXBException, FileNotFoundException {
        return this.universe = readData(universe.href+"/api/universe.xml", Universe.class);
    }  

    public Localization getLocalization(Universes.Universe universe)  throws MalformedURLException, JAXBException, FileNotFoundException {
        return this.localization = readData(universe.href+"/api/localization.xml", Localization.class);
    }

    public static OgameApi getInstance() {
        return ogameApi;
    }

    //private <T> T read(){
    public <T> T readData(String URL, Class<T> classType) throws MalformedURLException, JAXBException, FileNotFoundException {
        System.out.println(URL);
        JAXBContext jaxbContext = JAXBContext.newInstance(classType);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(new URL(URL));
    }
    //  return null;
    //}

}
