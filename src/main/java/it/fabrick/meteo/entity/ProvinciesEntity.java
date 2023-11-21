package it.fabrick.meteo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "provincia")
    private List<CitiesEntity> cities;

    @ManyToOne
    @JoinColumn(
            name = "id_regione",
            referencedColumnName = "id_regione")

    private RegionsEntity regions;

    @PrePersist
    private void upperCase() {
        sigla= sigla.toUpperCase();
       provincia= provincia.toUpperCase();
    }

}
