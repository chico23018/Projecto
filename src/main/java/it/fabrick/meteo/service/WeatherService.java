package it.fabrick.meteo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmeteo.sdk.WeatherApiResponse;
import it.fabrick.meteo.weartherDto.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {
    private final String weatherUrl = "https://api.open-meteo.com/v1/forecast";
    private final String weathe = "latitude=52.52,7.30434392&longitude=13.41,45.3151101";
    private final String latitude = "latitude";
    private final String longitude = "longitude";
    private final String daily = "daily=temperature_2m_max";
    private final String forecast_days = "forecast_days=1";
    private final String start_date= "start_date";
    private final String end_date= "end_date";
    private final RestTemplate template;
    private final ObjectMapper objectMapper;


    public WeatherDto readForecast(BigDecimal latitude, BigDecimal longitude) {


        WeatherApiResponse.ValidateVersion();
        UriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherUrl);
        URI uri1 = uriBuilderFactory.builder().queryParam(this.latitude, latitude)
                .queryParam(this.longitude, longitude)
                .query(daily)
                .query(forecast_days).build();

        HttpEntity<Void> httpEntity = new HttpEntity<>(null);

        WeatherDto weatherDto = template.exchange(uri1,
                HttpMethod.GET, httpEntity, WeatherDto.class).getBody();
        // weatherDto.setDaily(dailyDto);

        return weatherDto;
    }
    public   WeatherDto readForecastDate(BigDecimal latitude, BigDecimal longitude, LocalDate start,LocalDate end){

        WeatherApiResponse.ValidateVersion();
        UriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherUrl);
        URI uri1 = uriBuilderFactory.builder().queryParam(this.latitude, latitude)
                .queryParam(this.longitude, longitude)
                .query(daily)
                .queryParam(this.start_date, start)
                .queryParam(this.end_date, end)
                .build();

        HttpEntity<WeatherDto> httpEntity = new HttpEntity<>(null);

       WeatherDto weatherDto = template.exchange(uri1,
               HttpMethod.GET, httpEntity, WeatherDto.class).getBody();

        return weatherDto;
    }
    public   List<WeatherDto> readForecastProvince(List<BigDecimal> latitude, List<BigDecimal> longitude, LocalDate start){

        WeatherApiResponse.ValidateVersion();
        UriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherUrl);
        URI uri1 = uriBuilderFactory.builder().queryParam(this.latitude,createUrl( latitude))
                .queryParam(this.longitude, createUrl(longitude))
                .query(daily)
                .queryParam(this.start_date, start)
                .queryParam(this.end_date, start)
                .build();

        HttpEntity<WeatherDto> httpEntity = new HttpEntity<>(null);

        List<WeatherDto> weatherDto = template.exchange(uri1,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<WeatherDto>>() {
                }).getBody();

        return weatherDto;
    }

    private String createUrl(List<BigDecimal> list) {
        StringBuilder builder = new StringBuilder() ;
        for (BigDecimal bigDecimal :list){
            builder.append(bigDecimal)
                    .append(",");
        }
        return builder.toString();
    }
}
