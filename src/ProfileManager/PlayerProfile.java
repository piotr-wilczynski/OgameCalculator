/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package ProfileManager;

import Enums.LangEnum;
import java.io.Serializable;

/**
 *
 * @author Piotr
 */
public class PlayerProfile implements Serializable{
    private static final long serialVersionUID = 7614135346269102453L;
    
    private LangEnum language;
    private int server_ID;
    private String playerName;    

    public PlayerProfile() {
        this.language = LangEnum.Poland;
        this.server_ID = 1;
        this.playerName = "";
    }    
    
    public PlayerProfile(LangEnum language, int server_ID, String playerName) {
        this.language = language;
        this.server_ID = server_ID;
        this.playerName = playerName;
    }

    public LangEnum getLanguage() {
        return language;
    }

    public void setLanguage(LangEnum language) {
        this.language = language;
    }    

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }    

    public int getServer_ID() {
        return server_ID;
    }

    public void setServer_ID(int server_ID) {
        this.server_ID = server_ID;
    }
    
    
}
