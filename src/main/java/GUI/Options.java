/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package gui;

import statistics.Coordinates;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.*;
public class Options extends JPanel{
    private static final long serialVersionUID = -1859367914279114426L;
    private JButton simulation_start,clear_location;
    private JProgressBar progress_bar;
    private JTextField simulation_number;
    private JComboBox<String> fleet_derbis,defense_derbis,fleet_speed,location;
    private JCheckBox clipboard_check;
    private JLabel label_fleet_derbis,label_defense_derbis,label_simulation_number,label_fleet_speed,label_location;

    private final String[] percents = new String[]{"100%","90%","80%","70%","60%","50%","40%","30%","20%","10%"};
    
    public Options() {
        setOpaque(false);
        initComponents();
    }
    
    
    private void initComponents(){
        simulation_start = new JButton(lang.GUI_Lang.get("simulation_start"));
        clear_location = new JButton("X");
        progress_bar = new JProgressBar(0,100);
        simulation_number = new JTextField("100");
        fleet_derbis = new JComboBox<>(percents);
        defense_derbis = new JComboBox<>(percents);
        fleet_speed = new JComboBox<>(percents);
        location = new JComboBox<>();
        clipboard_check = new JCheckBox(lang.GUI_Lang.get("clipboard_check"), false);
        label_fleet_derbis = new JLabel(lang.GUI_Lang.get("fleet_derbis"));
        label_defense_derbis = new JLabel(lang.GUI_Lang.get("defense_derbis"));
        label_simulation_number = new JLabel(lang.GUI_Lang.get("simulation_number"));
        label_fleet_speed = new JLabel(lang.GUI_Lang.get("fleet_speed"));
        label_location = new JLabel(lang.GUI_Lang.get("own_location"));
        
        label_location.setForeground(Color.WHITE);
        label_simulation_number.setForeground(Color.WHITE);
        label_fleet_speed.setForeground(Color.WHITE);
        location.setOpaque(false);
        location.setEditable(true);
        
        simulation_start.setMargin(new Insets(14, 14, 14, 14));
        clear_location.setMargin(new Insets(0, 0, 0, 0));
        simulation_number.setColumns(3);
        
        progress_bar.setStringPainted(true);
        progress_bar.setString("");
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        GroupLayout l = new GroupLayout(panel);
        panel.setLayout(l);
        
        l.setHorizontalGroup(
            l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(l.createSequentialGroup()
                .addContainerGap()
                .addGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER,false)
                    .addGroup(l.createSequentialGroup()
                        .addComponent(label_location)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_fleet_speed)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(progress_bar)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, l.createSequentialGroup()
                        .addComponent(clear_location, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fleet_speed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(label_simulation_number)
                        .addComponent(simulation_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simulation_start)
                .addContainerGap())
        );
        l.setVerticalGroup(
            l.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, l.createSequentialGroup()
                //.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_location)
                    .addComponent(label_fleet_speed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(simulation_start)
                    .addGroup(l.createSequentialGroup()
                        .addGroup(l.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simulation_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fleet_speed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_simulation_number)
                            .addComponent(clear_location))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progress_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        /*
        l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(label_location)
                .addComponent(label_fleet_speed))
            .addGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                    .addGroup(l.createSequentialGroup()
                        .addComponent(clear_location)
                        .addComponent(location)
                        .addComponent(fleet_speed)
                        .addComponent(label_simulation_number)
                        .addComponent(simulation_number,40,40,40))
                    .addComponent(progress_bar))
                .addComponent(simulation_start)));
        l.setVerticalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addGroup(l.createParallelGroup()
                    .addGroup(l.createSequentialGroup()
                        .addComponent(label_location)
                        .addGroup(l.createParallelGroup()
                            .addComponent(clear_location)
                            .addComponent(location)))
                    .addGroup(l.createSequentialGroup()
                        .addComponent(label_fleet_speed)
                        .addComponent(fleet_speed))
                    .addGroup(l.createParallelGroup()
                        .addComponent(label_simulation_number)
                        .addComponent(simulation_number)))
                .addComponent(progress_bar))
            .addComponent(simulation_start));
        */
        
        l = new GroupLayout(this);
        setLayout(l);
        l.setHorizontalGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(panel,GroupLayout.Alignment.CENTER));
        l.setVerticalGroup(l.createParallelGroup().addComponent(panel,GroupLayout.Alignment.CENTER));
        
    }

    public JProgressBar getProgressBar() {
        return progress_bar;
    }

    public JButton getSimulationStartButton() {
        return simulation_start;
    }
    
    public int getNumberOfSimulations(){
        String text = simulation_number.getText().replaceAll("[^0-9]", "");
        int number = 100;
        try{
            number = Integer.parseInt(text);
        }catch(NumberFormatException ex){
            number = 100;
            simulation_number.setText("100");
        }
        return number;
    }
    
    public Coordinates getCoordinates(){
        String text = (String) location.getSelectedItem();
        location.setSelectedItem("1:1:1");
        return new Coordinates(text);
    }
    public int getFleetSpeedPercent(){
        String text = (String) fleet_speed.getSelectedItem();
        int percent = 100;
        try{
            percent = Integer.parseInt(text.substring(0, text.length()-1));            
        }catch(NumberFormatException ex){
            percent = 100;
            fleet_speed.setSelectedItem(percents[0]);
        }
        return percent;
    }
    
    
}
