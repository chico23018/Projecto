package it.fabrick.meteo.dto.dtoCity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter

public class CitiesRequestCreateDto {
    @Schema(description = "regione ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "[\\w\\s\\'\\-]+",message = "Only letters, spaces, - and '  are accepted ")
    private String comune;

    @Schema(description = "prefix")
    @Min(value = 1, message = "Should not be 0")
    private Integer prefisso;

    @Schema(description = "regione ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String codFisco;
    @Schema(description = "surface")
    @Min(value = 1, message = "Should not be 0")
    private Double superficie;
    @Schema(description = "resident")
    @Min(value = 1, message = "Should not be 0")
    private Integer numResident;


}
