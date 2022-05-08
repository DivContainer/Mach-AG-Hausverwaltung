package de.machag.HouseService.House;

import org.springframework.context.annotation.Bean;

public enum HouseStatus {

    RENTED,
    FREE;

    public static HouseStatus parseString(String s) {
        if(s.equalsIgnoreCase("RENTED")) {
            return HouseStatus.RENTED;
        } else if (s.equalsIgnoreCase("FREE")) {
            return HouseStatus.FREE;
        }
        return null;
    }

}
