
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Enums.Unit_Enum;
import utilities.IO_Utilities;

public class Shipyard extends ImagePanel{
    
    private UnitPanel[] ships;
    private JLabel label;
    public Shipyard(String text) {
        super(IO_Utilities.getImage("Shipyard.jpg"));
        label = new JLabel(text);
        initComponents();
    }
    
    private void initComponents(){
        JPanel Combat_ships = new JPanel();
        JPanel Civil_ships = new JPanel();
        Combat_ships.setOpaque(false);
        Civil_ships.setOpaque(false);
                
        label.setForeground(Color.WHITE);
        Font f = label.getFont();
        label.setBorder(new EmptyBorder(-1, -1,-1, -1));
        label.setFont(new Font(f.getName(), Font.BOLD, 26));
        
        ships = new UnitPanel[14];
        ships[0] = new UnitPanel(Unit_Enum.Light_Fighter);
        ships[1] = new UnitPanel(Unit_Enum.Heavy_Fighter);
        ships[2] = new UnitPanel(Unit_Enum.Cruiser);
        ships[3] = new UnitPanel(Unit_Enum.Battleship);
        ships[4] = new UnitPanel(Unit_Enum.Battlecruiser);
        ships[5] = new UnitPanel(Unit_Enum.Bomber);
        ships[6] = new UnitPanel(Unit_Enum.Destroyer);
        ships[7] = new UnitPanel(Unit_Enum.Deathstar);
        ships[8] = new UnitPanel(Unit_Enum.Small_Cargo);
        ships[9] = new UnitPanel(Unit_Enum.Large_Cargo);
        ships[10] = new UnitPanel(Unit_Enum.Colony_Ship);
        ships[11] = new UnitPanel(Unit_Enum.Recycler);
        ships[12] = new UnitPanel(Unit_Enum.Espionage_Probe);
        ships[13] = new UnitPanel(Unit_Enum.Solar_Satellite);        
        
        GroupLayout l = new GroupLayout(Combat_ships);
        Combat_ships.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(ships[0])
                .addComponent(ships[1])
                .addComponent(ships[2])
                .addComponent(ships[3]))
            .addGroup(l.createSequentialGroup()
                .addComponent(ships[4])
                .addComponent(ships[5])
                .addComponent(ships[6])
                .addComponent(ships[7])));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(ships[0])
                .addComponent(ships[1])
                .addComponent(ships[2])
                .addComponent(ships[3]))
            .addGroup(l.createParallelGroup()
                .addComponent(ships[4])
                .addComponent(ships[5])
                .addComponent(ships[6])
                .addComponent(ships[7])));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         
        l = new GroupLayout(Civil_ships);
        l.setAutoCreateGaps(true);
        l.setAutoCreateContainerGaps(true);
         Civil_ships.setLayout(l);
         
         l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(ships[8])
                .addComponent(ships[9])
                .addComponent(ships[10]))
            .addGroup(l.createSequentialGroup()
                .addComponent(ships[11])
                .addComponent(ships[12])
                .addComponent(ships[13])));
         l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(ships[8])
                .addComponent(ships[9])
                .addComponent(ships[10]))
            .addGroup(l.createParallelGroup()
                .addComponent(ships[11])
                .addComponent(ships[12])
                .addComponent(ships[13])));
         
        l = new GroupLayout(this);
        l.setAutoCreateGaps(true);
        setLayout(l);
        l.setHorizontalGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(l.createSequentialGroup()
                    .addComponent(Combat_ships)
                    .addComponent(Civil_ships)));
        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(label)
                .addGroup(
                    l.createParallelGroup()
                        .addComponent(Combat_ships)
                        .addComponent(Civil_ships))                 
         );
                 
    }
    
    public UnitPanel get(Unit_Enum unit){
        for(UnitPanel u:ships){
            if(((Unit_Enum)u.getObject()).equals(unit))
                return u;
        }
        return null;
    }
    
    public void addActionListener(ActionListener action){
        for(UnitPanel u:ships){
            u.addActionListener(action);
        }
    }
    
}
