package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class GeographicalRequestDto {

    private BigDecimal lng;

    private BigDecimal lat;


}
