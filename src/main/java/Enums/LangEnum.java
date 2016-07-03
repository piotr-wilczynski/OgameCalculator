/*
 * Copyright (C) 2015 Piotr Wilczynski.
 * All rights reserved. 
 *
 * Please refer any queries to Piotr Wilczynski <wilczynskipio@gmail.com>.
 */
package enums;

import java.util.Locale;

/**
 *
 * @author Piotr
 */
public enum LangEnum {

    Argentina(1),
    Germany(12),
    Japan(31),
    Poland(44),
    UnitedKingdom(56),
    UnitedStates(57);

    private final int flagId;

    LangEnum(int flagId) {
        this.flagId = flagId;
    }

    public static LangEnum fromLocale(Locale locale) {
        try {
            return valueOf(locale.getDisplayCountry(Locale.ENGLISH).replaceAll(" ", ""));
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public Locale toLocale() {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (this == fromLocale(locale)) {
                return locale;
            }
        }
        return Locale.ENGLISH;
    }

    public int getFlagId() {
        return flagId;
    }

    public String getServerCountryCode() {
        switch (this) {
            case UnitedKingdom:
                return "en";
            default:
                return toLocale().getCountry().toLowerCase();
        }
    }

}
