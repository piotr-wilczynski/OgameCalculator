/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import Statistics.Statistics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingWorker;

/**
 *
 * @author Piotr
 */
public class Simulation_SWING extends Simulation_2 {

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
    
    public void setListener(PropertyChangeListener listener){
        this.listener = listener;
    }

    @Override
    public int random(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    @Override
    public void simulate(int SimulationCount) {
        statistics = new Statistics[SimulationCount];
        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                ThreadGroup threads = new ThreadGroup("Simulations");
                int coutntDone = 0;
                int countStart = 0;
                done = 0;
                while (coutntDone < SimulationCount) {
                    if (threads.activeCount() < processors && countStart < SimulationCount) {
                        final int number = countStart;
                        countStart += 1;
                        Thread thread = new Thread(threads, new Runnable() {
                            @Override
                            public void run() {
                                statistics[number] = simulate();
                                synchronized(done){
                                    final int prev = done;
                                    done++;
                                    //pcs.firePropertyChange("Done", prev, done);
                                    publish(done);
                                }
                            }
                        }, "Simulation nr=" + countStart);
                        thread.start();
                    } else {
                        if (statistics[coutntDone] != null) {
                            coutntDone++;
                        }
                    }
                }
                long start = System.nanoTime();
                for (int i = 0; i < SimulationCount; i++) {
                    statistics[i] = simulate();
                }
                long end = System.nanoTime();
                System.out.printf("Simulation took %.2g seconds\n", (double) (end - start) / 1e9);
                return 0;
            }

            @Override
            protected void process(List<Integer> chunks) {
                super.process(chunks);
                System.out.println(done);
                firePropertyChange("done", done-1, done);
            }
            
            
        };
        if(listener!=null){
            worker.addPropertyChangeListener(listener);
        }
        worker.execute();
    }

}
