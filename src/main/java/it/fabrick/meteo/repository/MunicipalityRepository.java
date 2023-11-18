package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity,Long> {
}
