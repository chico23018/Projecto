package it.fabrick.meteo.crud;

import it.fabrick.meteo.controller.MeteoController;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestCreateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsRequestUpdateDto;
import it.fabrick.meteo.dto.dtoRegions.RegionsResponseDto;
import it.fabrick.meteo.model.RegionsModel;
import it.fabrick.meteo.service.RegionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudTest {

    @MockBean
    private RegionsService regionsRepository;
    @Autowired
    private MeteoController meteoController;

    @Test
    void shouldFindAll() {
        mock();
        List<RegionsResponseDto> regionsModels = meteoController.readRegions().getBody();
        assertEquals(2, regionsModels.size());
        assertEquals(regionsEntity().getRegione(), regionsModels.get(0).getRegione());

    }

    @Test
    void shouldCreate() {
        mock();

        ResponseEntity<RegionsResponseDto> responseDto = meteoController.createRegions(regionsRequestCreateDto());

        assertEquals(HttpStatus.CREATED, responseDto.getStatusCode());
        assertEquals(regionsEntity().getRegione(), responseDto.getBody().getRegione());

    }

    @Test
    void shouldUpdate() {
        mock();

        ResponseEntity<RegionsResponseDto> responseDto = meteoController.updateRegions(1, regionsRequestUpdateDto());

        assertEquals(HttpStatus.OK, responseDto.getStatusCode());
        assertEquals(regionsEntity().getRegione(), responseDto.getBody().getRegione());

    }

    @Test
    void shouldDelete() {
        mock();
        ResponseEntity<Void> responseDto = meteoController.deleteRegions(123l);
        assertEquals(HttpStatus.NO_CONTENT, responseDto.getStatusCode());


    }


    private void mock() {
        Mockito.when(regionsRepository.readRegions())
                .thenReturn(List.of(
                        regionsEntity(),
                        regionsEntity()
                ));
        Mockito.when(regionsRepository.createRegions(Mockito.any()))
                .thenReturn(regionsEntity());

        Mockito.doNothing().when(regionsRepository).deleteRegions(regionsEntity().getIdRegions());

        Mockito.when(regionsRepository.updateRegions(Mockito.anyLong(), Mockito.any()))
                .thenReturn(regionsEntity());

    }

    private RegionsModel regionsEntity() {
        RegionsModel regionsEntity = new RegionsModel();

        regionsEntity.setIdRegions(1l);
        regionsEntity.setRegione("Pippo");
        regionsEntity.setSuperficie(1524.5);
        regionsEntity.setPresidente("Topolino");
        regionsEntity.setPec("pippo.pec@pec.it");
        regionsEntity.setSede("Disneyland");
        regionsEntity.setPiva(350513l);
        regionsEntity.setCodIstat("asacas");
        regionsEntity.setNumResidenti(150058822);
        regionsEntity.setSito("www.pippo.com");
        return regionsEntity;
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

        regionsEntity.setRegione("Pippo");
        regionsEntity.setSuperficie(1524.5);
        regionsEntity.setPresidente("Topolino");

        regionsEntity.setNumResidenti(150058822);
     /*   regionsEntity.setPec("pippo.pec@pec.it");
      regionsEntity.setCodIstat("asacas");
        regionsEntity.setSede("Disneyland");
        regionsEntity.setCodIstat("asacas");
        regionsEntity.setPiva(350513l);
        regionsEntity.setSito("www.pippo.com");*/
        return regionsEntity;
    }
}
