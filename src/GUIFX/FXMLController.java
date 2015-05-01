/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFX;

import Statistics.Statistics;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simulation.Simulation;
import simulation.Battle_Technologies;
import Enums.Unit_Enum;
import utilities.FXIO;

/**
 * FXML Controller class
 *
 * @author Piotr
 */
public class FXMLController implements Initializable {
    
    private Image[] FleetImage;
    private Image[] DefenseImage;
    
    @FXML    
    private AnchorPane MainPane; 
    
    @FXML
    Parent root;
    
    public UnitPanelFX[] Combat_Ships_Attacker,Combat_Ships_Defender,Civil_Ships_Attacker,Civil_Ships_Defender,Defensive_Structures;
    
    @FXML
    private ProgressIndicator progress;
    
    @FXML 
    private GridPane Combat_Ships_Attacker_Pane;
    @FXML 
    private GridPane Combat_Ships_Defender_Pane;
    @FXML 
    private GridPane Civil_Ships_Attacker_Pane;
    @FXML 
    private GridPane Civil_Ships_Defender_Pane;
    @FXML 
    private GridPane Defensive_Structures_Pane;
    
    
    public FXMLController() {
        
        MainPane = new AnchorPane();
        progress = new ProgressIndicator();
        
        FleetImage = FXIO.getImageMatrix(FXIO.getImage("fleet.jpg"));
        DefenseImage = FXIO.getImageMatrix(FXIO.getImage("def.jpg"));        
        
        Combat_Ships_Attacker_Pane = new GridPane();
        Combat_Ships_Defender_Pane = new GridPane();
        Civil_Ships_Attacker_Pane = new GridPane();
        Civil_Ships_Defender_Pane = new GridPane();
        Defensive_Structures_Pane = new GridPane();        
        
        Combat_Ships_Attacker = new UnitPanelFX[8];
        Combat_Ships_Attacker[0] = new UnitPanelFX(Unit_Enum.Light_Fighter, FleetImage[0]);
        Combat_Ships_Attacker[1] = new UnitPanelFX(Unit_Enum.Heavy_Fighter, FleetImage[1]);
        Combat_Ships_Attacker[2] = new UnitPanelFX(Unit_Enum.Cruiser, FleetImage[2]);
        Combat_Ships_Attacker[3] = new UnitPanelFX(Unit_Enum.Battleship, FleetImage[3]);   
        Combat_Ships_Attacker[4] = new UnitPanelFX(Unit_Enum.Battlecruiser, FleetImage[4]);
        Combat_Ships_Attacker[5] = new UnitPanelFX(Unit_Enum.Bomber, FleetImage[5]);
        Combat_Ships_Attacker[6] = new UnitPanelFX(Unit_Enum.Destroyer, FleetImage[6]);
        Combat_Ships_Attacker[7] = new UnitPanelFX(Unit_Enum.Deathstar, FleetImage[7]);
        
        Combat_Ships_Defender = new UnitPanelFX[8];
        Combat_Ships_Defender[0] = new UnitPanelFX(Unit_Enum.Light_Fighter, FleetImage[0]);
        Combat_Ships_Defender[1] = new UnitPanelFX(Unit_Enum.Heavy_Fighter, FleetImage[1]);
        Combat_Ships_Defender[2] = new UnitPanelFX(Unit_Enum.Cruiser, FleetImage[2]);
        Combat_Ships_Defender[3] = new UnitPanelFX(Unit_Enum.Battleship, FleetImage[3]);   
        Combat_Ships_Defender[4] = new UnitPanelFX(Unit_Enum.Battlecruiser, FleetImage[4]);
        Combat_Ships_Defender[5] = new UnitPanelFX(Unit_Enum.Bomber, FleetImage[5]);
        Combat_Ships_Defender[6] = new UnitPanelFX(Unit_Enum.Destroyer, FleetImage[6]);
        Combat_Ships_Defender[7] = new UnitPanelFX(Unit_Enum.Deathstar, FleetImage[7]);
                
        Civil_Ships_Attacker = new UnitPanelFX[5];
        Civil_Ships_Attacker[0] = new UnitPanelFX(Unit_Enum.Small_Cargo, FleetImage[8]);
        Civil_Ships_Attacker[1] = new UnitPanelFX(Unit_Enum.Large_Cargo, FleetImage[9]);
        Civil_Ships_Attacker[2] = new UnitPanelFX(Unit_Enum.Colony_Ship, FleetImage[10]);    
        Civil_Ships_Attacker[3] = new UnitPanelFX(Unit_Enum.Recycler, FleetImage[11]);
        Civil_Ships_Attacker[4] = new UnitPanelFX(Unit_Enum.Espionage_Probe, FleetImage[12]);
        
        Civil_Ships_Defender = new UnitPanelFX[6];
        Civil_Ships_Defender[0] = new UnitPanelFX(Unit_Enum.Small_Cargo, FleetImage[8]);
        Civil_Ships_Defender[1] = new UnitPanelFX(Unit_Enum.Large_Cargo, FleetImage[9]);
        Civil_Ships_Defender[2] = new UnitPanelFX(Unit_Enum.Colony_Ship, FleetImage[10]);    
        Civil_Ships_Defender[3] = new UnitPanelFX(Unit_Enum.Recycler, FleetImage[11]);
        Civil_Ships_Defender[4] = new UnitPanelFX(Unit_Enum.Espionage_Probe, FleetImage[12]);
        Civil_Ships_Defender[5] = new UnitPanelFX(Unit_Enum.Solar_Satellite, FleetImage[13]);
                
        Defensive_Structures = new UnitPanelFX[8];
        Defensive_Structures[0] = new UnitPanelFX(Unit_Enum.Rocket_Launcher, DefenseImage[0]);
        Defensive_Structures[1] = new UnitPanelFX(Unit_Enum.Light_Laser, DefenseImage[1]);
        Defensive_Structures[2] = new UnitPanelFX(Unit_Enum.Heavy_Laser, DefenseImage[2]);
        Defensive_Structures[3] = new UnitPanelFX(Unit_Enum.Gauss_Cannon, DefenseImage[3]);
        Defensive_Structures[4] = new UnitPanelFX(Unit_Enum.Ion_Cannon, DefenseImage[4]);
        Defensive_Structures[5] = new UnitPanelFX(Unit_Enum.Plasma_Turret, DefenseImage[5]);
        Defensive_Structures[6] = new UnitPanelFX(Unit_Enum.Small_Shield_Dome, DefenseImage[6]);        
        Defensive_Structures[7] = new UnitPanelFX(Unit_Enum.Large_Shield_Dome, DefenseImage[7]);
    }
    
        
    @FXML 
    private void Simulate(){
        progress.progressProperty().unbind();
        progress.setProgress(0);
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {                
                HashMap<Unit_Enum,Integer> attacker = new HashMap<>();
                HashMap<Unit_Enum,Integer> defender = new HashMap<>();
                Battle_Technologies attacker_tech = new Battle_Technologies(1, 1, 1, 1, 1, 1);       
                Battle_Technologies defender_tech = new Battle_Technologies(1, 1, 1, 1, 1, 1);
                long start = System.nanoTime();
                //attacker.put(Unit_Enum.Small_Cargo, 8);
                /*
                for(int i=0;i<fleet1panel.length;i++){
                    attacker.put(fleet1panel[i].getUnit(),fleet1panel[i].getNumber());
                    System.out.println(fleet1panel[i].getUnit()+" "+fleet1panel[i].getNumber());
                }
                for(int i=0;i<fleet2panel.length;i++){
                    defender.put(fleet2panel[i].getUnit(),fleet2panel[i].getNumber());
                }
                for(int i=0;i<defense2panel.length;i++){
                    System.out.println(defense2panel[i].getUnit()+" "+defense2panel[i].getNumber());
                    defender.put(defense2panel[i].getUnit(),defense2panel[i].getNumber());
                }
                *//*
                attacker.put(Unit_Enum.Cruiser, 1000);
                defender.put(Unit_Enum.Light_Laser, 10000);
                //defender.put(Unit_Enum.Light_Laser, 8);

                ThreadGroup threads = new ThreadGroup("Simulations");
                List<Simulation> sims = new ArrayList<>();
                for(int i=0;i<1000;i++)
                    sims.add(new Simulation(attacker, defender, attacker_tech, defender_tech,threads,i));
                int i=0;  
                
                int t = Runtime.getRuntime().availableProcessors();
                while(i<sims.size()){
                    if(threads.activeCount()<t){
                        sims.get(i).start();
                        updateMessage("Simulation nr="+(i+1));
                        updateProgress(i+1, 1000);
                        i++;
                    }
                }
                while(threads.activeCount()>0);
                List<Statistics> attacker_stat = new ArrayList<>();
                List<Statistics> defender_stat = new ArrayList<>();
                for(i=0;i<sims.size();i++){
                    //attacker_stat.add(sims.get(i).getAttacker_Statistics());
                    //defender_stat.add(sims.get(i).getDefender_Statistics());
                }
                */
                //Statistics.getStats(attacker_stat);
                //Statistics.getStats(defender_stat);
                long end = System.nanoTime();
                System.out.printf("Simulation took %.2g seconds\n", (double)(end-start)/1e9);
                return true;
            }
        };      
        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override            
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //label.setText(newValue);
                System.out.println(newValue);
            }
        }); 
        progress.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
                
    }
    
    private void fillPane(GridPane pane, UnitPanelFX[] panels){
        int length = (int) Math.ceil(1.0*panels.length/2);
        for(int i = 0;i<length;i++){
            pane.add(panels[i], i, 0);
                if(i+length<panels.length)
            pane.add(panels[i+length], i, 1);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainPane.setStyle("-fx-background-image: url(\"images\\background.png\");");
        
        
        fillPane(Combat_Ships_Attacker_Pane, Combat_Ships_Attacker);
        fillPane(Combat_Ships_Defender_Pane, Combat_Ships_Defender);
        fillPane(Civil_Ships_Attacker_Pane, Civil_Ships_Attacker);
        fillPane(Civil_Ships_Defender_Pane, Civil_Ships_Defender);
        fillPane(Defensive_Structures_Pane, Defensive_Structures);
        
        //Border b = new TitledBorder("Tytul");
        /*
        for(int i=0;i<14;i++){
            if(i<fleet1panel.length)
                fleet1.add(fleet1panel[i], i, 0);   
            fleet2.add(fleet2panel[i], i, 0);
            if(i<defense2panel.length)
                fleet2.add(defense2panel[i], i, 1);
        }        
            //fleet1.add(fleet[9], 6, 0);       
                */
        // TODO
    }    
    
}
