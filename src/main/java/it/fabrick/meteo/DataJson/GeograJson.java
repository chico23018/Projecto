package it.fabrick.meteo.DataJson;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GeograJson {
    private Long istat;

    private BigDecimal lng;

    private BigDecimal lat;
}
