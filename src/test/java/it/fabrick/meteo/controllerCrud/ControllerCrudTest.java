/*package it.fabrick.meteo.controllerCrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.dto.dtoCity.CitiesRequestCreateDto;
import it.fabrick.meteo.dto.dtoCity.CitiesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestCreateDto;
import it.fabrick.meteo.dto.dtoProvince.ProvinciesRequestUpdateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestCreateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static it.fabrick.meteo.constant.Constant.path2;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerCrudTest {
    @Autowired
    MockMvc mockMvc;
    private String path = path2;
    private String createRegion = "";
    private String createProvince = "";
    private String createCities = "";

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    void shouldCreateRegion() throws Exception {
        String content = objectMapper.writeValueAsString(regionsRequestCreateDto());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        createRegion = mvcResult.getResponse().getHeader(HttpHeaders.LOCATION);


    }

    @Test
    @Order(1)
    void shouldCreateProvince() throws Exception {
        String content = objectMapper.writeValueAsString(provinciesRequestCreateDto());
        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.post(createRegion + "/province")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        createProvince = mvcResult1.getResponse().getHeader(HttpHeaders.LOCATION);
    }

    @Test
    @Order(2)
    void shouldCreateCity() throws Exception {
        String content = objectMapper.writeValueAsString(citiesRequestCreateDto());
        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post(createProvince + "/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        createCities = mvcResult2.getResponse().getHeader(HttpHeaders.LOCATION);

    }

    @Test
    @Order(3)
    void shouldUpdateRegion() throws Exception {
        String content = objectMapper.writeValueAsString(regionsRequestUpdateDto());
        mockMvc.perform(MockMvcRequestBuilders.put(createRegion)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.regione", Matchers.is("PippoUpdate".toUpperCase())))
        ;


    }

    @Test
    @Order(4)
    void shouldUpdateProvince() throws Exception {
        String content = objectMapper.writeValueAsString(provinciesRequestUpdateDto());
        System.out.println(createProvince);
        mockMvc.perform(MockMvcRequestBuilders.put(createProvince)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.provincia", Matchers.is("PippoUpdate".toUpperCase())))
        ;
    }

    @Test
    @Order(5)
    void shouldUpdateCity() throws Exception {
        String content = objectMapper.writeValueAsString(citiesRequestUpdateDto());
        mockMvc.perform(MockMvcRequestBuilders.put(createCities)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.comune", Matchers.is("pippoLandUpdate".toUpperCase())))
        ;
    }

    @Test
    @Order(6)
    void shouldDeleteCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(createCities))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @Order(7)
    void shouldDeleteProvince() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(createProvince))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @Order(8)
    void shouldDeleteRegion() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(createRegion))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    @Order(9)
    void shouldDeleteCityBad() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(createCities))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    private CitiesRequestUpdateDto citiesRequestUpdateDto() {
        CitiesRequestUpdateDto updateDto = new CitiesRequestUpdateDto();
        updateDto.setComune("pippoLandUpdate");
        updateDto.setSuperficie(1506.);
        updateDto.setNumResident(1500);
        updateDto.setCodFisco("152d");
        updateDto.setPrefisso(030);
        return updateDto;
    }

    private CitiesRequestCreateDto citiesRequestCreateDto() {
        CitiesRequestCreateDto requestCreateDto = new CitiesRequestCreateDto();
        requestCreateDto.setComune("pippoLandCreate");
        requestCreateDto.setSuperficie(1506.);
        requestCreateDto.setNumResident(1500);
        requestCreateDto.setCodFisco("152d");
        requestCreateDto.setPrefisso(030);
        return requestCreateDto;
    }

    private ProvinciesRequestCreateDto provinciesRequestCreateDto() {
        ProvinciesRequestCreateDto provinciesRequestCreateDto = new ProvinciesRequestCreateDto();
        provinciesRequestCreateDto.setSigla("pip");
        provinciesRequestCreateDto.setProvincia("PippoCreate");
        provinciesRequestCreateDto.setSuperficie(15000.);
        provinciesRequestCreateDto.setResidenti(1000);
        return provinciesRequestCreateDto;
    }

    private ProvinciesRequestUpdateDto provinciesRequestUpdateDto() {
        ProvinciesRequestUpdateDto updateDto = new ProvinciesRequestUpdateDto();
        updateDto.setProvincia("PippoUpdate");
        updateDto.setSuperficie(15000.);
        updateDto.setResidenti(1000);

        return updateDto;
    }

    private RegionsRequestCreateDto regionsRequestCreateDto() {
        RegionsRequestCreateDto regionsEntity = new RegionsRequestCreateDto();

        regionsEntity.setRegione("Pippo");
        regionsEntity.setSuperficie(1524.5);
        regionsEntity.setPresidente("Topolino");
        regionsEntity.setPec("pippo.pec@pec.it");
        regionsEntity.setSede("Disneyland");
        regionsEntity.setPiva(350513l);
        regionsEntity.setCodIstat("asacas");
        regionsEntity.setCodIstat("asacas");
        regionsEntity.setNumResidenti(150058822);
        regionsEntity.setSito("www.pippo.com");
        return regionsEntity;
    }

    private RegionsRequestUpdateDto regionsRequestUpdateDto() {
        RegionsRequestUpdateDto regionsEntity = new RegionsRequestUpdateDto();

        regionsEntity.setRegione("PippoUpdate");
        regionsEntity.setSuperficie(1524.5);
        regionsEntity.setPresidente("Topolino");

        regionsEntity.setNumResidenti(150058822);*/
     /*   regionsEntity.setPec("pippo.pec@pec.it");
      regionsEntity.setCodIstat("asacas");
        regionsEntity.setSede("Disneyland");
        regionsEntity.setCodIstat("asacas");
        regionsEntity.setPiva(350513l);
        regionsEntity.setSito("www.pippo.com");*/
       /* return regionsEntity;
    }
}*/
