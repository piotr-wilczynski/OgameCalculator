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

public class Defense extends ImagePanel{
    private static final long serialVersionUID = -4126305380843207028L;
    private UnitPanel[] defense;
    private JLabel label;
    public Defense(String text) {
        super(IO_Utilities.getImage("Defense.jpg")); 
        label = new JLabel(text);       
        initComponents();
    }
    private void initComponents(){
        JPanel defense_pane = new JPanel();        
        defense_pane.setOpaque(false);
        
        label.setForeground(Color.WHITE);
        Font f = label.getFont();
        label.setBorder(new EmptyBorder(-1, -1,-1, -1));
        label.setFont(new Font(f.getName(), Font.BOLD, 26));               
        
        defense = new UnitPanel[10];
        defense[0] = new UnitPanel(UnitEnum.RocketLauncher);
        defense[1] = new UnitPanel(UnitEnum.LightLaser);
        defense[2] = new UnitPanel(UnitEnum.HeavyLaser);
        defense[3] = new UnitPanel(UnitEnum.GaussCannon);
        defense[4] = new UnitPanel(UnitEnum.IonCannon);
        defense[5] = new UnitPanel(UnitEnum.PlasmaTurret);
        defense[6] = new UnitPanel(UnitEnum.SmallShieldDome);
        defense[7] = new UnitPanel(UnitEnum.LargeShieldDome);
        defense[8] = new UnitPanel(UnitEnum.AntiBallisticMissiles);
        defense[9] = new UnitPanel(UnitEnum.InterplanetaryMissiles);
        
        GroupLayout l = new GroupLayout(defense_pane);
        defense_pane.setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(defense[0])
                    .addGap(5,10,20)
                .addComponent(defense[1])
                    .addGap(5,10,20)
                .addComponent(defense[2])
                    .addGap(5,10,20)
                .addComponent(defense[3])
                    .addGap(5,10,20)
                .addComponent(defense[4])
                    .addGap(5,10,20)
                .addComponent(defense[5]))
            .addGroup(l.createSequentialGroup()
                .addComponent(defense[6])
                    .addGap(5,10,20)
                .addComponent(defense[7])
                    .addGap(5,10,20)
                .addComponent(defense[8])
                    .addGap(5,10,20)
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
        setLayout(l);
        l.setHorizontalGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(label)
            .addComponent(defense_pane));
        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(label)
                .addComponent(defense_pane)                 
         );
                 
    }
    
    public void addActionListener(ActionListener action){
        for(UnitPanel u:defense){
            u.addActionListener(action);
        }
    }
    
    public UnitPanel get(UnitEnum unit){
        for(UnitPanel u:defense){
            if(((UnitEnum)u.getObject()).equals(unit))
                return u;
        }
        return null;
    }
    
}
