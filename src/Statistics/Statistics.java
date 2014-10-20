
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
    public void add(List<BattleUnit> units){     
        for(int i=0;i<units.size();i++){
            Unit_Enum unit = units.get(i).getUnit();
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
    
    
    public static String getStats(List<Statistics> list){
        HashMap<Unit_Enum, Integer> temp = new HashMap<>();
        int size = list.size();
        for(int i=0;i<list.size();i++){            
            for (Entry<Unit_Enum,Integer> entry : list.get(i).getStatistics().entrySet()) {
                //System.out.println(i+":"+entry.getKey()+" "+(entry.getValue()));
                if(temp.containsKey(entry.getKey())){
                    int value = temp.get(entry.getKey());
                    temp.put(entry.getKey(), value+entry.getValue());
                }else{
                    temp.put(entry.getKey(), entry.getValue());                    
                }
            }
        }
        
        for (Entry<Unit_Enum,Integer> entry : temp.entrySet()) {
            System.out.println(entry.getKey()+" "+(1.0*entry.getValue())/size);
        }
        return "";
    }
    
}
