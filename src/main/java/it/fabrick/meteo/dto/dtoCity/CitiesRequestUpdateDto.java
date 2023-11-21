package it.fabrick.meteo.dto.dtoCity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class CitiesRequestUpdateDto {

    private String comune;

    private Integer prefisso;

    private String codFisco;

    private Double superficie;

    private Integer numResident;


}
