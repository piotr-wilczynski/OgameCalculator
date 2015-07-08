/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUIFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import Enums.UnitEnum;

public class UnitPanelFX extends AnchorPane{
    private ImageView image ;
    private TextField textfield;
    private Label label;
    private UnitEnum unit;
    private Tooltip label_tooltip,textfield_tooltip;

    public UnitPanelFX(UnitEnum unit,Image image) {
                
        this.image = new ImageView(image);
        this.image.setFitHeight(70);
        this.image.setFitWidth(70);
        getChildren().add(this.image);
        AnchorPane.setLeftAnchor(this.image,0.0);
        AnchorPane.setRightAnchor(this.image,0.0);
        AnchorPane.setTopAnchor(this.image, 0.0);
        AnchorPane.setBottomAnchor(this.image, 0.0);
        this.image.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                textfield.requestFocus();
            }
        });
        
        
        label = new Label("0");
        label.setTextFill(Paint.valueOf("RED"));
        label.setStyle("-fx-background-color: #FFFFFF;");
        label.setOpacity(0.7);
        label.setFont(new Font("System Bold", 10));
        getChildren().add(label);
        AnchorPane.setBottomAnchor(label, 0.0);
        AnchorPane.setLeftAnchor(label,0.0);
        AnchorPane.setRightAnchor(label,0.0);
        label.setVisible(false);
        //label.setText("-100000000");
        //label.setText("-900000000");
        
        
        textfield = new TextField("0");
        textfield.setFont(new Font("System Bold", 10));
        textfield.setPadding(new Insets(-1, -1,-1,-1));
        textfield.setAlignment(Pos.CENTER_RIGHT);
        textfield.setOpacity(0.7);
        getChildren().add(textfield);
        AnchorPane.setTopAnchor(textfield, 0.0);
        AnchorPane.setLeftAnchor(textfield,0.0);
        AnchorPane.setRightAnchor(textfield,0.0);
        
        textfield.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                setLabel(textfield.getText());
            }
        });
        
        this.unit = unit;
    }
    public void setText(String text){
        textfield.setText(text);
    }
    
    public int getNumber(){
        int number = 0;
        String text = textfield.getText();
        System.out.println(text.length());
        try{
            if(text.length()>0)
                number = Integer.parseInt(text);
            else
                throw new Exception();
        }catch(Exception ex){textfield.setText("0");}
        return 0;
    }
    
    public void setLabel(String text){
        label_tooltip = new Tooltip(text);
        label_tooltip.setFont(new Font(20));
        label.setTooltip(label_tooltip);
        label.setText(text);
        label.setVisible(true);
    }

    public UnitEnum getUnit() {
        return unit;
    }
    
    
    
    
    
    
}
