package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter

public class ProvinciesResponseDto {
    private String sigla;

    private String provincia;

    private Double superficie;

    private Integer residenti;


    private List<CitiesResponseDto> cities;

}
