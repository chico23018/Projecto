package it.fabrick.meteo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class CitiesModel {
    private Long istat;

    private String comune;

    private Integer prefisso;

    private Integer cod_fisco;

    private Double superficie;

    private Integer num_residenti;

    private GeographicalModel geographical;

}
