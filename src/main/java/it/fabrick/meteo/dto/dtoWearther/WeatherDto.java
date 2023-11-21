package it.fabrick.meteo.dto.dtoWearther;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WeatherDto {
    private String city;
    private DailyDto daily;
}
