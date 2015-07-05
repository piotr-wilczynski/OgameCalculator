package Simulation;

import Enums.Side_Enum;
import Enums.Unit_Enum;
import Statistics.Statistics;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingWorker;

/**
 *
 * @author Piotr
 */
public class Optimization extends Simulation {

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
        unitsEqualNumber = new int[Unit_Enum.getFleetNumber()];
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
        for (Unit_Enum u : Unit_Enum.values()) {
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
        //statistics = new Statistics[SimulationCount];
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                Side_Enum side = Side_Enum.Agressor;
                isDone = false;
                int counter = 0;
                while (!isDone) {
                    Unit_Enum unit = getBestUnit();
                    if (unit != null) {
                        units[side.ordinal()][unit.ordinal()]++;
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

    private Unit_Enum getBestUnit() {
        Statistics[] bestUnitStatistics = new Statistics[Unit_Enum.values().length];
        Statistics bestStats = new Statistics();
        for (Unit_Enum unit : Unit_Enum.values()) {
            if (threads.activeCount() < availableProcessors) {
                Thread thread = new Thread(threads, new Runnable() {
                    @Override
                    public void run() {
                        int[][] unitsCopy = arrayCopy(units);
                        unitsCopy[Side_Enum.Agressor.ordinal()][unit.ordinal()]++;
                        Statistics stats = simulate(unitsCopy);
                        bestUnitStatistics[unit.ordinal()] = stats;
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
        return Unit_Enum.Deathstar;
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

}
