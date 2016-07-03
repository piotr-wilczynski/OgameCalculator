package simulation;

import enums.SideEnum;
import enums.UnitEnum;

public interface Units {

	void setUnitNumber(SideEnum side, UnitEnum unit, int number);

	int getUnitNumber(SideEnum side, UnitEnum unit);

	void setTechnologies(SideEnum side, BattleTechnologies techs);

	BattleTechnologies getTechnologies(SideEnum side);

	int getUnitsNumber(SideEnum side);

    default int getUnitsNumber(){
		return getUnitsNumber(SideEnum.Agressor) + getUnitsNumber(SideEnum.Defender);
	}
}
