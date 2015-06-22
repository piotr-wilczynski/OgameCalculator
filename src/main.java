
import OgameApi.OgameApi;
import OgameApi.PlayerData;
import OgameApi.Players;
import OgameApi.Universes.Universe;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

public class main {

    public static void main(String[] args) throws MalformedURLException, JAXBException, FileNotFoundException {
        OgameApi api = OgameApi.getInstance();
        Universe myUni = null;
        for (Universe universe : OgameApi.getInstance().getUniverses("pl").getUniverse()) {
            //cygnus == null fail
            if("Cygnus".equals(OgameApi.getInstance().getServerData(universe).getName())){
                myUni = universe;
            }
            System.out.println(universe.getHref());
        }
        Players.Player myPlayer = null;
        for (Players.Player player : OgameApi.getInstance().getPlayers(myUni).getPlayer()) {
            if(player.getName().matches("Zagloba")){
                myPlayer = player;
            }
        }
        PlayerData.Planets.Planet planet = OgameApi.getInstance().getPlayerData(myUni, myPlayer).getPlanets().getPlanet().get(0);
        System.out.println(planet.getName()+" "+planet.getCoords());
        System.out.println(OgameApi.getInstance().getLocalization(myUni).getTechs().getName().get(0).getValue());
        //OgameApi.getInstance().getPlayers(myUni);
        //new GUI.GUI();
        //        */
        //HashMap<Unit_Enum,Integer> ships = new HashMap();
        //ships.put(Unit_Enum.Light_Fighter, 1);
        //Solver solver = new Solver(ships, new Battle_Technologies(), new Battle_Technologies());

        //new GUI.GUI();
        //        */
        //HashMap<Unit_Enum,Integer> ships = new HashMap();
        //ships.put(Unit_Enum.Light_Fighter, 1);
        //Solver solver = new Solver(ships, new Battle_Technologies(), new Battle_Technologies());
    }

}
