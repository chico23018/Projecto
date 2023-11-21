package it.fabrick.meteo.dto.dtoMunicipality    ;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class MunicipalityRequestCreateDto {
    @Schema(description = "municipality ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")

    private String municipality;
    @Schema(description = "regions ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String regions;
    @Schema(description = "province ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String provincia;
    @Schema(description = "address ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String address;
}
