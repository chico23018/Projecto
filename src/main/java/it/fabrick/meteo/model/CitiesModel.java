package it.fabrick.meteo.model;

import it.fabrick.meteo.dto.GeographicalRequestDto;
import it.fabrick.meteo.dto.ProvinciesRequestDto;
import it.fabrick.meteo.dto.RegionsRequestDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class CitiesModel {
    private Long istat;

    private String comune;

    private Integer prefisso;

    private Integer cod_fisco;

    private Double superficie;

    private Integer num_residenti;


    private RegionsRequestDto id_regione;
    private ProvinciesRequestDto provincia;

    private GeographicalRequestDto geographical;
}
