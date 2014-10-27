package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import simulation.Research_Enum;
import utilities.IO;

/**
 *
 * @author Piotr
 */
public class Technology extends ImagePanel{    
    private JLabel title;
    private JLabel label_attacker,label_defender;
    UnitPanel[] researches;
    public Technology(String text) {
        super(IO.getImage("Research.jpg"));        
        title = new JLabel(text);       
        initComponents();
    }
    public UnitPanel get(Research_Enum research){
        for(UnitPanel u:researches){
            if(((Research_Enum)u.getObject()).equals(research))
                return u;
        }
        return null;
    }
    
    private void initComponents(){ 
        
        JPanel attacker = new JPanel();
        JPanel defender = new JPanel();
        attacker.setOpaque(false);
        defender.setOpaque(false);
        
        label_attacker = new JLabel("Agresor");
        label_attacker.setOpaque(true);
        label_attacker.setForeground(new Color(255,193,7));
        Font f = label_attacker.getFont();
        label_attacker.setBorder(new LineBorder(Color.black,2,true));
        label_attacker.setFont(new Font(f.getName(), Font.BOLD, 26));   
        label_attacker.setBackground(new Color(10, 10, 10, 150));
        
        
        label_defender = new JLabel("Obrońca");
        label_defender.setForeground(new Color(255,193,7));
        f = label_defender.getFont();
        label_defender.setOpaque(true);
        label_defender.setBorder(new LineBorder(Color.black,2,true));
        label_defender.setFont(new Font(f.getName(), Font.BOLD, 26));   
        label_defender.setBackground(new Color(10, 10, 10, 150));
        
        title.setForeground(Color.WHITE);
        f = title.getFont();
        title.setBorder(new EmptyBorder(-1, -1,-1, -1));
        title.setFont(new Font(f.getName(), Font.BOLD, 26));               
        
        researches = new UnitPanel[14];
        researches[0] = new UnitPanel(Research_Enum.Weapons_Technology);
        researches[1] = new UnitPanel(Research_Enum.Shielding_Technology);
        researches[2] = new UnitPanel(Research_Enum.Armour_Technology);
        researches[3] = new UnitPanel(Research_Enum.Combustion_Drive);
        researches[4] = new UnitPanel(Research_Enum.Impulse_Drive);
        researches[5] = new UnitPanel(Research_Enum.Hyperspace_Drive);
        researches[6] = new UnitPanel(Research_Enum.Weapons_Technology);
        researches[7] = new UnitPanel(Research_Enum.Shielding_Technology);
        researches[8] = new UnitPanel(Research_Enum.Armour_Technology);
        int labelsize = 110;
        GroupLayout l = new GroupLayout(attacker);
        attacker.setLayout(l);
        l.setAutoCreateGaps(true);
        //l.setAutoCreateContainerGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addGroup(l.createSequentialGroup()    
                .addComponent(label_attacker,labelsize,labelsize,labelsize)
                .addComponent(researches[0])
                    //.addGap(5,10,20)
                .addComponent(researches[1])
                .addComponent(researches[2])
                .addGap(20)
                .addComponent(researches[3])
                .addComponent(researches[4])
                .addComponent(researches[5]))
            .addGroup(l.createSequentialGroup()  
                .addComponent(label_defender,labelsize,labelsize,labelsize)
                .addComponent(researches[6])
                .addComponent(researches[7])
                .addComponent(researches[8])));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(label_attacker,GroupLayout.Alignment.CENTER)
                .addComponent(researches[0])
                .addComponent(researches[1])
                .addComponent(researches[2])
                .addComponent(researches[3])
                .addComponent(researches[4])
                .addComponent(researches[5]))
            .addGroup(l.createParallelGroup()
                .addComponent(label_defender,GroupLayout.Alignment.CENTER)
                .addComponent(researches[6])
                .addComponent(researches[7])
                .addComponent(researches[8])
                ));
        
        
        
        
        l = new GroupLayout(this);        
        setLayout(l);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup()
            .addComponent(title,GroupLayout.Alignment.CENTER)
            .addComponent(attacker));
        l.setVerticalGroup(l.createSequentialGroup()
                .addComponent(title)
            .addComponent(attacker));                 
    }
    
}
