/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package Simulation;

import Enums.*;
import Statistics.Statistics;

public abstract class Simulation {
    protected int[][] units;
    protected Battle_Technologies[] techs;
    protected Statistics[] statistics;
            
    public Simulation() {
        units = new int[2][Unit_Enum.values().length];
        for(int i=0;i<units.length;i++){
            for(int j=0;j<units[i].length;j++){
                units[i][j] = 0;
            }
        }
        techs = new Battle_Technologies[2];
    }
    
    public void setUnit(Side_Enum side,Unit_Enum unit,int value){
        units[side.ordinal()][unit.ordinal()] = value;
    }
    
    public int getUnit(Side_Enum side,Unit_Enum unit){
        return units[side.ordinal()][unit.ordinal()];
    }
    
    public void setUnits(int[][] units){
        this.units = units;
    }

    public int[][] getUnits() {
        return units;
    }    
    
    public void setTechnologies(Side_Enum side,Battle_Technologies techs){
        this.techs[side.ordinal()] = techs;
    }
    
    public Battle_Technologies getTechnologies(Side_Enum side){
        Battle_Technologies techs = this.techs[side.ordinal()];
        if(techs==null)
            return new Battle_Technologies();
        else 
            return techs;
    }     

    public Statistics getStatistics() {
        return new Statistics(statistics);
    }    
    
    protected abstract int random(int bound);
    
    protected Statistics simulate(int[][] units){        
        BattleUnit[] a,d;
        int sum_a=0,sum_d=0;
        for(Unit_Enum unit:Unit_Enum.values()){
            sum_a += getUnit(Side_Enum.Agressor, unit);
            sum_d += getUnit(Side_Enum.Defender, unit);
        }
        a = new BattleUnit[sum_a];
        d = new BattleUnit[sum_d];
        sum_a=0;
        sum_d=0;
        for(Unit_Enum unit:Unit_Enum.values()){
            for(int i=0;i<units[Side_Enum.Agressor.ordinal()][unit.ordinal()];i++){
                a[sum_a] = new BattleUnit(unit, getTechnologies(Side_Enum.Agressor));
                sum_a++;
            }            
            for(int i=0;i<units[Side_Enum.Defender.ordinal()][unit.ordinal()];i++){
                d[sum_d] = new BattleUnit(unit, getTechnologies(Side_Enum.Defender));
                sum_d++;
            }
        }
        return Battle(a, d);        
    }
    
    protected Statistics simulate(){        
        return simulate(units);
    }
    
    private Statistics Battle(BattleUnit[] attacker,BattleUnit[] defender){  
        Side_Enum win;
        for(int i=0;i<6;i++){
            if(attacker.length==0||defender.length==0){
                break;
            }
            Attack_All(attacker, defender, getTechnologies(Side_Enum.Defender));
            Attack_All(defender, attacker, getTechnologies(Side_Enum.Agressor));   
            attacker = Clear_After_Round(attacker,getTechnologies(Side_Enum.Agressor));
            defender = Clear_After_Round(defender,getTechnologies(Side_Enum.Defender));            
        }
        if(defender.length==0&&attacker.length>0)
            win = Side_Enum.Agressor;
        else if(attacker.length==0&&defender.length>0)
            win = Side_Enum.Defender;
        else
            win = Side_Enum.Remis;
        return new Statistics(attacker,defender,win);
    }
    
    
    private void Attack_All(BattleUnit[] attacker, BattleUnit[] defender,Battle_Technologies defender_tech){ 
        for(int i=0;i<attacker.length;i++){
            int r = random(defender.length);
            while(attacker[i].Fight(defender[r], defender_tech)){
                r = random(defender.length);
            }
        }        
    }   
    private BattleUnit[] Clear_After_Round(BattleUnit[] units,Battle_Technologies tech){
        int counter = 0;
        for(int i=0;i<units.length;i++){
            if(!units[i].isDestroyed()){
                counter++;
            }
        }
        BattleUnit[] temp = new BattleUnit[counter];
        counter = 0;
        for(int i=0;i<units.length;i++){
            if(!units[i].isDestroyed()){
                units[i].renewShield(tech);
                temp[counter] = units[i];
                counter++;
            }
        }
        return temp;
    }      
    
    
}
