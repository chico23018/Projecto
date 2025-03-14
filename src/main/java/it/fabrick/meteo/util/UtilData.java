package it.fabrick.meteo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.entity.*;
import it.fabrick.meteo.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UtilData {
    private final RegionsRepository regionsRepository;
    private final ProvinciesRepository provinciesRepository;
    private final CitiesRepository citiesRepository;
    private final GeographicalRepository geographicalRepository;
    private final MunicipalityRepository municipalityRepository;
    private ObjectMapper objectMapper;

  /*  public void saveDate() {
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
            List<MunicipalityJson> lis4 = objectMapper.readValue(new File("json/italy_munic.json"), new TypeReference<List<MunicipalityJson>>() {
            });

            regionsRepository.saveAll(lis);
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
            for (MunicipalityJson g : lis4) {
                if (g.getIstat() != null & g.getRegione() != null & g.getProvincia() != null) {
                    for (CitiesEntity c : citiesEntities) {
                        if (g.getIstat().equals(c.getIstat())) {
                            MunicipalityEntity municipalityEntity = new MunicipalityEntity();
                            municipalityEntity.setMunicipality(g.getComune());
                            municipalityEntity.setAddress(g.getIndirizzo());
                            municipalityEntity.setProvincia(g.getProvincia());
                            municipalityEntity.setRegions(g.getRegione());
                            municipalityEntity.setCities(c);
                            municipalityRepository.save(municipalityEntity);
                        }
                    }
                }
            }
            createJson();

        } catch (IOException e) {
            log.error("Error read file json ");
        }

    }*/

    /* private void createJson() {
         try
             {
            // objectMapper.writeValue(new File("json/munic.json"), municipalityRepository.findAll());
              // objectMapper.writeValue(new File("json/cities.json"), citiesRepository.findAll());
              // objectMapper.writeValue(new File("json/geogra.json"), geographicalRepository.findAll());
             objectMapper.writeValue(new File("json/regionsAll.json"), regionsRepository.findAll());
             objectMapper.writeValue(new File("json/provinAll.json"),provinciesRepository.findAll());
             } catch (IOException e) {
             throw new RuntimeException(e);
         }

     }
 */
    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void saveData() {

        Resource resource = resourceLoader.getResource("classpath:" + "regionsAll.json");
        Resource resource1 = resourceLoader.getResource("classpath:" + "cities.json");
        Resource resource2 = resourceLoader.getResource("classpath:" + "geogra.json");
        Resource resource3 = resourceLoader.getResource("classpath:" + "provinAll.json");
        Resource resource4 = resourceLoader.getResource("classpath:" + "munic.json");
        try (InputStream inputStream = resource.getInputStream();
             InputStream inputStream1 = resource1.getInputStream();
             InputStream inputStream2 = resource2.getInputStream();
             InputStream inputStream3 = resource3.getInputStream();
             InputStream inputStream4 = resource4.getInputStream()) {
            List<RegionsEntity> lis = objectMapper.readValue(inputStream, new TypeReference<List<RegionsEntity>>() {
            });
            List<CitiesEntity> lis1 = objectMapper.readValue(inputStream1, new TypeReference<List<CitiesEntity>>() {
            });
            List<GeographicalEntity> lis3 = objectMapper.readValue(inputStream2, new TypeReference<List<GeographicalEntity>>() {
            });
            List<ProvinciesEntity> lis2 = objectMapper.readValue(inputStream3, new TypeReference<List<ProvinciesEntity>>() {
            });
            List<MunicipalityEntity> lis4 = objectMapper.readValue(inputStream4, new TypeReference<List<MunicipalityEntity>>() {
            });
            regionsRepository.saveAllAndFlush(lis);
            provinciesRepository.saveAllAndFlush(lis2);
            citiesRepository.saveAllAndFlush(lis1);
            geographicalRepository.saveAllAndFlush(lis3);
            municipalityRepository.saveAllAndFlush(lis4);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}