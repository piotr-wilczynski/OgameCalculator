
package GUI;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import simulation.Research_Enum;
import simulation.Resources_Enum;
import simulation.Unit_Enum;

public class Clipboard extends Thread implements ClipboardOwner{
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private java.awt.datatransfer.Clipboard clip;
    private Hashtable<Research_Enum,Integer> researches;
    private Hashtable<Resources_Enum,Integer> resources;
    private Hashtable<Unit_Enum,Integer> units;
    private long time;
    public Clipboard() {
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        time = System.currentTimeMillis();
        this.researches = new Hashtable<>();
        this.resources = new Hashtable<>();
        this.units = new Hashtable<>();
    }
    
    @Override
    public void run() {  
        Transferable trans = clip.getContents(this);  
        regainOwnership(trans);  
        while(true) {
            try {  
              Thread.sleep(1000);  
            } catch(Exception e) {  
                e.printStackTrace();
            }  
        }  
    }  

    public Hashtable<Research_Enum, Integer> getResearches() {
        return researches;
    }

    public Hashtable<Resources_Enum, Integer> getResources() {
        return resources;
    }

    public Hashtable<Unit_Enum, Integer> getUnits() {
        return units;
    }
    
    private void function(String text){
        Hashtable<Research_Enum,Integer> rese = new Hashtable<>();
        Hashtable<Resources_Enum,Integer> reso = new Hashtable<>();
        Hashtable<Unit_Enum,Integer> unit = new Hashtable<>();
        ArrayList<String> list = new ArrayList<>();
        String tab[] = text.split("\n");
        for(int i=0;i<tab.length;i++){
            String[] tab2 = tab[i].split("\t");
            for(int j=0;j<tab2.length;j++){
                list.add(tab2[j]);
                //System.out.println(tab2[j]);
            }
        }
        int level=0;
    final int researches = 0;
    final int units = 2;
    final int resources = 3;
        for(int i=list.size()-1;i>=0;i--){
            try{
                int number = Integer.parseInt(list.get(i).replaceAll("[.]", ""));
                if(i-1>=0){
                    switch(level){
                        case researches:{
                            for(Research_Enum res:Research_Enum.values()){
                                if(list.get(i-1).matches(lang.GUI_Lang.get(res.name()))){
                                    rese.put(res, number);
                                    break;
                                }
                            }                            
                        }
                        case units:{
                            for(Unit_Enum u:Unit_Enum.values()){
                                if(list.get(i-1).matches(lang.GUI_Lang.get(u.name()))){
                                    unit.put(u, number);
                                    level = units;
                                    break;
                                }
                            }     
                        }
                        case resources:{
                            for(Resources_Enum res:Resources_Enum.values()){
                                if(list.get(i-1).replaceAll("[:]", "").matches(lang.GUI_Lang.get(res.name()))){
                                    reso.put(res, number);
                                    if(res.equals(Resources_Enum.Metal)){
                                        if(i-2>=0){
                                            System.out.println("Planet="+list.get(i-2));
                                            long new_time = System.currentTimeMillis();
                                            this.researches = rese;
                                            this.resources = reso;
                                            this.units = unit;
                                            pcs.firePropertyChange("change",time, new_time);
                                            time = new_time;
                                        }
                                    }
                                    level = resources;
                                    break;
                                }
                            }     
                        }
                    }
                }                
            }catch(NumberFormatException ex){
                
            }
        }
    }


    @Override
    public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, Transferable contents) {
    try {  
      Thread.sleep(20);  
    } catch(Exception e) {  
        e.printStackTrace();
    }  
        Transferable c = clip.getContents(this);
        
        processContents(c);  
        regainOwnership(c);  
    }
    
    void processContents(Transferable t) {  
        try {
            function((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }  
    
    void regainOwnership(Transferable t) {  
        clip.setContents(t, this);  
    } 
    
    
    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }
}
