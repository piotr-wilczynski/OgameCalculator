package simulation;

import enums.SideEnum;
import enums.UnitEnum;
import statistics.Statistics;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapSimulationImpl extends MapUnitsImpl implements Simulation {

	public MapSimulationImpl() {
	}

	@Override
	public Statistics battle() {
		List<BattleUnit> attackerUnits = getFlatUnits(SideEnum.Agressor);
		List<BattleUnit> defenderUnits = getFlatUnits(SideEnum.Defender);
		SideEnum win;
		for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
			if (attackerUnits.isEmpty() || defenderUnits.isEmpty()) {
				break;
			}
			attackAll(attackerUnits, defenderUnits, getTechnologies(SideEnum.Defender));
			attackAll(defenderUnits, attackerUnits, getTechnologies(SideEnum.Agressor));
			attackerUnits = clearAfterRound(attackerUnits, getTechnologies(SideEnum.Agressor));
			defenderUnits = clearAfterRound(defenderUnits, getTechnologies(SideEnum.Defender));
		}
		if (defenderUnits.isEmpty() && !attackerUnits.isEmpty())
			win = SideEnum.Agressor;
		else if (attackerUnits.isEmpty() && !defenderUnits.isEmpty())
			win = SideEnum.Defender;
		else
			win = SideEnum.Remis;
		return new Statistics(attackerUnits, defenderUnits, win);
	}

	@Override
	public Statistics simulate(int count) {
		Stream<Statistics> statisticsStream = Stream.generate(this::battle);

		if (turnParallel(count)) {
			statisticsStream = statisticsStream.parallel();
		}

		Collection<Statistics> statistics = statisticsStream
				.limit(count)
				.collect(Collectors.toList());

		return new Statistics(statistics);
	}

	private boolean turnParallel(int count) {
		boolean result = false;
		if (getUnitsNumber() * count > 1_000_000) {
			result = true;
		}
		return result;
	}

	@Override
	public Statistics getStatistics() {
		return null;
	}

	private List<BattleUnit> getFlatUnits(SideEnum side) {
		return units.getOrDefault(side, Collections.emptyMap()).entrySet().stream()
				.map(entry -> createUnits(side, entry.getKey(), entry.getValue()))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	private List<BattleUnit> createUnits(SideEnum side, UnitEnum unit, int count) {
		return Stream.generate(() -> new BattleUnit(unit, getTechnologies(side)))
				.limit(count)
				.collect(Collectors.toList());
	}

	private void attackAll(List<BattleUnit> attacker, List<BattleUnit> defender, BattleTechnologies defenderTech) {
		attacker.stream().forEach(battleUnit -> {
			BattleUnit unitToAttack;
			do {
				unitToAttack = defender.get(random(defender.size()));
			} while (battleUnit.fight(unitToAttack, defenderTech));
		});
	}

	private int random(int length) {
		return ThreadLocalRandom.current().nextInt(length);
	}

	private List<BattleUnit> clearAfterRound(Collection<BattleUnit> units, BattleTechnologies tech) {
		return units.stream()
				.filter(unit -> !unit.isDestroyed())
				.peek(unit -> unit.renewShield(tech))
				.collect(Collectors.toList());
	}
}
