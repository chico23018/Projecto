package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.repository.MunicipalityRepository;
import it.fabrick.meteo.weartherDto.WeatherDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MunicipalityService {
    private final MunicipalityRepository municipalityRepository;
    private final IMunicipalityMapper iMunicipalityMapper;



    public List<MunicipalityModel> readMunicipalityGreatByRegion(int numResident, String regions) {
        List<MunicipalityModel> municipalityModels;
       try {
           municipalityModels = municipalityRepository.findByCitiesRegionsRegioneAndCitiesNumResidentGreaterThan(regions, numResident)
                   .stream()
                   .map(iMunicipalityMapper::modelFromEntity)
                   .collect(Collectors.toList());
       }catch (Exception e){
           throw generateGenericInternalError(e);
       }
        return municipalityModels;
    }
    public List<MunicipalityModel> readMunicipalityGreatByProvinvia(int numResident, String provinvia , String sigla) {
        List<MunicipalityModel> municipalityModels;
        try {
            municipalityModels = municipalityRepository.findByCitiesProvinciaProvinciaAndCitiesNumResidentGreaterThan(provinvia, numResident)
                    .stream()
                    .map(iMunicipalityMapper::modelFromEntity)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw generateGenericInternalError(e);
        }
        return municipalityModels;
    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String istat) {
        return new EntityNotFoundException("No data found for id " + istat, ErrorCode.DATA_NOT_FOUND);
    }
}
