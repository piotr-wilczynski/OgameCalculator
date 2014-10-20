
package simulation;

import Statistics.Statistics;
import java.util.HashMap;
import java.util.Random;

public class Simulation extends Thread{
    //private List<BattleUnit> attacker,defender;
    private final  HashMap<Unit_Enum,Integer> attacker,defender;
    private final Technologies attacker_tech,defender_tech;
    private Statistics attacker_statistics;
    private Statistics defender_statistics;
    private Random random;
    

    public Simulation(HashMap<Unit_Enum,Integer> attacker, HashMap<Unit_Enum,Integer> defender,Technologies attacket_tech,Technologies defender_tech,ThreadGroup group,int number) {
        super(group, "Simulation nr="+number);
        this.attacker = attacker;
        this.defender = defender;
        this.attacker_tech = attacket_tech;
        this.defender_tech = defender_tech;
        attacker_statistics = new Statistics();
        defender_statistics = new Statistics();
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
        Single_Simulation2(a, d);         
    }
    
    public Statistics getAttacker_Statistics(){
        return attacker_statistics;
    }
    
    public Statistics getDefender_Statistics(){
        return defender_statistics;
    }
    
    private void Single_Simulation2(BattleUnit[] attacker,BattleUnit[] defender){  
        for(int i=0;i<6;i++){
            Attack_All2(attacker, defender, defender_tech);
            Attack_All2(defender, attacker, attacker_tech);   
            attacker = Clear_After_Round2(attacker,attacker_tech);
            defender = Clear_After_Round2(defender,defender_tech);
            if(attacker.length==0||defender.length==0)
                break;
        } 
        attacker_statistics.add(attacker);
        defender_statistics.add(defender);
        attacker = null;
        defender = null;
    }
    
    private void Attack_All2(BattleUnit[] attacker, BattleUnit[] defender,Technologies defender_tech){ 
        for(int i=0;i<attacker.length;i++){
            int r = random.nextInt(defender.length);
            while(attacker[i].Fight(defender[r], defender_tech)){
                r = random.nextInt(defender.length);
            }
        }        
    }   
    private BattleUnit[] Clear_After_Round2(BattleUnit[] units,Technologies tech){
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
