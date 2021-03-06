/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package simulation;

import enums.UnitEnum;
import java.util.Random;

/**
 * This class represents single unit.
 *
 * Use this class in simulation process.
 *
 * @author Piotr Wilczynski
 * @version 1.0, 05/07/16
 */
public class BattleUnit {

    /**
     * Represents type {@value}) of unit.
     *
     * @see UnitEnum
     */
    private final UnitEnum unit;

    /**
     * Represents hull plating of unit.
     */
    private float hullPlating;

    /**
     * Represents shield strength of unit.
     */
    private float shieldStrength;

    /**
     * Represents attack strength of unit.
     */
    private float attackStrength;

    /**
     * Random object to get probability. Used in each unit to perform better
     * quality of multithreading.
     *
     * @see Random
     */
    private Random random;

    /**
     * Class constructor. This constructor creates new unit with parameters
     * dependent on technologies.
     *
     * @param unit type of unit.
     * @param tech technologies of unit.
     * @see UnitEnum
     * @see BattleTechnologies
     */
    public BattleUnit(UnitEnum unit, BattleTechnologies tech) {
        this.unit = unit;
        hullPlating = unit.getHullPlating(tech);
        shieldStrength = unit.getShieldStrength(tech);
        attackStrength = unit.getAttackStrength(tech);
        random = new Random();
    }

    /**
     * Class constructor. This constructor creates new unit with parameters
     * dependent on technologies.
     *
     * @param unit type of unit.
     * @param hullPlating level of technology named hull plating.
     * @param shieldStrength level of technology named shield strength.
     * @param attackStrength level of technology named attack strength.
     * @see UnitEnum
     */
    public BattleUnit(UnitEnum unit, float hullPlating, float shieldStrength, float attackStrength) {
        this.unit = unit;
        this.hullPlating = hullPlating;
        this.shieldStrength = shieldStrength;
        this.attackStrength = attackStrength;
        random = new Random();
    }

    /**
     * Simulate battle of two allUnits. On is this unit, second is passed in
     * parameter.
     *
     * @param unit unit to fight with.
     * @param tech technologies of unit passed in parameter.
     * @return true if unit get chance to shot again.
     * @see BattleUnit
     * @see BattleTechnologies
     */
    public boolean fight(BattleUnit unit, BattleTechnologies tech) {
        //If the Weaponry of the shooting unit is less than 1% of the Shielding of the target unit, the shot is bounced, and the target unit does not lose anything (i.e. shot is wasted).
        if (attackStrength < unit.getShieldStrength() / 100) {
        } else if (attackStrength < unit.getShieldStrength()) {
            unit.setShieldStrength(unit.getShieldStrength() - attackStrength);
        } else {
            float hull = unit.getHullPlating() + unit.getShieldStrength() - (attackStrength);
            if (hull < 0) {
                hull = 0;
            }
            unit.setHullPlating(hull);
            unit.setShieldStrength(0);
            float initialHull = unit.getUnit().getHullPlating(tech);
            if (unit.getHullPlating() < 0.7f * initialHull && hull > 0) {
                //if(rand() % 100 >= 100.f * obj->Life / MaxLifes[DefferID][ZielTeam][obj->Type])
                if (random.nextInt(100) >= 100 * (unit.getHullPlating() / initialHull)) {
                    unit.setHullPlating(0);
                }
            }
        }
        int rapidFire;
        if ((rapidFire = this.unit.getRapidfire(unit.getUnit())) > 0) {
            double probabilityToShotAgain = 100 - (100.0 / rapidFire);
            if (probability(probabilityToShotAgain)) {
                return true;
            }
        }
        //0.85714287
        return false;
    }

    /**
     * Getter for hull plating.
     *
     * @return hull plating of unit.
     */
    public float getHullPlating() {
        return hullPlating;
    }

    /**
     * Setter for hull plating.
     *
     * @param hullPlating value of hull plating.
     */
    public void setHullPlating(float hullPlating) {
        this.hullPlating = hullPlating;
    }

    /**
     * Getter for shield strength.
     *
     * @return shield strength of unit.
     */
    public float getShieldStrength() {
        return shieldStrength;
    }

    /**
     * Setter for shield strength.
     *
     * @param shieldStrength value of shield strength.
     */
    public void setShieldStrength(float shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    /**
     * Getter for attack strength.
     *
     * @return attack strength of unit.
     */
    public float getAttackStrength() {
        return attackStrength;
    }

    /**
     * Setter for attack strength.
     *
     * @param attackStrength value of attack strength.
     */
    public void setAttackStrength(float attackStrength) {
        this.attackStrength = attackStrength;
    }

    /**
     * Getter for unit type.
     *
     * @return unit type.
     * @see UnitEnum
     */
    public UnitEnum getUnit() {
        return unit;
    }

    /**
     * This method return state of the unit.
     *
     * @return true if unit is destroyed.
     */
    public boolean isDestroyed() {
        return hullPlating <= 0;
    }

    /**
     * This method is used after each round to renew unit shield.
     *
     * @param tech technologies of unit.
     * @see BattleTechnologies
     */
    public void renewShield(BattleTechnologies tech) {
        shieldStrength = unit.getShieldStrength(tech);
    }

    @Override
    public String toString() {
        return hullPlating + " " + shieldStrength + " " + attackStrength;
    }

    @Override
    protected BattleUnit clone() {
        return new BattleUnit(unit, hullPlating, shieldStrength, attackStrength);
    }

    /**
     * This method is used to get probability of rapid fire.
     *
     * @param chance value of probability. Must be between 0.0 and 100.0
     */
    private boolean probability(double chance) {
        double r = random.nextDouble();
        return r * 100 <= (chance);
    }

}
