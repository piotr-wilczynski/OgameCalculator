/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import simulation.Unit_Enum;
import utilities.FXIO;

/**
 * FXML Controller class
 *
 * @author Piotr
 */
public class FXMLController implements Initializable {
    
    private Image[] FleetImage;
    private Image[] DefenseImage;
    //@FXML
    public UnitPanelFX[] fleet1panel,fleet2panel,defense2panel;

    
    @FXML private GridPane fleet1,defense2;
    
    @FXML private GridPane fleet2;
    
    public FXMLController() {
        FleetImage = FXIO.getImageMatrix(FXIO.getImage("fleet.jpg"));
        DefenseImage = FXIO.getImageMatrix(FXIO.getImage("def.jpg"));
        fleet1 = new GridPane();
        fleet1panel = new UnitPanelFX[14];
        fleet1panel[0] = new UnitPanelFX(Unit_Enum.Light_Fighter, FleetImage[0]);
        fleet1panel[1] = new UnitPanelFX(Unit_Enum.Heavy_Fighter, FleetImage[1]);
        fleet1panel[2] = new UnitPanelFX(Unit_Enum.Cruiser, FleetImage[2]);
        fleet1panel[3] = new UnitPanelFX(Unit_Enum.Battleship, FleetImage[3]);
        fleet1panel[4] = new UnitPanelFX(Unit_Enum.Small_Cargo, FleetImage[8]);
        fleet1panel[5] = new UnitPanelFX(Unit_Enum.Large_Cargo, FleetImage[9]);
        fleet1panel[6] = new UnitPanelFX(Unit_Enum.Colony_Ship, FleetImage[10]);        
        fleet1panel[7] = new UnitPanelFX(Unit_Enum.Battlecruiser, FleetImage[4]);
        fleet1panel[8] = new UnitPanelFX(Unit_Enum.Bomber, FleetImage[5]);
        fleet1panel[9] = new UnitPanelFX(Unit_Enum.Destroyer, FleetImage[6]);
        fleet1panel[10] = new UnitPanelFX(Unit_Enum.Deathstar, FleetImage[7]);
        fleet1panel[11] = new UnitPanelFX(Unit_Enum.Recycler, FleetImage[11]);
        fleet1panel[12] = new UnitPanelFX(Unit_Enum.Espionage_Probe, FleetImage[12]);
        fleet1panel[13] = new UnitPanelFX(Unit_Enum.Solar_Satellite, FleetImage[13]);
        
        
        fleet2 = new GridPane();
        fleet2panel = new UnitPanelFX[14];
        fleet2panel[0] = new UnitPanelFX(Unit_Enum.Light_Fighter, FleetImage[0]);
        fleet2panel[1] = new UnitPanelFX(Unit_Enum.Heavy_Fighter, FleetImage[1]);
        fleet2panel[2] = new UnitPanelFX(Unit_Enum.Cruiser, FleetImage[2]);
        fleet2panel[3] = new UnitPanelFX(Unit_Enum.Battleship, FleetImage[3]);
        fleet2panel[4] = new UnitPanelFX(Unit_Enum.Small_Cargo, FleetImage[8]);
        fleet2panel[5] = new UnitPanelFX(Unit_Enum.Large_Cargo, FleetImage[9]);
        fleet2panel[6] = new UnitPanelFX(Unit_Enum.Colony_Ship, FleetImage[10]);        
        fleet2panel[7] = new UnitPanelFX(Unit_Enum.Battlecruiser, FleetImage[4]);
        fleet2panel[8] = new UnitPanelFX(Unit_Enum.Bomber, FleetImage[5]);
        fleet2panel[9] = new UnitPanelFX(Unit_Enum.Destroyer, FleetImage[6]);
        fleet2panel[10] = new UnitPanelFX(Unit_Enum.Deathstar, FleetImage[7]);
        fleet2panel[11] = new UnitPanelFX(Unit_Enum.Recycler, FleetImage[11]);
        fleet2panel[12] = new UnitPanelFX(Unit_Enum.Espionage_Probe, FleetImage[12]);
        fleet2panel[13] = new UnitPanelFX(Unit_Enum.Solar_Satellite, FleetImage[13]);
        
        
        defense2 = new GridPane();
        defense2panel = new UnitPanelFX[8];
        defense2panel[0] = new UnitPanelFX(Unit_Enum.Rocket_Launcher, FleetImage[0]);
        defense2panel[1] = new UnitPanelFX(Unit_Enum.Light_Laser, FleetImage[1]);
        defense2panel[2] = new UnitPanelFX(Unit_Enum.Heavy_Laser, FleetImage[2]);
        defense2panel[3] = new UnitPanelFX(Unit_Enum.Gauss_Cannon, FleetImage[3]);
        defense2panel[4] = new UnitPanelFX(Unit_Enum.Ion_Cannon, FleetImage[4]);
        defense2panel[5] = new UnitPanelFX(Unit_Enum.Plasma_Turret, FleetImage[5]);
        defense2panel[6] = new UnitPanelFX(Unit_Enum.Small_Shield_Dome, FleetImage[6]);        
        defense2panel[7] = new UnitPanelFX(Unit_Enum.Large_Shield_Dome, FleetImage[7]);
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        for(int i=0;i<7;i++){
            fleet1.add(fleet1panel[i], i, 0);     
            fleet1.add(fleet1panel[i+7], i, 1);  
            fleet2.add(fleet2panel[i], i, 0);       
            fleet2.add(fleet2panel[i+7], i, 1);  
            defense2.add(defense2panel[i], i, 0);
        }         
            //fleet1.add(fleet[9], 6, 0);       
        // TODO
    }    
    
}
