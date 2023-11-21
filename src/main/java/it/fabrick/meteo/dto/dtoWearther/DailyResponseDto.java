package it.fabrick.meteo.dto.dtoWearther;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class DailyResponseDto {
    private List<String> time;
    private List<Float> temperature;


    private Float mediaTemperature;

    public List<Float> getTemperature() {

        return temperature;
    }


}
