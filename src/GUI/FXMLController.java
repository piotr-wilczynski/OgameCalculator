/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Piotr
 */
public class FXMLController implements Initializable {
    
    //private BufferedImage[] FleetImage;

    @FXML GridPane fleet1;
    javafx.scene.image.Image im;
    public FXMLController() {
        try {
            im = new Image(new FileInputStream(new File("images/LM.PNG")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        fleet1 = new GridPane();
        //FleetImage = utilities.IO.getImageMatrix(utilities.IO.getImage("fleet.jpg"));
    }

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ImageView iv = new ImageView(im);
        iv.setFitWidth(70);
        iv.setFitHeight(70);
        fleet1.add(iv, 0, 0);
        // TODO
    }    
    
}
