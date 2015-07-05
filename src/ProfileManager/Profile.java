/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfileManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Piotr
 */
public class Profile implements Serializable{
    private static final long serialVersionUID = -7508759438067394579L;    
    
    private String lang;
    private List<PlayerProfile> profiles;
    private int mainProfile;    

    public Profile() {
        this.lang = "";
        this.profiles = new ArrayList<>();
        this.mainProfile = 0;    
    }    
    
    public Profile(Locale lang, List<PlayerProfile> profiles, int mainProfile) {
        this.lang = lang.getLanguage();
        this.profiles = profiles;
        this.mainProfile = mainProfile;
    }

    public Locale getLang() {
        return new Locale(lang);
    }

    public void setLang(Locale lang) {
        this.lang = lang.getLanguage();
    }

    public List<PlayerProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<PlayerProfile> profiles) {
        this.profiles = profiles;
    }

    public int getMainProfile() {
        return mainProfile;
    }

    public void setMainProfile(int mainProfile) {
        this.mainProfile = mainProfile;
    }
    
    
    
}
