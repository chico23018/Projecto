package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.model.ProvinciesModel;
import it.fabrick.meteo.model.ProvinciesModel;
import it.fabrick.meteo.repository.ProvinciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProvinciesService {
    private final ProvinciesRepository provinciesRepository;
    private final IProvinciesMapper iProvinciesMapper;

    public List<ProvinciesModel> readProvincies() {
        return provinciesRepository.findAll()
                .stream()
                .map(iProvinciesMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public ProvinciesModel CreateProvincies(ProvinciesModel provinciesModel) {
        ProvinciesEntity provincies;
        try {
            provincies = provinciesRepository.save(iProvinciesMapper.entityFromModel(provinciesModel));
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iProvinciesMapper.modelFromEntity(provincies);
    }

    public ProvinciesModel updateProvincies(String sigla, ProvinciesModel provinciesModel) {
        Optional<ProvinciesEntity> reservationEntity;
        try {
            reservationEntity = provinciesRepository.findById(sigla)
                    .map(entity -> {
                        Optional.ofNullable(provinciesModel.getSuperficie())
                                .ifPresent(entity::setSuperficie);
                        Optional.ofNullable(provinciesModel.getResidenti())
                                .ifPresent(entity::setResidenti);
                        Optional.ofNullable(provinciesModel.getProvincia())
                                .ifPresent(entity::setProvincia);
                        return provinciesRepository.save(entity);
                    });
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        return reservationEntity
                .map(iProvinciesMapper::modelFromEntity)
                .orElseThrow(() -> generateEntityNotFound(sigla));

    }

    public void deleteProvincies(String sigla) {

        int howMany = 0;
        try {
            howMany = provinciesRepository.deleteBySigla(sigla);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(sigla);

    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String id) {
        return new EntityNotFoundException("No data found for id " + id, ErrorCode.DATA_NOT_FOUND);
    }
}