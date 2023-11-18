package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.ProvinciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciesRepository extends JpaRepository<ProvinciesEntity,String> {
    int deleteBySigla(String sigla);
}
