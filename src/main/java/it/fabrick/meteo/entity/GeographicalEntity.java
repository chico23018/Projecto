package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "geographical")
@Getter
@Setter
public class GeographicalEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "longitude")
    private BigDecimal lng;
    @Column(name = "latitude")
    private BigDecimal lat;


    @JoinColumn(name = "istat", referencedColumnName = "istat")
    @ManyToOne
    private CitiesEntity istat;
   /* @PrePersist
    private void generic(){
        if(id==null){

        }
    }*/
}
