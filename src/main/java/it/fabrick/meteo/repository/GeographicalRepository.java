package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.GeographicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicalRepository extends JpaRepository<GeographicalEntity,Long> {
    int deleteByIdC(long id);
}
