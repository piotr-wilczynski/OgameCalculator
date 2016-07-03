package simulation;

import enums.SideEnum;
import enums.UnitEnum;
import statistics.Statistics;

import java.time.Duration;
import java.time.Instant;
public class SimulationTest {
	private static final int COUNT = 10000;

	private SimpleSimulationImpl simulation;

	private MapSimulationImpl mapSimulation;

	@org.junit.Before
	public void setUp() throws Exception {
		simulation = new SimulationSwing();
		mapSimulation = new MapSimulationImpl();
		simulation.setUnitNumber(SideEnum.Agressor, UnitEnum.Cruiser, 2600);
		mapSimulation.setUnitNumber(SideEnum.Agressor, UnitEnum.Cruiser, 2600);
		simulation.setUnitNumber(SideEnum.Defender, UnitEnum.Battleship, 1000);
		mapSimulation.setUnitNumber(SideEnum.Defender, UnitEnum.Battleship, 1000);
	}

	@org.junit.Test
	public void simulate() throws Exception {
		Instant before = Instant.now();
		Statistics statistics = simulation.simulate(COUNT);
		Instant after = Instant.now();
		System.out.println("Simple: " + Duration.between(before, after).toMillis() + " result= " + statistics.getResult(SideEnum.Agressor));
		before = Instant.now();
		statistics = mapSimulation.simulate(COUNT);
		after = Instant.now();
		System.out.println("Map: " + Duration.between(before, after).toMillis() + " result= " + statistics.getResult(SideEnum.Agressor));
	}

}