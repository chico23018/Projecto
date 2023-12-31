package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoWearther.DailyDto;
import it.fabrick.meteo.dto.dtoWearther.DailyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IDailyMapper {

    @Mapping(source = "temperature_2m_max", target = "temperature")
    DailyResponseDto responseFromDto(DailyDto dailyDto);

    DailyDto dtoFromResponse(DailyResponseDto responseDto);


}
