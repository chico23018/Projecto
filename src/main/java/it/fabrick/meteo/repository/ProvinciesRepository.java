package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.ProvinciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciesRepository extends JpaRepository<ProvinciesEntity,String> {
}
