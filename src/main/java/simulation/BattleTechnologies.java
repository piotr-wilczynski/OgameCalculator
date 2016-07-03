/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package simulation;
public class BattleTechnologies {
    private final int Weapons_Technology;
    private final int Shielding_Technology;
    private final int Armour_Technology;
    
    private final int Combustion_Drive;
    private final int Impulse_Drive;
    private final int Hyperspace_Drive;   

    public BattleTechnologies(int Weapons_Technology, int Shielding_Technology, int Armour_Technology, int Combustion_Drive, int Impulse_Drive, int Hyperspace_Drive) {
        this.Weapons_Technology = Weapons_Technology;
        this.Shielding_Technology = Shielding_Technology;
        this.Armour_Technology = Armour_Technology;
        this.Combustion_Drive = Combustion_Drive;
        this.Impulse_Drive = Impulse_Drive;
        this.Hyperspace_Drive = Hyperspace_Drive;
    }
    public BattleTechnologies(int Weapons_Technology, int Shielding_Technology, int Armour_Technology) {
        this.Weapons_Technology = Weapons_Technology;
        this.Shielding_Technology = Shielding_Technology;
        this.Armour_Technology = Armour_Technology;
        this.Combustion_Drive = 0;
        this.Impulse_Drive = 0;
        this.Hyperspace_Drive = 0;
    }

    
    
    public BattleTechnologies() {
        this.Weapons_Technology = 0;
        this.Shielding_Technology = 0;
        this.Armour_Technology = 0;
        this.Combustion_Drive = 0;
        this.Impulse_Drive = 0;
        this.Hyperspace_Drive = 0;    
    }

    public int getWeapons_Technology() {
        return Weapons_Technology;
    }

    public int getShielding_Technology() {
        return Shielding_Technology;
    }

    public int getArmour_Technology() {
        return Armour_Technology;
    }

    public int getCombustion_Drive() {
        return Combustion_Drive;
    }

    public int getImpulse_Drive() {
        return Impulse_Drive;
    }

    public int getHyperspace_Drive() {
        return Hyperspace_Drive;
    }
    
    
}
