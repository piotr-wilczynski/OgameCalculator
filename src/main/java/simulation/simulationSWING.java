/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package simulation;

import statistics.Statistics;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Piotr
 */
public class SimulationSwing extends SimpleSimulationImpl {

	private int processors;
	private SwingWorker<Integer, Integer> worker;
	private Integer done;
	private PropertyChangeListener listener = null;

	public SimulationSwing() {
		super();
		processors = Runtime.getRuntime().availableProcessors();
	}

	public int getProcessors() {
		return processors;
	}

	public void setProcessors(int processors) {
		this.processors = processors;
	}

	public void setListener(PropertyChangeListener listener) {
		this.listener = listener;
	}

	@Override
	public int random(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public SwingWorker<Integer, Integer> getWorker() {
		return worker;
	}

	@Override
	public Statistics simulate(int SimulationCount) {
		statistics = new Statistics[SimulationCount];
		worker = new SwingWorker<Integer, Integer>() {
			@Override
			protected Integer doInBackground() throws Exception {
				ThreadGroup threads = new ThreadGroup("Simulations");
				int countDone = 0;
				int countStart = 0;
				done = 0;
				Object lock = new Object();
				long start = System.nanoTime();
				while (countDone < SimulationCount) {
					if (threads.activeCount() < processors && countStart < SimulationCount) {
						final int number = countStart;
						countStart += 1;
						Thread thread = new Thread(threads, () -> {
							statistics[number] = battle();
							synchronized (lock) {
								done++;
								publish(done);
							}
						}, "simulation nr=" + countStart);
						thread.setDaemon(true);
						thread.start();
					} else {
						if (statistics[countDone] != null) {
							countDone++;
						}
					}
				}
				long end = System.nanoTime();
				System.out.printf("simulation took %.2g seconds\n", (double) (end - start) / 1e9);
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
		while (!worker.isDone()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return getStatistics();
	}
}
