package it.fabrick.meteo.DataJson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitiesJson {
    private Long istat;

    private String comune;

    private Integer prefisso;

    private String cod_fisco;

    private Double superficie;

    private Integer num_residenti;
    private String regione;
    private String provincia;

}
