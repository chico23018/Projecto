package it.fabrick.meteo.entity;

import javax.persistence.*;

@Entity(name = "geographical")
public class GeographicalEntity {
    @Id
    private Long id;
    @Column(name = "longitude")
    private Double lng;
    @Column(name = "latitude")
    private Double lat;
    @OneToOne
    @JoinColumn(name = "istat", referencedColumnName = "istat")
    private CitiesEntity istat;
}
