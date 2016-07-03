/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package simulation;

import enums.SideEnum;
import enums.UnitEnum;
import statistics.Statistics;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Piotr
 */
public class Optimization extends SimpleSimulationImpl {

    //initProps
    private int availableProcessors;
    private PropertyChangeListener listener = null;
    private SwingWorker<Integer, Integer> worker;
    private boolean isDone;
    private ThreadGroup threads;
    private final int sleepTime = 10;

    private int[] unitsEqualNumber;

    float metalRate, crystalRate, deuteriumRate;

    public Optimization() {
        super();
        threads = new ThreadGroup("Optimalization");
        availableProcessors = Runtime.getRuntime().availableProcessors();
        unitsEqualNumber = new int[UnitEnum.getFleetNumber()];
        metalRate = 3;
        crystalRate = 2;
        deuteriumRate = 1;
    }

    public void setProcessors(int processors) {
        this.availableProcessors = processors;
    }

    public int getProcessors() {
        return availableProcessors;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected int random(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public void setResourcesRate(float metal, float crystal, float deuterium) {
        this.metalRate = metal;
        this.crystalRate = crystal;
        this.deuteriumRate = deuterium;
    }

    private void getUnitsEqualNumber() {
        double maxCost = 0;
        int maxUnit = 0;
        double[] costs = new double[unitsEqualNumber.length];
        int i = 0;
        for (UnitEnum u : UnitEnum.values()) {
            if (u.isFleet()) {
                double cost = u.getMetal() / metalRate + u.getCrystal() / crystalRate + u.getDeuterium() / deuteriumRate;
                costs[i] = cost;
                if (cost > maxCost) {
                    maxUnit = i;
                    maxCost = cost;
                }
                i++;
            }
        }

        unitsEqualNumber[maxUnit] = 1;
        for (i = 0; i < costs.length; i++) {
            if (i != maxUnit) {
                unitsEqualNumber[i] = (int) Math.round(maxCost/costs[i]);
            }
        }
    }

    public void optimize() {
        //statistics = new statistics[SimulationCount];
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                SideEnum side = SideEnum.Agressor;
                isDone = false;
                int counter = 0;
                while (!isDone) {
                    UnitEnum unit = getBestUnit();
                    if (unit != null) {
                        allUnits[side.ordinal()][unit.ordinal()]++;
                        publish(counter);
                    }
                }
                return 0;
            }

            @Override
            protected void process(List<Integer> chunks) {
                super.process(chunks);
                //firePropertyChange("done", done - 1, done);
            }

        };
        if (listener != null) {
            worker.addPropertyChangeListener(listener);
        }
        worker.execute();
    }

    private UnitEnum getBestUnit() {
        Statistics[] bestUnitStatistics = new Statistics[UnitEnum.values().length];
        Statistics bestStats = new Statistics();
        for (UnitEnum unit : UnitEnum.values()) {
            if (threads.activeCount() < availableProcessors) {
                Thread thread = new Thread(threads, new Runnable() {
                    @Override
                    public void run() {
                        int[][] unitsCopy = arrayCopy(allUnits);
                        unitsCopy[SideEnum.Agressor.ordinal()][unit.ordinal()]++;
                        //Statistics stats = simulate(unitsCopy);
                        //bestUnitStatistics[unit.ordinal()] = stats;
                    }
                });
                thread.start();
            } else {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                }
            }
        }
        return UnitEnum.Deathstar;
    }

    private int[][] arrayCopy(int[][] array) {
        int[][] arrayCopy = new int[array.length][array[0].length];
        for (int i = 0; i < arrayCopy.length; i++) {
            for (int j = 0; j < arrayCopy[0].length; j++) {
                arrayCopy[i][j] = array[i][j];
            }
        }
        return arrayCopy;
    }

    @Override
    public Statistics simulate(int count) {
        return null;
    }
}
