package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.MunicipalityRequestDto;
import it.fabrick.meteo.dto.MunicipalityResponseDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.MunicipalityEntity;
import it.fabrick.meteo.model.MunicipalityModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMunicipalityMapper {
    MunicipalityEntity entityFromModel(MunicipalityModel municipalityModel);

    MunicipalityModel modelFromEntity(MunicipalityEntity cities);

    MunicipalityResponseDto responseFromModel(MunicipalityModel municipalityModel);

    MunicipalityModel modelFromResponse(MunicipalityResponseDto municipalityResponseDto);

    MunicipalityRequestDto requestFromModel(MunicipalityModel municipalityModel);
    MunicipalityModel modelFromRequest(MunicipalityRequestDto citiesRequestDto);
}
