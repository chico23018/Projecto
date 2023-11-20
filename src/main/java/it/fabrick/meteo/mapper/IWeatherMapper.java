package it.fabrick.meteo.mapper;

import it.fabrick.meteo.weartherDto.WeatherDto;
import it.fabrick.meteo.weartherDto.WeatherRequestDto;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherMapper {
    @Mapping(source = "daily.temperature_2m_max", target = "daily.temperature")
    WeatherResponseDto responseFromDto(WeatherDto weatherDto);

    WeatherDto dtoFromResponse(WeatherResponseDto weatherResponseDto);

    WeatherRequestDto requestFromDto(WeatherDto weatherDto);

    WeatherDto dtoFromRequest(WeatherRequestDto weatherRequestDto);
}
