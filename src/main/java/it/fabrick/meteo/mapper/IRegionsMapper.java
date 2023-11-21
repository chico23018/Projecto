package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoRegions.RegionsRequestCreateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestUpdateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsResponseDto;
import it.fabrick.meteo.entity.RegionsEntity;
import it.fabrick.meteo.model.RegionsModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRegionsMapper {
    RegionsEntity entityFromModel(RegionsModel regionsModel);
    RegionsModel modelFromEntity(RegionsEntity regions);

    RegionsResponseDto responseFromModel(RegionsModel regionsModel);
    RegionsModel modelFromResponse(RegionsResponseDto regionsResponseDto);

    RegionsRequestCreateDto requestFromModel(RegionsModel regionsModel);
    RegionsModel modelFromRequest(RegionsRequestCreateDto regionsRequestCreateDto);
    RegionsRequestUpdateDto requestUpdateFromModel(RegionsModel regionsModel);
    RegionsModel modelFromRequestUpdate(RegionsRequestUpdateDto regionsRequestUpdateDto);
}
