package it.fabrick.meteo.dto.dtoCity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CitiesResponseDto {
    @Schema(description = " codice istat")
    private Long istat;
    @Schema(description = "name municipality")
    private String comune;

    @Schema(description = "  telephone prefixl")
    private String prefisso;
    @Schema(description = "codice fiscale")
    private String codFisco;
    @Schema(description = "surface city ")
    private Double superficie;
    @Schema(description = "number resident")
    private Integer numResident;


}
