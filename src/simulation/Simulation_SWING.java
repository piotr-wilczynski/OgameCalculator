/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import Statistics.Statistics;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingWorker;

/**
 *
 * @author Piotr
 */
public class Simulation_SWING extends Simulation {

    private int processors;
    private SwingWorker<Integer, Integer> worker;
    private Integer done;
    private PropertyChangeListener listener = null;

    public Simulation_SWING() {
        super();
        processors = Runtime.getRuntime().availableProcessors();
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public int getProcessors() {
        return processors;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public int random(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
    
    public void simulate(int SimulationCount) {
        statistics = new Statistics[SimulationCount];
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                ThreadGroup threads = new ThreadGroup("Simulations");
                int coutntDone = 0;
                int countStart = 0;
                done = 0;
                long start = System.nanoTime();
                while (coutntDone < SimulationCount) {
                    if (threads.activeCount() < processors && countStart < SimulationCount) {
                        final int number = countStart;
                        countStart += 1;
                        Thread thread = new Thread(threads, new Runnable() {
                            @Override
                            public void run() {
                                statistics[number] = simulate();
                                synchronized (done) {
                                    done++;
                                    publish(done);
                                }
                            }
                        }, "Simulation nr=" + countStart);
                        thread.setDaemon(true);
                        thread.start();
                    } else {
                        if (statistics[coutntDone] != null) {
                            coutntDone++;
                        }
                    }
                }
                long end = System.nanoTime();
                System.out.printf("Simulation took %.2g seconds\n", (double) (end - start) / 1e9);
                return done;
            }

            @Override
            protected void process(List<Integer> chunks) {
                super.process(chunks);
                firePropertyChange("done", done - 1, done);
            }

        };
        if (listener != null) {
            worker.addPropertyChangeListener(listener);
        }
        worker.execute();
    }

}
