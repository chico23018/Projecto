package it.fabrick.meteo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.fabrick.meteo.dto.ErrorResponseDto;
import it.fabrick.meteo.dto.RequestNunResident;
import it.fabrick.meteo.dto.RequestNunResidentAndPlace;
import it.fabrick.meteo.dto.dtoCity.CitiesRequestCreateDto;
import it.fabrick.meteo.dto.dtoCity.CitiesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoCity.CitiesResponseDto;
import it.fabrick.meteo.dto.dtoMunicipality.MunicipalityResponseDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestCreateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesResponseDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestCreateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestUpdateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsResponseDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherDays;
import it.fabrick.meteo.dto.dtoWearther.WeatherRequestDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherResponseDto;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.model.ProvinciesModel;
import it.fabrick.meteo.model.RegionsModel;
import it.fabrick.meteo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @Operation(description = "create regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),

            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<RegionsResponseDto> createRegions(@RequestBody RegionsRequestCreateDto regionsRequestCreateDto) {
        validationService.doValidate(regionsRequestCreateDto);
        RegionsModel regionsModel = regionsService.createRegions(iRegionsMapper.modelFromRequest(regionsRequestCreateDto));
        return ResponseEntity.created(URI.create("/v1.0/weather/" + regionsModel.getIdRegions())).body(iRegionsMapper.responseFromModel(regionsModel));
    }

    @Operation(description = "put regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{idRegions}")
    public ResponseEntity<RegionsResponseDto> updateRegions(@PathVariable(name = "idRegions") long id,
                                                            @RequestBody RegionsRequestUpdateDto regionsRequestUpdateDto) {
        RegionsModel regionsModel = regionsService.updateRegions
                (id,
                        iRegionsMapper
                                .modelFromRequestUpdate(regionsRequestUpdateDto));
        return ResponseEntity.ok(iRegionsMapper.responseFromModel(regionsModel));
    }

    @Operation(description = "delete regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/{idRegions}")
    public ResponseEntity<Void> deleteRegions(@PathVariable(name = "idRegions") Long id) {
        regionsService.deleteRegions(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "read province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{idRegions}/province")
    public ResponseEntity<List<ProvinciesResponseDto>> readProvince(@PathVariable("idRegions") Long idRegions) {
        List<ProvinciesResponseDto> regionsResponseDtos = provinciesService.readProvincies(idRegions)
                .stream()
                .map(iProvinciesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionsResponseDtos);
    }

    @Operation(description = "create province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),

            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/{idRegions}/province")
    public ResponseEntity<ProvinciesResponseDto> createProvince(@PathVariable(name = "idRegions") Long idRegions,
                                                                @RequestBody
                                                                ProvinciesRequestCreateDto provinciesRequestCreateDto) {
        validationService.doValidate(provinciesRequestCreateDto);
        ProvinciesModel provinciesModel = provinciesService.createProvincies(idRegions, iProvinciesMapper.modelFromRequest(provinciesRequestCreateDto));
        return ResponseEntity.created(URI.create("/v1.0/weather/"+idRegions+"/province/" + provinciesModel.getSigla())).body(iProvinciesMapper.responseFromModel(provinciesModel));
    }

    @Operation(description = "put province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{idRegions}/province/{sigla}")
    public ResponseEntity<ProvinciesResponseDto> updateProvince(@PathVariable(name = "idRegions") Long idRegions,
                                                                @PathVariable(name = "sigla") String sigla,
                                                                @RequestBody ProvinciesRequestUpdateDto requestUpdateDto) {
        ProvinciesModel provinciesModel = provinciesService.updateProvincies
                (idRegions, sigla, iProvinciesMapper.modelFromRequestUpdate(requestUpdateDto));
        return ResponseEntity.ok(iProvinciesMapper.responseFromModel(provinciesModel));
    }

    @Operation(description = "delete province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/{idRegions}/province/{sigla}")
    public ResponseEntity<Void> deleteProvince(@PathVariable(name = "idRegions") Long idRegions,
                                               @PathVariable(name = "sigla") String sigla) {
        provinciesService.deleteProvincies(idRegions, sigla);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "read cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{idRegions}/province/{sigla}/cities")
    public ResponseEntity<List<CitiesResponseDto>> readCities(@PathVariable("idRegions") Long idRegions,
                                                              @PathVariable("sigla") String sigla) {
        List<CitiesResponseDto> regionsResponseDtos = citiesService.readCities(idRegions, sigla)
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionsResponseDtos);
    }

    @Operation(description = "create Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),

            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/{idRegions}/province/{sigla}/cities")
    public ResponseEntity<CitiesResponseDto> createCities(@PathVariable(name = "idRegions") Long idRegions,
                                                          @PathVariable(name = "sigla") String sigla,

                                                          @RequestBody
                                                          CitiesRequestCreateDto citiesRequestCreateDto) {
        validationService.doValidate(citiesRequestCreateDto);
        CitiesModel citiesModel = citiesService.createCities
                (idRegions, sigla, iCitiesMapper.modelFromRequest(citiesRequestCreateDto));
        return ResponseEntity.created(URI.create("/v1.0/weather/" + idRegions + "/province/" + sigla + "/cities/" + citiesModel.getIstat()))
                .body(iCitiesMapper.responseFromModel(citiesModel));
    }

    @Operation(description = "put Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/{idRegions}/province/{sigla}/cities/{istat}")
    public ResponseEntity<CitiesResponseDto> updateCities(@PathVariable(name = "idRegions") Long idRegions,
                                                          @PathVariable(name = "sigla") String sigla,
                                                          @PathVariable(name = "istat") Long istat,
                                                          @RequestBody CitiesRequestUpdateDto citiesRequestUpdateDto) {
        CitiesModel citiesModel = citiesService.updateCities
                (idRegions, sigla, istat, iCitiesMapper.modelFromRequestUpdate(citiesRequestUpdateDto));
        return ResponseEntity.ok(iCitiesMapper.responseFromModel(citiesModel));
    }

    @Operation(description = "delete Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/{idRegions}/province/{sigla}/cities/{istat}")
    public ResponseEntity<Void> deleteCities(@PathVariable(name = "idRegions") Long idRegions,
                                             @PathVariable(name = "sigla") String sigla,
                                             @PathVariable(name = "istat") Long istat) {
        citiesService.deleteCities(idRegions, sigla, istat);
        return ResponseEntity.noContent().build();
    }


    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/numResident/city")
    public ResponseEntity<List<CitiesResponseDto>> readGreaterTha(@RequestBody RequestNunResident requestNunResident) {
        validationService.doValidate(requestNunResident);
        List<CitiesResponseDto> responseDtos = citiesService.readGreater(requestNunResident.getNumResident())
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/numResident/regions")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreaterByRegion(@RequestBody RequestNunResidentAndPlace requestNunResidentAndPlace) {
        validationService.doValidate(requestNunResidentAndPlace);
        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByRegion(requestNunResidentAndPlace.getNumResident(), requestNunResidentAndPlace.getPlace());


        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

    @Operation(description = "read city greater than a number inhabitants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/numResident/provincia")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreaterByProvince(@RequestBody RequestNunResidentAndPlace requestNunResidentAndPlace) {
        validationService.doValidate(requestNunResidentAndPlace);

        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByProvinvia(requestNunResidentAndPlace.getNumResident(), requestNunResidentAndPlace.getPlace());


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

    @PostMapping("/forecast/{city}")
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
    @PostMapping("/forecast/days")
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
    @PostMapping("/forecast/date")
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
    @PostMapping("/forecast/provincia")
    public ResponseEntity<String> readForecasProvicia(@Schema(description = "date formatter yyyy-MM-dd", implementation = WeatherRequestDto.class)
                                                      @RequestBody WeatherRequestDto weatherRequestDto) {
        validationService.doValidate(weatherRequestDto);
        String media = citiesService.readForecastProvincia(weatherRequestDto.getPlace(), weatherRequestDto.getDate());


        return ResponseEntity.ok(media);
    }

}
