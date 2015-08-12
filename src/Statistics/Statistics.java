/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package Statistics;

import Enums.*;
import Simulation.BattleUnit;
import Simulation.Battle_Technologies;

public class Statistics {

    private double[][] units;
    private double[] result;
    private int done;

    private Coordinates start, end;
    private boolean circularUniverses;
    private int universeSpeed;
    private int[][] startUnits;
    private int fleetSpeedPercent;
    private double derbisFleet;
    private double derbisDefense;
    private Battle_Technologies aggressorTechnologies;
    private long[] planetResources;
    private Player_Status_Enum playerStatus;

    public Statistics() {
        units = new double[2][];
        units[Side_Enum.Agressor.ordinal()] = new double[UnitEnum.values().length];
        units[Side_Enum.Defender.ordinal()] = new double[UnitEnum.values().length];
        startUnits = new int[2][];
        startUnits[Side_Enum.Agressor.ordinal()] = new int[UnitEnum.values().length];
        startUnits[Side_Enum.Defender.ordinal()] = new int[UnitEnum.values().length];
        result = new double[Side_Enum.values().length];
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[i].length; j++) {
                units[i][j] = 0;
                startUnits[i][j] = 0;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        start = new Coordinates(1, 1, 1);
        end = new Coordinates(1, 1, 1);
        circularUniverses = false;
        universeSpeed = 1;
        derbisFleet = 0.3;
        derbisDefense = 0;
        fleetSpeedPercent = 1;
        aggressorTechnologies = new Battle_Technologies();
        planetResources = new long[]{0, 0, 0};
        playerStatus = Player_Status_Enum.Neutral;
    }

    public Statistics(BattleUnit[] units_agressor, BattleUnit[] units_defender, Side_Enum result) {
        this();
        //System.out.println(units);
        for (int i = 0; i < units_agressor.length; i++) {
            UnitEnum unit = units_agressor[i].getUnit();
            //System.out.println(unit.ordinal()+" "+Side_Enum.Defender.ordinal());
            units[Side_Enum.Agressor.ordinal()][unit.ordinal()] += 1;
        }
        for (int i = 0; i < units_defender.length; i++) {
            UnitEnum unit = units_defender[i].getUnit();
            units[Side_Enum.Defender.ordinal()][unit.ordinal()] += 1;
        }
        this.result[result.ordinal()] = 1.0;
        done = 1;
    }

    public Statistics(Statistics[] statistics) {
        this();
        int count = 0;
        for (int i = 0; i < statistics.length; i++) {
            Statistics stat = statistics[i];
            if (stat != null) {
                for (UnitEnum unit : UnitEnum.values()) {
                    units[Side_Enum.Agressor.ordinal()][unit.ordinal()] += stat.getShip(Side_Enum.Agressor, unit);
                    units[Side_Enum.Defender.ordinal()][unit.ordinal()] += stat.getShip(Side_Enum.Defender, unit);
                }
                for (Side_Enum side : Side_Enum.values()) {
                    result[side.ordinal()] += stat.getResult(side);
                }
                count++;
            }
        }
        if (count > 0) {
            for (UnitEnum unit : UnitEnum.values()) {
                units[Side_Enum.Agressor.ordinal()][unit.ordinal()] = units[Side_Enum.Agressor.ordinal()][unit.ordinal()] / count;
                units[Side_Enum.Defender.ordinal()][unit.ordinal()] = units[Side_Enum.Defender.ordinal()][unit.ordinal()] / count;
            }
            for (Side_Enum side : Side_Enum.values()) {
                result[side.ordinal()] = result[side.ordinal()] / count;
            }
        }

        done = count;
    }

