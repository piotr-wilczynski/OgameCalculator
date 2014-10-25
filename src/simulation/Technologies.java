
package simulation;
public class Technologies {   
    
    private int Weapons_Technology;
    private int Shielding_Technology;
    private int Armour_Technology;
    
    private int Combustion_Drive;
    private int Impulse_Drive;
    private int Hyperspace_Drive;   

    public Technologies(int Weapons_Technology, int Shielding_Technology, int Armour_Technology, int Combustion_Drive, int Impulse_Drive, int Hyperspace_Drive) {
        this.Weapons_Technology = Weapons_Technology;
        this.Shielding_Technology = Shielding_Technology;
        this.Armour_Technology = Armour_Technology;
        this.Combustion_Drive = Combustion_Drive;
        this.Impulse_Drive = Impulse_Drive;
        this.Hyperspace_Drive = Hyperspace_Drive;
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

    public void setWeapons_Technology(int Weapons_Technology) {
        this.Weapons_Technology = Weapons_Technology;
    }

    public void setShielding_Technology(int Shielding_Technology) {
        this.Shielding_Technology = Shielding_Technology;
    }

    public void setArmour_Technology(int Armour_Technology) {
        this.Armour_Technology = Armour_Technology;
    }

    public void setCombustion_Drive(int Combustion_Drive) {
        this.Combustion_Drive = Combustion_Drive;
    }

    public void setImpulse_Drive(int Impulse_Drive) {
        this.Impulse_Drive = Impulse_Drive;
    }

    public void setHyperspace_Drive(int Hyperspace_Drive) {
        this.Hyperspace_Drive = Hyperspace_Drive;
    }
    
    
}
