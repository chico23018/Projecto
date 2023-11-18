package it.fabrick.meteo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "geographical")
@Getter
@Setter
public class GeographicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "longitude")
    private Double lng;
    @Column(name = "latitude")
    private Double lat;


    @JoinColumn(name = "istat", referencedColumnName = "istat")
    @ManyToOne
    private CitiesEntity istat;
    @PrePersist
    private void generic(){
        if(id==null){

        }
    }
}
