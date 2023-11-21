package it.fabrick.meteo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class RequestNunResident {

    @Min(value = 1, message = "Should not be 0")
    private Integer numResident;


}
