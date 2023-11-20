package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class RegionsRequestDto {

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
