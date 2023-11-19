package it.fabrick.meteo.weartherDto;

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
        mediaTempera();
        return temperature;
    }

    private void mediaTempera() {
    if(temperature.size()>1){


      mediaTemperature=  temperature.stream()
              .reduce(Float::sum).get()/ temperature.size()
        ;
    }
    }
}
