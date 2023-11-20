package it.fabrick.meteo.test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RestController {
    @Autowired
    MockMvc mockMvc;
    private String path="/v1.0/weather";

    @Test
    void shouldReadRegion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/regions?regions=lombardia&resident=100000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }
    @Test
    void shouldReadRegionBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/regions?regions=Ciao&resident=100000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void shouldReadProvinvia() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/provincia?provincia=milano&resident=1000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }
    @Test
    void shouldReadProvinviaBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/provincia?provincia=milan&resident=100000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void shouldReadCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/city?resident=1000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.greaterThan(3)));
    }
 /*   @Test
    void shouldReadCityBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(path+"/city?resident=0"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }*/

}
