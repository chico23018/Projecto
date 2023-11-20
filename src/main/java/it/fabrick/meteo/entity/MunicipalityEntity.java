package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "municipalities")
@Getter
@Setter
public class MunicipalityEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMunicipality;
    @OneToOne
    @JoinColumn(name = "istat", referencedColumnName = "istat")
    private CitiesEntity cities;
    @Column(name = "municipality")
    private String municipality;
    @Column(name = "regions")
    private String regions;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "address")
    private String address;

    @PrePersist
    private void upperCase() {
     municipality=   municipality.toUpperCase();
    }
}
