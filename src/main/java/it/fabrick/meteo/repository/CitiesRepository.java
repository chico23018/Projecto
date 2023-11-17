package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesEntity,String> {
}
