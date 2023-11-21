package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.dtoWearther.WeatherDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherRequestDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherMapper {
    @Mapping(source = "daily.temperature_2m_max", target = "daily.temperature")
    WeatherResponseDto responseFromDto(WeatherDto weatherDto);
    @Mapping(source = "daily.temperature", target = "daily.temperature_2m_max")
    WeatherDto dtoFromResponse(WeatherResponseDto weatherResponseDto);


}
