package it.fabrick.meteo.dto.dtoProvince;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class ProvinciesResponseDto {
    @Schema(description = "abbreviation of the province")
    private String sigla;
    @Schema(description = "name provincia")
    private String provincia;
    @Schema(description = "surface provincia ")
    private Double superficie;
    @Schema(description = "number resident")
    private Integer residenti;


  //  private List<CitiesResponseDto> cities;

}
