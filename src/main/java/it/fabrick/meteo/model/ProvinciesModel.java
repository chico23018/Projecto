package it.fabrick.meteo.model;

import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.dto.RegionsResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class ProvinciesModel {
    private String sigla;

    private String provincia;

    private Double superficie;

    private Integer residenti;


    private List<CitiesResponseDto> cities;


    private RegionsResponseDto id_regione;
}
