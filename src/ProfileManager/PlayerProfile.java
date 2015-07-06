/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package ProfileManager;

import Enums.Lang_Enum;
import java.io.Serializable;

/**
 *
 * @author Piotr
 */
public class PlayerProfile implements Serializable{
    private static final long serialVersionUID = 7614135346269102453L;
    private Lang_Enum language;
    private int server_ID;
    private String playerName;    

    public PlayerProfile() {
        this.language = null;
        this.server_ID = 0;
        this.playerName = null;
    }    
    
    public PlayerProfile(Lang_Enum language, int server_ID, String playerName) {
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