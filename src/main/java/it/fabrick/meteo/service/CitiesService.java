package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IWeatherMapper;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.repository.CitiesRepository;
import it.fabrick.meteo.repository.GeographicalRepository;
import it.fabrick.meteo.util.Utility;
import it.fabrick.meteo.weartherDto.WeatherDto;
import it.fabrick.meteo.weartherDto.WeatherResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    private GeographicalRepository geographicalRepository;

    public List<CitiesModel> readGreater(int numResident) {
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
            if (citiesModel.getComune() != null && citiesModel.getCodFisco() != null && citiesModel.getSuperficie() != null
                    && citiesModel.getPrefisso() != null &&
                    citiesModel.getNumResident() != null) {
                howMany = citiesRepository.updateByIstatAndAll(istat, citiesModel.getComune(), citiesModel.getPrefisso()
                        , citiesModel.getCodFisco(), citiesModel.getSuperficie(), citiesModel.getNumResident()
                        , regions, provincia);
            } else if (citiesModel.getComune() != null) {
                howMany = citiesRepository.updateByIstatAndComune(istat, citiesModel.getComune(), regions, provincia);
            } else if (citiesModel.getCodFisco() != null) {
                howMany = citiesRepository.updateByIstatAndCodFisco(istat, citiesModel.getCodFisco(), regions, provincia);
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
            throw generateEntityNotFound(istat, "id");

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
            throw generateEntityNotFound(istat, "id");

    }

    public WeatherResponseDto readForecastCity(String municipality) {
        municipality = Utility.converteString(municipality);
        CitiesEntity cities;
        try {
            cities = citiesRepository.findByComune(municipality);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        if (cities == null)
            throw generateEntityNotFound(municipality, "City");
        WeatherResponseDto responseDto = iWeatherMapper.responseFromDto(
                weatherService.readForecast(
                        cities.getGeographical().getLat()
                        , cities.getGeographical().getLng()));

        return responseDto;
    }

    public WeatherResponseDto readForecastDate(String city, LocalDate date) {
        city = Utility.converteString(city);
        CitiesEntity cities;

        try {
            cities = citiesRepository.findByComune(city);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        if (cities == null)
            throw generateEntityNotFound(city, "City");
        WeatherDto weatherDto;
        if (Utility.date(date)) {
             weatherDto = weatherService.readForecastDate(
                    cities.getGeographical().getLat()
                    , cities.getGeographical().getLng()
                    , LocalDate.now()
                    , date
            );
        } else {
            weatherDto = weatherService.readForecastDate(
                    cities.getGeographical().getLat()
                    , cities.getGeographical().getLng()
                    , date
                    ,  LocalDate.now()
            );
        }

        return iWeatherMapper.responseFromDto(weatherDto);
    }

    public String readForecastProvincia(String provincia, LocalDate date) {
        provincia = Utility.converteString(provincia);
        List<GeographicalEntity> geographicalEntities = null;
        try {

            geographicalEntities = geographicalRepository.findByCitiesProvinciaProvincia(provincia);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (geographicalEntities == null || geographicalEntities.isEmpty())
            throw generateEntityNotFound(provincia, "Provincia");

        List<BigDecimal> lat = geographicalEntities.stream()
                .map(x -> x.getLat())
                .collect(Collectors.toList());
        List<BigDecimal> lng = geographicalEntities.stream()
                .map(x -> x.getLng())
                .collect(Collectors.toList());


        List<WeatherDto> weatherDto = weatherService.readForecastProvince(lat, lng, date);


        List<Float> list = weatherDto.stream()
                .map(iWeatherMapper::responseFromDto)
                .collect(Collectors.toList())
                .stream().map(x -> x.getDaily().getTemperature()).findFirst()
                .get();

        String media = String.valueOf(list.stream()
                .reduce(Float::sum).get() / list.size());

        String n = String.format("la media delle temperature è '%s' per la provincia di '%s' ", media, provincia);

        return n;
    }


    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String istat, String st) {
        return new EntityNotFoundException("No data found for " + st + " :" + istat, ErrorCode.DATA_NOT_FOUND);
    }

}

