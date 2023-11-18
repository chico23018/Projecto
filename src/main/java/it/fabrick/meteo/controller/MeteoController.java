package it.fabrick.meteo.controller;

import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IGeographicalMapper;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.service.CitiesService;
import it.fabrick.meteo.service.GeographicalService;
import it.fabrick.meteo.service.ProvinciesService;
import it.fabrick.meteo.service.RegionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1.0/weather")
@AllArgsConstructor
public class MeteoController {
    private final RegionsService regionsService;
    private final CitiesService citiesService;
    private final GeographicalService geographicalService;
    private final ProvinciesService provinciesService;
    private final IGeographicalMapper iGeographicalMapper;
    private final ICitiesMapper iCitiesMapper;
    private final IProvinciesMapper iProvinciesMapper;
    private final IRegionsMapper iRegionsMapper;

    @GetMapping
    public ResponseEntity<List<CitiesResponseDto>> readGreatTha(@RequestParam(name = "resident") int id) {
        List<CitiesResponseDto> responseDtos = citiesService.readGreat(id)
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

}
