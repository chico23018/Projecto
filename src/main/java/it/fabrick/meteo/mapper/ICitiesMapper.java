package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.CitiesRequestDto;
import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.model.CitiesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICitiesMapper {
    CitiesEntity entityFromModel(CitiesModel citiesModel);

    CitiesModel modelFromEntity(CitiesEntity cities);

    CitiesResponseDto responseFromModel(CitiesModel citiesModel);

    CitiesModel modelFromResponse(CitiesResponseDto citiesResponseDto);

    CitiesRequestDto requestFromModel(CitiesModel citiesModel);
    CitiesModel modelFromRequest(CitiesRequestDto citiesRequestDto);
}
