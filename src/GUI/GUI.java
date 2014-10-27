
package GUI;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lang.GUI_Lang;
import simulation.Unit_Enum;
import utilities.IO;

public class GUI extends JFrame{
    private Shipyard attacker_shipyard,defender_shipyard;
    private Defense defense;
    private Technology technology;
    public GUI() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void initComponents(){
        JPanel p = new ImagePanel(IO.getImage("background.png"));
        p.setOpaque(true);
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        JPanel options = new JPanel();
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
                    );
        l.setVerticalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(attacker_shipyard)
                .addComponent(technology))
            .addGroup(l.createSequentialGroup()
                .addComponent(defender_shipyard)
                .addComponent(defense)));
        
    }

    
    
        
}
