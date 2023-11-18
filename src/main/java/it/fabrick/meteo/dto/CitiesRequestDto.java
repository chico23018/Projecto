package it.fabrick.meteo.dto;

import it.fabrick.meteo.entity.GeographicalEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class CitiesRequestDto {
    private Long istat;

    private String comune;

    private Integer prefisso;

    private String cod_fisco;

    private Double superficie;

    private Integer numResident;


}
