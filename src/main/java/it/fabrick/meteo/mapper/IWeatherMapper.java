package it.fabrick.meteo.mapper;

import it.fabrick.meteo.dto.CitiesRequestDto;
import it.fabrick.meteo.dto.CitiesResponseDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.weartherDto.WeatherDto;
import it.fabrick.meteo.weartherDto.WeatherRequestDto;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IWeatherMapper {

    WeatherResponseDto responseFromDto(WeatherDto weatherDto);

    WeatherDto dtoFromResponse(WeatherResponseDto citiesResponseDto);

    WeatherRequestDto requestFromDto(WeatherDto weatherDto);

    WeatherDto dtoFromRequest(WeatherRequestDto weatherRequestDto);
}