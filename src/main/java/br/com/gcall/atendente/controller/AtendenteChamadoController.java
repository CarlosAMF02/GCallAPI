package br.com.gcall.atendente.controller;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.entity.AtendenteChamado;
import br.com.gcall.atendente.models.AtendenteChamadoVM;
import br.com.gcall.atendente.services.AtendenteChamadoService;
import br.com.gcall.atendente.services.AtendenteService;
import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.chamado.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendente-chamado")
public class AtendenteChamadoController {
    @Autowired
    AtendenteChamadoService atendenteChamadoService;
    @Autowired
    AtendenteService atendenteService;
    @Autowired
    ChamadoService chamadoService;

    @GetMapping
    public List<AtendenteChamado> getAttendantsCalls() {
        return atendenteChamadoService.getAttendantsCalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AtendenteChamado>> getAttendantCalls(@PathVariable(name = "id") long attendantId) {
        Atendente atendente = atendenteService.getAttendantById(attendantId).orElse(null);

        if (atendente == null) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(atendenteChamadoService.getAttendantCallsById(atendente));
    }

    @PostMapping
    public ResponseEntity<AtendenteChamadoVM> insertCallToAttendant(@RequestBody AtendenteChamadoVM atendenteChamadoVM) {
        Atendente atendente = atendenteService.getAttendantById(atendenteChamadoVM.getAtendenteId()).orElse(null);
        Chamado chamado = chamadoService.getCallById(atendenteChamadoVM.getChamadoId()).orElse(null);
        if (atendente == null || chamado == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        atendenteChamadoService.insertCallToAttendant(atendente, chamado);

        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteChamadoVM);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAttendantCall(@PathVariable(name = "id") long attendantCallId) {
        int responseStatus = atendenteChamadoService.deleteAttendantCall(attendantCallId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
