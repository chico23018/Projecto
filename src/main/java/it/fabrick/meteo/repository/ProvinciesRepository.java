package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.ProvinciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinciesRepository extends JpaRepository<ProvinciesEntity,String> {
    int deleteByRegionsRegioneAndSigla(String region, String sigla);
    ProvinciesEntity findByProvincia(String provincia);
    Optional<ProvinciesEntity> findByRegionsRegioneAndSigla(String region, String sigla);
    List<ProvinciesEntity> findByRegionsRegione(String region);
}
