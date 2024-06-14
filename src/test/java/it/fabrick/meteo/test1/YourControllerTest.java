package it.fabrick.meteo.test1;

import it.fabrick.meteo.dto.RequestNunResident;
import it.fabrick.meteo.dto.dtoCity.CitiesResponseDto;
import it.fabrick.meteo.dto.dtoWearther.WeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YourControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testReadGreaterThaConcurrent() throws InterruptedException {
        int numThreads = 10; // Número de hilos a ejecutar concurrentemente
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executor.execute(() -> {
                RequestNunResident request = new RequestNunResident(); // Crear un objeto de prueba
                request.setNumResident(5); // Establecer el número de residentes
                HttpEntity<CitiesResponseDto> httpEntity = new HttpEntity<>(null);
                ResponseEntity<List<CitiesResponseDto>> responseEntity = restTemplate.exchange("http://localhost:"+port+"/v1.0/resident/search/numResident/city", HttpMethod.POST,httpEntity, new ParameterizedTypeReference<List< CitiesResponseDto >>() {
                });

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertNotNull(responseEntity.getBody());
                // Realizar más aserciones según el resultado esperado
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS); // Esperar a que todos los hilos terminen
    }
}

