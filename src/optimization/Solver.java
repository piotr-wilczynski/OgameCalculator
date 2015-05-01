/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimization;

import Enums.Unit_Enum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.linear.SolutionCallback;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import simulation.Battle_Technologies;

/**
 *
 * @author Piotr
 */
public class Solver {

    public Solver(HashMap<Unit_Enum, Integer> attacker, Battle_Technologies attacket_tech, Battle_Technologies defender_tech) {

        int fleet = 0;
        for (Unit_Enum unit : Unit_Enum.values()) {
            if (unit.isFleet()) {
                fleet++;
            }
        }
        double[] tab = new double[fleet];
        double[] atack = new double[fleet];
        double[] defense = new double[fleet];
        int i=0;
        for (Unit_Enum unit : Unit_Enum.values()) {
            if (unit.isFleet()) {
                tab[i] = 0;//unit.getFuel_usage();
                atack[i] = unit.getAttack_Strength(defender_tech);
                defense[i] = unit.getHull_Plating(defender_tech)+unit.getShield_Strength(defender_tech);
                i++;
            }
        }

        long atack_value = 0;
        long defense_value = 0;
        for(Unit_Enum unit:Unit_Enum.values()){
            int v = attacker.getOrDefault(unit,0);
            atack_value+=unit.getAttack_Strength(attacket_tech)*v;
            defense_value += unit.getHull_Plating(attacket_tech)*v+unit.getShield_Strength(attacket_tech);
        }
        
        LinearObjectiveFunction f = new LinearObjectiveFunction(tab, 0);

        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(atack, Relationship.GEQ, defense_value));
        constraints.add(new LinearConstraint(defense, Relationship.GEQ, atack_value));
        SimplexSolver solver = new SimplexSolver();
        SolutionCallback callback = new SolutionCallback();
        PointValuePair solution = null;
        try {
            solution = solver.optimize(new MaxIter(100), f, new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true), callback);
        } catch (TooManyIterationsException ex) {

        }
        for (double d : solution.getPoint()) {
            System.out.print(d + " ");
        }
    }

}
