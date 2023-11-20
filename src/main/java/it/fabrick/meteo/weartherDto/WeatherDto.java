package it.fabrick.meteo.weartherDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WeatherDto {
    private String city;
    private DailyDto daily;
}
