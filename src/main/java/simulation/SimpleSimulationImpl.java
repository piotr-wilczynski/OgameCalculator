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

public abstract class SimpleSimulationImpl extends SimpleUnitsImpl implements Simulation {

	protected Statistics[] statistics;

	@Override
	public Statistics getStatistics() {
		return new Statistics(statistics);
	}

	public int[][] getAllUnits() {
		return allUnits;
	}

	public void setAllUnits(int[][] allUnits) {
		this.allUnits = allUnits;
	}

	protected abstract int random(int bound);

	private BattleUnit[] getFlatUnits(SideEnum side){
		BattleUnit[] units;
		int unitCount = 0;

		for (UnitEnum unit : UnitEnum.values()) {
			unitCount += getUnitNumber(side, unit);
		}
		units = new BattleUnit[unitCount];
		int counter = 0;
		for (UnitEnum unit : UnitEnum.values()) {
			for (int i = 0; i < getUnitNumber(side, unit); i++) {
				units[counter] = new BattleUnit(unit, getTechnologies(SideEnum.Agressor));
				counter++;
			}
		}
		return units;
	}

	@Override
	public Statistics battle() {
		BattleUnit[] attackerUnits = getFlatUnits(SideEnum.Agressor);
		BattleUnit[] defenderUnits = getFlatUnits(SideEnum.Defender);
		SideEnum win;
		for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
			if (attackerUnits.length == 0 || defenderUnits.length == 0) {
				break;
			}
			attackAll(attackerUnits, defenderUnits, getTechnologies(SideEnum.Defender));
			attackAll(defenderUnits, attackerUnits, getTechnologies(SideEnum.Agressor));
			attackerUnits = clearAfterRound(attackerUnits, getTechnologies(SideEnum.Agressor));
			defenderUnits = clearAfterRound(defenderUnits, getTechnologies(SideEnum.Defender));
		}
		if (defenderUnits.length == 0 && attackerUnits.length > 0)
			win = SideEnum.Agressor;
		else if (attackerUnits.length == 0 && defenderUnits.length > 0)
			win = SideEnum.Defender;
		else
			win = SideEnum.Remis;
		return new Statistics(attackerUnits, defenderUnits, win);
	}


	private void attackAll(BattleUnit[] attacker, BattleUnit[] defender, BattleTechnologies defenderTech) {
		for (BattleUnit unit : attacker) {
			int r = random(defender.length);
			while (unit.fight(defender[r], defenderTech)) {
				r = random(defender.length);
			}
		}
	}

	private BattleUnit[] clearAfterRound(BattleUnit[] units, BattleTechnologies tech) {
		int counter = 0;
		for (BattleUnit unit : units) {
			if (!unit.isDestroyed()) {
				counter++;
			}
		}
		BattleUnit[] temp = new BattleUnit[counter];
		counter = 0;
		for (BattleUnit unit : units) {
			if (!unit.isDestroyed()) {
				unit.renewShield(tech);
				temp[counter] = unit;
				counter++;
			}
		}
		return temp;
	}

}
