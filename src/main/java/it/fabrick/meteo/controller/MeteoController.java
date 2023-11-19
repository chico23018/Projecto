package it.fabrick.meteo.controller;

import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.dto.MunicipalityResponseDto;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.service.CitiesService;
import it.fabrick.meteo.service.MunicipalityService;
import it.fabrick.meteo.service.ProvinciesService;
import it.fabrick.meteo.service.RegionsService;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
    private final IMunicipalityMapper iMunicipalityMapper;


    @GetMapping
    public ResponseEntity<List<CitiesResponseDto>> readGreatTha(@RequestParam(name = "resident") int id) {
        List<CitiesResponseDto> responseDtos = citiesService.readGreat(id)
                .stream()
                .map(iCitiesMapper::responseFromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreatByRegion(@RequestParam(name = "regions") String region
            , @RequestParam(name = "resident") int resident) {

        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreatByRegion(resident, region);

        System.out.println(municipalityModel.size());
        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }

    @GetMapping("/provincia")
    public ResponseEntity<List<MunicipalityResponseDto>> readMunicipalityGreatByProvincia(@RequestParam(name = "provincia") String provincia
            , @RequestParam(name = "resident") int resident) {

        List<MunicipalityModel> municipalityModel = municipalityService.readMunicipalityGreatByProvinvia(resident, provincia, null);

        System.out.println(municipalityModel.size());
        return ResponseEntity.ok(municipalityModel.stream()
                .map(iMunicipalityMapper::responseFromModel)
                .collect(Collectors.toList()));
    }


    @GetMapping("/forecast")
    public ResponseEntity<WeatherResponseDto> readForecastCity(@RequestParam(name = "city") String city) {


        return ResponseEntity.ok(citiesService.readForecastCity(city));
    }

    @GetMapping("/forecastDate")
    public ResponseEntity<WeatherResponseDto> readForecastDate(@RequestParam(name = "city") String city,
                                                               @RequestParam(name = "start") String start,
                                                               @RequestParam(name = "end") String end
    ) {


        return ResponseEntity.ok(citiesService.readForecastDate(city, LocalDate.parse(start), LocalDate.parse(end)));
    }

    @GetMapping("/forecastProvincia")
    public ResponseEntity<String> readForecasProvicia(@RequestParam(name = "provicia") String provicia,
                                                      @RequestParam(name = "start") String date

    ) {

        String media = citiesService.readForecastProvincia(provicia, LocalDate.parse(date));


        return ResponseEntity.ok(media);
    }

}
