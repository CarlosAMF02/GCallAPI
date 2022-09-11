package br.com.gcall.atendente.controller;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.models.AtendenteVM;
import br.com.gcall.atendente.services.AtendenteService;
import br.com.gcall.empresa.models.EmpresaVM;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {
    @Autowired
    AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> getAllAttendants() {
        return atendenteService.getAttendants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> getAttendant(@PathVariable(name = "id") long attendantId) {
        return ResponseEntity.of(atendenteService.getAttendantById(attendantId));
    }

    @GetMapping("/empresa/{id}")
    public List<Atendente> getAttendantByCompanyId(@PathVariable(name = "id") long comapnyId) {
        return atendenteService.getAttendantsByCompanyId(comapnyId);
    }

    @PostMapping
    public ResponseEntity<AtendenteVM> createAttendant(@RequestBody AtendenteVM atendenteVM) {
        int responseStatus = atendenteService.insertAttendant(atendenteVM);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (responseStatus == 3) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteVM);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AtendenteVM> updateAttendant(@RequestBody AtendenteVM atendenteVM, @PathVariable(name = "id") long attendantId) {
        int responseStatus = atendenteService.updateAttendant(atendenteVM, attendantId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(atendenteVM);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAttendant(@PathVariable(name = "id") long attendantId) {
        int responseStatus = atendenteService.deleteAttendant(attendantId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginModel> login (@RequestBody LoginModel credentials) {
        int responseStatus = atendenteService.login(credentials);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (responseStatus == 2) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(credentials);

    }
}
