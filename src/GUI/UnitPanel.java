
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import simulation.Unit_Enum;
import utilities.IO;

public class UnitPanel extends JPanel{
    private BufferedImage background;
    private final BufferedImage background2;
    private final BufferedImage images[];
    private JLabel label;
    private JTextField textfield;
    private Unit_Enum unit;
    public UnitPanel(Unit_Enum unit) {  
        this.unit = unit;
        images = getImage(unit);
        background2 = IO.getImage("labels.gif");  
        initComonents();
    }
    
    private void initComonents(){
        background = images[0];     
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.TRAILING);
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
        textfield.setHorizontalAlignment(textfield.TRAILING);
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
    
    private  BufferedImage[] getImage(Unit_Enum unit){
        BufferedImage[][]ships = IO.getImageMatrix(IO.getImage("ships.png"), 3, 14);
        BufferedImage[][]defense = IO.getImageMatrix(IO.getImage("defensive_structures.png"), 3, 11);
        switch(unit){
            case Small_Cargo: return ships[0];
            case Large_Cargo: return ships[1];
            case Light_Fighter: return ships[2];
            case Heavy_Fighter: return ships[3];
            case Cruiser: return ships[4];
            case Battleship: return ships[5];
            case Colony_Ship: return ships[6];
            case Recycler: return ships[7];
            case Espionage_Probe: return ships[8];
            case Bomber: return ships[9];
            case Solar_Satellite: return ships[10];
            case Destroyer: return ships[11];
            case Deathstar: return ships[12];
            case Battlecruiser: return ships[13];
                
            case Rocket_Launcher: return defense[0];
            case Light_Laser: return defense[1];
            case Heavy_Laser: return defense[2];
            case Gauss_Cannon: return defense[3];
            case Ion_Cannon: return defense[4];
            case Plasma_Turret: return defense[5];
            case Small_Shield_Dome: return defense[6];
            case Large_Shield_Dome: return defense[7];
            case Anti_Ballistic_Missiles: return defense[9];
            case Interplanetary_Missiles: return defense[10];
        }
        return ships[0];
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
        }else{
            background = images[2];
            textfield.setVisible(enabled);
        }
        super.setEnabled(enabled);
    }    

    public Unit_Enum getUnit() {
        return unit;
    }

    public int getNumber() {
        int i = 0;
        try{
            Integer.parseInt(textfield.getText());
        }catch(NumberFormatException ex){
            return 0;
        }
        return i;
    }

    public void setText(String text) {
        this.textfield.setText(text);
    }
    
    public void setLabel(String text){
        label.setText(text);
    }
    
    
}
