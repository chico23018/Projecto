package it.fabrick.meteo.model;

import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.dto.ProvinciesResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class RegionsModel{
    private Long id_regione;


    private String regione;

    private Double superficie;

    private Integer num_residenti;

    private String presidente;


    private List<CitiesModel> cities;

    private List<ProvinciesResponseDto> provincies;
}
