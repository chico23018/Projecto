package it.fabrick.meteo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MunicipalityResponseDto {
    @Schema(description = "id  Municipality")
    private Long idMunicipality;
    @Schema(description = "name municipality")
    private String municipality;
    @Schema(description = "name region")
    private String regions;
    @Schema(description = "name provincia")
    private String provincia;
    @Schema(description = "address")
    private String address;
}
