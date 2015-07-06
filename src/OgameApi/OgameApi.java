/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package OgameApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Integer counter;

    public Universes getUniverses(String lang) {
        ThreadGroup tg = new ThreadGroup("Universes Search");
        universes = null;
        counter = 1;
        Universes temp = new Universes();
        while (universes == null) {
            final String url = "http://s" + counter + "-" + lang + ".ogame.gameforge.com/api/universes.xml";

            if (tg.activeCount() < Runtime.getRuntime().availableProcessors()) {
                counter++;
                new Thread(tg, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(url);
                            if (exists(url)) {
                                universes = readData(url, Universes.class, false);
                            }
                        } catch (MalformedURLException | JAXBException | FileNotFoundException ex) {
                        }

                    }
                }).start();
            }
            //i++;
        }
        return universes;
    }

    public ServerData getServerData(String URL, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return serverData = readData(URL + "/api/serverData.xml", ServerData.class, Update);
    }

    public ServerData getServerData(Universes.Universe universe, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return serverData = getServerData(universe.getHref(), Update);
    }

    public ServerData getServerData(String lang, int serverID, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return serverData = getServerData("http://s" + serverID + "-" + lang + ".ogame.gameforge.com", Update);
    }

    public Players getPlayers(String URL, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return players = readData(URL + "/api/players.xml", Players.class, Update);
    }

    public Players getPlayers(Universes.Universe universe, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return players = getPlayers(universe.getHref(), Update);
    }

    public Players getPlayers(String lang, int serverID, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return players = getPlayers("http://s" + serverID + "-" + lang + ".ogame.gameforge.com", Update);
    }

    public Players getPlayers(ServerData serverData, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return players = getPlayers("http://" + serverData.getDomain(), Update);
    }

    public PlayerData getPlayerData(String URL, int playerId, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return playerData = readData(URL + "/api/playerData.xml?id=" + playerId, PlayerData.class, Update);
    }

    public PlayerData getPlayerData(Universes.Universe universe, Players.Player player, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return playerData = getPlayerData(universe.getHref(), player.getId().intValue(), Update);
    }

    public PlayerData getPlayerData(String lang, int serverID, int playerId, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return playerData = getPlayerData("http://s" + serverID + "-" + lang + ".ogame.gameforge.com", playerId, Update);
    }

    public Universe getUniverse(String URL, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return universe = readData(URL + "/api/universe.xml", Universe.class, Update);
    }

    public Universe getUniverse(Universes.Universe universe, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return this.universe = getUniverse(universe.getHref(), Update);
    }

    public Universe getUniverse(String lang, int serverID, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return universe = getUniverse("http://s" + serverID + "-" + lang + ".ogame.gameforge.com", Update);
    }

    public Universe getUniverse(ServerData serverData, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return universe = getUniverse("http://" + serverData.getDomain(), Update);
    }

    public Localization getLocalization(String URL, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return localization = readData(URL + "/api/localization.xml", Localization.class, Update);
    }

    public Localization getLocalization(Universes.Universe universe, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return localization = getLocalization(universe.getHref(), Update);
    }

    public Localization getLocalization(String lang, int serverID, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return localization = getLocalization("http://s" + serverID + "-" + lang + ".ogame.gameforge.com", Update);
    }

    public Localization getLocalization(String lang) throws MalformedURLException, JAXBException, FileNotFoundException {
        Universes unis = getUniverses(lang);
        if (unis != null) {
            return localization = getLocalization(unis.getUniverse().get(0), true);
        } else {
            return null;
        }
    }

    public Localization getLocalization(ServerData serverData, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        return localization = getLocalization("http://" + serverData.getDomain(), Update);
    }

    public static OgameApi getInstance() {
        return ogameApi;
    }

    private <T> T readData(String URL, Class<T> classType, boolean Update) throws MalformedURLException, JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(classType);
        File file = null;
        if (!Update) {
            file = loadFromDisc(URL, classType);
        }
        if (file == null) {
            file = loadFromInternet(URL, classType);
        }
        if (file == null && Update) {
            file = loadFromDisc(URL, classType);
        }
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        if (file != null) {
            T classInstance = classType.cast(jaxbUnmarshaller.unmarshal(file));
            Timestamp timeStamp = (Timestamp) classInstance;
            System.out.println(new Date(timeStamp.getTimestamp().longValue() * 1000));
            return classInstance;
        } else {
            return null;
        }
    }

    private <T> File loadFromInternet(String URL, Class<T> classType) {
        System.out.println("Load from the interet");
        if (!exists(URL)) {
            return null;
        }
        String fileURL = "saved/" + URL.substring(URL.indexOf("//") + 2).replaceFirst("api/", "");
        File file = new File(fileURL + "_");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            BufferedInputStream bis = new BufferedInputStream(new URL(URL).openStream());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            file.delete();
            return null;
        }
        if (validateFile(file, classType)) {
            File original = new File(fileURL);
            if (original.exists()) {
                original.delete();
            }
            file.renameTo(new File(fileURL));
        }
        return new File(fileURL);
    }

    private <T> File loadFromDisc(String URL, Class<T> classType) {
        System.out.println("Load from the dics");
        String fileURL = "saved/" + URL.substring(URL.indexOf("//") + 2).replaceFirst("api/", "");
        File file = new File(fileURL);
        if (file.exists()) {
            validateFile(file, classType);
            return file;
        } else {
            return null;
        }
    }

    private <T> boolean validateFile(File file, Class<T> classType) {
        return true;
    }

    private boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con
                    = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}
