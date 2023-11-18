package it.fabrick.meteo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "regions")
@Getter
@Setter

public class RegionsEntity {
    @Id
    @Column(name = "id_regione")
    @JsonProperty("id_regione")
    private Long idRegions;
    @Column(name = "regione")
    private String regione;
    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "num_residenti")
    private Integer num_residenti;
    @Column(name = "presidente")
    private String presidente;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "regions")
    private List<CitiesEntity> cities;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "regions")
    private List<ProvinciesEntity> provincies;


   /* @Column(name = "cod_istat")
    private String cod_istat;
    @Column(name = "cod_fiscale")
    private String cod_fiscale;
    @Column(name = "piva")
    private String piva;
    @Column(name = "pec")
    private String pec;
    @Column(name = "sito")
    private String sito;
    @Column(name = "sede")
    private String sede;*/
}
