package it.fabrick.meteo.model;

import it.fabrick.meteo.entity.CitiesEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class MunicipalityModel {

    private Long idMunicipality;
    private String municipality;
    private String regions;
    private String provincia;
    private String address;
}
