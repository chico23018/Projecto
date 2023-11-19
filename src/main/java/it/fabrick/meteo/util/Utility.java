package it.fabrick.meteo.util;

public final class Utility {
    private Utility() {

    }

    public static String converteString(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
