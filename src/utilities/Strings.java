
package utilities;
public class Strings {
    public static String format(double value,int precision){
        String text = ""+(Math.round(100*value))/100.0;
        //text = text.substring(0, text.indexOf("."));
        return text;
    }
}
