package it.fabrick.meteo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
    @JsonProperty("num_residenti")
    private Integer numResidenti;
    @Column(name = "presidente")
    private String presidente;

    @Column(name = "cod_istat")
    @JsonProperty("cod_istat")
    private String codIstat;
    @Column(name = "cod_fiscale")
    @JsonProperty("cod_fiscale")
    private Long codFiscale;
    @JsonProperty("piva")
    @Column(name = "piva")
    private Long piva;
    @Column(name = "pec")
    private String pec;
    @Column(name = "sito")
    private String sito;
    @Column(name = "sede")
    private String sede;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "regions")
    private List<CitiesEntity> cities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "regions")
    private List<ProvinciesEntity> provincies;

    @PrePersist
    public void v() {
        if (piva == null) {
            piva = (long) (Math.random() * 50000);
        }
        if (codFiscale == null) {
            codFiscale = (long) (Math.random() * 50000);
        }
        if (idRegions == null) {
            idRegions = (long) (Math.random() * 500000);
        }
      regione=  regione.toUpperCase();

    }
}
