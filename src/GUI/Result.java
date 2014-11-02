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
import Enums.Side_Enum;
import Enums.Unit_Enum;
import Statistics.Coordinates;
import utilities.Strings;

/**
 *
 * @author Piotr
 */
public class Result extends JPanel{
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

    public Result() {
        initComponents();
        setBattlePlace("planeta",new Coordinates(1, 22, 3));
        setWinner(0,0,100, 1);
        setTacticalRetreat(29000.0, 8000.0);
        setDerbis(0, 0);
        setChanceForMoon(0);
        setAgressorLosses(0, 0, 0);
        setDefenderLosses(0, 0, 0);
        setTeoreticalPlunder(0, 0, 0, 0);
        setRealPlunder(0, 0, 0, 0);
        setFuel(0);
        setTime(7200,200000);
    }
    private void initComponents(){
        label_battle_place = new JLabel(lang.GUI_Lang.get("label_battle_place"));
        label_winner = new JLabel(lang.GUI_Lang.get("label_winner"));
        label_tactical_retreat = new JLabel(lang.GUI_Lang.get("label_tactical_retreat"));
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
        tactical_retreat = new JLabel();
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
        attacker_losses.setForeground(Color.red);
        defender_losses.setForeground(Color.red);
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
                .addComponent(battle_place)
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
        l.setVerticalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()
                .addComponent(label_battle_place)
                .addComponent(label_winner)
                .addComponent(label_tactical_retreat)
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
                .addComponent(tactical_retreat)
                .addComponent(derbis)
                .addComponent(chance_for_moon)
                .addComponent(attacker_losses)
                .addComponent(defender_losses)
                .addComponent(teoretical_plunder)
                .addComponent(real_plunder)
                .addComponent(fuel)
                .addComponent(time)));        
    }

    public void setBattlePlace(String planet_name,Coordinates coords) {
        battle_place.setText(String.format(lang.GUI_Lang.get("battle_place"), planet_name,coords));
    }

    public void setWinner(int agressor,int defender,int remis,int rounds) {
        String text="";
        String a = lang.GUI_Lang.get(Side_Enum.Agressor.name())+"("+agressor+"%) ";
        String d = lang.GUI_Lang.get(Side_Enum.Defender.name())+"("+defender+"%) ";
        String r = lang.GUI_Lang.get(Side_Enum.Remis.name())+"("+remis+"%) ";
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
        
        winner.setText(String.format(lang.GUI_Lang.get("winner"), text,rounds));
    }    

    public void setTacticalRetreat(Double Agressor,Double Defender) {
        
        String left = "",right="";
        if(Agressor>Defender){
            left = ""+Strings.format(1.0*Agressor/Defender);
            right="1";
        }else if(Defender>Agressor){
            left="1";
            right = ""+Strings.format(1.0*Defender/Agressor);            
        }else{
            left="1";
            right="1";
        }
        
        tactical_retreat.setText(String.format("<html>%s<span style=\"color:green;\"> %s:%s </span>%s</html>", 
                lang.GUI_Lang.get(Side_Enum.Agressor.name()),
                left,
                right,
                lang.GUI_Lang.get(Side_Enum.Defender.name())
                ));
    }
    
    public void setDerbis(long Metal, long Crystal) {
        String text = 
                Strings.format(Metal)+" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(Crystal)+" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name());
        double number = (1.0*(Metal+Crystal))/Unit_Enum.Recycler.getCargo_Capacity();
        derbis.setText(text + " (~"+(int)Math.ceil(number)+" "+lang.GUI_Lang.get(Unit_Enum.Recycler.name())+")");
    }
    
    public void setChanceForMoon(int percent) {
        chance_for_moon.setText(String.format(lang.GUI_Lang.get("chance_for_moon"), percent));
    }
    
    public void setAgressorLosses(long Metal, long Crystal, long Deuterium) {
        String text = 
                Strings.format(Metal)       +" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(Crystal)     +" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Strings.format(Deuterium)   +" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        attacker_losses.setText(text);
    }
    
    public void setDefenderLosses(long Metal, long Crystal, long Deuterium) {
        String text = 
                Strings.format(Metal)       +" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(Crystal)     +" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Strings.format(Deuterium)   +" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        defender_losses.setText(text);
    }
    
    public void setTeoreticalPlunder(long Metal, long Crystal, long Deuterium,int percent) {
        String text = 
                Strings.format(Metal)       +" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(Crystal)     +" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Strings.format(Deuterium)   +" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
        teoretical_plunder.setText(String.format(lang.GUI_Lang.get("teoretical_plunder"),text ,percent));
    }
        
    public void setRealPlunder(long Metal, long Crystal, long Deuterium,int percent) {
        String text = 
                Strings.format(Metal)       +" "+lang.GUI_Lang.get(Resources_Enum.Metal.name())+", "+
                Strings.format(Crystal)     +" "+lang.GUI_Lang.get(Resources_Enum.Crystal.name())+" i "+
                Strings.format(Deuterium)   +" "+lang.GUI_Lang.get(Resources_Enum.Deuterium.name());
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
