package it.fabrick.meteo.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class CitiesModel {
    private Long istat;

    private String comune;

    private Integer prefisso;

    private String cod_fisco;

    private Double superficie;

    private Integer numResident;

}
