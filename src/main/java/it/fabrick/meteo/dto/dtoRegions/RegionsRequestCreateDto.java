package it.fabrick.meteo.dto.dtoRegions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter

public class RegionsRequestCreateDto {
    @Schema(description = "regione ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "[\\w\\s\\'\\-]+",message = "Only letters, spaces, - and '  are accepted ")
    private String regione;
    @Min(value = 1, message = "Should not be 0")
    private Double superficie;
    @Min(value = 1, message = "Should not be 0")
    private Integer numResidenti;
    @Schema(description = "presidente ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "[\\w\\s\\'\\-]+",message = "Only letters, spaces, - and '  are accepted ")
    private String presidente;
    @Schema(description = "take the place ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "[\\w\\s]+",message = "Only letters, spaces, - and '  are accepted ")
    private String codIstat;
    @Schema(description = "cod fiscal ")
    @Min(value = 1, message = "Should not be 0")
    private Long codFiscale;
    @Schema(description = "piva ")
    @Min(value = 1, message = "Should not be 0")
    private Long piva;
    @Schema(description = "pec")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "EMAIL",message = "Should contains  EMAIL")
    private String pec;
    @Schema(description = "sito ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp = "[\\w\\s\\.]+",message = "Only letters, spaces and dot  are accepted ")
    private String sito;
    @Schema(description = "sede")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String sede;


}
