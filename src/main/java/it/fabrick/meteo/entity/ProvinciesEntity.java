package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "PROVINCIA")
public class ProvinciesEntity {
    @Id
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "residenti")
    private Integer residenti;

    @OneToMany
    private List<CitiesEntity> cities;

    @ManyToOne
    @JoinColumn(
            name = "id_regione",
            referencedColumnName = "id_regione" )
    private RegionsEntity regions;

}