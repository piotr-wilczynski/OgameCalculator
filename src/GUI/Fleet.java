
package GUI;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import utilities.IO;

public class Fleet extends JPanel{    

    public Fleet() {
        
        GridLayout gl = new GridLayout(0,1,0,0);
        setLayout(gl);     
        BufferedImage[] fleet = IO.getImageMatrix(IO.getImage("fleet.jpg"));        
        int size = 40;
        for(int i=0;i<fleet.length;i++){
            UnitPanel u = new UnitPanel("Text", fleet[i].getScaledInstance(size, size, Image.SCALE_SMOOTH));
            add(u);
            //add(l);
        }
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }
        
}
