package it.fabrick.meteo.DataJson;

import it.fabrick.meteo.dto.CitiesResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProvinJson {
    private String sigla;

    private String provincia;

    private Double superficie;

    private Integer residenti;

    private Long id_regione;

}
