package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.IProvinciesMapper;
import it.fabrick.meteo.model.ProvinciesModel;
import it.fabrick.meteo.repository.ProvinciesRepository;
import it.fabrick.meteo.repository.RegionsRepository;
import it.fabrick.meteo.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProvinciesService {
    private final ProvinciesRepository provinciesRepository;
    private final RegionsRepository regionsRepository;
    private final IProvinciesMapper iProvinciesMapper;

    public List<ProvinciesModel> readProvincies(Long IdRegions) {

        List<ProvinciesEntity> provinciesEntities;
        try {
            provinciesEntities = provinciesRepository.findByRegionsIdRegions(IdRegions);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (provinciesEntities.isEmpty())
            throw generateEntityNotFound(""+IdRegions, "Region");


        return provinciesEntities
                .stream()
                .map(iProvinciesMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public ProvinciesModel createProvincies(Long IdRegions, ProvinciesModel provinciesModel) {
        ProvinciesEntity provincies = iProvinciesMapper.entityFromModel(provinciesModel);
        try {
            provincies.setRegions(regionsRepository.findById(IdRegions).get());
            provincies = provinciesRepository.save(provincies);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iProvinciesMapper.modelFromEntity(provincies);
    }

    public ProvinciesModel updateProvincies(Long region, String sigla, ProvinciesModel provinciesModel) {

        sigla = Utility.converteString(sigla);
        Optional<ProvinciesEntity> reservationEntity;
        try {

            reservationEntity = provinciesRepository.findByRegionsIdRegionsAndSigla(region, sigla)
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
        String finalSigla = sigla;
        return reservationEntity
                .map(iProvinciesMapper::modelFromEntity)
                .orElseThrow(() -> generateEntityNotFound(finalSigla, "sigla"));

    }

    @Transactional
    public void deleteProvincies(Long IdRegione, String sigla) {

        int howMany = 0;
        try {
            howMany = provinciesRepository.deleteByRegionsIdRegionsAndSigla(IdRegione, sigla);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(sigla, "provincia");

    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String provincia, String pro) {
        return new EntityNotFoundException("No data found for " + pro + ": " + provincia, ErrorCode.DATA_NOT_FOUND);
    }
}
