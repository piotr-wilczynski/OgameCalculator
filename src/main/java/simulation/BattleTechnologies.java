/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package simulation;

public class BattleTechnologies {
	private final int weaponsTechnology;
	private final int shieldingTechnology;
	private final int armourTechnology;

	private final int combustionDrive;
	private final int impulseDrive;
	private final int hyperspaceDrive;

	public BattleTechnologies(int weaponsTechnology, int shieldingTechnology, int armourTechnology, int combustionDrive, int impulseDrive, int hyperspaceDrive) {
		this.weaponsTechnology = weaponsTechnology;
		this.shieldingTechnology = shieldingTechnology;
		this.armourTechnology = armourTechnology;
		this.combustionDrive = combustionDrive;
		this.impulseDrive = impulseDrive;
		this.hyperspaceDrive = hyperspaceDrive;
	}

	public BattleTechnologies(int weaponsTechnology, int shieldingTechnology, int armourTechnology) {
		this(weaponsTechnology, shieldingTechnology, armourTechnology, 0, 0, 0);
	}

	public BattleTechnologies() {
		this(0, 0, 0, 0, 0, 0);
	}

	public int getWeaponsTechnology() {
		return weaponsTechnology;
	}

	public int getShieldingTechnology() {
		return shieldingTechnology;
	}

	public int getArmourTechnology() {
		return armourTechnology;
	}

	public int getCombustionDrive() {
		return combustionDrive;
	}

	public int getImpulseDrive() {
		return impulseDrive;
	}

	public int getHyperspaceDrive() {
		return hyperspaceDrive;
	}


}
