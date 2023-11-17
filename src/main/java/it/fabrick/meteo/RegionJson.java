package it.fabrick.meteo;

import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.ProvinciesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegionJson {

    private Long id_regione;

    private String regione;

    private Double superficie;

    private Integer num_residenti;




    private long num_comuni;
    private long num_provincie;
    private String presidente;
    private String cod_istat;

    private String cod_fiscale;

    private String piva;

    private String pec;

    private String sito;

    private String sede;
}
