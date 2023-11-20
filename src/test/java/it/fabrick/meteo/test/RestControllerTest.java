package it.fabrick.meteo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.dto.RequestNunResident;
import it.fabrick.meteo.dto.RequestNunResidentAndPlace;
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTest {
    @Autowired
    MockMvc mockMvc;
    private String path = "/v1.0/weather";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReadRegion() throws Exception {

        String content = objectMapper.writeValueAsString(create("lombardia", 10000));
        mockMvc.perform(MockMvcRequestBuilders.post(path + "/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }

    @Test
    void shouldReadRegionBadRequest() throws Exception {
        String content = objectMapper.writeValueAsString(create("lomba", 10000));
        mockMvc.perform(MockMvcRequestBuilders.post(path + "/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldReadProvinvia() throws Exception {
        String content = objectMapper.writeValueAsString(create("milano", 10000));
        mockMvc.perform(MockMvcRequestBuilders.post(path + "/provincia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }

    @Test
    void shouldReadProvinviaBadRequest() throws Exception {
        String content = objectMapper.writeValueAsString(create("milan", 10000));
        mockMvc.perform(MockMvcRequestBuilders.post(path + "/provincia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldReadCity() throws Exception {
        RequestNunResident requestNunResident = new RequestNunResident();
        requestNunResident.setNumResident(1000);
        String content = objectMapper.writeValueAsString(requestNunResident);
        mockMvc.perform(MockMvcRequestBuilders.post(path + "/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }

       @Test
       void shouldReadCityBadRequest() throws Exception {
           RequestNunResident requestNunResident = new RequestNunResident();
           requestNunResident.setNumResident(0);
           String content = objectMapper.writeValueAsString(requestNunResident);
           mockMvc.perform(MockMvcRequestBuilders.post(path + "/city")
                           .contentType(MediaType.APPLICATION_JSON)
                           .content(content))
                   .andDo(MockMvcResultHandlers.print())
                   .andExpect(MockMvcResultMatchers.status().isBadRequest());
       }
    private RequestNunResidentAndPlace create(String place, int numResident) {
        RequestNunResidentAndPlace requestNunResidentAndPlace = new RequestNunResidentAndPlace();
        requestNunResidentAndPlace.setPlace(place);
        requestNunResidentAndPlace.setNumResident(numResident);

        return requestNunResidentAndPlace;
    }

}