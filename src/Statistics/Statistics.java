
package Statistics;

import Enums.Side_Enum;
import java.util.HashMap;
import java.util.Map.Entry;
import simulation.BattleUnit;
import Enums.Unit_Enum;

public class Statistics {
    private HashMap<Unit_Enum,Double> map_agressor;
    private HashMap<Unit_Enum,Double> map_defender;
    private HashMap<Side_Enum,Double> result;
    public Statistics(BattleUnit[] units_agressor,BattleUnit[] units_defender,Side_Enum result) {
        map_agressor = new HashMap<>();
        map_defender = new HashMap<>();
        this.result = new HashMap<>();
        for(int i=0;i<units_agressor.length;i++){
            Unit_Enum unit = units_agressor[i].getUnit();
            if(map_agressor.containsKey(unit)){
                double number = map_agressor.get(unit);
                map_agressor.put(unit, number+1);
            }else{
                map_agressor.put(unit, 1.0);
            }
        }
        for(int i=0;i<units_defender.length;i++){
            Unit_Enum unit = units_defender[i].getUnit();
            if(map_defender.containsKey(unit)){
                double number = map_defender.get(unit);
                map_defender.put(unit, number+1);
            }else{
                map_defender.put(unit, 1.0);
            }
        } 
        for(Side_Enum side:Side_Enum.values()){
            this.result.put(side, 0.0);
        }
        this.result.put(result, 1.0);
    }
    
    public Statistics(HashMap<Integer ,Statistics> statistics){
        map_agressor = new HashMap<>();
        map_defender = new HashMap<>();
        result = new HashMap<>();
        int size = statistics.size();
        int in = 0;
        for (int i=0;i<size;i++) {
            if(statistics.get(i)!=null){
                for (Entry<Unit_Enum,Double> entry : statistics.get(i).getAgressorStats().entrySet()) {
                    if(map_agressor.containsKey(entry.getKey())){
                        double value = map_agressor.get(entry.getKey());
                        map_agressor.put(entry.getKey(), value+entry.getValue());
                    }else{
                        map_agressor.put(entry.getKey(), entry.getValue());                    
                    }                    
                }
                for (Entry<Unit_Enum,Double> entry : statistics.get(i).getDefenderStats().entrySet()) {
                    if(map_defender.containsKey(entry.getKey())){
                        double value = map_defender.get(entry.getKey());
                        map_defender.put(entry.getKey(), value+entry.getValue());
                    }else{
                        map_defender.put(entry.getKey(), entry.getValue());                    
                    }                    
                }
                for (Entry<Side_Enum,Double> entry : statistics.get(i).getResult().entrySet()) {
                    if(result.containsKey(entry.getKey())){
                        double value = result.get(entry.getKey());
                        result.put(entry.getKey(), value+entry.getValue());
                    }else{
                        result.put(entry.getKey(), entry.getValue());                    
                    }                    
                }
                in++;
            }
        }
        for(Unit_Enum u:Unit_Enum.values()){
            double number = (1.0*map_agressor.getOrDefault(u, 0.0))/in;
            map_agressor.put(u, number);
            
        }
        for(Unit_Enum u:Unit_Enum.values()){
            double number = (1.0*map_defender.getOrDefault(u, 0.0))/in;
            map_defender.put(u, number);            
        }
        for(Side_Enum side:Side_Enum.values()){
            double number = (1.0*result.getOrDefault(side, 0.0))/in;
            result.put(side, number);            
        }
        
    }
    
    public HashMap<Unit_Enum, Double> getAgressorStats() {
        return map_agressor;
    }

    public HashMap<Unit_Enum, Double> getDefenderStats() {
        return map_defender;
    }

    public HashMap<Side_Enum, Double> getResult() {
        return result;
    }
    
    
}
