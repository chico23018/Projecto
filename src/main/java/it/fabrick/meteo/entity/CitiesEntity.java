package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity(name = "CITTA")
public class CitiesEntity {
    @Id
    @Column(name = "istat")
    private Long istat;

    @Column(name = "comune")
    private String comune;
    @Column(name = "prefisso")
    private Integer prefisso;

    @Column(name = "cod_fisco")
    private String cod_fisco;

    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "num_residenti")
    private Integer numResident;

    @ManyToOne
    @JoinColumn(name = "id_regione", referencedColumnName = "id_regione")
    private RegionsEntity regions;


    @ManyToOne
    @JoinColumn(name = "sigla", referencedColumnName = "sigla")
    private ProvinciesEntity provincia;


    /*@OneToMany(mappedBy = "cities")
    private List<GeographicalEntity> geographical;*/
    @OneToOne(mappedBy = "cities")
    private GeographicalEntity geographical;
}
