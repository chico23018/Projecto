package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.weartherDto.DailyDto;
import it.fabrick.meteo.weartherDto.DailyRequestDto;
import it.fabrick.meteo.weartherDto.DailyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IDailyMapper {


    DailyResponseDto responseFromDto(DailyDto dailyDto);
    DailyDto dtoFromResponse(CitiesResponseDto citiesResponseDto);

    DailyRequestDto requestFromDto(DailyDto dailyDto);
    DailyDto dtoFromRequest(DailyRequestDto dailyRequestDto);
}
