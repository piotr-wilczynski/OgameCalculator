/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Piotr
 */
public class ImagePanel extends JPanel{    
    private static final long serialVersionUID = 4426229139205446582L;
    private BufferedImage background;

    public ImagePanel(BufferedImage image) {
        background = image;
        setMaximumSize(new Dimension(image.getWidth(),image.getHeight()));
        setMinimumSize(new Dimension(image.getWidth(),image.getHeight()));
        setSize(new Dimension(image.getWidth(),image.getHeight()));
    }

    public void paintComponent(Graphics g) {
      g.drawImage(background, 0, 0, null);
    }    


    
    
}
