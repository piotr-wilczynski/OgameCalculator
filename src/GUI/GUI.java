
package GUI;

import Statistics.Statistics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import lang.GUI_Lang;
import simulation.Research_Enum;
import simulation.Simulation;
import simulation.Technologies;
import simulation.Unit_Enum;
import utilities.IO;
import utilities.Strings;

public class GUI extends JFrame{
    private Shipyard attacker_shipyard,defender_shipyard;
    private Defense defense;
    private Technology technology;
    //private JPanel options = new JPanel();
    public GUI() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    private void initComponents(){
        JPanel p = new ImagePanel(IO.getImage("background.png"));
        p.setOpaque(true);
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        JPanel options = new JPanel();
        JButton b = new JButton("przycisk");
        options.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        options.setOpaque(false);
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
        l.setHorizontalGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(l.createSequentialGroup()
                    .addComponent(attacker_shipyard)             
                    .addGap(20)
                    .addComponent(defender_shipyard))
                .addGroup(l.createSequentialGroup()
                    .addComponent(technology)         
                    .addGap(20)
                    .addComponent(defense))
                .addComponent(options));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(attacker_shipyard)
                .addComponent(defender_shipyard))
            .addGroup(l.createParallelGroup()
                .addComponent(technology)
                .addComponent(defense))
            .addComponent(options));        
        
        
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
    }
    
    private void Action(){
        //new Clipboard();
        new Worker(1000).execute();
    }
    
    
    class Worker extends SwingWorker<Integer, Integer> {
        private List<Simulation> sims = new ArrayList<>();
        private int done=0;
        private final int repeat;
        private HashMap<Integer ,Statistics> attacker_stat;
        private HashMap<Integer ,Statistics> defender_stat;
        private int precision = 2;
        public Worker(int repeat) {
            this.repeat = repeat;
        }
        
        
        @Override
        protected Integer doInBackground(){
            simulation.Technologies tech_agressor = new Technologies();
            simulation.Technologies tech_defender = new Technologies();;
            attacker_stat = new HashMap<>();
            defender_stat =  new HashMap<>();
            tech_agressor.setWeapons_Technology(technology.get(Research_Enum.Weapons_Technology, Technology.Attacker_Side).getNumber());
            tech_agressor.setShielding_Technology(technology.get(Research_Enum.Shielding_Technology, Technology.Attacker_Side).getNumber());
            tech_agressor.setArmour_Technology(technology.get(Research_Enum.Armour_Technology, Technology.Attacker_Side).getNumber());
            tech_agressor.setCombustion_Drive(technology.get(Research_Enum.Combustion_Drive, Technology.Attacker_Side).getNumber());
            tech_agressor.setImpulse_Drive(technology.get(Research_Enum.Impulse_Drive, Technology.Attacker_Side).getNumber());
            tech_agressor.setHyperspace_Drive(technology.get(Research_Enum.Hyperspace_Drive, Technology.Attacker_Side).getNumber());

            tech_defender.setWeapons_Technology(technology.get(Research_Enum.Weapons_Technology, Technology.Defender_Side).getNumber());
            tech_defender.setShielding_Technology(technology.get(Research_Enum.Shielding_Technology, Technology.Defender_Side).getNumber());
            tech_defender.setArmour_Technology(technology.get(Research_Enum.Armour_Technology, Technology.Defender_Side).getNumber());


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
                        attacker_stat.put(done, sims.get(done).getAttacker_Statistics());
                        defender_stat.put(done, sims.get(done).getDefender_Statistics());
                        done++;
                        publish(done);
                    }
                }
            }
            while(threads.activeCount()>0){                                
                if(!sims.get(done).isAlive()){
                    attacker_stat.put(done, sims.get(done).getAttacker_Statistics());
                    defender_stat.put(done, sims.get(done).getDefender_Statistics());
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
            HashMap<Unit_Enum,Double> result_agressor = Statistics.getStats(attacker_stat);
            HashMap<Unit_Enum,Double> result_defender = Statistics.getStats(defender_stat);

            for(Unit_Enum u:Unit_Enum.values()){
                UnitPanel a = attacker_shipyard.get(u);
                UnitPanel d = defender_shipyard.get(u);
                UnitPanel dd = defense.get(u);

                if(a!=null){
                    a.setLabel(Strings.format(result_agressor.get(u),precision));
                }
                if(d!=null){
                    d.setLabel(Strings.format(result_defender.get(u),precision));
                }
                if(dd!=null){
                    dd.setLabel(Strings.format(result_defender.get(u),precision));
                }
            }            
        }

        @Override
        protected void done() { 
            for(int i=0;i<sims.size();i++){                
                attacker_stat.put(i, sims.get(i).getAttacker_Statistics());
                defender_stat.put(i, sims.get(i).getDefender_Statistics());
            }    
            publish_results();
        }
        
            
        
    }
    
        
}
