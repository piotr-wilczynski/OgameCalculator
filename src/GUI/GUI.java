
package GUI;

import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utilities.IO;

public class GUI extends JFrame{

    Fleet fleet = new Fleet();
    public GUI() {
        BufferedImage[] def = IO.getImageMatrix(IO.getImage("def.jpg"));
        JPanel p = new JPanel();
        //GridLayout gl = new GridLayout(0,1);
        //p.setLayout(gl);
        GroupLayout l = new GroupLayout(getContentPane());
        getContentPane().setLayout(l);
        l.setHorizontalGroup(l.createParallelGroup()
            .addComponent(fleet));
        l.setVerticalGroup(l.createSequentialGroup()
            .addComponent(fleet));
        
        /*
        int size = 40;
        for(int i=0;i<def.length-2;i++){
            JLabel l = new JLabel(new ImageIcon(def[i].getScaledInstance(size, size, Image.SCALE_SMOOTH)));
            p.add(l);
        }
                */
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
        
}
