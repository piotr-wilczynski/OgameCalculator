
import Enums.Lang_Enum;
import ProfileManager.PlayerProfile;
import ProfileManager.Profile;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class main {

    public static void main(String[] args) throws MalformedURLException, JAXBException, FileNotFoundException {
        /*String lang = Locale.getDefault().getLanguage();
         Localization localization = OgameApi.getInstance().getLocalization(lang);
         if (localization != null) {
         for (LocalizationValues.Name name : localization.getTechs().getName()) {
         System.out.println(name.getId() + " " + name.getValue());
         }
         }*/

        List<PlayerProfile> playerProfile = new ArrayList<>();
        playerProfile.add(new PlayerProfile(Lang_Enum.Polish, 1, "PlayerName1"));
        playerProfile.add(new PlayerProfile(Lang_Enum.Polish, 2, "PlayerName2"));
        playerProfile.add(new PlayerProfile(Lang_Enum.Polish, 3, "PlayerName3"));
        Profile profile = new Profile(Locale.getDefault(), playerProfile, 0);


        XMLEncoder xe = null;
        try {
            File f = new File("profile.xml");
            f.createNewFile();
            xe = new XMLEncoder(new FileOutputStream(f));
            xe.writeObject(profile);
            xe.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int i;
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
