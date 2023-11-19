package it.fabrick.meteo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class GeographicalModel {

    private Long idGeographical;

    private BigDecimal lng;

    private BigDecimal lat;


}