    public void setStart(Coordinates start) {
        this.start = start;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public void setCircularUniverses(boolean circularUniverses) {
        this.circularUniverses = circularUniverses;
    }

    public void setUniverseSpeed(int universeSpeed) {
        this.universeSpeed = universeSpeed;
    }

    public void setStartUnits(int[][] startUnits) {
        this.startUnits = startUnits;
    }

    public void setAggressorTechnologies(Battle_Technologies aggressorTechnologies) {
        this.aggressorTechnologies = aggressorTechnologies;
    }

    public void setFleetSpeedPercent(int fleetSpeedPercent) {
        this.fleetSpeedPercent = fleetSpeedPercent;
    }

    public void setDerbisDefense(double derbisDefense) {
        this.derbisDefense = derbisDefense;
    }

    public void setDerbisFleet(double derbisFleet) {
        this.derbisFleet = derbisFleet;
    }

    public void setPlanetResources(long[] planetResources) {
        this.planetResources = planetResources;
    }

    public void setPlayerStatus(Player_Status_Enum playerStatus) {
        this.playerStatus = playerStatus;
    }

    @Override
    public String toString() {
        System.out.println("Agressor");
        for (UnitEnum unit : UnitEnum.values()) {
            double number = units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            if (number > 0) {
                System.out.println("\t" + unit.name() + " \t\t\t" + number);
            }
        }
        System.out.println("Defender");
        for (UnitEnum unit : UnitEnum.values()) {
            double number = units[Side_Enum.Defender.ordinal()][unit.ordinal()];
            if (number > 0) {
                System.out.println("\t" + unit.name() + " \t\t\t" + number);
            }
        }
        return null;
    }

    public int getDone() {
        return done;
    }

    public double getShip(Side_Enum side, UnitEnum unit) {
        return units[side.ordinal()][unit.ordinal()];
    }

    public double getResult(Side_Enum side) {
        return result[side.ordinal()];
    }

    public long[] getDerbis() {
        long[] derbis = new long[Resources_Enum.values().length];
        for (int i = 0; i < derbis.length; i++) {
            derbis[i] = 0;
        }
        for (UnitEnum unit : UnitEnum.values()) {
            double a = startUnits[Side_Enum.Agressor.ordinal()][unit.ordinal()] - this.units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            double d = startUnits[Side_Enum.Defender.ordinal()][unit.ordinal()] - this.units[Side_Enum.Defender.ordinal()][unit.ordinal()];
            long rounda = Math.round(a);
            long roundd = Math.round(d);
            if (rounda > 0) {
                if (unit.isFleet()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * rounda * derbisFleet);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * rounda * derbisFleet);
                }
            }
            if (roundd > 0) {
                if (unit.isFleet()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * roundd * derbisFleet);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * roundd * derbisFleet);
                }
                if (unit.isDefense()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * roundd * derbisDefense);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * roundd * derbisDefense);
                }
            }
        }
        return derbis;
    }

    public long[] getLosses(Side_Enum side) {
        long[] losses = new long[Resources_Enum.values().length];
        for (int i = 0; i < losses.length; i++) {
            losses[i] = 0;
        }

        for (UnitEnum unit : UnitEnum.values()) {
            double a = startUnits[side.ordinal()][unit.ordinal()] - this.units[side.ordinal()][unit.ordinal()];
            long round = Math.round(a);
            if (round > 0) {
                for (Resources_Enum res : Resources_Enum.values()) {
                    losses[res.ordinal()] += unit.getResources(res) * round;
                }
            }
        }
        return losses;
    }

    public double[] getTactitalRetreat() {
        double[] retreat = new double[2];
        for (int i = 0; i < retreat.length; i++) {
            retreat[i] = 0;
        }
        for (UnitEnum unit : UnitEnum.values()) {
            long numbera = startUnits[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            long numberd = startUnits[Side_Enum.Defender.ordinal()][unit.ordinal()];
            switch (unit) {
                case LightFighter:
                case HeavyFighter:
                case Cruiser:
                case Battleship:
                case Battlecruiser:
                case Bomber:
                case Deathstar:
                case Destroyer: {
                    long cost = unit.getMetal() + unit.getCrystal() + unit.getDeuterium();
                    retreat[Side_Enum.Agressor.ordinal()] += (numbera * cost);
                    retreat[Side_Enum.Defender.ordinal()] += (numberd * cost);
                }
                break;
                case SmallCargo:
                case LargeCargo:
                case ColonyShip:
                case Recycler: {
                    long cost = unit.getMetal() + unit.getCrystal() + unit.getDeuterium();
                    retreat[Side_Enum.Agressor.ordinal()] += (numbera * cost / 4);
                    retreat[Side_Enum.Defender.ordinal()] += (numberd * cost / 4);
                }
                break;
            }
        }
        return retreat;
    }

    public long[] getTeoreticalPlunder() {
        long[] plunder = new long[Resources_Enum.values().length];
        for (int i = 0; i < plunder.length; i++) {
            plunder[i] = 0;
        }
        double percent = playerStatus.getPlunder_ratio();
        for (Resources_Enum res : Resources_Enum.values()) {
            plunder[res.ordinal()] = (long) Math.floor(planetResources[res.ordinal()] * percent);
        }
        return plunder;
    }

    public long[] getRealPlunder() {
        long[] plunder = getTeoreticalPlunder();
        long max_metal = plunder[Resources_Enum.Metal.ordinal()];
        long max_crystal = plunder[Resources_Enum.Crystal.ordinal()];
        long max_deuterium = plunder[Resources_Enum.Deuterium.ordinal()];
        long max_capacity = 0;
        for (UnitEnum unit : UnitEnum.values()) {
            max_capacity += (Math.round(units[Side_Enum.Agressor.ordinal()][unit.ordinal()]) * unit.getCargoCapacity());
        }
        for (int i = 0; i < plunder.length; i++) {
            plunder[i] = 0;
        }
        long temp = Math.min((long) Math.ceil(1.0 * max_capacity / 3), max_metal);
        max_capacity -= temp;
        max_metal -= temp;
        plunder[Resources_Enum.Metal.ordinal()] += temp;

        temp = Math.min((long) Math.ceil(1.0 * max_capacity / 2), max_crystal);
        max_capacity -= temp;
        max_crystal -= temp;
        plunder[Resources_Enum.Crystal.ordinal()] += temp;

        temp = Math.min(max_capacity, max_deuterium);
        max_capacity -= temp;
        max_deuterium -= temp;
        plunder[Resources_Enum.Deuterium.ordinal()] += temp;

        if (max_capacity > 0) {
            temp = Math.min((long) Math.ceil(1.0 * max_capacity / 2), max_metal);
            max_capacity -= temp;
            max_metal -= temp;
            plunder[Resources_Enum.Metal.ordinal()] += temp;

            temp = Math.min(max_capacity, max_crystal);
            max_capacity -= temp;
            max_crystal -= temp;
            plunder[Resources_Enum.Crystal.ordinal()] += temp;
        }
        return plunder;
    }

    public long getDistance() {
        if (start.equals(end)) {
            return 5;
        }
        if (circularUniverses) {

        } else {
            if (start.getGalaxy() == end.getGalaxy()) {
                if (start.getSolar_system() == end.getSolar_system()) {
                    int value = start.getPosition() - end.getPosition();
                    if (value < 0) {
                        value *= -1;
                    }
                    return 1000 + (5 * (value));
                } else {
                    int value = start.getSolar_system() - end.getSolar_system();
                    if (value < 0) {
                        value *= -1;
                    }
                    return 2700 + (95 * (value));

                }
            } else {
                int value = start.getGalaxy() - end.getGalaxy();
                if (value < 0) {
                    value *= -1;
                }
                return 20000 * value;
            }
        }
        return 0;
    }

    public long getDuration() {
        float min = Long.MAX_VALUE;
        for (UnitEnum unit : UnitEnum.values()) {
            if (startUnits[Side_Enum.Agressor.ordinal()][unit.ordinal()] > 0) {
                if (min > unit.getSpeed(aggressorTechnologies)) {
                    min = unit.getSpeed(aggressorTechnologies);
                }
            }
        }
        long distance = getDistance();
        int value = (int) Math.round((35000.0 / fleetSpeedPercent * Math.pow((distance * 1000 / (min)), 0.5) + 10) / (universeSpeed));
        return value;
    }

    public long getFuel() {
        double fuel = 0;
        long duration = getDuration();
        long distance = getDistance();
        for (UnitEnum unit : UnitEnum.values()) {
            int ships = startUnits[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            if (ships <= 0) {
                continue;
            }
            float speed = unit.getSpeed(aggressorTechnologies);
            int fuelcost = unit.getFuelUsage(aggressorTechnologies);
            double cost = (ships) * (fuelcost) * distance / 35000.0 * Math.pow((35000.0 / ((duration) * (universeSpeed) - 10.0) * Math.pow((distance * 10.0 / (speed)), 0.5)) / 10.0 + 1, 2);
            fuel += cost;
        }
        return Math.round(fuel) + 1;
    }
}
