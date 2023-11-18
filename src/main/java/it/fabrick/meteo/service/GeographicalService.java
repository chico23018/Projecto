package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IGeographicalMapper;
import it.fabrick.meteo.model.GeographicalModel;
import it.fabrick.meteo.repository.GeographicalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GeographicalService {
    private final GeographicalRepository geographicalRepository;
    private final IGeographicalMapper iGeographicalMapper;

    public List<GeographicalModel> readGeographical() {
        return geographicalRepository.findAll()
                .stream()
                .map(iGeographicalMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public GeographicalModel CreateGeographical(GeographicalModel geographicalModel) {
        GeographicalEntity geographical;
        try {
            geographical = geographicalRepository.save(iGeographicalMapper.entityFromModel(geographicalModel));
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iGeographicalMapper.modelFromEntity(geographical);
    }

    public GeographicalModel updateGeographical(long id, GeographicalModel geographicalModel) {
        Optional<GeographicalEntity> reservationEntity;
        try {
            reservationEntity = geographicalRepository.findById(id)
                    .map(entity -> {
                        Optional.ofNullable(geographicalModel.getLat())
                                .ifPresent(entity::setLat);
                        Optional.ofNullable(geographicalModel.getLng())
                                .ifPresent(entity::setLng);
                        return geographicalRepository.save(entity);
                    });
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        return reservationEntity
                .map(iGeographicalMapper::modelFromEntity)
                .orElseThrow(() -> generateEntityNotFound(id));
       
    }

    public void deleteGeographical(Long id) {
        int howMany = 0;

        try {
            howMany = geographicalRepository.deleteByIdC(id);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(id);
    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(long id) {
        return new EntityNotFoundException("No data found for id " + id, ErrorCode.DATA_NOT_FOUND);
    }
}
