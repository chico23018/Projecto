package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity,Long> {
    List<MunicipalityEntity> findByCitiesRegionsRegioneAndCitiesNumResidentGreaterThan(String regions, int numResident);
    List<MunicipalityEntity> findByCitiesProvinciaProvinciaAndCitiesNumResidentGreaterThan(String provincia, int numResident);
    List<MunicipalityEntity> findByCitiesProvinciaSiglaAndCitiesNumResidentGreaterThan(String sigla, int numResident);
}
