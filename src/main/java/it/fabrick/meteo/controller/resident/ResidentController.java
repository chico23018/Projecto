package it.fabrick.meteo.controller.resident;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.fabrick.meteo.dto.ErrorResponseDto;
import it.fabrick.meteo.dto.RequestNunResident;
import it.fabrick.meteo.dto.RequestNunResidentAndPlace;
import it.fabrick.meteo.dto.dtoCity.CitiesResponseDto;
import it.fabrick.meteo.dto.dtoMunicipality.MunicipalityResponseDto;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


import static it.fabrick.meteo.constant.Constant.*;

@RestController
@RequestMapping(path3)
@AllArgsConstructor
public class ResidentController {
    private final CitiesService citiesService;
    private final MunicipalityService municipalityService;
    private final ValidationService validationService;
    private final ICitiesMapper iCitiesMapper;

    private final IMunicipalityMapper iMunicipalityMapper;
    @Operation(description = "read city greater than a number inhabitants the Italy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(search+"/city")
    public ResponseEntity<List<CitiesResponseDto>> readGreaterTha(@RequestBody RequestNunResident requestNunResident) {
        validationService.doValidate(requestNunResident);
        List<CitiesResponseDto> responseDtos = citiesService.readGreater(requestNunResident.getNumResident())
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(description = "read city greater than a number inhabitants for regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(search+"/regions")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreaterByRegion(@RequestBody RequestNunResidentAndPlace requestNunResidentAndPlace) {
        validationService.doValidate(requestNunResidentAndPlace);
        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByRegion(requestNunResidentAndPlace.getNumResident(), requestNunResidentAndPlace.getPlace());


        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

    @Operation(description = "read city greater than a number inhabitants for province")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "DATA NOT VALID",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(search+"/provincia")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreaterByProvince(@RequestBody RequestNunResidentAndPlace requestNunResidentAndPlace) {
        validationService.doValidate(requestNunResidentAndPlace);

        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreaterByProvinvia(requestNunResidentAndPlace.getNumResident(), requestNunResidentAndPlace.getPlace());


        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

}
