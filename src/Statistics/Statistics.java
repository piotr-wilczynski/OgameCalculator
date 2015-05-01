package Statistics;

import Enums.*;
import simulation.BattleUnit;
import simulation.Battle_Technologies;

public class Statistics {

    private double[][] units;
    private double[] result;
    private int done;

    public Statistics() {
        units = new double[2][];
        units[Side_Enum.Agressor.ordinal()] = new double[Unit_Enum.values().length];
        units[Side_Enum.Defender.ordinal()] = new double[Unit_Enum.values().length];
        result = new double[Side_Enum.values().length];
        for (int i = 0; i < units.length; i++) {
            for (int j = 0; j < units[i].length; j++) {
                units[i][j] = 0;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
    }

    public Statistics(BattleUnit[] units_agressor, BattleUnit[] units_defender, Side_Enum result) {
        this();
        //System.out.println(units);
        for (int i = 0; i < units_agressor.length; i++) {
            Unit_Enum unit = units_agressor[i].getUnit();
            //System.out.println(unit.ordinal()+" "+Side_Enum.Defender.ordinal());
            units[Side_Enum.Agressor.ordinal()][unit.ordinal()] += 1;
        }
        for (int i = 0; i < units_defender.length; i++) {
            Unit_Enum unit = units_defender[i].getUnit();
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
                for (Unit_Enum unit : Unit_Enum.values()) {
                    units[Side_Enum.Agressor.ordinal()][unit.ordinal()] += stat.getShip(Side_Enum.Agressor, unit);
                    units[Side_Enum.Defender.ordinal()][unit.ordinal()] += stat.getShip(Side_Enum.Defender, unit);
                }
                for (Side_Enum side : Side_Enum.values()) {
                    result[side.ordinal()] += stat.getResult(side);
                }
                count++;
            }
        }
        for (Unit_Enum unit : Unit_Enum.values()) {
            units[Side_Enum.Agressor.ordinal()][unit.ordinal()] = units[Side_Enum.Agressor.ordinal()][unit.ordinal()] / count;
            units[Side_Enum.Defender.ordinal()][unit.ordinal()] = units[Side_Enum.Defender.ordinal()][unit.ordinal()] / count;
        }

        for (Side_Enum side : Side_Enum.values()) {
            result[side.ordinal()] = result[side.ordinal()] / count;
        }
        done = count;
    }

    @Override
    public String toString() {
        System.out.println("Agressor");
        for (Unit_Enum unit : Unit_Enum.values()) {
            double number = units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            if (number > 0) {
                System.out.println("\t" + unit.name() + " \t\t\t" + number);
            }
        }
        System.out.println("Defender");
        for (Unit_Enum unit : Unit_Enum.values()) {
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

    public double getShip(Side_Enum side, Unit_Enum unit) {
        return units[side.ordinal()][unit.ordinal()];
    }

    public double getResult(Side_Enum side) {
        return result[side.ordinal()];
    }

    public long[] getDerbis(int[][] units, double percentfleet, double percentdefense) {
        long[] derbis = new long[Resources_Enum.values().length];
        for (int i = 0; i < derbis.length; i++) {
            derbis[i] = 0;
        }
        for (Unit_Enum unit : Unit_Enum.values()) {
            double a = units[Side_Enum.Agressor.ordinal()][unit.ordinal()] - this.units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            double d = units[Side_Enum.Defender.ordinal()][unit.ordinal()] - this.units[Side_Enum.Defender.ordinal()][unit.ordinal()];
            long rounda = Math.round(a);
            long roundd = Math.round(d);
            if (rounda > 0) {
                if (unit.isFleet()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * rounda * percentfleet);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * rounda * percentfleet);
                }
            }
            if (roundd > 0) {
                if (unit.isFleet()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * roundd * percentfleet);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * roundd * percentfleet);
                }
                if (unit.isDefense()) {
                    derbis[Resources_Enum.Metal.ordinal()] += Math.round(unit.getMetal() * roundd * percentdefense);
                    derbis[Resources_Enum.Crystal.ordinal()] += Math.round(unit.getCrystal() * roundd * percentdefense);
                }
            }
        }
        return derbis;
    }

    public long[] getLosses(Side_Enum side, int[][] units) {
        long[] losses = new long[Resources_Enum.values().length];
        for (int i = 0; i < losses.length; i++) {
            losses[i] = 0;
        }

        for (Unit_Enum unit : Unit_Enum.values()) {
            double a = units[side.ordinal()][unit.ordinal()] - this.units[side.ordinal()][unit.ordinal()];
            long round = Math.round(a);
            if (round > 0) {
                for (Resources_Enum res : Resources_Enum.values()) {
                    losses[res.ordinal()] += unit.getResources(res) * round;
                }
            }
        }
        return losses;
    }

    public double[] getTactitalRetreat(int[][] units) {
        double[] retreat = new double[2];
        for (int i = 0; i < retreat.length; i++) {
            retreat[i] = 0;
        }
        for (Unit_Enum unit : Unit_Enum.values()) {
            long numbera = units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            long numberd = units[Side_Enum.Defender.ordinal()][unit.ordinal()];
            switch (unit) {
                case Light_Fighter:
                case Heavy_Fighter:
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
                case Small_Cargo:
                case Large_Cargo:
                case Colony_Ship:
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

    public long[] getTeoreticalPlunder(long[] resources, Player_Status_Enum player) {
        long[] plunder = new long[Resources_Enum.values().length];
        for (int i = 0; i < plunder.length; i++) {
            plunder[i] = 0;
        }
        double percent = player.getPlunder_ratio();
        for (Resources_Enum res : Resources_Enum.values()) {
            plunder[res.ordinal()] = (long) Math.floor(resources[res.ordinal()] * percent);
        }
        return plunder;
    }

    public long[] getRealPlunder(long[] resources, Player_Status_Enum player) {
        long[] plunder = getTeoreticalPlunder(resources, player);
        long max_metal = plunder[Resources_Enum.Metal.ordinal()];
        long max_crystal = plunder[Resources_Enum.Crystal.ordinal()];
        long max_deuterium = plunder[Resources_Enum.Deuterium.ordinal()];
        long max_capacity = 0;
        for (Unit_Enum unit : Unit_Enum.values()) {
            max_capacity += (Math.round(units[Side_Enum.Agressor.ordinal()][unit.ordinal()]) * unit.getCargo_Capacity());
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

    public long getDistance(Coordinates start, Coordinates end, boolean circularUniverses) {
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

    public long getDuration(int[][] units, Coordinates start, Coordinates end, boolean circularUniverses, int universeSpeed, Battle_Technologies techs) {
        float min = Long.MAX_VALUE;
        for (Unit_Enum unit : Unit_Enum.values()) {
            if (units[Side_Enum.Agressor.ordinal()][unit.ordinal()] > 0) {
                if (min > unit.getSpeed(techs)) {
                    min = unit.getSpeed(techs);
                }
            }
        }
        long distance = getDistance(start, end, circularUniverses);
        int value = (int) Math.round((350 * Math.pow((distance * 1000 / (min)), 0.5) + 10) / (universeSpeed));
        return value;
    }

    public long getFuel(int[][] units, Coordinates start, Coordinates end, boolean circularUniverses, int universeSpeed, Battle_Technologies techs) {
        double fuel = 0;
        long duration = getDuration(units, start, end, circularUniverses, universeSpeed, techs);
        long distance = getDistance(start, end, circularUniverses);
        for (Unit_Enum unit : Unit_Enum.values()) {
            int ships = units[Side_Enum.Agressor.ordinal()][unit.ordinal()];
            if (ships <= 0) {
                continue;
            }
            float speed = unit.getSpeed(techs);
            int fuelcost = unit.getFuel_usage(techs);
            double cost = (ships) * (fuelcost) * distance / 35000.0 * Math.pow((35000.0 / ((duration) * (universeSpeed) - 10.0) * Math.pow((distance * 10.0 / (speed)), 0.5)) / 10.0 + 1, 2);
            fuel += cost;
        }
        return Math.round(fuel) + 1;
    }
}
