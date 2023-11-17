package it.fabrick.meteo.model;

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


    private List<CitiesModel> cities;


}
