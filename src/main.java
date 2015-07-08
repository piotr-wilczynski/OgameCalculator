/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Locale;
import javax.xml.bind.JAXBException;

public class main {

    public static void main(String[] args) throws MalformedURLException, JAXBException, FileNotFoundException {
        new GUI.GUI();

        /*
         PlayerProfile[] playerProfile = new PlayerProfile[3];
         playerProfile[0]=(new PlayerProfile(LangEnum.Polish, 1, "PlayerName1"));
         playerProfile[1]=(new PlayerProfile(LangEnum.Polish, 2, "PlayerName2"));
         playerProfile[2]=(new PlayerProfile(LangEnum.Polish, 3, "PlayerName3"));
         Profile profile = new Profile(Locale.getDefault(), playerProfile, 10);

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
         */
        //OgameApi.getInstance().getPlayers(myUni);
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
