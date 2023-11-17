package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<CitiesEntity,String> {
}
