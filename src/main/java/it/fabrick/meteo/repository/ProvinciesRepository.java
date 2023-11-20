package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.model.ProvinciesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciesRepository extends JpaRepository<ProvinciesEntity,String> {
    int deleteBySigla(String sigla);
    List<ProvinciesEntity> findByRegionsRegione(String region);
}
