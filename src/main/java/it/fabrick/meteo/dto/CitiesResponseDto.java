package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

public class CitiesResponseDto {

    private Long istat;

    private String comune;

    private Integer prefisso;

    private Integer cod_fisco;

    private Double superficie;

    private Integer num_residenti;


    private RegionsResponseDto id_regione;
    private ProvinciesResponseDto provincia;

    private GeographicalResponseDto geographical;
}