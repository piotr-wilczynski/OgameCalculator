
package GUI;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import simulation.Unit_Enum;
import utilities.IO;

public class Defense extends ImagePanel{
    private UnitPanel[] defense;
    public Defense() {
        super(IO.getImage("Defense.jpg"));        
        initComponents();
    }
    private void initComponents(){
        JPanel defense_pane = new JPanel();
        defense_pane.setOpaque(false);
                
        defense = new UnitPanel[14];
        defense[0] = new UnitPanel(Unit_Enum.Rocket_Launcher);
        defense[1] = new UnitPanel(Unit_Enum.Light_Laser);
        defense[2] = new UnitPanel(Unit_Enum.Heavy_Laser);
        defense[3] = new UnitPanel(Unit_Enum.Gauss_Cannon);
        defense[4] = new UnitPanel(Unit_Enum.Ion_Cannon);
        defense[5] = new UnitPanel(Unit_Enum.Plasma_Turret);
        defense[6] = new UnitPanel(Unit_Enum.Small_Shield_Dome);
        defense[7] = new UnitPanel(Unit_Enum.Large_Shield_Dome);
        defense[8] = new UnitPanel(Unit_Enum.Anti_Ballistic_Missiles);
        defense[9] = new UnitPanel(Unit_Enum.Interplanetary_Missiles);
        
        GroupLayout l = new GroupLayout(defense_pane);
        defense_pane.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(defense[0])
                .addComponent(defense[1])
                .addComponent(defense[2])
                .addComponent(defense[3])
                .addComponent(defense[4])
                .addComponent(defense[5]))
            .addGroup(l.createSequentialGroup()
                .addComponent(defense[6])
                .addComponent(defense[7])
                .addComponent(defense[8])
                .addComponent(defense[9])));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(defense[0])
                .addComponent(defense[1])
                .addComponent(defense[2])
                .addComponent(defense[3])
                .addComponent(defense[4])
                .addComponent(defense[5]))
            .addGroup(l.createParallelGroup()
                .addComponent(defense[6])
                .addComponent(defense[7])
                .addComponent(defense[8])
                .addComponent(defense[9])));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         
        l = new GroupLayout(this);
        l.setAutoCreateGaps(true);
        l.setAutoCreateContainerGaps(true);
        setLayout(l);
        l.setHorizontalGroup(l.createSequentialGroup()
            .addComponent(defense_pane));
        l.setVerticalGroup(l.createSequentialGroup()
                .addGap(30)
                .addComponent(defense_pane)                 
         );
                 
    }
    
    public UnitPanel get(Unit_Enum unit){
        for(UnitPanel u:defense){
            if(u.getUnit().equals(unit))
                return u;
        }
        return null;
    }
    
}
