/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package profilemanager;

import enums.LangEnum;
import api.Localization;
import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author Piotr
 */
public class Profile implements Serializable{
    private static final long serialVersionUID = -7508759438067394579L;  
    private static Profile profile = new Profile();  
    
    private String lang;
    private PlayerProfile[] profiles;
    private int currnetProfile;    
    private transient Localization localization = null;

    public Profile() {
        this.lang = Locale.getDefault().getLanguage();
        this.profiles = new PlayerProfile[]{new PlayerProfile(LangEnum.Poland, 1, "Player")};
        this.currnetProfile = 0;    
        localization = api.OgameApi.getInstance().getLocalization(profiles[0].getLanguage().getServerCountryCode());
    }    
    
    public Profile(Locale lang, PlayerProfile[] profiles, int mainProfile) {
        this.lang = lang.getLanguage();
        this.profiles = profiles;
        this.currnetProfile = mainProfile;
        if(profiles != null && profiles.length>0)
            localization = api.OgameApi.getInstance().getLocalization(profiles[0].getLanguage().getServerCountryCode(),profiles[0].getServer_ID(),false);
    }    

    public Locale getLanguage() {
        return new Locale(lang);
    } 
    public void setLanguage(Locale lang) {
        this.lang = lang.getLanguage();
    }

    public Localization getLocalization() {
        return localization;
    }    

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public PlayerProfile[] getProfiles() {
        return profiles;
    }

    public void setProfiles(PlayerProfile[] profiles) {
        this.profiles = profiles;
    }

    public int getMainProfile() {
        return currnetProfile;
    }

    public void setMainProfile(int mainProfile) {
        this.currnetProfile = mainProfile;
    }

    public static Profile getInstance() {
        return profile;
    }
    
    
    
    
}
