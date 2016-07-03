/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package enums;

/**
 *
 * @author Piotr
 */
public enum Player_Status_Enum {
    Neutral(0.5),
    Honorable(0.75),
    Bandit(1.0),;
    
    private double plunder_ratio;
    
    private Player_Status_Enum(double plunder_ratio) {
        this.plunder_ratio = plunder_ratio;
    }

    public double getPlunder_ratio() {
        return plunder_ratio;
    }
    
}
