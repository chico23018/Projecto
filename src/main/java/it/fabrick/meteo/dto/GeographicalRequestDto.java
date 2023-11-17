package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GeographicalRequestDto {

    private Long id;

    private Double lng;

    private Double lat;

    private String istat;
}
