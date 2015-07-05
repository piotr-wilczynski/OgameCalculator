
package Utilities;
public class Strings {
    public static String precision(double value,int precision){
        String text = ""+(long)value+"."+(((long)(100*value))%100);
        if(value%1==0){
            text = ""+(long)value;
        }
        return text;
        //return ""+value;
    }
    public static String format(double value){   
        String text = ""+precision(value, 2);  
        String split[] = text.split("[.]");
        text=split[0];
        String temp="";
        double max =  Math.ceil(1.0*text.length()/3);
        for(long i=0;i<max;i++){
            if(i!=max-1){
                temp=text.substring(text.length()-3)+" "+temp;
                text = text.substring(0, text.length()-3);
            }else
                temp=text+" "+temp;
        }        
        if(split.length>1)
            try{
            if(Integer.parseInt(split[1])!=0)
                temp=temp.substring(0, temp.length()-1)+"."+split[1];
            }catch(NumberFormatException ex){
                
            }
        return temp;
    }
}
