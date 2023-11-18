package it.fabrick.meteo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.DataJson.CitiesJson;
import it.fabrick.meteo.DataJson.GeograJson;
import it.fabrick.meteo.DataJson.ProvinJson;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.repository.CitiesRepository;
import it.fabrick.meteo.repository.GeographicalRepository;
import it.fabrick.meteo.repository.ProvinciesRepository;
import it.fabrick.meteo.repository.RegionsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UtilData {
    private final RegionsRepository regionsRepository;
    private final ProvinciesRepository provinciesRepository;
    private final CitiesRepository citiesRepository;
    private final GeographicalRepository  geographicalRepository;
    private ObjectMapper objectMapper ;

    public void saveDate() {
        List<RegionsEntity> lis;
        try {
            lis = objectMapper.readValue(new File("json/italy_regions.json"), new TypeReference<List<RegionsEntity>>() {
            });
            List<CitiesJson> lis1 = objectMapper.readValue(new File("json/italy_cities.json"), new TypeReference<List<CitiesJson>>() {
            });
            List<GeograJson> lis3 = objectMapper.readValue(new File("json/italy_geo.json"), new TypeReference<List<GeograJson>>() {
            });
            List<ProvinJson> lis2 = objectMapper.readValue(new File("json/italy_provincies.json"), new TypeReference<List<ProvinJson>>() {
            });


            regionsRepository.saveAllAndFlush(lis);
            List<ProvinciesEntity> provinciesEntities = new ArrayList<>();

            for (ProvinJson n : lis2) {
                for (RegionsEntity p : lis) {

                    if (n.getSuperficie() != null & n.getId_regione().equals(p.getIdRegions()) & n.getSuperficie() != null) {
                        ProvinciesEntity pa = new ProvinciesEntity();
                        pa.setSigla(n.getSigla());
                        pa.setResidenti(n.getResidenti());
                        pa.setRegions(p);
                        pa.setProvincia(n.getProvincia());
                        pa.setSuperficie(n.getSuperficie());
                        provinciesEntities.add(pa);
                        provinciesRepository.save(pa);
                    }
                }
            }
            List<CitiesEntity> citiesEntities = new LinkedList<>();
            for (CitiesJson c : lis1) {
                if (c.getIstat() != null && c.getCod_fisco() != null) {
                    for (ProvinciesEntity p : provinciesEntities) {
                        if (c.getProvincia().equals(p.getSigla())) {
                            CitiesEntity ci = new CitiesEntity();
                            ci.setPrefisso(c.getPrefisso());
                            ci.setIstat(c.getIstat());
                            ci.setSuperficie(c.getSuperficie());
                            ci.setProvincia(p);
                            ci.setRegions(p.getRegions());
                            ci.setComune(c.getComune());
                            ci.setCod_fisco(c.getCod_fisco());
                            ci.setNumResident(c.getNum_residenti());
                            citiesEntities.add(ci);
                            citiesRepository.save(ci);
                        }
                    }
                }
            }
            for (GeograJson g : lis3) {
                if (g.getIstat() != null & g.getLat() != null & g.getLng() != null) {
                    for (CitiesEntity c : citiesEntities) {
                        if (g.getIstat().equals(c.getIstat())) {
                            GeographicalEntity geographical = new GeographicalEntity();
                            geographical.setLat(g.getLat());
                            geographical.setLng(g.getLng());
                            geographical.setCities(c);
                            geographicalRepository.save(geographical);
                        }
                    }
                }
            }


        } catch (IOException e) {
           log.error("Error read file json ");
        }
    }

}
