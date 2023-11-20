package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MunicipalityRequestDto {

    private String municipality;
    private String regions;
    private String provincia;
    private String address;
}
