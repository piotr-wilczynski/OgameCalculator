
package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import simulation.Unit_Enum;
import utilities.IO;

public class UnitPanel extends JPanel{
    private BufferedImage background;
    private final BufferedImage background2;
    private final BufferedImage images[];
    private JLabel label;
    private JTextField textfield;
    public UnitPanel(Unit_Enum unit) {  
        images = getImage(unit);
        background = images[0];
        background2 = IO.getImage("labels.gif");
        setMaximumSize(new Dimension(background.getWidth(), background.getHeight()));
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
        g.drawImage(background2, 0, 0, null);
    }
    
    

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }
    
    private JTextField text;
    
}
