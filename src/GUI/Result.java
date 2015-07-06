/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUI;

import Enums.Player_Status_Enum;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Enums.Resources_Enum;
import Enums.Side_Enum;
import Enums.Unit_Enum;
import Statistics.Coordinates;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import Utilities.Strings;

/**
 *
 * @author Piotr
 */
public class Result extends JPanel{
    private static final long serialVersionUID = -1891145963751146404L;
    private JLabel 
            label_battle_place,battle_place,
            label_winner,winner,
            label_tactical_retreat,tactical_retreat,
            label_derbis,derbis,
            label_chance_for_moon,chance_for_moon,
            label_attacker_losses,attacker_losses,
            label_defender_losses,defender_losses,
            label_teoretical_plunder,teoretical_plunder,
            label_real_plunder,real_plunder,
            label_fuel,fuel,
            label_time,time;
    private JButton change_planet;
    private JComboBox<String> player_status;
    private Planet planet;

    public Result() {
        initComponents();
        setBattlePlace("Planeta",new Coordinates(1, 1, 1));
        setWinner(0,0,100, 1);
        setTacticalRetreat(new double[]{0.0,0.0});
        setDerbis(new long[]{0,0});
        setChanceForMoon(0);
        setLosses(Side_Enum.Agressor, new long[]{0,0,0});
        setLosses(Side_Enum.Defender, new long[]{0,0,0});
        setTeoreticalPlunder(new long[]{0,0,0}, Unit_Enum.Large_Cargo);
        setRealPlunder(new long[]{0,0,0}, new long[]{0,0,0});
        setFuel(0);
        setTime(0,0);
    }
    
    
    private void initComponents(){
        String[] values = new String[3];
        values[0] = Lang.GUI_Lang.get(Player_Status_Enum.Neutral.name());
        values[1] = Lang.GUI_Lang.get(Player_Status_Enum.Honorable.name());
        values[2] = Lang.GUI_Lang.get(Player_Status_Enum.Bandit.name());
        label_battle_place = new JLabel(Lang.GUI_Lang.get("label_battle_place"));
        label_winner = new JLabel(Lang.GUI_Lang.get("label_winner"));
        label_tactical_retreat = new JLabel(Lang.GUI_Lang.get("label_tactical_retreat"));
        label_derbis = new JLabel(Lang.GUI_Lang.get("label_derbis"));
        label_chance_for_moon = new JLabel(Lang.GUI_Lang.get("label_chance_for_moon"));
        label_attacker_losses = new JLabel(Lang.GUI_Lang.get("label_attacker_losses"));
        label_defender_losses = new JLabel(Lang.GUI_Lang.get("label_defender_losses"));
        label_teoretical_plunder = new JLabel(Lang.GUI_Lang.get("label_teoretical_plunder"));
        label_real_plunder = new JLabel(Lang.GUI_Lang.get("label_real_plunder"));
        label_fuel = new JLabel(Lang.GUI_Lang.get("label_fuel"));
        label_time = new JLabel(Lang.GUI_Lang.get("label_time"));        
        change_planet = new JButton(Lang.GUI_Lang.get("change_planet"));
        player_status = new JComboBox<String>(values);
        
        battle_place = new JLabel();
        winner = new JLabel();
        tactical_retreat = new JLabel();
        derbis = new JLabel();
        chance_for_moon = new JLabel();
        attacker_losses = new JLabel();
        defender_losses = new JLabel();
        teoretical_plunder = new JLabel();
        real_plunder = new JLabel();
        fuel = new JLabel();
        time = new JLabel();
        planet = new Planet();
        planet.setLocationRelativeTo(change_planet);
        
        change_planet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planet.setVisible(true);
            }
        });
        
        
        change_planet.setMargin(new Insets(0, 5, 0, 5));
        Color font_color = Color.WHITE;
        label_battle_place.setForeground(font_color);
        label_winner.setForeground(font_color);
        label_tactical_retreat.setForeground(font_color);
        label_derbis.setForeground(font_color);
        label_chance_for_moon.setForeground(font_color);
        label_attacker_losses.setForeground(font_color);
        label_defender_losses.setForeground(font_color);
        label_teoretical_plunder.setForeground(font_color);
        label_real_plunder.setForeground(font_color);
        label_fuel.setForeground(font_color);
        label_time.setForeground(font_color);
        
        battle_place.setForeground(font_color);
        winner.setForeground(font_color);
        tactical_retreat.setForeground(font_color);
        derbis.setForeground(font_color);
        chance_for_moon.setForeground(font_color);
        attacker_losses.setForeground(Color.RED);
        defender_losses.setForeground(Color.RED);
        teoretical_plunder.setForeground(font_color);
        real_plunder.setForeground(font_color);
        fuel.setForeground(Color.CYAN);
        time.setForeground(font_color);
        
        setOpaque(false);
        
        final int label_size = 200;
        GroupLayout l = new GroupLayout(this);
        setLayout(l);
        l.setHorizontalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(label_battle_place,label_size,label_size,label_size)
                .addComponent(label_winner,label_size,label_size,label_size)
                .addComponent(label_tactical_retreat,label_size,label_size,label_size)
                .addComponent(label_derbis,label_size,label_size,label_size)
                .addComponent(label_chance_for_moon,label_size,label_size,label_size)
                .addComponent(label_attacker_losses,label_size,label_size,label_size)
                .addComponent(label_defender_losses,label_size,label_size,label_size)
                .addComponent(label_teoretical_plunder,label_size,label_size,label_size)
                .addComponent(label_real_plunder,label_size,label_size,label_size)
                .addComponent(label_fuel,label_size,label_size,label_size)
                .addComponent(label_time,label_size,label_size,label_size))
            .addGroup(l.createParallelGroup()
                .addGroup(l.createSequentialGroup()
                    .addComponent(battle_place)
                    .addContainerGap(10, 200)
                    .addComponent(player_status,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, 10)
                    .addComponent(change_planet,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(winner)
                .addComponent(tactical_retreat)
                .addComponent(derbis)
                .addComponent(chance_for_moon)
                .addComponent(attacker_losses)
                .addComponent(defender_losses)
                .addComponent(teoretical_plunder)
                .addComponent(real_plunder)
                .addComponent(fuel)
                .addComponent(time)));
        
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(label_battle_place)
                .addComponent(battle_place)
                .addComponent(player_status)
                .addComponent(change_planet)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_winner)
                .addComponent(winner)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_tactical_retreat)
                .addComponent(tactical_retreat)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_derbis)
                .addComponent(derbis)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_chance_for_moon)
                .addComponent(chance_for_moon)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_attacker_losses)
                .addComponent(attacker_losses)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_defender_losses)
                .addComponent(defender_losses)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_teoretical_plunder)
                .addComponent(teoretical_plunder)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_real_plunder)
                .addComponent(real_plunder)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_fuel)
                .addComponent(fuel)
                )
            .addGroup(l.createParallelGroup()
                .addComponent(label_time)
                .addComponent(time)
                ));
    }

    public void setBattlePlace(String planet_name,Coordinates coords) {
        battle_place.setText(String.format(Lang.GUI_Lang.get("battle_place"), planet_name,coords));
    }

    public void setWinner(int agressor,int defender,int remis,int rounds) {
        String text="";
        String a = Lang.GUI_Lang.get(Side_Enum.Agressor.name())+"("+agressor+"%) ";
        String d = Lang.GUI_Lang.get(Side_Enum.Defender.name())+"("+defender+"%) ";
        String r = Lang.GUI_Lang.get(Side_Enum.Remis.name())+"("+remis+"%) ";
        if(agressor>=defender&&agressor>=remis){
            text+=a;
            if(defender>=remis&&defender>0){
                text+=d;
                if(remis>0)
                    text+=r;
            }else if(remis>defender&&remis>0){
                text+=r;
                if(defender>0){
                    text+=d;
                }
            }
        }else if(defender>=agressor&&defender>=remis){
            text+=d;
            if(agressor>=remis&&agressor>0){
                text+=a;
                if(remis>0)
                    text+=r;
            }else if(remis>agressor&&remis>0){
                text+=r;
                if(agressor>0)
                    text+=a;
            }
        }else if(remis>=agressor&&remis>=defender){
            text+=r;
            if(agressor>=defender&&agressor>0){
                text+=a;
                if(defender>0)
                    text+=d;
            }else if(defender>agressor&&defender>0){
                text+=d;
                if(agressor>0)
                    text+=a;
            }
            
        }
        
        winner.setText(String.format(Lang.GUI_Lang.get("winner"), text,rounds));
    }    

    public void setTacticalRetreat(double[] tacticalRetreat) {
        if(tacticalRetreat[Side_Enum.Agressor.ordinal()]==0)
            tacticalRetreat[Side_Enum.Agressor.ordinal()]=1.0;
        if(tacticalRetreat[Side_Enum.Defender.ordinal()]==0)
            tacticalRetreat[Side_Enum.Defender.ordinal()]=1.0;
        String left = "",right="";
        double min = Math.min(tacticalRetreat[Side_Enum.Agressor.ordinal()], tacticalRetreat[Side_Enum.Defender.ordinal()]);
        left = ""+Strings.format(tacticalRetreat[Side_Enum.Agressor.ordinal()]/min);
        right = ""+Strings.format(tacticalRetreat[Side_Enum.Defender.ordinal()]/min);
        tactical_retreat.setText(String.format("<html>%s<span style=\"color:green;\"> %s:%s </span>%s</html>", 
                Lang.GUI_Lang.get(Side_Enum.Agressor.name()),
                left,
                right,
                Lang.GUI_Lang.get(Side_Enum.Defender.name())
                ));
    }
    
    public void setDerbis(long[] derbis) {
        String text = 
                Strings.format(derbis[Resources_Enum.Metal.ordinal()])+" "+Lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(derbis[Resources_Enum.Crystal.ordinal()])+" "+Lang.GUI_Lang.get(Resources_Enum.Crystal.name());
        double number = (1.0*(derbis[Resources_Enum.Metal.ordinal()]+derbis[Resources_Enum.Crystal.ordinal()]))/Unit_Enum.Recycler.getCargo_Capacity();
        this.derbis.setText(text + " (~"+(int)Math.ceil(number)+" "+Lang.GUI_Lang.get(Unit_Enum.Recycler.name())+")");
    }
    
    public void setChanceForMoon(int percent) {
        chance_for_moon.setText(String.format(Lang.GUI_Lang.get("chance_for_moon"), percent));
    }
    
    public void setLosses(Side_Enum side,long[] resources){
        String text = 
                Strings.format(resources[Resources_Enum.Metal.ordinal()])       +" "+Lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(resources[Resources_Enum.Crystal.ordinal()])     +" "+Lang.GUI_Lang.get(Resources_Enum.Crystal.name())+", "+
                Strings.format(resources[Resources_Enum.Deuterium.ordinal()])   +" "+Lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        switch(side){
            case Agressor:{attacker_losses.setText(text);}break;
            case Defender:{defender_losses.setText(text);}break; 
        }
        
    }
    
    public void setTeoreticalPlunder(long[] resources, Unit_Enum unit) {
        String text = 
                Strings.format(resources[Resources_Enum.Metal.ordinal()])       +" "+Lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(resources[Resources_Enum.Crystal.ordinal()])     +" "+Lang.GUI_Lang.get(Resources_Enum.Crystal.name())+", "+
                Strings.format(resources[Resources_Enum.Deuterium.ordinal()])   +" "+Lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        double number = 1.0*(resources[Resources_Enum.Metal.ordinal()]+resources[Resources_Enum.Crystal.ordinal()]+resources[Resources_Enum.Deuterium.ordinal()])/unit.getCargo_Capacity();
        teoretical_plunder.setText(text + " (~"+(int)Math.ceil(number)+" "+Lang.GUI_Lang.get(unit.name())+")");
    }
        
    public void setRealPlunder(long[] realplund,long[] teorplund) {        
        long rsum = realplund[Resources_Enum.Metal.ordinal()]+realplund[Resources_Enum.Crystal.ordinal()]+realplund[Resources_Enum.Deuterium.ordinal()];
        long tsum = teorplund[Resources_Enum.Metal.ordinal()]+teorplund[Resources_Enum.Crystal.ordinal()]+teorplund[Resources_Enum.Deuterium.ordinal()];
        int percent = (int) (100.0 * (rsum) / (tsum));
        String text = 
                Strings.format(realplund[Resources_Enum.Metal.ordinal()])       +" "+Lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(realplund[Resources_Enum.Crystal.ordinal()])     +" "+Lang.GUI_Lang.get(Resources_Enum.Crystal.name())+", "+
                Strings.format(realplund[Resources_Enum.Deuterium.ordinal()])   +" "+Lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        real_plunder.setText(String.format(Lang.GUI_Lang.get("real_plunder"),text ,percent));
    }    
    
    public void setFuel(long Fuel){
        fuel.setText(String.format("%d "+Lang.GUI_Lang.get(Enums.Resources_Enum.Deuterium.name()), Fuel));
    }

    public void setTime(long time,long time_recycler) {
        this.time.setText(String.format("%s(%s: %s)",getTime(time),Lang.GUI_Lang.get(Unit_Enum.Recycler.name()),getTime(time_recycler)));
    }
    
    private String getTime(long time){
        long seconds = time%60;
        long minutes = ((time-seconds)%3600)/60;
        long hours = (time-minutes-seconds)/3600;
        String sseconds="",sminutes="",shours="";
        if(seconds<10)
            sseconds = "0";
        if(minutes<10)
            sminutes = "0";
        if(hours<10)
            shours = "0";
        return shours+hours+":"+sminutes+minutes+":"+sseconds+seconds+" h";
        
    }

    public Planet getPlanet() {
        return planet;
    }

    public JComboBox<String> getPlayer_status() {
        return player_status;
    }
    
    
    
    
    
}
