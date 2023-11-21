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

    public List<ProvinciesModel> readProvincies(String region) {
        region = Utility.converteString(region);
        List<ProvinciesEntity> provinciesEntities;
        try {
            provinciesEntities = provinciesRepository.findByRegionsRegione(region);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (provinciesEntities.isEmpty())
            throw generateEntityNotFound(region, "Region");


        return provinciesEntities
                .stream()
                .map(iProvinciesMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public ProvinciesModel createProvincies(String region, ProvinciesModel provinciesModel) {
        ProvinciesEntity provincies = iProvinciesMapper.entityFromModel(provinciesModel);
        try {
            provincies.setRegions(regionsRepository.findByRegione(region));
            provincies = provinciesRepository.save(provincies);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iProvinciesMapper.modelFromEntity(provincies);
    }

    public ProvinciesModel updateProvincies(String region, String sigla, ProvinciesModel provinciesModel) {
        region = Utility.converteString(region);
        sigla = Utility.converteString(sigla);
        Optional<ProvinciesEntity> reservationEntity;
        try {

            reservationEntity = provinciesRepository.findByRegionsRegioneAndSigla(region, sigla)
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
    public void deleteProvincies(String region, String sigla) {

        int howMany = 0;
        try {
            howMany = provinciesRepository.deleteByRegionsRegioneAndSigla(region, sigla);
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
