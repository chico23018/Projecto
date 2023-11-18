package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IRegionsMapper;
import it.fabrick.meteo.model.RegionsModel;
import it.fabrick.meteo.repository.RegionsRepository;
import it.fabrick.meteo.util.UtilData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionsService {
    private final RegionsRepository regionsRepository;
    private final UtilData data;
    private final IRegionsMapper iRegionsMapper;

    public RegionsService(RegionsRepository regionsRepository, UtilData data, IRegionsMapper iRegionsMapper) {
        this.regionsRepository = regionsRepository;

        this.data = data;
        this.iRegionsMapper = iRegionsMapper;
        data.saveData();
    }

    public List<RegionsModel> readRegions() {
        return regionsRepository.findAll()
                .stream()
                .map(iRegionsMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public RegionsModel CreateRegions(RegionsModel regionsModel) {
        RegionsEntity regions;
        try {
            regions = regionsRepository.save(iRegionsMapper.entityFromModel(regionsModel));
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iRegionsMapper.modelFromEntity(regions);
    }

    public RegionsModel updateRegions(long id_regions, RegionsModel regionsModel) {
        Optional<RegionsEntity> reservationEntity;
        try {
            reservationEntity = regionsRepository.findById(id_regions)
                    .map(entity -> {
                        Optional.ofNullable(regionsModel.getNum_residenti())

                                .ifPresent(entity::setNum_residenti);
                        Optional.ofNullable(regionsModel.getSuperficie())
                                .ifPresent(entity::setSuperficie);
                        Optional.ofNullable(regionsModel.getRegione())
                                .ifPresent(entity::setRegione);
                        Optional.ofNullable(regionsModel.getPresidente())
                                .ifPresent(entity::setPresidente);
                        ;
                        return regionsRepository.save(entity);
                    });
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        return reservationEntity
                .map(iRegionsMapper::modelFromEntity)
                .orElseThrow(() -> generateEntityNotFound(id_regions));

    }

    public void deleteRegions(long id_regions) {
        int howMany = 0;
        try {
            howMany = regionsRepository.deleteByIdRegions(id_regions);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(id_regions);


    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(long id) {
        return new EntityNotFoundException("No data found for id " + id, ErrorCode.DATA_NOT_FOUND);
    }
}
