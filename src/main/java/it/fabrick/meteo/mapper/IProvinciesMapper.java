package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.ProvinciesRequestDto;
import it.fabrick.meteo.dto.ProvinciesResponseDto;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.model.ProvinciesModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProvinciesMapper {

    ProvinciesEntity entityFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromEntity(ProvinciesEntity provinciesEntity);

    ProvinciesResponseDto responseFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromResponse(ProvinciesResponseDto provinciesResponseDto);

    ProvinciesRequestDto requestFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromRequest(ProvinciesRequestDto provinciesRequestDto);
}
