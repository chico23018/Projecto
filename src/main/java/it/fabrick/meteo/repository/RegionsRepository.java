package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.RegionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionsRepository extends JpaRepository<RegionsEntity,Long> {
}
