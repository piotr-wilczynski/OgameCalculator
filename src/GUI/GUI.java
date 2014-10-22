
package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import simulation.Unit_Enum;
import utilities.IO;

public class GUI extends JFrame{
    private Shipyard attacker_shipyard,defender_shipyard;
    private Defense defense;
    public GUI() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void initComponents(){
        JPanel p = new ImagePanel(IO.getImage("background.png"));
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        JPanel options = new JPanel();
        options.setOpaque(false);
        attacker_shipyard = new Shipyard();
        defender_shipyard = new Shipyard();
        defense = new Defense();
        attacker_shipyard.get(Unit_Enum.Solar_Satellite).setEnabled(false);
        
        GroupLayout l = new GroupLayout(p);
        p.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                    .addComponent(attacker_shipyard)             
                    .addGap(50)
                    .addComponent(defender_shipyard))
                .addGroup(l.createSequentialGroup()
                    .addComponent(options)         
                    .addGap(50)
                    .addComponent(defense))
                    );
        l.setVerticalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(attacker_shipyard)
                .addComponent(options))
            .addGroup(l.createSequentialGroup()
                .addComponent(defender_shipyard)
                .addComponent(defense)));
        
    }

    
    
        
}
