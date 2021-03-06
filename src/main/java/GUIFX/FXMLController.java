/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUIFX;

import java.net.URL;
import java.util.HashMap;
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
import simulation.BattleTechnologies;
import enums.UnitEnum;
import utilities.FXIO;

/**
 * FXML Controller class
 *
 * @author Piotr
 */
public class FXMLController implements Initializable {

    public UnitPanelFX[] Combat_Ships_Attacker, Combat_Ships_Defender, Civil_Ships_Attacker, Civil_Ships_Defender, Defensive_Structures;
    @FXML
    Parent root;
    private Image[] FleetImage;
    private Image[] DefenseImage;
    @FXML
    private AnchorPane MainPane;
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
        Combat_Ships_Attacker[0] = new UnitPanelFX(UnitEnum.LightFighter, FleetImage[0]);
        Combat_Ships_Attacker[1] = new UnitPanelFX(UnitEnum.HeavyFighter, FleetImage[1]);
        Combat_Ships_Attacker[2] = new UnitPanelFX(UnitEnum.Cruiser, FleetImage[2]);
        Combat_Ships_Attacker[3] = new UnitPanelFX(UnitEnum.Battleship, FleetImage[3]);   
        Combat_Ships_Attacker[4] = new UnitPanelFX(UnitEnum.Battlecruiser, FleetImage[4]);
        Combat_Ships_Attacker[5] = new UnitPanelFX(UnitEnum.Bomber, FleetImage[5]);
        Combat_Ships_Attacker[6] = new UnitPanelFX(UnitEnum.Destroyer, FleetImage[6]);
        Combat_Ships_Attacker[7] = new UnitPanelFX(UnitEnum.Deathstar, FleetImage[7]);
        
        Combat_Ships_Defender = new UnitPanelFX[8];
        Combat_Ships_Defender[0] = new UnitPanelFX(UnitEnum.LightFighter, FleetImage[0]);
        Combat_Ships_Defender[1] = new UnitPanelFX(UnitEnum.HeavyFighter, FleetImage[1]);
        Combat_Ships_Defender[2] = new UnitPanelFX(UnitEnum.Cruiser, FleetImage[2]);
        Combat_Ships_Defender[3] = new UnitPanelFX(UnitEnum.Battleship, FleetImage[3]);   
        Combat_Ships_Defender[4] = new UnitPanelFX(UnitEnum.Battlecruiser, FleetImage[4]);
        Combat_Ships_Defender[5] = new UnitPanelFX(UnitEnum.Bomber, FleetImage[5]);
        Combat_Ships_Defender[6] = new UnitPanelFX(UnitEnum.Destroyer, FleetImage[6]);
        Combat_Ships_Defender[7] = new UnitPanelFX(UnitEnum.Deathstar, FleetImage[7]);
                
        Civil_Ships_Attacker = new UnitPanelFX[5];
        Civil_Ships_Attacker[0] = new UnitPanelFX(UnitEnum.SmallCargo, FleetImage[8]);
        Civil_Ships_Attacker[1] = new UnitPanelFX(UnitEnum.LargeCargo, FleetImage[9]);
        Civil_Ships_Attacker[2] = new UnitPanelFX(UnitEnum.ColonyShip, FleetImage[10]);    
        Civil_Ships_Attacker[3] = new UnitPanelFX(UnitEnum.Recycler, FleetImage[11]);
        Civil_Ships_Attacker[4] = new UnitPanelFX(UnitEnum.EspionageProbe, FleetImage[12]);
        
        Civil_Ships_Defender = new UnitPanelFX[6];
        Civil_Ships_Defender[0] = new UnitPanelFX(UnitEnum.SmallCargo, FleetImage[8]);
        Civil_Ships_Defender[1] = new UnitPanelFX(UnitEnum.LargeCargo, FleetImage[9]);
        Civil_Ships_Defender[2] = new UnitPanelFX(UnitEnum.ColonyShip, FleetImage[10]);    
        Civil_Ships_Defender[3] = new UnitPanelFX(UnitEnum.Recycler, FleetImage[11]);
        Civil_Ships_Defender[4] = new UnitPanelFX(UnitEnum.EspionageProbe, FleetImage[12]);
        Civil_Ships_Defender[5] = new UnitPanelFX(UnitEnum.SolarSatellite, FleetImage[13]);
                
