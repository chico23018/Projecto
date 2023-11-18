package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.CitiesEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesEntity, String> {
    int deleteByIstat(String istat);


    List<CitiesEntity> findByNumResidentGreaterThan(int resident);

    @Modifying
    @Query("update cities c set c.comune = :comune," +
            "c.prefisso = :prefisso,c.cod_fisco = :cod_fisco,c.superficie = :superficie," +
            "c.numResident = :numResident where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndAll(String istat, String comune, int prefisso
            , String cod_fisco, double superficie, int numResident
            , String regione, String provincia);

    @Modifying
    @Query("update cities c set c.comune = :comune where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndComune(String istat, String comune, String regione, String provincia);

    @Modifying
    @Query("update cities c set c.prefisso = :prefisso where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndPrefisso(String istat, int prefisso, String regione, String provincia    );

    @Modifying
    @Query("update cities c set c.cod_fisco = :cod_fisco  where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndCod_fisco(String istat, String cod_fisco, String regione, String provincia);

    @Modifying
    @Query("update cities c set c.superficie = :superficie where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndSuperficie(String istat,double superficie, String regione, String provincia);

    @Modifying
    @Query("update cities c set c.numResident = :numResident where c.istat = :istat and c.regions.idRegions = :regione and c.provincia.sigla = :provincia")
    int updateByIstatAndNum_residenti(String istat,  int numResident, String regione, String provincia);

}
