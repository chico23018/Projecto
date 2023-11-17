package it.fabrick.meteo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.meteo.repository.RegionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UtilData {
    private  final RegionsRepository  regionsRepository;
    private ObjectMapper objectMapper= new ObjectMapper();
    public void  saveDate(){
        try {
            List<Map<String,String> >lis = objectMapper.readValue(new File("json/italy_regions.json"), new TypeReference<  List<Map<String,String> > >() {
            });

         for(){
             for{
                 
             }
         }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
