import Statistics.Statistics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import simulation.*;

public class main {
    public static void main(String[] args) {
        //List<BattleUnit> attacker = new ArrayList<>();
        //List<BattleUnit> defender = new ArrayList<>();
        HashMap<Unit_Enum,Integer> attacker = new HashMap<>();
        HashMap<Unit_Enum,Integer> defender = new HashMap<>();
        Technologies attacker_tech = new Technologies(1, 1, 1, 1, 1, 1);       
        Technologies defender_tech = new Technologies(1, 1, 1, 1, 1, 1);
        //attacker.put(Unit_Enum.Small_Cargo, 8);
        attacker.put(Unit_Enum.Cruiser, 100);
        defender.put(Unit_Enum.Light_Laser, 1000);
        //defender.put(Unit_Enum.Light_Laser, 8);
        
        ThreadGroup threads = new ThreadGroup("Simulations");
        long start = System.nanoTime();
        List<Simulation> sims = new ArrayList<>();
        for(int i=0;i<1000;i++)
            sims.add(new Simulation(attacker, defender, attacker_tech, defender_tech,threads,i));
        int i=0;        
        int t = Runtime.getRuntime().availableProcessors();
        while(i<sims.size()){
            if(threads.activeCount()<t){
                sims.get(i).start();
                i++;
                System.out.println(i);
            }
        }
        while(threads.activeCount()>0);
        List<Statistics> attacker_stat = new ArrayList<>();
        List<Statistics> defender_stat = new ArrayList<>();
        for(i=0;i<sims.size();i++){
            attacker_stat.add(sims.get(i).getAttacker_Statistics());
            defender_stat.add(sims.get(i).getDefender_Statistics());
        }
        Statistics.getStats(attacker_stat);
        Statistics.getStats(defender_stat);
        long end = System.nanoTime();
        System.out.printf("Simulation took %.2g seconds\n", (double)(end-start)/1e9);
    }
        
        
        
        
        
//new GUI.GUI();
    

    
}
