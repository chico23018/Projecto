package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.RegionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionsRepository extends JpaRepository<RegionsEntity,Long> {
}
