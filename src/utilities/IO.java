
package utilities;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import javax.imageio.ImageIO;

public class IO {
    public static BufferedImage getImage(String file){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("images/"+file));
        } catch (IOException ex) {
            try {
                bi = ImageIO.read(IO.class.getResource("/images/"+file));
            } catch (IOException ex1) {
                return null;
            }
        }
        return bi;
    }
    public static void readIni() throws IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("units/Ships/Small Cargo Ship.ini"));
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
        File file = new File("units/"+name+".ini");
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        p.save(out, "");
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
