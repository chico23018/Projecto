package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.GeographicalRequestDto;
import it.fabrick.meteo.dto.GeographicalResponseDto;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.model.GeographicalModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IGeographicalMapper {
    GeographicalEntity entityFromModel(GeographicalModel geographicalModel);
    GeographicalModel modelFromEntity(GeographicalEntity geographicalEntity);

    GeographicalResponseDto responseFromModel(GeographicalModel citiesModel);
    GeographicalModel modelFromResponse(GeographicalResponseDto geographicalResponseDto);

    GeographicalRequestDto requestFromModel(GeographicalModel citiesModel);
    GeographicalModel modelFromRequest(GeographicalRequestDto geographicalRequestDto);
}
