package it.fabrick.meteo.model;

import it.fabrick.meteo.entity.RegionsEntity;
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
