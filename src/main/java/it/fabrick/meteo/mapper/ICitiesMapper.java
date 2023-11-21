package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoCity.CitiesRequestCreateDto;
import it.fabrick.meteo.dto.dtoCity.CitiesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoCity.CitiesResponseDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestUpdateDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.model.ProvinciesModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICitiesMapper {
    CitiesEntity entityFromModel(CitiesModel citiesModel);

    CitiesModel modelFromEntity(CitiesEntity cities);

    CitiesResponseDto responseFromModel(CitiesModel citiesModel);

    CitiesModel modelFromResponse(CitiesResponseDto citiesResponseDto);

    CitiesRequestCreateDto requestFromModel(CitiesModel citiesModel);
    CitiesModel modelFromRequest(CitiesRequestCreateDto citiesRequestCreateDto);
    CitiesRequestUpdateDto requestUpdateFromModel(CitiesModel citiesModel);
    CitiesModel modelFromRequestUpdate(CitiesRequestUpdateDto updateDto);
}
