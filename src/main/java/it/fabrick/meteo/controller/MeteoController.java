package it.fabrick.meteo.controller;

import it.fabrick.meteo.service.RegionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/weather")
@AllArgsConstructor
public class MeteoController {
    private final RegionsService regionsService;

}
