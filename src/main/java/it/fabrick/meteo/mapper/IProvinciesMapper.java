package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestCreateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesResponseDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestUpdateDto;
import it.fabrick.meteo.entity.ProvinciesEntity;
import it.fabrick.meteo.model.ProvinciesModel;
import it.fabrick.meteo.model.RegionsModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProvinciesMapper {

    ProvinciesEntity entityFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromEntity(ProvinciesEntity provinciesEntity);

    ProvinciesResponseDto responseFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromResponse(ProvinciesResponseDto provinciesResponseDto);

    ProvinciesRequestCreateDto requestFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromRequest(ProvinciesRequestCreateDto provinciesRequestDto);
    ProvinciesRequestUpdateDto requestUpdateFromModel(ProvinciesModel provinciesModel);
    ProvinciesModel modelFromRequestUpdate(ProvinciesRequestUpdateDto updateDto);
}
