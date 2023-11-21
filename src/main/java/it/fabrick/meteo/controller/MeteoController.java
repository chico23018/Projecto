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
        return ResponseEntity.created(URI.create("/v1.0/weather" + regionsModel.getRegione())).body(iRegionsMapper.responseFromModel(regionsModel));
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
    @PutMapping("/{id}")
    public ResponseEntity<RegionsResponseDto> updateRegions(@PathVariable(name = "id") long id,
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegions(@PathVariable(name = "id") Long id) {
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
    @GetMapping("/{regions}/province")
    public ResponseEntity<List<ProvinciesResponseDto>> readProvince(@PathVariable("regions") String region) {
        List<ProvinciesResponseDto> regionsResponseDtos = provinciesService.readProvincies(region)
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
    @PostMapping("/{regions}/province")
    public ResponseEntity<ProvinciesResponseDto> createProvince(@PathVariable(name = "regions") String regions,
                                                                @RequestBody
                                                                ProvinciesRequestCreateDto provinciesRequestCreateDto) {
        validationService.doValidate(provinciesRequestCreateDto);
        ProvinciesModel provinciesModel = provinciesService.createProvincies(regions, iProvinciesMapper.modelFromRequest(provinciesRequestCreateDto));
        return ResponseEntity.created(URI.create("/v1.0/weather" + provinciesModel.getSigla())).body(iProvinciesMapper.responseFromModel(provinciesModel));
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
    @PutMapping("/{regions}/province/{sigla}")
    public ResponseEntity<ProvinciesResponseDto> updateProvince(@PathVariable(name = "regions") String regions,
                                                                @PathVariable(name = "sigla") String sigla,
                                                                @RequestBody ProvinciesRequestUpdateDto requestUpdateDto) {
        ProvinciesModel provinciesModel = provinciesService.updateProvincies
                (regions, sigla, iProvinciesMapper.modelFromRequestUpdate(requestUpdateDto));
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
    @DeleteMapping("/{regions}/province/{sigla}")
    public ResponseEntity<Void> deleteProvince(@PathVariable(name = "regions") String regions, @PathVariable(name = "sigla") String sigla) {
        provinciesService.deleteProvincies(regions, sigla);
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
    @GetMapping("/{region}/province/{province}/cities")
    public ResponseEntity<List<CitiesResponseDto>> readCities(@PathVariable("region") String region,
                                                              @PathVariable("province") String province) {
        List<CitiesResponseDto> regionsResponseDtos = citiesService.readCities(region, province)
                .stream()
                .map(iCitiesMapper::responseFromModel)
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
    @PostMapping("/{region}/province/{province}/cities")
    public ResponseEntity<CitiesResponseDto> createCities(@PathVariable(name = "region") String regions,
                                                          @PathVariable(name = "province") String province,

                                                          @RequestBody
                                                          CitiesRequestCreateDto citiesRequestCreateDto) {
        validationService.doValidate(citiesRequestCreateDto);
        CitiesModel citiesModel = citiesService.createCities
                (regions, province, iCitiesMapper.modelFromRequest(citiesRequestCreateDto));
        return ResponseEntity.created(URI.create("/v1.0/weather/" + regions + "/province/" + province + "/cities" + citiesModel.getIstat()))
                .body(iCitiesMapper.responseFromModel(citiesModel));
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
    @PutMapping("/{region}/province/{province}/cities/{istat}")
    public ResponseEntity<CitiesResponseDto> updateCities(@PathVariable(name = "region") String regions,
                                                          @PathVariable(name = "province") String province,
                                                          @PathVariable(name = "istat") String istat,
                                                          @RequestBody CitiesRequestUpdateDto citiesRequestUpdateDto) {
        CitiesModel citiesModel = citiesService.updateCities
                (regions, province, istat, iCitiesMapper.modelFromRequestUpdate(citiesRequestUpdateDto));
        return ResponseEntity.ok(iCitiesMapper.responseFromModel(citiesModel));
    }

    @Operation(description = "delete province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/{region}/province/{province}/cities/{istat}")
    public ResponseEntity<Void> deleteCities(@PathVariable(name = "region") String regions,
                                             @PathVariable(name = "province") String province,
                                             @PathVariable(name = "istat") String istat) {
        citiesService.deleteCities(regions, province,istat);
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
