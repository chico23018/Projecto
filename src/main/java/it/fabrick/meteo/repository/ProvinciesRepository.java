package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.ProvinciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinciesRepository extends JpaRepository<ProvinciesEntity,String> {
    int deleteByRegionsIdRegionsAndSigla(Long region, String sigla);
    ProvinciesEntity findByProvincia(String provincia);
    Optional<ProvinciesEntity> findByRegionsIdRegionsAndSigla(Long region, String sigla);
    List<ProvinciesEntity> findByRegionsIdRegions(Long region);
}
