package it.fabrick.meteo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RequestNunResidentAndPlace {

    @Schema(description = "take the place ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Pattern(regexp =  "[\\w\\s\\-\\']+")
    private String place;
    @Schema(description = "number resident")
    @Min(value = 1, message = "Should not be 0")
    private Integer numResident;


}
