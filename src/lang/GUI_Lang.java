/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lang;

public class GUI_Lang {
    private static final GUI_Lang gui = new GUI_Lang();
    public final String shipyard_of_argessor;
    public final String shipyard_of_defender;
    public final String defense_of_defender;
    public final String research;;
    public final String agressor;;
    public final String defender;
    public GUI_Lang() {
        shipyard_of_argessor = "Stocznia agresora";
        shipyard_of_defender = "Stocznia obrońcy";
        defense_of_defender = "Obrona obrońcy";
        research = "Badania";
        agressor = "Agresor";
        defender = "Obrońca";
        
    }
    public static GUI_Lang getGUI(){
        return gui;
    }

    
}
