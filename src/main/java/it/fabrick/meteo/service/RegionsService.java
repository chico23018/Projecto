package it.fabrick.meteo.service;

import it.fabrick.meteo.repository.RegionsRepository;
import it.fabrick.meteo.util.UtilData;
import org.springframework.stereotype.Service;

@Service
public class RegionsService {
    private final RegionsRepository regionsRepository;
    private final UtilData data ;

    public RegionsService(RegionsRepository regionsRepository, UtilData data) {
        this.regionsRepository = regionsRepository;

        this.data = data;
        data.saveDate();
    }
}
