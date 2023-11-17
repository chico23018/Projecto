package it.fabrick.meteo.dto;

import it.fabrick.meteo.entity.GeographicalEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter

public class CitiesResponseDto {

    private Long istat;

    private String comune;

    private Integer prefisso;

    private Integer cod_fisco;

    private Double superficie;

    private Integer num_residenti;

  /*  private GeographicalResponseDto geographical;*/

 /* private List<GeographicalResponseDto> geographical;*/

}
