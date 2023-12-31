package it.fabrick.meteo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "cities")
public class CitiesEntity {
    @Id
    @Column(name = "istat")
    private Long istat;

    @Column(name = "comune")
    private String comune;
    @Column(name = "prefisso")
    private Integer prefisso;

    @Column(name = "cod_fisco")
    @JsonProperty("cod_fisco")
    private String codFisco;

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


    @OneToOne(mappedBy = "cities")
    private GeographicalEntity geographical;

    @OneToOne(mappedBy = "cities")
    private MunicipalityEntity municipalityEntity;

    @PrePersist
    @PreUpdate
    private void upperCase() {
        comune = comune.replace("è","e");
        comune = comune.toUpperCase();
        if (istat == null) {
            istat = (long) (Math.random() * 500000);
        }
    }
}
