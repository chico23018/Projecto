package it.fabrick.meteo.dto.dtoRegions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class RegionsResponseDto {
    @Schema(description = "id regions")
    private Long idRegions;
    @Schema(description = "name region")
    private String regione;
    @Schema(description = "surface region ")
    private Double superficie;
    @Schema(description = "number resident")
    private Integer numResidenti;
    @Schema(description = "presidente region")
    private String presidente;
    @Schema(description = " codice istat")
    private String codIstat;
    @Schema(description = "codice fiscale")
    private Long codFiscale;
    @Schema(description = "Partita IVA")
    private Long piva;
    @Schema(description = "pec region")
    private String pec;
    @Schema(description = "web site")
    private String sito;
    @Schema(description = "address")
    private String sede;


}
