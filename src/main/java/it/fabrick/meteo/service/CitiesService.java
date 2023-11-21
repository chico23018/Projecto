package it.fabrick.meteo.service;

import it.fabrick.meteo.classEnum.ErrorCode;
import it.fabrick.meteo.dto.dtoWearther.WeatherDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherResponseDto;
import it.fabrick.meteo.entity.CitiesEntity;
import it.fabrick.meteo.entity.GeographicalEntity;
import it.fabrick.meteo.exception.EntityNotFoundException;
import it.fabrick.meteo.exception.InternalErrorException;
import it.fabrick.meteo.mapper.ICitiesMapper;
import it.fabrick.meteo.mapper.IWeatherMapper;
import it.fabrick.meteo.model.CitiesModel;
import it.fabrick.meteo.repository.CitiesRepository;
import it.fabrick.meteo.repository.GeographicalRepository;
import it.fabrick.meteo.repository.ProvinciesRepository;
import it.fabrick.meteo.repository.RegionsRepository;
import it.fabrick.meteo.util.Utility;
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
    private final ProvinciesRepository provinciesRepository;
    private final RegionsRepository regionsRepository;

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
        if (citiesModels.isEmpty())
            throw generateEntityNotFound("" + numResident, "resident");

        return citiesModels;
    }

    public List<CitiesModel> readCities(Long idRegion, String sigla) {

        sigla = Utility.converteString(sigla);
        List<CitiesEntity> citiesEntities = null;
        try {
            citiesEntities = citiesRepository.findByRegionsIdRegionsAndProvinciaSigla(idRegion, sigla);
        } catch (Exception e) {
            generateGenericInternalError(e);
        }
        if (citiesEntities != null && citiesEntities.isEmpty())
            throw generateEntityNotFound(idRegion + " and " + sigla, "Region and Province");
        return citiesEntities
                .stream()
                .map(iCitiesMapper::modelFromEntity)
                .collect(Collectors.toList());
    }

    public CitiesModel createCities(Long region, String sigla, CitiesModel citiesModel) {

        sigla = Utility.converteString(sigla);
        CitiesEntity cities = iCitiesMapper.entityFromModel(citiesModel);
        try {

            cities.setRegions(regionsRepository.findById(region).get());
            cities.setProvincia(provinciesRepository.findById(sigla).get());
            cities = citiesRepository.save(cities);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        return iCitiesMapper.modelFromEntity(cities);
    }

    @Transactional
    public CitiesModel updateCities(Long idRegion, String sigla, Long istat, CitiesModel citiesModel) {

        sigla = Utility.converteString(sigla);
        int howMany = 0;
        try {
            if (citiesModel.getComune() != null && citiesModel.getCodFisco() != null && citiesModel.getSuperficie() != null
                    && citiesModel.getPrefisso() != null &&
                    citiesModel.getNumResident() != null) {
                howMany = citiesRepository.updateByIstatAndAll(istat, citiesModel.getComune(), citiesModel.getPrefisso()
                        , citiesModel.getCodFisco(), citiesModel.getSuperficie(), citiesModel.getNumResident()
                        , idRegion, sigla);
            } else if (citiesModel.getComune() != null) {
                howMany = citiesRepository.updateByIstatAndComune(istat, citiesModel.getComune(), idRegion, sigla);
            } else if (citiesModel.getCodFisco() != null) {
                howMany = citiesRepository.updateByIstatAndCodFisco(istat, citiesModel.getCodFisco(), idRegion, sigla);
            } else if (citiesModel.getSuperficie() != null) {
                howMany = citiesRepository.updateByIstatAndSuperficie(istat, citiesModel.getSuperficie(), idRegion, sigla);
            } else if (citiesModel.getPrefisso() != null) {
                howMany = citiesRepository.updateByIstatAndPrefisso(istat, citiesModel.getPrefisso(), idRegion, sigla);
            } else if (citiesModel.getNumResident() != null) {
                howMany = citiesRepository.updateByIstatAndNum_residenti(istat, citiesModel.getNumResident(), idRegion, sigla);
            }
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }

        if (howMany == 0)
            throw generateEntityNotFound(""+istat, "id");

        return citiesRepository.findById(istat).map(iCitiesMapper::modelFromEntity).get();
    }

    @Transactional
    public void deleteCities(Long idRegion, String sigla, Long istat) {
        int howMany = 0;
        try {
            howMany = citiesRepository.deleteByRegionsIdRegionsAndProvinciaSiglaAndIstat(idRegion, sigla, istat);
        } catch (Exception e) {
            throw generateGenericInternalError(e);
        }
        if (howMany == 0)
            throw generateEntityNotFound(""+istat, "id");

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
        responseDto.setCity(municipality);

        return responseDto;
    }

    public WeatherResponseDto readForecastDays(String city, int days) {

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
        weatherDto = weatherService.readForecastDays(
                cities.getGeographical().getLat()
                , cities.getGeographical().getLng()
                , days
        );
        WeatherResponseDto responseDto = iWeatherMapper.responseFromDto(weatherDto);
        responseDto.setCity(city);
        responseDto.getDaily().setMediaTemperature(Utility.mediaTempera(responseDto.getDaily().getTemperature()));
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
                    , cities.getGeographical().getLng(),
                    LocalDate.now(), date
            );
        } else {

            weatherDto = weatherService.readForecastDate(
                    cities.getGeographical().getLat()
                    , cities.getGeographical().getLng()
                    , date, LocalDate.now()
            );
        }

        WeatherResponseDto responseDto = iWeatherMapper.responseFromDto(weatherDto);
        responseDto.setCity(city);
        responseDto.getDaily().setMediaTemperature(Utility.mediaTempera(responseDto.getDaily().getTemperature()));

        return responseDto;
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

        String response = String.format("the average temperature is '%s' for the  province of '%s' for '%s' ", media, provincia, date);

        return response;
    }


    private InternalErrorException generateGenericInternalError(Exception e) {
        return new InternalErrorException("Something went wrong", e, ErrorCode.INTERNAL_ERROR);
    }

    private EntityNotFoundException generateEntityNotFound(String istat, String st) {
        return new EntityNotFoundException("No data found for " + st + " :" + istat, ErrorCode.DATA_NOT_FOUND);
    }

}

