package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.CitiesRequestDto;
import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.dto.RegionsRequestDto;
import it.fabrick.meteo.dto.RegionsResponseDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.model.RegionsModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRegionsMapper {
/*    RegionsEntity entityFromModel(RegionsModel regionsModel);
    RegionsModel modelFromEntity(CitiesEntity cities);

    RegionsResponseDto responseFromModel(RegionsModel regionsModel);
    RegionsModel modelFromResponse(RegionsResponseDto regionsResponseDto);

    RegionsRequestDto requestFromModel(RegionsModel regionsModel);
    RegionsModel modelFromRequest(RegionsRequestDto regionsRequestDto);*/
}
