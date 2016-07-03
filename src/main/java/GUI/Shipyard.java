/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import enums.UnitEnum;
import utilities.IO_Utilities;

public class Shipyard extends ImagePanel{
    private static final long serialVersionUID = 151794423153413346L;
    
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
        ships[0] = new UnitPanel(UnitEnum.LightFighter);
        ships[1] = new UnitPanel(UnitEnum.HeavyFighter);
        ships[2] = new UnitPanel(UnitEnum.Cruiser);
        ships[3] = new UnitPanel(UnitEnum.Battleship);
        ships[4] = new UnitPanel(UnitEnum.Battlecruiser);
        ships[5] = new UnitPanel(UnitEnum.Bomber);
        ships[6] = new UnitPanel(UnitEnum.Destroyer);
        ships[7] = new UnitPanel(UnitEnum.Deathstar);
        ships[8] = new UnitPanel(UnitEnum.SmallCargo);
        ships[9] = new UnitPanel(UnitEnum.LargeCargo);
        ships[10] = new UnitPanel(UnitEnum.ColonyShip);
        ships[11] = new UnitPanel(UnitEnum.Recycler);
        ships[12] = new UnitPanel(UnitEnum.EspionageProbe);
        ships[13] = new UnitPanel(UnitEnum.SolarSatellite);        
        
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
    
    public UnitPanel get(UnitEnum unit){
        for(UnitPanel u:ships){
            if(u.getObject().equals(unit))
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
