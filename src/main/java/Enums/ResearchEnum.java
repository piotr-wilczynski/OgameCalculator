/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package enums;
public enum ResearchEnum {
    EnergyTechnology(113),
    LaserTechnology(120),
    IonTechnology(121),
    HyperspaceTechnology(114),
    PlasmaTechnology(122),
    CombustionDrive(115),
    ImpulseDrive(117),
    HyperspaceDrive(118),
    EspionageTechnology(106),
    ComputerTechnology(108),
    Astrophysics(124),
    IntergalacticResearchNetwork(123),
    GravitonTechnology(199),
    WeaponsTechnology(109),
    ShieldingTechnology(110),
    ArmourTechnology(111);    

    private final int localizationId;
    ResearchEnum(int localizationId) {
        this.localizationId = localizationId;
    }
    
    
    public int getLocalizationId() {
        return localizationId;
    }   
}
