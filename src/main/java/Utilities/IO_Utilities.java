/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

public class IO_Utilities {
    public static BufferedImage getImage(String file){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(IO_Utilities.class.getResource("/Images/" +file));
        } catch (IOException ex) {
            
        }
        return bi;
    }
    public static void readIni() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("allUnits/Ships/Small Cargo Ship.ini"));
        p.list(System.out);
        //p2.load(new FileInputStream("file.ini"));
        //p2.setProperty("LM", "Lekki myśliwiec");
        //FileOutputStream out = new FileOutputStream("file.ini");
        //p2.save(out, "/* properties updated */");
        //out.close();        
    }
    public static void ini(String name,int a,int b,int c,int d,int e,int f) throws IOException{
        Properties p = new Properties();
        p.setProperty("Name", name);
        p.setProperty("Structural Integrity", ""+a);
        p.setProperty("Shield Power", ""+b);
        p.setProperty("Weapon Power", ""+c);
        p.setProperty("Cargo Capacity", ""+d);
        p.setProperty("Base Speed", ""+e);
        p.setProperty("Fuel Consumption", ""+f);
        File file = new File("allUnits/"+name+".ini");
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        p.store(out, "");
        out.close();        
    }
    public static void save_ini(String name,Hashtable<String,String> lang) throws IOException{   
        Properties p = new Properties(); 
        
        File file = new File(name+".ini");
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        p.putAll(lang);
        p.store(out, "Language");
        out.close();        
    }
    public static BufferedImage[][] getImageMatrix(BufferedImage image,int rows,int columns){   
        if(columns==0||rows==0)
            return new BufferedImage[0][0];
        BufferedImage[][] matrix = new BufferedImage[columns][rows];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                matrix[j][i] = image.getSubimage(j*image.getWidth()/columns,i*image.getHeight()/rows, image.getWidth()/columns, image.getHeight()/rows);
            }
        }
        return matrix;
    }
}
