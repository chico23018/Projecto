package it.fabrick.meteo.controller.weather;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.fabrick.meteo.dto.ErrorResponseDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherDays;
import it.fabrick.meteo.dto.dtoWearther.WeatherRequestDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherResponseDto;
import it.fabrick.meteo.service.CitiesService;
import it.fabrick.meteo.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static it.fabrick.meteo.constant.Constant.path;
import static it.fabrick.meteo.constant.Constant.searchForecast;

@RestController
@RequestMapping(path)
@AllArgsConstructor
public class MeteoController {

    private final CitiesService citiesService;
    private final ValidationService validationService;




    @Operation(description = "read weather forecast for city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @PostMapping(searchForecast+"/{city}")
    public ResponseEntity<WeatherResponseDto> readForecastCity(@PathVariable(name = "city") String city) {


        return ResponseEntity.ok(citiesService.readForecastCity(city));
    }

    @Operation(description = "read weather forecast for city and days ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(searchForecast+"/days")
    public ResponseEntity<WeatherResponseDto> readForecastDays(@Schema(description = "days Should not be greater 16 days ", implementation = WeatherDays.class)
                                                               @RequestBody WeatherDays days) {
        validationService.doValidate(days);

        return ResponseEntity.ok(citiesService.readForecastDays(days.getPlace(), days.getDays()));
    }

    @Operation(description = "read weather forecast for city and date ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(searchForecast+"/date")
    public ResponseEntity<WeatherResponseDto> readForecastDate(@Schema(description = "date formatter yyyy-MM-dd", implementation = WeatherRequestDto.class)
                                                               @RequestBody WeatherRequestDto days) {
        validationService.doValidate(days);


        return ResponseEntity.ok(citiesService.readForecastDate(days.getPlace(), days.getDate()));
    }

    @Operation(description = "read weather forecast for province and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(searchForecast+"/provincia")
    public ResponseEntity<String> readForecasProvicia(@Schema(description = "date formatter yyyy-MM-dd", implementation = WeatherRequestDto.class)
                                                      @RequestBody WeatherRequestDto weatherRequestDto) {
        validationService.doValidate(weatherRequestDto);
        String media = citiesService.readForecastProvincia(weatherRequestDto.getPlace(), weatherRequestDto.getDate());


        return ResponseEntity.ok(media);
    }

}
