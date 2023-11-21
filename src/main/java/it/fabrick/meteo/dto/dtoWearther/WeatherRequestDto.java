package it.fabrick.meteo.dto.dtoWearther;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class WeatherRequestDto {

    @Schema(description = "take the place ")
    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    public String place;
    @Schema(description = "The date")
    @NotNull(message = "Should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate date;

}

