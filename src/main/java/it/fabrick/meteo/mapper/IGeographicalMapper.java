package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoGeographical.GeographicalRequestCreateDto;
import it.fabrick.meteo.dto.dtoGeographical.GeographicalResponseDto;
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

    GeographicalRequestCreateDto requestFromModel(GeographicalModel citiesModel);
    GeographicalModel modelFromRequest(GeographicalRequestCreateDto geographicalRequestCreateDto);
}
