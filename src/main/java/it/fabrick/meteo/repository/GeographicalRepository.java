package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeographicalRepository extends JpaRepository<GeographicalEntity,Long> {
}
