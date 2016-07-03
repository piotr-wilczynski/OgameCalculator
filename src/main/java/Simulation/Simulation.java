package simulation;

import statistics.Statistics;

/**
 * Created by Piotr Wilczyński on 03.07.2016.
 */
public interface Simulation {

	int MAX_NUMBER_OF_ROUNDS = 6;

	Statistics battle();

	Statistics simulate(int count);

	Statistics getStatistics();

}
