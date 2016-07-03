/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import enums.ResearchEnum;
import enums.UnitEnum;
import utilities.IO_Utilities;

public class UnitPanel extends JPanel{
    private static final long serialVersionUID = 7351637395493681985L;
    private final BufferedImage background2;
    private final BufferedImage images[];
    private BufferedImage background;
    private JLabel label;
    private JTextField textfield;
    private Object object;
    
    public UnitPanel(UnitEnum unit) {  
        this.object = unit;
        images = getImage(unit);
        background2 = IO_Utilities.getImage("labels.gif");  
        setToolTipText(lang.GUI_Lang.getUnit(unit));
        initComonents();
    }

    public UnitPanel(ResearchEnum research) {
        this.object = research;
        images = getImage(research);
        background2 = IO_Utilities.getImage("labels.gif");  
        initComonents();
    }
    
    public void addActionListener(ActionListener action){
        textfield.addActionListener(action);
    }
    
    
    private void initComonents(){
        background = images[0];             
        setOpaque(false);
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.TRAILING);
        label.setOpaque(false);
        label.setForeground(Color.RED);
        Font f = label.getFont();
        label.setFont(new java.awt.Font(f.getName(), Font.BOLD, 11));
        label.setBorder(new EmptyBorder(0, 5, 0, 5));
        setLayout(new BorderLayout());
        add(label,BorderLayout.PAGE_END);
        
        textfield = new JTextField("0");
        textfield.setOpaque(false);
        f = textfield.getFont();
        textfield.setFont(new java.awt.Font(f.getName(), Font.BOLD, 11));
        textfield.setBorder(new EmptyBorder(0, 5, 0, 5));
        textfield.setHorizontalAlignment(SwingConstants.TRAILING);
        textfield.setForeground(new Color(255,193,7));
        add(textfield,BorderLayout.PAGE_START);
        
        textfield.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if("0123456789".indexOf(e.getKeyChar())==-1){
                    e.consume();
                    String text = textfield.getText().replaceAll("[^0-9]", "");
                    if(text.length()>0){
                        textfield.setText(""+Integer.parseInt(text));
                    }else
                        textfield.setText(text);
                }
            }
            
            
            
        });        
        
        setMaximumSize(new Dimension(background.getWidth(), background.getHeight()));
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(isEnabled()){
                    textfield.requestFocus();
                    textfield.setSelectionStart(0);
                    textfield.setSelectionEnd(textfield.getText().length());
                }
            }
            
        });
    }
    
    private  BufferedImage[] getImage(UnitEnum unit){
        BufferedImage[][]ships = IO_Utilities.getImageMatrix(IO_Utilities.getImage("ships.png"), 3, 14);
        BufferedImage[][]defense = IO_Utilities.getImageMatrix(IO_Utilities.getImage("defensive_structures.png"), 3, 11);
        switch(unit){
            case SmallCargo: return ships[0];
            case LargeCargo: return ships[1];
            case LightFighter: return ships[2];
            case HeavyFighter: return ships[3];
            case Cruiser: return ships[4];
            case Battleship: return ships[5];
            case ColonyShip: return ships[6];
            case Recycler: return ships[7];
            case EspionageProbe: return ships[8];
            case Bomber: return ships[9];
            case SolarSatellite: return ships[10];
            case Destroyer: return ships[11];
            case Deathstar: return ships[12];
            case Battlecruiser: return ships[13];
                
            case RocketLauncher: return defense[0];
            case LightLaser: return defense[1];
            case HeavyLaser: return defense[2];
            case GaussCannon: return defense[3];
            case IonCannon: return defense[4];
            case PlasmaTurret: return defense[5];
            case SmallShieldDome: return defense[6];
            case LargeShieldDome: return defense[7];
            case AntiBallisticMissiles: return defense[9];
            case InterplanetaryMissiles: return defense[10];
        }
        return null;
    }
    
    private BufferedImage[] getImage(ResearchEnum research){
        BufferedImage[][]researches = IO_Utilities.getImageMatrix(IO_Utilities.getImage("technologies.png"), 3, ResearchEnum.values().length);
        switch(research){
            case EspionageTechnology: return researches[0];
            case ComputerTechnology: return researches[1];
            case WeaponsTechnology: return researches[2];
            case ShieldingTechnology: return researches[3];
            case ArmourTechnology: return researches[4];
            case EnergyTechnology: return researches[5];
            case HyperspaceTechnology: return researches[6];
            case CombustionDrive: return researches[7];
            case ImpulseDrive: return researches[8];
            case HyperspaceDrive: return researches[9];
            case LaserTechnology: return researches[10];
            case IonTechnology: return researches[11];
            case PlasmaTechnology: return researches[12];
            case IntergalacticResearchNetwork: return researches[13];
            case GravitonTechnology: return researches[14];
            case Astrophysics: return researches[15];
        }       
        
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
        if(isEnabled()){
            if(label.getText().length()==0)
                g.drawImage(background2.getSubimage(0, 0, background2.getWidth(), background2.getHeight()/2), 0, 0, null);
                else
            g.drawImage(background2, 0, 0, null);
            
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if(enabled){
            background = images[0];
            textfield.setVisible(enabled);
            label.setEnabled(enabled);
        }else{
            background = images[2];
            textfield.setVisible(enabled);
            label.setVisible(enabled);
        }
        super.setEnabled(enabled);
        updateUI();
    }    
    
    public void setEdtable(boolean enabled){
        if(enabled){
            background = images[0];
            textfield.setEditable(enabled);
        }else{
            background = images[1];
            textfield.setEditable(enabled);
        }
        updateUI();
    }

    public Object getObject() {
        return object;
    }

    public int getNumber() {
        int i = 0;
        try{
            i = Integer.parseInt(textfield.getText());
        }catch(NumberFormatException ex){
            textfield.setText(""+0);
            return 0;
        }
        return i;
    }

    public void setNumber(String text) {
        this.textfield.setText(text);
    }
    
    public void setLabel(String text){
        label.setText(text);
        try{
            int field = Integer.parseInt(this.textfield.getText());
            double lab = Double.parseDouble(label.getText());
            if( field==lab  && field ==0){
                label.setText("");
            }
        }catch(NumberFormatException ex){
            
        }
    }
    
    
}
