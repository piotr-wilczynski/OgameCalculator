package simulation;

import enums.SideEnum;
import enums.UnitEnum;

import java.util.*;

public class MapUnitsImpl implements Units {

	protected Map<SideEnum, Map<UnitEnum, Integer>> units;

	private Map<SideEnum, BattleTechnologies> technologies;

	public MapUnitsImpl() {
		units = new EnumMap<>(SideEnum.class);
		technologies = new EnumMap<>(SideEnum.class);
	}

	@Override
	public void setUnitNumber(SideEnum side, UnitEnum unit, int number) {
		Map<UnitEnum, Integer> sideUnits = Optional.of(units)
				.map(allUnits -> allUnits.get(side))
				.orElse(new HashMap<>());
		sideUnits.put(unit, number);
		units.put(side, sideUnits);
	}

	@Override
	public int getUnitNumber(SideEnum side, UnitEnum unit) {
		return Optional.of(units)
				.map(allUnits -> allUnits.getOrDefault(side, Collections.emptyMap()))
				.map(sideUnits -> sideUnits.get(unit))
				.orElse(0);
	}

	@Override
	public void setTechnologies(SideEnum side, BattleTechnologies technologies) {
		this.technologies.put(side, technologies);
	}

	@Override
	public BattleTechnologies getTechnologies(SideEnum sideEnum) {
		return this.technologies.getOrDefault(sideEnum, new BattleTechnologies());
	}

	@Override
	public int getUnitsNumber(SideEnum side) {
		return units.getOrDefault(side, Collections.emptyMap()).entrySet().stream()
				.mapToInt(Map.Entry::getValue)
				.sum();
	}
}
