package de.machag.HouseService.House;

public enum HouseStatus {

    FREE,
    RENTED;

    public static HouseStatus parseString(String s) throws IllegalArgumentException {
        if(s.equalsIgnoreCase("free")) {
            return HouseStatus.FREE;
        } else if(s.equalsIgnoreCase("rented")) {
            return HouseStatus.RENTED;
        }
        throw new IllegalArgumentException("Argument does not match");
    }

}
