package it.fabrick.meteo.constant;

public final class Constant {
    public static final String path = "/v1.0/weather";
    public static final String path2 = "/v1.0/region";
    public static final String path3 = "/v1.0/resident";
    public static final String region = "/{idRegions}";
    public static final String province = "/{idRegions}/province";
    public static final String city = "/{idRegions}/province/{sigla}/cities";

    public static final String searchForecast = "/search/forecast";
    public static final String search =  "/search/numResident";

    private Constant (){

    }
}
