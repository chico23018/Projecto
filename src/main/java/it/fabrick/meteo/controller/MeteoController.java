package it.fabrick.meteo.controller;

import it.fabrick.meteo.classEnum.Provincie;
import it.fabrick.meteo.classEnum.Regions;
import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.dto.MunicipalityResponseDto;
import it.fabrick.meteo.mapper.*;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.service.*;
import it.fabrick.meteo.weartherDto.WeatherDto;
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
    private final ProvinciesService provinciesService;
    private final MunicipalityService municipalityService;
    private final ICitiesMapper iCitiesMapper;
    private final IProvinciesMapper iProvinciesMapper;
    private final IRegionsMapper iRegionsMapper;
    private  final IMunicipalityMapper iMunicipalityMapper;


    @GetMapping
    public ResponseEntity<List<CitiesResponseDto>> readGreatTha(@RequestParam(name = "resident") int id) {
        List<CitiesResponseDto> responseDtos = citiesService.readGreat(id)
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreatByRegion(@RequestParam(name = "regions") Regions regions
            ,@RequestParam(name = "resident")int resident) {

      List<MunicipalityModel> municipalityModel= municipalityService.readMunicipalityGreatByRegion(resident,regions.name());

        System.out.println(municipalityModel.size());
        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }
    @GetMapping("/provincia")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreatByProvincia(@RequestParam(name = "regions") Provincie provincie
            ,@RequestParam(name = "resident")int resident) {

        List<MunicipalityModel> municipalityModel= municipalityService.readMunicipalityGreatByProvinvia(resident,provincie.name(),null);

        System.out.println(municipalityModel.size());
        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }
    @GetMapping("/forecast")
    public ResponseEntity<WeatherDto> readForecast(@RequestParam(name = "forecast") String provincie) {

        citiesService.dto(provincie);

        return ResponseEntity.ok( citiesService.dto(provincie));
    }

}
