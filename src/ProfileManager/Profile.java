/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfileManager;

import Enums.Lang_Enum;
import java.io.Serializable;

/**
 *
 * @author Piotr
 */
public class Profile implements Serializable{
    private static final long serialVersionUID = 7614135346269102453L;
    private final Lang_Enum language;
    private final int server_ID;
    private final String playerName;    

    public Profile() {
        this.language = null;
        this.server_ID = 0;
        this.playerName = null;
    }    
    
    public Profile(Lang_Enum language, int server_ID, String playerName) {
        this.language = language;
        this.server_ID = server_ID;
        this.playerName = playerName;
    }

    public Lang_Enum getLanguage() {
        return language;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getServer_ID() {
        return server_ID;
    }    
    
}
