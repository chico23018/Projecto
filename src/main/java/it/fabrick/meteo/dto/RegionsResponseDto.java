package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter

public class RegionsResponseDto {
    private Long id_regione;


    private String regione;

    private Double superficie;

    private Integer num_residenti;

    private String presidente;


    private List<CitiesResponseDto> cities;

    private List<ProvinciesResponseDto> provincies;
}
