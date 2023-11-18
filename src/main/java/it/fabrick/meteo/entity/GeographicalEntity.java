package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Entity(name = "geographical")

public class GeographicalEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGeographical;
    @Column(name = "longitude")
    private BigDecimal lng;
    @Column(name = "latitude")
    private BigDecimal lat;



    @OneToOne
    @JoinColumn(name = "istat", referencedColumnName = "istat")
    private CitiesEntity cities;

}
