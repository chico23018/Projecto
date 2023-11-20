package it.fabrick.meteo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter

public class RegionsResponseDto {
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
