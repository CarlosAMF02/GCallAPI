package br.com.gcall.atendente.controller;

import br.com.gcall.atendente.entity.AtendenteChamado;
import br.com.gcall.atendente.models.AtendenteChamadoVM;
import br.com.gcall.atendente.services.AtendenteChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendente-chamado")
public class AtendenteChamadoController {
    @Autowired
    AtendenteChamadoService atendenteChamadoService;

    @GetMapping
    public List<AtendenteChamado> getAttendantsCalls() {
        return atendenteChamadoService.getAttendantsCalls();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AtendenteChamado> getAttendantCalls(@PathVariable(name = "id") long attendantId) {
        return atendenteChamadoService.getAttendantCallsById(attendantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int insertCallToAttendant(@RequestBody AtendenteChamadoVM atendenteChamadoVM) {
        return atendenteChamadoService.insertCallToAttendant(atendenteChamadoVM);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteAttendantCall(@PathVariable(name = "id") long attendantCallId) {
        return atendenteChamadoService.deleteAttendantCall(attendantCallId);
    }

}
