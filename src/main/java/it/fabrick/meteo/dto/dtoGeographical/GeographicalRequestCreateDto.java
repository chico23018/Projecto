package it.fabrick.meteo.dto.dtoGeographical;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class GeographicalRequestCreateDto {

    private BigDecimal lng;

    private BigDecimal lat;


}
