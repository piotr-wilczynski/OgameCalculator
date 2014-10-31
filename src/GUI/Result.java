/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Enums.Resources_Enum;
import Enums.SideEnum;
import Enums.Unit_Enum;

/**
 *
 * @author Piotr
 */
public class Result extends JPanel{
    private JLabel 
            label_battle_place,battle_place,
            label_winner,winner,
            label_derbis,derbis,
            label_chance_for_moon,chance_for_moon,
            label_attacker_losses,attacker_losses,
            label_defender_losses,defender_losses,
            label_teoretical_plunder,teoretical_plunder,
            label_real_plunder,real_plunder,
            label_fuel,fuel,
            label_time,time;

    public Result() {
        initComponents();
        setBattlePlace("planeta","[0:0:0]");
        setWinner(SideEnum.Remis, 100, 1);
        setDerbis(0, 0);
        setChanceForMoon(0);
        setAttacker_losses(0, 0, 0);
        setDefender_losses(0, 0, 0);
        setTeoreticalPlunder(0, 0, 0, 0);
        setRealPlunder(0, 0, 0, 0);
        setFuel(0);
        setTime(7200,200000);
    }
    private void initComponents(){
        label_battle_place = new JLabel(lang.GUI_Lang.get("label_battle_place"));
        label_winner = new JLabel(lang.GUI_Lang.get("label_winner"));
        label_derbis = new JLabel(lang.GUI_Lang.get("label_derbis"));
        label_chance_for_moon = new JLabel(lang.GUI_Lang.get("label_chance_for_moon"));
        label_attacker_losses = new JLabel(lang.GUI_Lang.get("label_attacker_losses"));
        label_defender_losses = new JLabel(lang.GUI_Lang.get("label_defender_losses"));
        label_teoretical_plunder = new JLabel(lang.GUI_Lang.get("label_teoretical_plunder"));
        label_real_plunder = new JLabel(lang.GUI_Lang.get("label_real_plunder"));
        label_fuel = new JLabel(lang.GUI_Lang.get("label_fuel"));
        label_time = new JLabel(lang.GUI_Lang.get("label_time"));        
        
        battle_place = new JLabel();
        winner = new JLabel();
        derbis = new JLabel();
        chance_for_moon = new JLabel();
        attacker_losses = new JLabel();
        defender_losses = new JLabel();
        teoretical_plunder = new JLabel();
        real_plunder = new JLabel();
        fuel = new JLabel();
        time = new JLabel();
        
        Color font_color = Color.WHITE;
        label_battle_place.setForeground(font_color);
        label_winner.setForeground(font_color);
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
        derbis.setForeground(font_color);
        chance_for_moon.setForeground(font_color);
        attacker_losses.setForeground(font_color);
        defender_losses.setForeground(font_color);
        teoretical_plunder.setForeground(font_color);
        real_plunder.setForeground(font_color);
        fuel.setForeground(font_color);
        time.setForeground(font_color);
        
        setOpaque(false);
        
        final int label_size = 200;
        GroupLayout l = new GroupLayout(this);
        setLayout(l);
        l.setHorizontalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(label_battle_place,label_size,label_size,label_size)
                .addComponent(label_winner,label_size,label_size,label_size)
                .addComponent(label_derbis,label_size,label_size,label_size)
                .addComponent(label_chance_for_moon,label_size,label_size,label_size)
                .addComponent(label_attacker_losses,label_size,label_size,label_size)
                .addComponent(label_defender_losses,label_size,label_size,label_size)
                .addComponent(label_teoretical_plunder,label_size,label_size,label_size)
                .addComponent(label_real_plunder,label_size,label_size,label_size)
                .addComponent(label_fuel,label_size,label_size,label_size)
                .addComponent(label_time,label_size,label_size,label_size))
            .addGroup(l.createParallelGroup()
                .addComponent(battle_place)
                .addComponent(winner)
                .addComponent(derbis)
                .addComponent(chance_for_moon)
                .addComponent(attacker_losses)
                .addComponent(defender_losses)
                .addComponent(teoretical_plunder)
                .addComponent(real_plunder)
                .addComponent(fuel)
                .addComponent(time)));
        l.setVerticalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(label_battle_place)
                .addComponent(label_winner)
                .addComponent(label_derbis)
                .addComponent(label_chance_for_moon)
                .addComponent(label_attacker_losses)
                .addComponent(label_defender_losses)
                .addComponent(label_teoretical_plunder)
                .addComponent(label_real_plunder)
                .addComponent(label_fuel)
                .addComponent(label_time))
            .addGroup(l.createSequentialGroup()
                .addComponent(battle_place)
                .addComponent(winner)
                .addComponent(derbis)
                .addComponent(chance_for_moon)
                .addComponent(attacker_losses)
                .addComponent(defender_losses)
                .addComponent(teoretical_plunder)
                .addComponent(real_plunder)
                .addComponent(fuel)
                .addComponent(time)));        
    }

    public void setBattlePlace(String planet_name,String coords) {
        battle_place.setText(String.format(lang.GUI_Lang.get("battle_place"), planet_name,coords));
    }

    public void setWinner(SideEnum side,int percent,int rounds) {
        winner.setText(String.format(lang.GUI_Lang.get("winner"), lang.GUI_Lang.get(side.name())+"("+percent+")",rounds));
    }    
    
    public void setDerbis(long Metal, long Crystal) {
        String text = 
                Metal+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Crystal+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name());
        double number = (1.0*(Metal+Crystal))/Unit_Enum.Recycler.getCargo_Capacity();
        derbis.setText(text + " (~"+(int)Math.ceil(number)+" "+lang.GUI_Lang.get(Unit_Enum.Recycler.name())+")");
    }
    
    public void setChanceForMoon(int percent) {
        chance_for_moon.setText(String.format(lang.GUI_Lang.get("chance_for_moon"), percent));
    }
    
    public void setAttacker_losses(long Metal, long Crystal, long Deuterium) {
        String text = 
                Metal+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Crystal+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Deuterium+" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        attacker_losses.setText(text);
    }
    
    public void setDefender_losses(long Metal, long Crystal, long Deuterium) {
        String text = 
                Metal+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Crystal+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Deuterium+" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        defender_losses.setText(text);
    }
    
    public void setTeoreticalPlunder(long Metal, long Crystal, long Deuterium,int percent) {
        String text = 
                Metal+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Crystal+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Deuterium+" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        teoretical_plunder.setText(String.format(lang.GUI_Lang.get("teoretical_plunder"),text ,percent));
    }
        
    public void setRealPlunder(long Metal, long Crystal, long Deuterium,int percent) {
        String text = 
                Metal+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Crystal+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Deuterium+" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        real_plunder.setText(String.format(lang.GUI_Lang.get("real_plunder"),text ,percent));
    }    
    
    public void setFuel(int Fuel){
        fuel.setText(String.format("%d "+lang.GUI_Lang.get(Enums.Resources_Enum.Deuterium.name()), Fuel));
    }

    public void setTime(int time,int time_recycler) {
        this.time.setText(String.format("%s(%s: %s)",getTime(time),lang.GUI_Lang.get(Unit_Enum.Recycler.name()),getTime(time_recycler)));
    }
    private String getTime(int time){
        int seconds = time%60;
        int minutes = ((time-seconds)%3600)/60;
        int hours = (time-minutes-seconds)/3600;
        String sseconds="",sminutes="",shours="";
        if(seconds<10)
            sseconds = "0";
        if(minutes<10)
            sminutes = "0";
        if(hours<10)
            shours = "0";
        return shours+hours+":"+sminutes+minutes+":"+sseconds+seconds+" h";
        
    }
    
    
}
