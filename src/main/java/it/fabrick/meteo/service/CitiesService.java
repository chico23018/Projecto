package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IWeatherMapper;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.repository.CitiesRepository;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitiesService {
    private final CitiesRepository citiesRepository;
    private final ICitiesMapper iCitiesMapper;
    private final WeatherService weatherService;
    private final IWeatherMapper iWeatherMapper;

    public List<CitiesModel> readGreat(int numResident) {
        List<CitiesModel> citiesModels;
        try {
            citiesModels = citiesRepository.findByNumResidentGreaterThan(numResident)
                    .stream()
                    .map(iCitiesMapper::modelFromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return citiesModels;
    }

    public List<CitiesModel> readCities() {
        return citiesRepository.findAll()
                .stream()
                .map(iCitiesMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public CitiesModel CreateCities(CitiesModel citiesModel) {
        CitiesEntity cities;
        try {
            cities = citiesRepository.save(iCitiesMapper.entityFromModel(citiesModel));
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iCitiesMapper.modelFromEntity(cities);
    }

    @Transactional
    public CitiesModel updateCities(String istat, CitiesModel citiesModel, String regions, String provincia) {

        int howMany = 0;
        try {
            if (citiesModel.getComune() != null && citiesModel.getCod_fisco() != null && citiesModel.getSuperficie() != null
                    && citiesModel.getPrefisso() != null &&
                    citiesModel.getNumResident() != null) {
                howMany = citiesRepository.updateByIstatAndAll(istat, citiesModel.getComune(), citiesModel.getPrefisso()
                        , citiesModel.getCod_fisco(), citiesModel.getSuperficie(), citiesModel.getNumResident()
                        , regions, provincia);
            } else if (citiesModel.getComune() != null) {
                howMany = citiesRepository.updateByIstatAndComune(istat, citiesModel.getComune(), regions, provincia);
            } else if (citiesModel.getCod_fisco() != null) {
                howMany = citiesRepository.updateByIstatAndCod_fisco(istat, citiesModel.getCod_fisco(), regions, provincia);
            } else if (citiesModel.getSuperficie() != null) {
                howMany = citiesRepository.updateByIstatAndSuperficie(istat, citiesModel.getSuperficie(), regions, provincia);
            } else if (citiesModel.getPrefisso() != null) {
                howMany = citiesRepository.updateByIstatAndPrefisso(istat, citiesModel.getPrefisso(), regions, provincia);
            } else if (citiesModel.getNumResident() != null) {
                howMany = citiesRepository.updateByIstatAndNum_residenti(istat, citiesModel.getNumResident(), regions, provincia);
            }
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        if (howMany == 0)
            throw generateEntityNotFound(istat);

        return citiesRepository.findById(istat).map(iCitiesMapper::modelFromEntity).get();
    }

    public void deleteCities(String istat) {
        int howMany = 0;
        try {
            howMany = citiesRepository.deleteByIstat(istat);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(istat);

    }

    public WeatherResponseDto readForecastCity(String municipality) {
        CitiesEntity cities = citiesRepository.findByComune(municipality);
        if (cities == null)
            throw new EntityNotFoundException("No data found for city : " + municipality, ErrorCode.DATA_NOT_FOUND);
        WeatherResponseDto responseDto = iWeatherMapper.responseFromDto(
                weatherService.readForecast(
                        cities.getGeographical().getLat()
                        , cities.getGeographical().getLng()));

        return responseDto;
    }

    public List<WeatherResponseDto> readForecastDate(String city, LocalDate start, LocalDate end) {
        CitiesEntity cities = citiesRepository.findByComune(city);
        if (cities == null)
            throw new EntityNotFoundException("No data found for city : " + city, ErrorCode.DATA_NOT_FOUND);
        List<WeatherResponseDto> responseDtos = weatherService.readForecastDate(
                        cities.getGeographical().getLat()
                        , cities.getGeographical().getLng()
                        , start
                        , end
                )
                .stream()
                .map(iWeatherMapper::responseFromDto)
                .collect(Collectors.toList());
        return responseDtos;
    }

    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String istat) {
        return new EntityNotFoundException("No data found for id " + istat, ErrorCode.DATA_NOT_FOUND);
    }

}

