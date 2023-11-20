package it.fabrick.meteo.weartherDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WeatherDays {
    @Schema(description = "take the place ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String place;
    @Schema(description = "days  ")
    @Min(value = 1, message = "Should not be 0")
    @Max(value = 16, message = "Should not be greater 16")
    private Integer days;
}
