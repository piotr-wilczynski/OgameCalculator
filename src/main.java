
import OgameApi.LocalizationValues;
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
        for(LocalizationValues.Name name : OgameApi.getInstance().getLocalization("jp", 1).getTechs().getName()){
            System.out.println(name.getId()+" "+name.getValue());
        }
        
        
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
