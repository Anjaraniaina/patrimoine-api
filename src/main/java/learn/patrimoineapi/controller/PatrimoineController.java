package learn.patrimoineapi.controller;

import learn.patrimoineapi.model.Patrimoine;
import learn.patrimoineapi.service.PatrimoineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PatrimoineController {
    private final PatrimoineService service;

    @GetMapping("/patrimoines/{id}")
    public ResponseEntity<Patrimoine> getPatrimoineById(@PathVariable String id) {
        Patrimoine patrimoine = service.getPatrimoineById(id);
        return ResponseEntity.ok(patrimoine);
    }

    @PutMapping("/patrimoines/{id}")
    public ResponseEntity<Patrimoine> crupdatePatrimoine(@PathVariable String id,
                                        @RequestBody Patrimoine patrimoine) {
        Patrimoine crupdatedPatrimoine = service.crupdatePatrimoine(id, patrimoine);
        return ResponseEntity.ok(crupdatedPatrimoine);
    }
}
