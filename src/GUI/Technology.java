/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JLabel;
import utilities.IO;

/**
 *
 * @author Piotr
 */
public class Technology extends ImagePanel{    
    private JLabel label;
    
    public Technology() {
        super(IO.getImage("Research.jpg"));
    }
    
    
}
