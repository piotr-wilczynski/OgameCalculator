package Enums;

import java.util.HashMap;
import java.util.Map;
import simulation.Battle_Technologies;

public enum Unit_Enum {
    //Name                  M       C       D       SI      SS    AS     Speed     CC      FU   Rapidfire   //LF  HF  C   B   BC  B   D   DS  SC  LC  CS  R   EP  SS

    Rocket_Launcher(2000, 0, 0, 2000, 20, 80, 0, 0, 0),
    Light_Laser(1500, 500, 0, 2000, 25, 100, 0, 0, 0),
    Heavy_Laser(6000, 2000, 0, 8000, 100, 250, 0, 0, 0),
    Gauss_Cannon(20000, 15000, 2000, 35000, 200, 1100, 0, 0, 0),
    Ion_Cannon(2000, 6000, 0, 8000, 500, 150, 0, 0, 0),
    Plasma_Turret(50000, 50000, 30000, 100000, 300, 3000, 0, 0, 0),
    Small_Shield_Dome(10000, 10000, 0, 20000, 2000, 1, 0, 0, 0),
    Large_Shield_Dome(50000, 50000, 0, 100000, 10000, 1, 0, 0, 0),
    Anti_Ballistic_Missiles(8000, 0, 2000, 8000, 1, 1, 0, 0, 0),
    Interplanetary_Missiles(12500, 2500, 10000, 15000, 1, 12000, 0, 0, 0),
    Espionage_Probe(0, 1000, 0, 1000, 0.01f, 0.01f, 100000000, 5, 1),
    Solar_Satellite(0, 2000, 500, 2000, 1, 1, 0, 0, 0),
    Small_Cargo(2000, 2000, 0, 4000, 10, 5, 5000, 5000, 10, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}),
    Large_Cargo(6000, 6000, 0, 12000, 25, 5, 7500, 25000, 50, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}),
    Colony_Ship(10000, 20000, 10000, 30000, 100, 50, 2500, 7500, 1000, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}),
    Recycler(10000, 6000, 2000, 16000, 10, 1, 2000, 20000, 300, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}),
    Light_Fighter(3000, 1000, 0, 4000, 10, 50, 12500, 50, 20, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}), //0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
    Heavy_Fighter(6000, 4000, 0, 10000, 25, 150, 10000, 100, 75, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5), new RF(Small_Cargo, 3)}),
    Cruiser(20000, 7000, 2000, 27000, 50, 400, 15000, 800, 300, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5), new RF(Light_Fighter, 6), new RF(Rocket_Launcher, 10)}),
    Battleship(45000, 15000, 0, 60000, 200, 1000, 10000, 1500, 500, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5)}),
    Battlecruiser(30000, 40000, 15000, 70000, 400, 700, 10000, 750, 250, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5), new RF(Small_Cargo, 3), new RF(Large_Cargo, 3), new RF(Heavy_Fighter, 4), new RF(Cruiser, 4), new RF(Battleship, 7)}),
    Bomber(50000, 25000, 15000, 75000, 500, 1000, 4000, 500, 1000, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5), new RF(Rocket_Launcher, 20), new RF(Light_Laser, 20), new RF(Heavy_Laser, 10), new RF(Ion_Cannon, 10)}),
    Destroyer(60000, 50000, 15000, 110000, 500, 2000, 5000, 2000, 1000, new RF[]{new RF(Espionage_Probe, 5), new RF(Solar_Satellite, 5), new RF(Light_Laser, 10), new RF(Battlecruiser, 2)}),
    Deathstar(5000000, 4000000, 1000000, 9000000, 50000, 200000, 100, 1000000, 1, new RF[]{new RF(Small_Cargo, 250), new RF(Large_Cargo, 250), new RF(Light_Fighter, 200), new RF(Heavy_Fighter, 100), new RF(Cruiser, 33), new RF(Battleship, 30), new RF(Colony_Ship, 250), new RF(Recycler, 250), new RF(Espionage_Probe, 1250), new RF(Solar_Satellite, 1250), new RF(Bomber, 25), new RF(Destroyer, 5), new RF(Rocket_Launcher, 200), new RF(Light_Laser, 200), new RF(Heavy_Laser, 100), new RF(Gauss_Cannon, 50), new RF(Ion_Cannon, 100), new RF(Battlecruiser, 15)});

    private final int Metal;
    private final int Crystal;
    private final int Deuterium;
    private final int Structural_Integrity;
    private final float Shield_Strength;
    private final float Attack_Strength;
    private final float Speed;
    private final int Cargo_Capacity;
    private final int Fuel_Usage;
    private final HashMap<Unit_Enum, Integer> Rapidfire;

    private Unit_Enum(int Metal, int Crystal, int Deuterium, int Structural_Integrity, float Shield_Strength, float Attack_Strength, int Speed, int Cargo_Capacity, int Fuel_Usage) {
        this.Metal = Metal;
        this.Crystal = Crystal;
        this.Deuterium = Deuterium;
        this.Structural_Integrity = Structural_Integrity;
        this.Shield_Strength = Shield_Strength;
        this.Attack_Strength = Attack_Strength;
        this.Speed = Speed;
        this.Cargo_Capacity = Cargo_Capacity;
        this.Fuel_Usage = Fuel_Usage;
        Rapidfire = new HashMap<>();
    }

    private Unit_Enum(int Metal, int Crystal, int Deuterium, int Structural_Integrity, float Shield_Strength, float Attack_Strength, int Speed, int Cargo_Capacity, int Fuel_Usage, RF[] rapidfire) {
        this.Metal = Metal;
        this.Crystal = Crystal;
        this.Deuterium = Deuterium;
        this.Structural_Integrity = Structural_Integrity;
        this.Shield_Strength = Shield_Strength;
        this.Attack_Strength = Attack_Strength;
        this.Speed = Speed;
        this.Cargo_Capacity = Cargo_Capacity;
        this.Fuel_Usage = Fuel_Usage;
        Rapidfire = new HashMap<>();
        for (int i = 0; i < rapidfire.length; i++) {
            Rapidfire.put(rapidfire[i].unit, rapidfire[i].rapidfire);
        }
    }

    public boolean isFleet() {
        switch (this) {
            case Light_Fighter:
            case Heavy_Fighter:
            case Cruiser:
            case Battleship:
            case Battlecruiser:
            case Bomber:
            case Destroyer:
            case Deathstar:
            case Small_Cargo:
            case Large_Cargo:
            case Colony_Ship:
            case Recycler:
            case Espionage_Probe:
            case Solar_Satellite: {
                return true;
            }
        }
        return false;
    }

    public static int getFleetNumber() {
        int i = 0;
        for (Unit_Enum u : values()) {
            if (u.isFleet()) {
                i++;
            }
        }
        return i;
    }

    public boolean isDefense() {
        switch (this) {
            case Rocket_Launcher:
            case Light_Laser:
            case Heavy_Laser:
            case Gauss_Cannon:
            case Ion_Cannon:
            case Plasma_Turret:
            case Small_Shield_Dome:
            case Large_Shield_Dome:
            case Anti_Ballistic_Missiles:
            case Interplanetary_Missiles: {
                return true;
            }
        }
        return false;
    }

    public int getStructural_Integrity() {
        return Structural_Integrity;
    }

    public int getStructural_Integrity(Battle_Technologies tech) {
        return Structural_Integrity + ((Structural_Integrity * tech.getArmour_Technology()) / 10);
    }

    public float getHull_Plating(Battle_Technologies tech) {
        return 0.1f * (Structural_Integrity + ((Structural_Integrity * tech.getArmour_Technology()) / 10));
    }

    public float getShield_Strength() {
        return Shield_Strength;
    }

    public float getShield_Strength(Battle_Technologies tech) {
        return Shield_Strength + ((Shield_Strength * tech.getShielding_Technology()) / 10);
    }

    public float getAttack_Strength() {
        return Attack_Strength;
    }

    public float getAttack_Strength(Battle_Technologies tech) {
        return Attack_Strength + ((Attack_Strength * tech.getWeapons_Technology()) / 10);
    }

    public float getSpeed() {
        return Speed;
    }

    public float getSpeed(Battle_Technologies tech) {
        switch (this) {
            case Light_Fighter:
            case Large_Cargo:
            case Recycler:
            case Espionage_Probe: {
                return Speed + (tech.getCombustion_Drive() * Speed / 10);
            }
            case Heavy_Fighter:
            case Cruiser:
            case Colony_Ship: {
                return Speed + (tech.getImpulse_Drive() * Speed * 2 / 10);
            }
            case Battleship:
            case Battlecruiser:
            case Destroyer:
            case Deathstar: {
                return Speed + (tech.getHyperspace_Drive() * Speed * 3 / 10);
            }
            case Small_Cargo: {
                if (tech.getImpulse_Drive() < 5) {
                    return Speed + (tech.getCombustion_Drive() * Speed / 10);
                } else {
                    return 10000 + (tech.getImpulse_Drive() * 10000 * 2 / 10);
                }
            }
            case Bomber: {
                if (tech.getHyperspace_Drive() < 8) {
                    return Speed + (tech.getImpulse_Drive() * Speed * 2 / 10);
                } else {
                    return 5000 + (tech.getHyperspace_Drive() * 5000 * 3 / 10);
                }
            }

        }
        return 0;
    }

    public int getCargo_Capacity() {
        return Cargo_Capacity;
    }

    @SuppressWarnings("fallthrough")
    public int getFuel_usage(Battle_Technologies technologies) {
        switch (this) {
            case Small_Cargo: {
                if (technologies.getImpulse_Drive() >= 5) {
                    return 20;
                }
            }
            ;
            default: {
                return Fuel_Usage;
            }
        }

    }

    public int getMetal() {
        return Metal;
    }

    public int getCrystal() {
        return Crystal;
    }

    public int getDeuterium() {
        return Deuterium;
    }

    public int getResources(Resources_Enum res) {
        switch (res) {
            case Metal: {
                return getMetal();
            }
            case Crystal: {
                return getCrystal();
            }
            case Deuterium: {
                return getDeuterium();
            }
        }
        return 0;
    }

    public int getRapidfire(Unit_Enum unit) {
        if (Rapidfire.get(unit) != null) {
            return Rapidfire.get(unit);
        }
        return 0;
    }

    public String toString(Battle_Technologies tech) {
        String rapids = "";
        for (Map.Entry<Unit_Enum, Integer> entry : Rapidfire.entrySet()) {
            rapids += " Rapidfire(" + entry.getKey().toString() + ")=" + entry.getValue();
        }
        float hull = getHull_Plating(tech);
        float shild = getShield_Strength(tech);
        float attack = getAttack_Strength(tech);
        System.out.printf("%-15s%s\n", "Typ =", "" + this.name());
        System.out.printf("%-15s%s\n", "Uzbrojenie =", "" + attack);
        System.out.printf("%-15s%s\n", "Tarcza =", "" + shild);
        System.out.printf("%-15s%s\n", "Osłona =", "" + hull);
        return toString();//+" Structural="+getStructural_Integrity(tech)+" Shield="+getShield_Strength(tech)+" Attack="+getAttack_Strength(tech)+" Speed="+getSpeed(tech)+rapids;

    }

    private static class RF {

        public Unit_Enum unit;
        public int rapidfire;

        public RF(Unit_Enum unit, int rapidfire) {
            this.unit = unit;
            this.rapidfire = rapidfire;
        }
    }

}
