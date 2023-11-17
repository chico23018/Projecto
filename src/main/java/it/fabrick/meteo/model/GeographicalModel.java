package it.fabrick.meteo.model;

import it.fabrick.meteo.dto.CitiesResponseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GeographicalModel {

    private Long id;

    private Double lng;

    private Double lat;

    private CitiesResponseDto istat;
}
