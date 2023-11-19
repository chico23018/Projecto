package it.fabrick.meteo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmeteo.sdk.*;
import it.fabrick.meteo.weartherDto.DailyDto;
import it.fabrick.meteo.weartherDto.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilderFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {
    private final String weatherUrl = "https://api.open-meteo.com/v1/forecast";
    private final String weathe = "latitude=52.52,7.30434392&longitude=13.41,45.3151101";
    private final String latitude = "latitude";
    private final String longitude = "longitude";
    private final String daily = "daily=temperature_2m_max&forecast_days=1";
    private final RestTemplate template;
    private final ObjectMapper objectMapper;


    public WeatherDto readForecast(BigDecimal latitude, BigDecimal longitude) {


        WeatherApiResponse.ValidateVersion();
        UriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherUrl);
        URI uri1 = uriBuilderFactory.builder().queryParam(this.latitude, latitude)
                .queryParam(this.longitude, longitude)
                .query(daily).build();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Void> httpEntity = new HttpEntity<>(null);

        WeatherDto weatherDto = new WeatherDto();

        DailyDto dailyDto = template.exchange("https://api.open-meteo.com/v1/forecast?latitude=52.52,7.30434392&longitude=13.41,45.3151101&daily=temperature_2m_max&forecast_days=1",
                HttpMethod.GET, httpEntity, DailyDto.class).getBody();
        weatherDto.setDaily(dailyDto);
        String response = this.template.getForObject("https://api.open-meteo.com/v1/forecast?latitude=52.52,7.30434392&longitude=13.41,45.3151101&daily=temperature_2m_max&forecast_days=1", String.class);
        System.out.println(response);

        return null
                ;
    }

    private byte[] responseIN = null;

    private void n() throws IOException {


// Step 1 : Request e.g.
        String mUrl = "https://api.open-meteo.com/v1/forecast?latitude=49.70808&longitude=8.08829&timezone=Europe/Berlin&minutely_15=temperature_2m,weathercode&format=flatbuffers";

        template.getRequestFactory().createRequest(URI.create(mUrl), HttpMethod.GET);
        ClientHttpRequest client = template.getRequestFactory().createRequest(URI.create(mUrl), HttpMethod.GET);
        responseIN = client.execute().getStatusText().getBytes();


// Step 2 : Use Binary Response buffer and convert it to ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(responseIN).order(ByteOrder.LITTLE_ENDIAN);

// Step 3 : create the ApiResponse Instance
// Note : The first 4 bytes interpret the length of the datablock
        WeatherApiResponse mApiResponse = WeatherApiResponse.getRootAsWeatherApiResponse((ByteBuffer) buffer.position(4));


// Step 4 : get the minutely15 block
        VariablesWithTime minutely15 = mApiResponse.minutely15();

        VariableWithValues temperature2m = new VariablesSearch(minutely15)
                .variable(Variable.temperature)
                .altitude(2)
                .first();
        VariableWithValues wmo = new VariablesSearch(minutely15)
                .variable(Variable.weather_code)
                .first();

        for (int i = 0; i < temperature2m.valuesLength(); i++) {
            String s = "Temperature and wmo at index : " + i + " -> " + temperature2m.values(i) + " / " + wmo.values(i);
            log.info(s);
        }
        buffer.clear();
    }
}
