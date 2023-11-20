package it.fabrick.meteo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.dto.*;
import it.fabrick.meteo.exception.DataNotValidException;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.service.*;
import it.fabrick.meteo.weartherDto.WeatherRequestDto;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1.0/weather")
@AllArgsConstructor
public class MeteoController {
    private final RegionsService regionsService;
    private final CitiesService citiesService;
    private final ProvinciesService provinciesService;
    private final MunicipalityService municipalityService;
    private final ValidationService validationService;
    private final ICitiesMapper iCitiesMapper;
    private final IProvinciesMapper iProvinciesMapper;
    private final IRegionsMapper iRegionsMapper;
    private final IMunicipalityMapper iMunicipalityMapper;
    @Operation(description = "read regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<RegionsResponseDto>> readRegions() {
        List<RegionsResponseDto> regionsResponseDtos = regionsService.readRegions()
                .stream()
                .map(iRegionsMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionsResponseDtos);
    }
    @Operation(description = "read province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("{regions}/province")
    public ResponseEntity<List<ProvinciesResponseDto>> readProvince() {
        List<ProvinciesResponseDto> regionsResponseDtos = provinciesService.readProvincies()
                .stream()
                .map(iProvinciesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionsResponseDtos);
    }
    @Operation(description = "read cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("{regions}/province/{province}/cities")
    public ResponseEntity<List<CitiesResponseDto>> readCities() {
        List<CitiesResponseDto> regionsResponseDtos = citiesService.readCities()
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionsResponseDtos);
    }

    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/city")
    public ResponseEntity<List<CitiesResponseDto>> readGreaterTha(@RequestParam(name = "resident")

                                                                  Integer resident) {
        validationService.doValidate(resident);
        List<CitiesResponseDto> responseDtos = citiesService.readGreater(resident)
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/regions")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreaterByRegion(@RequestParam(name = "regions") String region
            , @RequestParam(name = "resident") int resident) {
        System.out.println(resident);
        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByRegion(resident, region);


        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/provincia")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreatByProvincia(@RequestParam(name = "provincia") String provincia
            , @RequestParam(name = "resident") int resident) {

        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByProvinvia(resident, provincia, null);


        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

    @Operation(description = "read weather forecast for city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })

    @PostMapping("/forecast")
    public ResponseEntity<WeatherResponseDto> readForecastCity(@RequestParam(name = "city") String city) {


        return ResponseEntity.ok(citiesService.readForecastCity(city));
    }

    @Operation(description = "read weather forecast for city and date ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/forecast/date")
    public ResponseEntity<WeatherResponseDto> readForecastDate(@RequestBody WeatherRequestDto weatherRequestDto) {
        validationService.doValidate(weatherRequestDto);

        return ResponseEntity.ok(citiesService.readForecastDate(weatherRequestDto.getPlace(), weatherRequestDto.getDate()));
    }

    @Operation(description = "read weather forecast for provincia and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/forecast/provincia")
    public ResponseEntity<String> readForecasProvicia(@RequestBody WeatherRequestDto weatherRequestDto) {
        validationService.doValidate(weatherRequestDto);
        String media = citiesService.readForecastProvincia(weatherRequestDto.getPlace(), weatherRequestDto.getDate());


        return ResponseEntity.ok(media);
    }

}
