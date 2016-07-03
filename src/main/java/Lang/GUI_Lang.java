/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package lang;

import enums.UnitEnum;
import api.Localization;
import api.LocalizationValues.Name;

import java.util.Hashtable;
import java.util.List;

public class GUI_Lang {

    private static final GUI_Lang gui = new GUI_Lang();
    public final String shipyard_of_argessor;
    public final String shipyard_of_defender;
    public final String defense_of_defender;
    public final String research;
    ;
    public final String agressor;
    ;
    public final String defender;
    private static Hashtable<String, String> texts;

    public GUI_Lang() {
        texts = new Hashtable<>();
        shipyard_of_argessor = "Stocznia agresora";
        shipyard_of_defender = "Stocznia obrońcy";
        defense_of_defender = "Obrona obrońcy";
        research = "Badania";
        agressor = "Agresor";
        defender = "Obrońca";

        texts.put("Metal", "Metal");
        texts.put("Crystal", "Kryształ");
        texts.put("Deuterium", "Deuter");

        texts.put("Agressor", "Atakujący");
        texts.put("Defender", "Obrońca");
        texts.put("Remis", "Resmis");

        texts.put("Neutral", "Neutralny");
        texts.put("Honorable", "Honorowy");
        texts.put("Bandit", "Bandyta");

        texts.put("Light_Fighter", "Lekki myśliwiec");
        texts.put("Heavy_Fighter", "Ciężki myśliwiec");
        texts.put("Cruiser", "Krążownik");
        texts.put("Battleship", "Okręt wojenny");
        texts.put("Battlecruiser", "Pancernik");
        texts.put("Bomber", "Bombowiec");
        texts.put("Destroyer", "Niszczyciel");
        texts.put("Deathstar", "Gwiazda Śmierci");
        texts.put("Small_Cargo", "Mały transporter");
        texts.put("Large_Cargo", "Duży transporter");
        texts.put("Colony_Ship", "Statek kolonizacyjny");
        texts.put("Recycler", "Recykler");
        texts.put("Espionage_Probe", "Sonda szpiegowska");
        texts.put("Solar_Satellite", "Satelita słoneczny");

        texts.put("Rocket_Launcher", "Wyrzutnia rakiet");
        texts.put("Light_Laser", "Lekkie działo laserowe");
        texts.put("Heavy_Laser", "Ciężkie działo laserowe");
        texts.put("Gauss_Cannon", "Działo Gaussa");
        texts.put("Ion_Cannon", "Działo jonowe");
        texts.put("Plasma_Turret", "Wyrzutnia plazmy");
        texts.put("Small_Shield_Dome", "Mała powłoka ochronna");
        texts.put("Large_Shield_Dome", "Duża powłoka ochronna");
        texts.put("Anti_Ballistic_Missiles", "Przeciwrakieta");
        texts.put("Interplanetary_Missiles", "Rakieta międzyplanetarna");

        texts.put("Energy_Technology", "Technologia energetyczna");
        texts.put("Laser_Technology", "Technologia laserowa");
        texts.put("Ion_Technology", "Technologia jonowa");
        texts.put("Hyperspace_Technology", "Technologia nadprzestrzenna");
        texts.put("Plasma_Technology", "Technologia plazmowa");
        texts.put("Combustion_Drive", "Napęd spalinowy");
        texts.put("Impulse_Drive", "Napęd impulsowy");
        texts.put("Hyperspace_Drive", "Napęd nadprzestrzenny");
        texts.put("Espionage_Technology", "Technologia szpiegowska");
        texts.put("Computer_Technology", "Technologia komputerowa");
        texts.put("Astrophysics", "Astrofizyka");
        texts.put("Intergalactic_Research_Network", "Międzygalaktyczna Sieć Badań Naukowych");
        texts.put("Graviton_Technology", "Rozwój grawitonów");
        texts.put("Weapons_Technology", "Technologia bojowa");
        texts.put("Shielding_Technology", "Technologia ochronna");
        texts.put("Armour_Technology", "Opancerzenie");

        texts.put("simulation_start", "Rozpocznij Symulację");
        texts.put("own_location", "Własna pozycja:");
        texts.put("fleet_speed", "Prędkość:");
        texts.put("simulation_number", "Ilość symulacji:");

        texts.put("label_battle_place", "Bitwa...");
        texts.put("label_winner", "Zwycięży");
        texts.put("label_tactical_retreat", "Taktyczny odwrót");
        texts.put("label_derbis", "Odpadki");
        texts.put("label_chance_for_moon", "Szansa na księżyc");
        texts.put("label_attacker_losses", "Straty agresora");
        texts.put("label_defender_losses", "Straty obrońcy");
        texts.put("label_teoretical_plunder", "Teoretyczny Rabunek");
        texts.put("label_real_plunder", "Rzeczywisty Rabunek");
        texts.put("label_fuel", "Potrzebne Paliwo");
        texts.put("label_time", "Czas Lotu");

        texts.put("battle_place", "Bitwa na %s %s");
        texts.put("winner", "%s po %d rundach");
        texts.put("chance_for_moon", "Szansa na księżyc wynosi %d%%");
        texts.put("real_plunder", "%s (%d%% Łupu)");

        texts.put("Resources", "Zasoby");
        texts.put("Cancel", "Anuluj");
        texts.put("Moon", "Księżyc");
        texts.put("label_planet_locations", "Położenie");
        texts.put("label_planet_name", "Nazwa planety");
        texts.put("change_planet", "Zmień");

        /*try {
         IO_Utilities.save_ini("polish", texts);
         } catch (IOException ex) {
         ex.printStackTrace();
         }
         */
    }

    public static GUI_Lang getInstance() {
        return gui;
    }

    public static String get(String key) {
        return texts.getOrDefault(key, key);
    }

    public static String getUnit(UnitEnum unit) {
        Localization loc = profilemanager.Profile.getInstance().getLocalization();
        if (loc != null) {
            List<Name> list = loc.getTechs().getName();
            for (Name name : list) {
                if (name.getId() == unit.getLocalizationId()) {
                    return name.getValue();
                }
            }            
        } 
        return null;
    }

}
