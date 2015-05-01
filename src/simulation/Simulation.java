
package simulation;

import Enums.Side_Enum;
import Enums.Unit_Enum;
import Statistics.Statistics;
import java.util.HashMap;
import java.util.Random;

public class Simulation extends Thread{
    //private List<BattleUnit> attacker,defender;
    private final  HashMap<Unit_Enum,Integer> attacker,defender;
    private final Battle_Technologies attacker_tech,defender_tech;
    private Statistics statistics;
    private Random random;
    private Side_Enum win;
    

    public Simulation(HashMap<Unit_Enum,Integer> attacker, HashMap<Unit_Enum,Integer> defender,Battle_Technologies attacket_tech,Battle_Technologies defender_tech,ThreadGroup group,int number) {
        super(group, "Simulation nr="+number);
        this.attacker = attacker;
        this.defender = defender;
        this.attacker_tech = attacket_tech;
        this.defender_tech = defender_tech;
        random = new Random();
    }   
    
    
    @Override
    public void run(){
        BattleUnit[] a,d;
        int sum_a=0,sum_d=0;
        for(Unit_Enum unit:Unit_Enum.values()){
            sum_a += attacker.getOrDefault(unit, 0);
            sum_d += defender.getOrDefault(unit, 0);
        }
        a = new BattleUnit[sum_a];
        d = new BattleUnit[sum_d];
        sum_a=0;
        sum_d=0;
        for(Unit_Enum unit:Unit_Enum.values()){
            for(int i=0;i<attacker.getOrDefault(unit, 0);i++){
                a[sum_a] = new BattleUnit(unit, attacker_tech);
                sum_a++;
            }            
            for(int i=0;i<defender.getOrDefault(unit, 0);i++){
                d[sum_d] = new BattleUnit(unit, defender_tech);
                sum_d++;
            }
        }
        Single_Simulation(a, d);                
    }
    
    public Side_Enum getResult(){
        return win;
    }
    
    public Statistics getStatistics(){
        return statistics;
    }
    
    private void Single_Simulation(BattleUnit[] attacker,BattleUnit[] defender){  
        for(int i=0;i<6;i++){
            if(attacker.length==0||defender.length==0){
                break;
            }
            Attack_All(attacker, defender, defender_tech);
            Attack_All(defender, attacker, attacker_tech);   
            attacker = Clear_After_Round(attacker,attacker_tech);
            defender = Clear_After_Round(defender,defender_tech);            
        }
        if(defender.length==0&&attacker.length>0)
            win = Side_Enum.Agressor;
        else if(attacker.length==0&&defender.length>0)
            win = Side_Enum.Defender;
        else
            win = Side_Enum.Remis;
        statistics = new Statistics(attacker,defender,win);
        attacker = null;
        defender = null;
    }
    
    private void Attack_All(BattleUnit[] attacker, BattleUnit[] defender,Battle_Technologies defender_tech){ 
        for(int i=0;i<attacker.length;i++){
            int r = random.nextInt(defender.length);
            while(attacker[i].Fight(defender[r], defender_tech)){
                r = random.nextInt(defender.length);
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
