package it.fabrick.meteo.dto.dtoWearther;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class DailyDto {

    private List<String> time;
    private List<String> temperature_2m_max;


}
