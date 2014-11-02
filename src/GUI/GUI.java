
package GUI;

import Statistics.Statistics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import lang.GUI_Lang;
import Enums.Research_Enum;
import Enums.Side_Enum;
import simulation.Simulation;
import simulation.Battle_Technologies;
import Enums.Unit_Enum;
import utilities.IO_Utilities;
import utilities.Strings;

public class GUI extends JFrame{
    private Shipyard attacker_shipyard,defender_shipyard;
    private Defense defense;
    private Technology technology;
    private Clipboard clipboard;
    private Options options;
    private Result result;
    //private JPanel options = new JPanel();
    public GUI() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    private void initComponents(){
        try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPanel p = new ImagePanel(IO_Utilities.getImage("background.png"));
        p.setOpaque(true);
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        options = new Options();
        result = new Result();
        options.getSimulationStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        //options.setOpaque(false);
        attacker_shipyard = new Shipyard(GUI_Lang.getGUI().shipyard_of_argessor);
        defender_shipyard = new Shipyard(GUI_Lang.getGUI().shipyard_of_defender);
        defense = new Defense(GUI_Lang.getGUI().defense_of_defender);
        attacker_shipyard.get(Unit_Enum.Solar_Satellite).setEnabled(false);
        defense.get(Unit_Enum.Interplanetary_Missiles).setEnabled(false);
        technology = new Technology(GUI_Lang.getGUI().research);
        
        GroupLayout l = new GroupLayout(p);
        p.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createSequentialGroup()             
            .addGroup(l.createParallelGroup()
                .addComponent(attacker_shipyard)
                .addComponent(technology)
                .addComponent(result))
            .addGap(20)
            .addGroup(l.createParallelGroup()
                .addComponent(defender_shipyard)
                .addComponent(defense)
                .addComponent(options,GroupLayout.Alignment.CENTER)));    
        l.setVerticalGroup(l.createParallelGroup()             
            .addGroup(l.createSequentialGroup()
                .addComponent(attacker_shipyard)
                .addComponent(technology)
                .addComponent(result))
            .addGroup(l.createSequentialGroup()
                .addComponent(defender_shipyard)
                .addComponent(defense)
                .addComponent(options)));        
        
        
        attacker_shipyard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        defender_shipyard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        technology.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });        
        clipboard = new Clipboard();
        clipboard.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                for(Unit_Enum unit:Unit_Enum.values()){
                    UnitPanel s = defender_shipyard.get(unit);
                    UnitPanel d = defense.get(unit);
                    if(s!=null){                    
                        s.setNumber(""+clipboard.getUnits().getOrDefault(unit, 0));
                    }
                    if(d!=null){                    
                        d.setNumber(""+clipboard.getUnits().getOrDefault(unit, 0));
                    }
                }
                for(Research_Enum reserches:Research_Enum.values()){
                    UnitPanel t = technology.get(reserches,Technology.Defender_Side);
                    if(t!=null)
                        t.setNumber(""+clipboard.getResearches().getOrDefault(reserches, 0));
                    
                }
            }
        });        
        new Thread(clipboard).start();
    }
    
    private void Action(){
        new Worker(options.getNumberOfSimulations()).execute();
    }
    
    
    class Worker extends SwingWorker<Integer, Integer> {
        private List<Simulation> sims = new ArrayList<>();
        private int done=0;
        private final int repeat;
        private HashMap<Integer ,Statistics> stats;
        private int precision = 2;
        public Worker(int repeat) {
            stats = new HashMap<>();
            this.repeat = repeat;
            options.getProgressBar().setMaximum(repeat);
            options.getProgressBar().setValue(0);
        }
        
        
        @Override
        protected Integer doInBackground(){
            simulation.Battle_Technologies tech_agressor;
            simulation.Battle_Technologies tech_defender;
            int wt = technology.get(Research_Enum.Weapons_Technology, Technology.Attacker_Side).getNumber();
            int st =  technology.get(Research_Enum.Shielding_Technology, Technology.Attacker_Side).getNumber();
            int at = technology.get(Research_Enum.Armour_Technology, Technology.Attacker_Side).getNumber();
            int cd = technology.get(Research_Enum.Combustion_Drive, Technology.Attacker_Side).getNumber();
            int id = technology.get(Research_Enum.Impulse_Drive, Technology.Attacker_Side).getNumber();
            int hd = technology.get(Research_Enum.Hyperspace_Drive, Technology.Attacker_Side).getNumber();
            tech_agressor = new Battle_Technologies(wt, st, at, cd, id, hd);

            wt = technology.get(Research_Enum.Weapons_Technology, Technology.Defender_Side).getNumber();
            st = technology.get(Research_Enum.Shielding_Technology, Technology.Defender_Side).getNumber();
            at = technology.get(Research_Enum.Armour_Technology, Technology.Defender_Side).getNumber();
            tech_defender = new Battle_Technologies(wt, st, at);

            HashMap<Unit_Enum,Integer> units_attacker = new HashMap<>();
            HashMap<Unit_Enum,Integer> units_defender = new HashMap<>();

            for(Unit_Enum u:Unit_Enum.values()){
                UnitPanel a = attacker_shipyard.get(u);
                UnitPanel d = defender_shipyard.get(u);
                UnitPanel dd = defense.get(u);

                if(a!=null){
                    units_attacker.put(u, a.getNumber());
                }
                if(d!=null){
                    units_defender.put(u, d.getNumber());
                }
                if(dd!=null){
                    units_defender.put(u, dd.getNumber());
                }
            }

            ThreadGroup threads = new ThreadGroup("Simulations");
            long start = System.nanoTime();
            sims = new ArrayList<>();
            for(int i=0;i<repeat;i++){
                sims.add(new Simulation(units_attacker, units_defender, tech_agressor, tech_defender,threads,i));
                
            }
            int i=0; 
            int t = Runtime.getRuntime().availableProcessors();
            while(i<sims.size()){
                if(threads.activeCount()<t){
                    sims.get(i).start();
                    i++;       
                    if(!sims.get(done).isAlive()){
                        stats.put(done, sims.get(done).getStatistics());
                        done++;
                        publish(done);
                    }
                }
            }
            while(threads.activeCount()>0){                                
                if(!sims.get(done).isAlive()){
                    stats.put(done, sims.get(done).getStatistics());
                    done++;
                    publish(done);
                }
            };

            long end = System.nanoTime();
            System.out.printf("Simulation took %.2g seconds\n", (double)(end-start)/1e9);
            return 0;
        }

        @Override
        protected void process(List<Integer> chunks) { 
            publish_results();            
        }
        private void publish_results(){
            Statistics statistics = new Statistics(stats);           
            
            options.getProgressBar().setValue(done);
            HashMap<Side_Enum,Double> res = statistics.getResult();
            result.setWinner((int)Math.round(100*res.get(Side_Enum.Agressor)),(int)Math.round(100*res.get(Side_Enum.Defender)),(int)Math.round(100*res.get(Side_Enum.Remis)), 1);
            for(Unit_Enum u:Unit_Enum.values()){
                UnitPanel a = attacker_shipyard.get(u);
                UnitPanel d = defender_shipyard.get(u);
                UnitPanel dd = defense.get(u);

                if(a!=null){
                    a.setLabel(Strings.format(statistics.getAgressorStats().get(u),precision));
                }
                if(d!=null){
                    d.setLabel(Strings.format(statistics.getDefenderStats().get(u),precision));
                }
                if(dd!=null){
                    dd.setLabel(Strings.format(statistics.getDefenderStats().get(u),precision));
                }
            }            
        }

        @Override
        protected void done() { 
            done = repeat;
            for(int i=0;i<sims.size();i++){        
                stats.put(i, sims.get(i).getStatistics());
            }    
            publish_results();
        }
        
            
        
    }
    
        
}
