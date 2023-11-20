package it.fabrick.meteo.dto;

import it.fabrick.meteo.entity.GeographicalEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class CitiesResponseDto {

    private Long istat;

    private String comune;

    private String prefisso;

    private String codFisco;

    private Double superficie;

    private Integer numResident;



}
