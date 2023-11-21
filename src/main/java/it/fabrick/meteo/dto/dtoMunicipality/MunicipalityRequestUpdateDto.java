package it.fabrick.meteo.dto.dtoMunicipality;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MunicipalityRequestUpdateDto {

    private String municipality;
    private String regions;
    private String provincia;
    private String address;
}
