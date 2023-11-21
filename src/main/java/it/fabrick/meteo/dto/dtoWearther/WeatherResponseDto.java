package it.fabrick.meteo.dto.dtoWearther;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WeatherResponseDto {
    private String city;
    private DailyResponseDto daily;
}
