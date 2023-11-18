package it.fabrick.meteo.DataJson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipalityJson {
    private Long istat;
    private String comune;
    private String regione;
    private String provincia;
    private String indirizzo;
}
