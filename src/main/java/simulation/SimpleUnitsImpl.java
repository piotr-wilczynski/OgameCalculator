package simulation;

import enums.SideEnum;
import enums.UnitEnum;

import java.util.Optional;

/**
 * Created by Piotr Wilczyński on 03.07.2016.
 */
public class SimpleUnitsImpl implements Units {

	protected int[][] allUnits;
	protected BattleTechnologies[] techs;

	public SimpleUnitsImpl() {
		allUnits = new int[2][UnitEnum.values().length];
		for (int side = 0; side < allUnits.length; side++) {
			for (int units = 0; units < this.allUnits[side].length; units++) {
				this.allUnits[side][units] = 0;
			}
		}
		techs = new BattleTechnologies[2];
	}

	@Override
	public void setUnitNumber(SideEnum side, UnitEnum unit, int value) {
		allUnits[side.ordinal()][unit.ordinal()] = value;
	}

	@Override
	public int getUnitNumber(SideEnum side, UnitEnum unit) {
		return allUnits[side.ordinal()][unit.ordinal()];
	}

	@Override
	public void setTechnologies(SideEnum side, BattleTechnologies techs) {
		this.techs[side.ordinal()] = techs;
	}

	@Override
	public BattleTechnologies getTechnologies(SideEnum sideEnum) {
		return Optional.ofNullable(sideEnum)
				.map(side -> techs[side.ordinal()])
				.orElse(new BattleTechnologies());
	}

	@Override
	public int getUnitsNumber(SideEnum side) {
		int counter = 0;
		for (int[] sideUnits:allUnits) {
			for (int unitNumber:sideUnits) {
				counter += unitNumber;
			}			
		}
		return 0;
	}

}
