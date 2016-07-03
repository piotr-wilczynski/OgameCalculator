/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package enums;

import simulation.BattleTechnologies;

import java.util.HashMap;
import java.util.Map;

public enum UnitEnum {
    RocketLauncher(2000, 0, 0, 2000, 20, 80, 0, 0, 0, 401),
    LightLaser(1500, 500, 0, 2000, 25, 100, 0, 0, 0, 402),
    HeavyLaser(6000, 2000, 0, 8000, 100, 250, 0, 0, 0, 403),
    GaussCannon(20000, 15000, 2000, 35000, 200, 1100, 0, 0, 0, 404),
    IonCannon(2000, 6000, 0, 8000, 500, 150, 0, 0, 0, 405),
    PlasmaTurret(50000, 50000, 30000, 100000, 300, 3000, 0, 0, 0,406),
    SmallShieldDome(10000, 10000, 0, 20000, 2000, 1, 0, 0, 0, 407),
    LargeShieldDome(50000, 50000, 0, 100000, 10000, 1, 0, 0, 0, 408),
    AntiBallisticMissiles(8000, 0, 2000, 8000, 1, 1, 0, 0, 0, 502),
    InterplanetaryMissiles(12500, 2500, 10000, 15000, 1, 12000, 0, 0, 0, 503),
    EspionageProbe(0, 1000, 0, 1000, 0.01f, 0.01f, 100000000, 5, 1, 210),
    SolarSatellite(0, 2000, 500, 2000, 1, 1, 0, 0, 0, 212),
    SmallCargo(2000, 2000, 0, 4000, 10, 5, 5000, 5000, 10, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},202),
    LargeCargo(6000, 6000, 0, 12000, 25, 5, 7500, 25000, 50, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},203),
    ColonyShip(10000, 20000, 10000, 30000, 100, 50, 2500, 7500, 1000, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},208),
    Recycler(10000, 6000, 2000, 16000, 10, 1, 2000, 20000, 300, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},209),
    LightFighter(3000, 1000, 0, 4000, 10, 50, 12500, 50, 20, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},204), //0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
    HeavyFighter(6000, 4000, 0, 10000, 25, 150, 10000, 100, 75, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5), new RapidFire(SmallCargo, 3)},205),
    Cruiser(20000, 7000, 2000, 27000, 50, 400, 15000, 800, 300, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5), new RapidFire(LightFighter, 6), new RapidFire(RocketLauncher, 10)},206),
    Battleship(45000, 15000, 0, 60000, 200, 1000, 10000, 1500, 500, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5)},207),
    Battlecruiser(30000, 40000, 15000, 70000, 400, 700, 10000, 750, 250, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5), new RapidFire(SmallCargo, 3), new RapidFire(LargeCargo, 3), new RapidFire(HeavyFighter, 4), new RapidFire(Cruiser, 4), new RapidFire(Battleship, 7)},215),
    Bomber(50000, 25000, 15000, 75000, 500, 1000, 4000, 500, 1000, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5), new RapidFire(RocketLauncher, 20), new RapidFire(LightLaser, 20), new RapidFire(HeavyLaser, 10), new RapidFire(IonCannon, 10)},211),
    Destroyer(60000, 50000, 15000, 110000, 500, 2000, 5000, 2000, 1000, new RapidFire[]{new RapidFire(EspionageProbe, 5), new RapidFire(SolarSatellite, 5), new RapidFire(LightLaser, 10), new RapidFire(Battlecruiser, 2)},213),
    Deathstar(5000000, 4000000, 1000000, 9000000, 50000, 200000, 100, 1000000, 1, new RapidFire[]{new RapidFire(SmallCargo, 250), new RapidFire(LargeCargo, 250), new RapidFire(LightFighter, 200), new RapidFire(HeavyFighter, 100), new RapidFire(Cruiser, 33), new RapidFire(Battleship, 30), new RapidFire(ColonyShip, 250), new RapidFire(Recycler, 250), new RapidFire(EspionageProbe, 1250), new RapidFire(SolarSatellite, 1250), new RapidFire(Bomber, 25), new RapidFire(Destroyer, 5), new RapidFire(RocketLauncher, 200), new RapidFire(LightLaser, 200), new RapidFire(HeavyLaser, 100), new RapidFire(GaussCannon, 50), new RapidFire(IonCannon, 100), new RapidFire(Battlecruiser, 15)},214);

    private final int Metal;
    private final int Crystal;
    private final int Deuterium;
    private final int Structural_Integrity;
    private final float Shield_Strength;
    private final float Attack_Strength;
    private final float Speed;
    private final int Cargo_Capacity;
    private final int Fuel_Usage;
    private final HashMap<UnitEnum, Integer> Rapidfire;
    private final int localizationId;

    private UnitEnum(int Metal, int Crystal, int Deuterium, int Structural_Integrity, float Shield_Strength, float Attack_Strength, int Speed, int Cargo_Capacity, int Fuel_Usage,int localizationId) {
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
        this.localizationId = localizationId; 
    }

    private UnitEnum(int Metal, int Crystal, int Deuterium, int Structural_Integrity, float Shield_Strength, float Attack_Strength, int Speed, int Cargo_Capacity, int Fuel_Usage, RapidFire[] rapidfire,int localizationId) {
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
        for (RapidFire rf : rapidfire) {
            Rapidfire.put(rf.unit, rf.rapidfire);
        }
        this.localizationId = localizationId; 
    }

    public boolean isFleet() {
        switch (this) {
            case LightFighter:
            case HeavyFighter:
            case Cruiser:
            case Battleship:
            case Battlecruiser:
            case Bomber:
            case Destroyer:
            case Deathstar:
            case SmallCargo:
            case LargeCargo:
            case ColonyShip:
            case Recycler:
            case EspionageProbe:
            case SolarSatellite: {
                return true;
            }
        }
        return false;
    }

    public static int getFleetNumber() {
        int i = 0;
        for (UnitEnum u : values()) {
            if (u.isFleet()) {
                i++;
            }
        }
        return i;
    }

    public boolean isDefense() {
        switch (this) {
            case RocketLauncher:
            case LightLaser:
            case HeavyLaser:
            case GaussCannon:
            case IonCannon:
            case PlasmaTurret:
            case SmallShieldDome:
            case LargeShieldDome:
            case AntiBallisticMissiles:
            case InterplanetaryMissiles: {
                return true;
            }
        }
        return false;
    }

    public int getStructuralIntegrity() {
        return Structural_Integrity;
    }

    public int getStructuralIntegrity(BattleTechnologies tech) {
        return Structural_Integrity + ((Structural_Integrity * tech.getArmour_Technology()) / 10);
    }

    public float getHullPlating(BattleTechnologies tech) {
        return 0.1f * (Structural_Integrity + ((Structural_Integrity * tech.getArmour_Technology()) / 10));
    }

    public float getShieldStrength() {
        return Shield_Strength;
    }

    public float getShieldStrength(BattleTechnologies tech) {
        return Shield_Strength + ((Shield_Strength * tech.getShielding_Technology()) / 10);
    }

    public float getAttackStrength() {
        return Attack_Strength;
    }

    public float getAttackStrength(BattleTechnologies tech) {
        return Attack_Strength + ((Attack_Strength * tech.getWeapons_Technology()) / 10);
    }

    public float getSpeed() {
        return Speed;
    }

    public float getSpeed(BattleTechnologies tech) {
        switch (this) {
            case LightFighter:
            case LargeCargo:
            case Recycler:
            case EspionageProbe: {
                return Speed + (tech.getCombustion_Drive() * Speed / 10);
            }
            case HeavyFighter:
            case Cruiser:
            case ColonyShip: {
                return Speed + (tech.getImpulse_Drive() * Speed * 2 / 10);
            }
            case Battleship:
            case Battlecruiser:
            case Destroyer:
            case Deathstar: {
                return Speed + (tech.getHyperspace_Drive() * Speed * 3 / 10);
            }
            case SmallCargo: {
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

    public int getCargoCapacity() {
        return Cargo_Capacity;
    }

    @SuppressWarnings("fallthrough")
    public int getFuelUsage(BattleTechnologies technologies) {
        switch (this) {
            case SmallCargo: {
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

    public int getRapidfire(UnitEnum unit) {
        if (Rapidfire.get(unit) != null) {
            return Rapidfire.get(unit);
        }
        return 0;
    }

    public int getLocalizationId() {
        return localizationId;
    }   
    
    public String toString(BattleTechnologies tech) {
        String rapids = "";
        for (Map.Entry<UnitEnum, Integer> entry : Rapidfire.entrySet()) {
            rapids += " Rapidfire(" + entry.getKey().toString() + ")=" + entry.getValue();
        }
        float hull = getHullPlating(tech);
        float shild = getShieldStrength(tech);
        float attack = getAttackStrength(tech);
        System.out.printf("%-15s%s\n", "Typ =", "" + this.name());
        System.out.printf("%-15s%s\n", "Uzbrojenie =", "" + attack);
        System.out.printf("%-15s%s\n", "Tarcza =", "" + shild);
        System.out.printf("%-15s%s\n", "Osłona =", "" + hull);
        return toString();//+" Structural="+getStructuralIntegrity(tech)+" Shield="+getShieldStrength(tech)+" Attack="+getAttackStrength(tech)+" Speed="+getSpeed(tech)+rapids;

    }

    private static class RapidFire {

        public UnitEnum unit;
        public int rapidfire;

        public RapidFire(UnitEnum unit, int rapidfire) {
            this.unit = unit;
            this.rapidfire = rapidfire;
        }
    }

}
