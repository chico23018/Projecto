package it.fabrick.meteo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.dto.dtoWearther.WeatherDays;
import it.fabrick.meteo.dto.dtoWearther.WeatherRequestDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static it.fabrick.meteo.constant.Constant.searchForecast;
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WeatherServiceTest {
    @Autowired
    MockMvc mockMvc;
    private String path = "/v1.0/weather";

    @Autowired
    private ObjectMapper objectMapper;







    @Test
    void readForecast() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path +searchForecast+ "/brescia"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("BRESCIA")));
    }

    @Test
    void readForecastDays() throws Exception {
        WeatherDays days = new WeatherDays();
        days.setDays(5);
        days.setPlace("desenzano del garda");
        String content = objectMapper.writeValueAsString(days);
        mockMvc.perform(MockMvcRequestBuilders.post(path +searchForecast+ "/days")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is("DESENZANO DEL GARDA")));
    }

    @Test
    void readForecastDate() throws Exception {
        WeatherRequestDto weatherRequestDto = new WeatherRequestDto();
        weatherRequestDto.setPlace("milano");
        weatherRequestDto.setDate(LocalDate.parse("2023-11-10"));

        String content = objectMapper.writeValueAsString(weatherRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post(path +searchForecast+ "/date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void readForecastProvince() throws Exception {
        WeatherRequestDto weatherRequestDto = new WeatherRequestDto();
        weatherRequestDto.setPlace("milano");
        weatherRequestDto.setDate(LocalDate.parse("2023-11-10"));

        String content = objectMapper.writeValueAsString(weatherRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post(path +searchForecast+ "/provincia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    private WeatherRequestDto create(String place, LocalDate numResident) {
        WeatherRequestDto weatherRequestDto = new WeatherRequestDto();
        weatherRequestDto.setPlace(place);
        weatherRequestDto.setDate(numResident);

        return weatherRequestDto;
    }

}