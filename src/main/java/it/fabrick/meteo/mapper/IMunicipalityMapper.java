package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoMunicipality.MunicipalityRequestUpdateDto;
import it.fabrick.meteo.dto.dtoMunicipality.MunicipalityResponseDto;
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

    MunicipalityRequestUpdateDto requestFromModel(MunicipalityModel municipalityModel);
    MunicipalityModel modelFromRequest(MunicipalityRequestUpdateDto citiesRequestDto);
}
