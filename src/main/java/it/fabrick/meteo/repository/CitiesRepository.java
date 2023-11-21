package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesEntity, Long> {
    int deleteByRegionsIdRegionsAndProvinciaSiglaAndIstat(Long idRegion, String provinvia, Long istat);

    CitiesEntity findByComune(String comune);

    List<CitiesEntity> findByRegionsIdRegionsAndProvinciaSigla(Long region, String sigla);

    List<CitiesEntity> findByNumResidentGreaterThan(int resident);

    CitiesEntity findByProvinciaProvincia(String pronvivia);

    @Modifying
    @Query("update cities c set c.comune = :comune," +
            "c.prefisso = :prefisso,c.codFisco = :cod_fisco,c.superficie = :superficie," +
            "c.numResident = :numResident where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndAll(Long istat, String comune, int prefisso
            , String cod_fisco, double superficie, int numResident
            , Long regione, String provincia);

    @Modifying
    @Query("update cities c set c.comune = :comune where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndComune(Long istat, String comune, Long regione, String provincia);

    @Modifying
    @Query("update cities c set c.prefisso = :prefisso where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndPrefisso(Long istat, int prefisso, Long regione, String provincia);

    @Modifying
    @Query("update cities c set c.codFisco = :cod_fisco  where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndCodFisco(Long istat, String cod_fisco, Long regione, String provincia);

    @Modifying
    @Query("update cities c set c.superficie = :superficie where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndSuperficie(Long istat, double superficie, Long regione, String provincia);

    @Modifying
    @Query("update cities c set c.numResident = :numResident where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndNum_residenti(Long istat, int numResident, Long regione, String provincia);

}
