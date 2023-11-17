package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
public class GeographicalResponseDto {

    private Long id;

    private Double lng;

    private Double lat;

    private CitiesResponseDto istat;
}
