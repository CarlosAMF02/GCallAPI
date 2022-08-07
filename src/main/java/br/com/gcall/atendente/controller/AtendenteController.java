package br.com.gcall.atendente.controller;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.models.AtendenteVM;
import br.com.gcall.atendente.services.AtendenteService;
import br.com.gcall.empresa.models.EmpresaVM;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.FOUND)
    public Atendente getAttendant(@PathVariable(name = "id") long attendantId) {
        return atendenteService.getAttendantById(attendantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int createAttendant(@RequestBody AtendenteVM atendenteVM) {
        return atendenteService.insertAttendant(atendenteVM);
    }

    @PostMapping("/update/{id}/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public int updateAttendant(@RequestBody AtendenteVM attendant, @PathVariable(name = "id") long attendantId, @PathVariable(name = "companyId") long companyId) {
        return atendenteService.updateAttendant(attendant, attendantId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteAttendant(@PathVariable(name = "id") long attendantId) {
        return atendenteService.deleteAttendant(attendantId);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean login (LoginModel credentials) {
        return atendenteService.login(credentials);
    }
}
