
package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.IO;

public class GUI extends JFrame{
    Shipyard attacker_shipyard,defender_shipyard;
    Fleet fleet = new Fleet();
    public GUI() {
        //BufferedImage[] def = IO.getImageMatrix(IO.getImage("def.jpg"));
        //pack();
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void initComponents(){
        JPanel p = new ImagePanel(IO.getImage("background.png"));
        getContentPane().add(p);
        this.setSize(p.getMaximumSize());
        
        attacker_shipyard = new Shipyard();
        defender_shipyard = new Shipyard();
        
        GroupLayout l = new GroupLayout(p);
        p.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                    .addComponent(attacker_shipyard)             
                    .addGap(50)
                    .addComponent(defender_shipyard))
                    );
        l.setVerticalGroup(l.createSequentialGroup()
                .addGap(50)
                .addGroup(l.createParallelGroup()
                    .addComponent(attacker_shipyard)
                    .addComponent(defender_shipyard))
                .addGroup(l.createParallelGroup())
                    );
        
    }

    
    
        
}
