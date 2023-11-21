package it.fabrick.meteo.repository;

import it.fabrick.meteo.entity.GeographicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeographicalRepository extends JpaRepository<GeographicalEntity,Long> {
    int deleteByIdGeographical(Long id);
   /* SELECT g.* ,c.sigla FROM GEOGRAPHICAL  g ,CITIES  c where  c.SIGLA = 'TO'  and g.ISTAT = c.ISTAT ;*/
   // @Query("SELECT g FROM geographical  g ,cities  c where  c.provincia = :sigla  and g.cities.istat = c.istat")
    List<GeographicalEntity> findByCitiesProvinciaProvincia(String sigla);
}
