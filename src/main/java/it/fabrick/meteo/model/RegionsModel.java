package it.fabrick.meteo.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class RegionsModel{
    private Long idRegions;
    private String regione;
    private Double superficie;
    private Integer numResidenti;
    private String presidente;
    private String codIstat;
    private Long codFiscale;
    private Long piva;
    private String pec;
    private String sito;
    private String sede;
}