        Defensive_Structures = new UnitPanelFX[8];
        Defensive_Structures[0] = new UnitPanelFX(UnitEnum.RocketLauncher, DefenseImage[0]);
        Defensive_Structures[1] = new UnitPanelFX(UnitEnum.LightLaser, DefenseImage[1]);
        Defensive_Structures[2] = new UnitPanelFX(UnitEnum.HeavyLaser, DefenseImage[2]);
        Defensive_Structures[3] = new UnitPanelFX(UnitEnum.GaussCannon, DefenseImage[3]);
        Defensive_Structures[4] = new UnitPanelFX(UnitEnum.IonCannon, DefenseImage[4]);
        Defensive_Structures[5] = new UnitPanelFX(UnitEnum.PlasmaTurret, DefenseImage[5]);
        Defensive_Structures[6] = new UnitPanelFX(UnitEnum.SmallShieldDome, DefenseImage[6]);        
        Defensive_Structures[7] = new UnitPanelFX(UnitEnum.LargeShieldDome, DefenseImage[7]);
    }
    
        
    @FXML 
    private void Simulate(){
        progress.progressProperty().unbind();
        progress.setProgress(0);
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {                
                HashMap<UnitEnum,Integer> attacker = new HashMap<>();
                HashMap<UnitEnum,Integer> defender = new HashMap<>();
                BattleTechnologies attacker_tech = new BattleTechnologies(1, 1, 1, 1, 1, 1);
                BattleTechnologies defender_tech = new BattleTechnologies(1, 1, 1, 1, 1, 1);
                long start = System.nanoTime();
                //attacker.put(UnitEnum.SmallCargo, 8);
                /*
                for(int i=0;i<fleet1panel.length;i++){
                    attacker.put(fleet1panel[i].getUnitNumber(),fleet1panel[i].getNumber());
                    System.out.println(fleet1panel[i].getUnitNumber()+" "+fleet1panel[i].getNumber());
                }
                for(int i=0;i<fleet2panel.length;i++){
                    defender.put(fleet2panel[i].getUnitNumber(),fleet2panel[i].getNumber());
                }
                for(int i=0;i<defense2panel.length;i++){
                    System.out.println(defense2panel[i].getUnitNumber()+" "+defense2panel[i].getNumber());
                    defender.put(defense2panel[i].getUnitNumber(),defense2panel[i].getNumber());
                }
                *//*
                attacker.put(UnitEnum.Cruiser, 1000);
                defender.put(UnitEnum.LightLaser, 10000);
                //defender.put(UnitEnum.LightLaser, 8);

                ThreadGroup threads = new ThreadGroup("Simulations");
                List<simulation> sims = new ArrayList<>();
                for(int i=0;i<1000;i++)
                    sims.add(new simulation(attacker, defender, attacker_tech, defender_tech,threads,i));
                int i=0;  
                
                int t = Runtime.getRuntime().availableProcessors();
                while(i<sims.size()){
                    if(threads.activeCount()<t){
                        sims.get(i).start();
                        updateMessage("simulation nr="+(i+1));
                        updateProgress(i+1, 1000);
                        i++;
                    }
                }
                while(threads.activeCount()>0);
                List<statistics> attacker_stat = new ArrayList<>();
                List<statistics> defender_stat = new ArrayList<>();
                for(i=0;i<sims.size();i++){
                    //attacker_stat.add(sims.get(i).getAttacker_Statistics());
                    //defender_stat.add(sims.get(i).getDefender_Statistics());
                }
                */
                //statistics.getStats(attacker_stat);
                //statistics.getStats(defender_stat);
                long end = System.nanoTime();
                System.out.printf("simulation took %.2g seconds\n", (double)(end-start)/1e9);
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
