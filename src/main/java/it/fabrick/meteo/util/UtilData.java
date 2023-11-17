package it.fabrick.meteo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.DataJson.CitiesJson;
import it.fabrick.meteo.DataJson.GeograJson;
import it.fabrick.meteo.DataJson.ProvinJson;
import it.fabrick.meteo.RegionJson;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.repository.CitiesRepository;
import it.fabrick.meteo.repository.ProvinciesRepository;
import it.fabrick.meteo.repository.RegionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UtilData {
    private final RegionsRepository regionsRepository;
    private final ProvinciesRepository provinciesRepository;
    private final CitiesRepository citiesRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

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

                    if (n.getSuperficie() != null & n.getId_regione().equals(p.getId_regione()) & n.getSuperficie() != null) {
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
                if (c.getIstat()!=null&&c.getCod_fisco()!=null) {
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
                            ci.setNum_residenti(c.getNum_residenti());
                            citiesEntities.add(ci);
                            citiesRepository.save(ci);
                        }
                    }
                }else{
                    System.out.println(c);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
