package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IMunicipalityMapper;
import it.fabrick.meteo.model.MunicipalityModel;
import it.fabrick.meteo.repository.MunicipalityRepository;
import it.fabrick.meteo.repository.ProvinciesRepository;
import it.fabrick.meteo.repository.RegionsRepository;
import it.fabrick.meteo.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MunicipalityService {
    private final MunicipalityRepository municipalityRepository;
    private final IMunicipalityMapper iMunicipalityMapper;
    private final RegionsRepository regionsRepository;
    private final ProvinciesRepository provinciesRepository;


    public List<MunicipalityModel> readMunicipalityGreaterByRegion(int numResident, String regions) {
        regions = Utility.converteString(regions);
        RegionsEntity regionsEntity;
        List<MunicipalityModel> municipalityModels;
        try {
            regionsEntity = regionsRepository.findByRegione(regions);
            municipalityModels = municipalityRepository.findByCitiesRegionsRegioneAndCitiesNumResidentGreaterThan(regions, numResident)
                    .stream()
                    .map(iMunicipalityMapper::modelFromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (regionsEntity==null)
            throw generateEntityNotFound(regions, "region");
        if (municipalityModels.isEmpty())
            throw generateEntityNotFound("" + numResident, "resident");
        return municipalityModels;
    }

    public List<MunicipalityModel> readMunicipalityGreaterByProvinvia(int numResident, String province) {
        province = Utility.converteString(province);
        List<MunicipalityModel> municipalityModels;
        ProvinciesEntity provinciesEntity;
        try {
            provinciesEntity=  provinciesRepository.findByProvincia(province);
            municipalityModels = municipalityRepository.findByCitiesProvinciaProvinciaAndCitiesNumResidentGreaterThan(province, numResident)
                    .stream()
                    .map(iMunicipalityMapper::modelFromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (provinciesEntity==null)
            throw generateEntityNotFound(province, "province");
        if (municipalityModels.isEmpty())
            throw generateEntityNotFound("" + numResident, "Provincia");
        return municipalityModels;
    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String istat, String st) {
        return new EntityNotFoundException("No data found for " + st + " :" + istat, ErrorCode.DATA_NOT_FOUND);
    }
}
