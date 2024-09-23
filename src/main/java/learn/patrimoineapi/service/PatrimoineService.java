package learn.patrimoineapi.service;

import learn.patrimoineapi.model.Patrimoine;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class PatrimoineService {
    private final Map<String, Patrimoine> memory = new HashMap<>();

    public Patrimoine getPatrimoineById (String id) {
        return memory.get(id);
    }

    public Patrimoine crupdatePatrimoine (String id, Patrimoine patrimoine) {
        patrimoine.setId(id);
        if(Objects.isNull(patrimoine.getDerniereModification())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            patrimoine.setDerniereModification(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        }
        memory.put(id, patrimoine);
        return patrimoine;
    }
}

