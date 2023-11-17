package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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
    private Integer cod_fisco;
    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "num_residenti")
    private Integer num_residenti;

    @ManyToOne
    @JoinColumn(name = "id_regione", referencedColumnName = "id_regione" )
    private RegionsEntity id_regione;

    @JoinColumn(name = "sigla", referencedColumnName = "sigla")
    @ManyToOne
    private ProvinciesEntity provincia;



    @OneToOne
    private GeographicalEntity geographical;
}
