package GUI;

import Enums.Resources_Enum;
import Statistics.Coordinates;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Piotr
 */
public class Planet extends JDialog{    
    private static final long serialVersionUID = -3446203745199891504L;
    private Coordinates coord;
    private JLabel label_metal,label_crystal,label_deuterium,label_planet_name,label_planet_locations;
    private JTextField metal,crystal,deuterium,planet_name,planet_location; 
    private JButton ok,cancel;
    private JCheckBox moon;
    public Planet() {
        this(new Coordinates());
    }

    public Planet(Coordinates coordinates) {
        coord = coordinates;
        initComponents();
    }
    
    
    private void initComponents(){
        label_metal = new JLabel(lang.GUI_Lang.get(Resources_Enum.Metal.name()));
        label_crystal = new JLabel(lang.GUI_Lang.get(Resources_Enum.Crystal.name()));
        label_deuterium = new JLabel(lang.GUI_Lang.get(Resources_Enum.Deuterium.name()));
        label_planet_name = new JLabel(lang.GUI_Lang.get("label_planet_name"));
        label_planet_locations = new JLabel(lang.GUI_Lang.get("label_planet_locations"));
        metal = new JTextField("0",6);
        crystal = new JTextField("0",6);
        deuterium = new JTextField("0",6);
        planet_name = new JTextField();
        planet_location = new JTextField("0:0:0",5);
        ok = new JButton(lang.GUI_Lang.get("OK"));
        cancel = new JButton(lang.GUI_Lang.get("Cancel"));
        moon = new JCheckBox(lang.GUI_Lang.get("Moon"));
        label_planet_name.setBorder(new EmptyBorder(0, 0, 0, 0));
        label_planet_locations.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        JPanel resources = new JPanel();
        resources.setBorder(new TitledBorder(lang.GUI_Lang.get("Resources")));
        GroupLayout l = new GroupLayout(resources);
        resources.setLayout(l);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createSequentialGroup()
            .addGap(5)
            .addGroup(l.createParallelGroup()
                .addComponent(label_metal)
                .addComponent(label_crystal)
                .addComponent(label_deuterium))
            .addGroup(l.createParallelGroup()
                .addComponent(metal)
                .addComponent(crystal)
                .addComponent(deuterium))
            .addGap(5));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(label_metal)
                .addComponent(metal))
            .addGroup(l.createParallelGroup()
                .addComponent(label_crystal)
                .addComponent(crystal))
            .addGroup(l.createParallelGroup()
                .addComponent(label_deuterium)
                .addComponent(deuterium)));
        
        
        JPanel planet = new JPanel();
        l = new GroupLayout(planet);
        planet.setLayout(l);
        l.setAutoCreateGaps(true);
        
        l.setHorizontalGroup(l.createParallelGroup()
            .addComponent(label_planet_locations)
            .addGroup(l.createSequentialGroup()
                .addComponent(planet_location)
                .addComponent(moon))
            .addComponent(label_planet_name)
            .addComponent(planet_name));
        l.setVerticalGroup(l.createSequentialGroup()
            .addComponent(label_planet_locations,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
            .addGroup(l.createParallelGroup()
                .addComponent(planet_location,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                .addComponent(moon,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE))
            .addComponent(label_planet_name,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
            .addComponent(planet_name,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE));
        
        
        l = new GroupLayout(getContentPane());
        getContentPane().setLayout(l);
        l.setAutoCreateContainerGaps(true);
        l.setAutoCreateGaps(true);
        l.setHorizontalGroup(l.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(l.createSequentialGroup()
                .addComponent(resources)
                .addComponent(planet))
            .addGroup(l.createSequentialGroup()
                .addComponent(cancel)
                .addComponent(ok)));
        l.setVerticalGroup(l.createSequentialGroup()
            .addGroup(l.createParallelGroup()
                .addComponent(resources)
                .addComponent(planet))
            .addGroup(l.createParallelGroup()
                .addComponent(cancel)
                .addComponent(ok)));
        
        setIconImage(utilities.IO_Utilities.getImage("icon.png"));        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
    }
    
    public void setMetal(long Metal){
        metal.setText(""+Metal);
    }
    public void setCrystal(long Crystal){
        crystal.setText(""+Crystal);
    }
    public void setDeuterium(long Deuterium){
        deuterium.setText(""+Deuterium);
    }

    public long getMetal() {
        try{
            return Long.parseLong(metal.getText());
        }catch(Exception e){
            return 0;
        }
    }

    public long getCrystal() {
        try{
            return Long.parseLong(crystal.getText());
        }catch(Exception e){
            return 0;
        }
    }

    public long getDeuterium() {
        try{
            return Long.parseLong(deuterium.getText());
        }catch(Exception e){
            return 0;
        }
    }
    
    public long[] getResources(){
        return new long[]{getMetal(),getCrystal(),getDeuterium()};
    }

    public Coordinates getCoordinates() {
        return new Coordinates(planet_location.getText());
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coord = coordinates;
        planet_location.setText(coord.getGalaxy()+":"+coord.getSolar_system()+":"+coord.getPosition());        
    }
    
    
    
    
    
    
    private void Action(){
        setVisible(false);        
    }
}
