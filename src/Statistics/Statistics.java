
package Statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import simulation.BattleUnit;
import simulation.Unit_Enum;

public class Statistics {
    private HashMap<Unit_Enum,Integer> map;

    public Statistics() {
        map = new HashMap<>();
    }
    
    public Statistics(BattleUnit[] units) {
        map = new HashMap<>();
        for(int i=0;i<units.length;i++){
            Unit_Enum unit = units[i].getUnit();
            if(map.containsKey(unit)){
                int number = map.get(unit);
                map.put(unit, number+1);
            }else{
                map.put(unit, 1);
            }
        } 
    }
    public void add(BattleUnit[] units){     
        for(int i=0;i<units.length;i++){
            Unit_Enum unit = units[i].getUnit();
            if(map.containsKey(unit)){
                int number = map.get(unit);
                map.put(unit, number+1);
            }else{
                map.put(unit, 1);
            }
        }                
    }

    @Override
    public String toString() {        
        String text="";
        for (Entry<Unit_Enum,Integer> entry : map.entrySet()) {
            text+=entry.getKey()+" "+entry.getValue()+"\n";
	}
        text+="\n";
        return text;
    }

    public HashMap<Unit_Enum, Integer> getStatistics() {
        return map;
    }
    
     public static HashMap<Unit_Enum, Double> getStats(HashMap<Integer, Statistics> list){
        HashMap<Unit_Enum, Integer> temp = new HashMap<>();
        int size = list.size();
        int in = 0;
        for (int i=0;i<size;i++) {
            if(list.get(i)!=null){
                for (Entry<Unit_Enum,Integer> entry : list.get(i).getStatistics().entrySet()) {
                    if(temp.containsKey(entry.getKey())){
                        int value = temp.get(entry.getKey());
                        temp.put(entry.getKey(), value+entry.getValue());
                    }else{
                        temp.put(entry.getKey(), entry.getValue());                    
                    }
                }
                in++;
            }
        }
        HashMap<Unit_Enum, Double> result = new HashMap<>();
        for(Unit_Enum u:Unit_Enum.values()){
            double number = (1.0*temp.getOrDefault(u, 0))/in;
            result.put(u, number);
            
        }
         //System.out.println(in);
        return result;
         
     }
    
    public static HashMap<Unit_Enum, Double> getStats(List<Statistics> list){
        HashMap<Unit_Enum, Integer> temp = new HashMap<>();
        int size = list.size();
        for(int i=0;i<size;i++){            
            for (Entry<Unit_Enum,Integer> entry : list.get(i).getStatistics().entrySet()) {
                if(temp.containsKey(entry.getKey())){
                    int value = temp.get(entry.getKey());
                    temp.put(entry.getKey(), value+entry.getValue());
                }else{
                    temp.put(entry.getKey(), entry.getValue());                    
                }
            }
        }
        HashMap<Unit_Enum, Double> result = new HashMap<>();
        for(Unit_Enum u:Unit_Enum.values()){
            double number = (1.0*temp.getOrDefault(u, 0))/size;
            result.put(u, number);
            
        }
        return result;
    }
    
}
