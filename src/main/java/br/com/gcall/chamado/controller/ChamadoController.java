package br.com.gcall.chamado.controller;

import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.chamado.models.ChamadoVM;
import br.com.gcall.chamado.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    public List<Chamado> getAllCalls() {
        return chamadoService.getCalls();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Chamado getCall(@PathVariable(name = "id") long chamadoId) {
        return chamadoService.getCallById(chamadoId);
    }

    @PostMapping("/{statusId}/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public int createCall(@RequestBody ChamadoVM chamadoVM, @PathVariable(name = "statusId") long statusId, @PathVariable("clientId") long clientId) {
        return chamadoService.insertCall(chamadoVM, statusId, clientId);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int updateCall(@RequestBody ChamadoVM chamadoVM, @PathVariable(name = "id") long chamadoId) {
        return chamadoService.updateCall(chamadoVM, chamadoId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteCall(@PathVariable(name = "id") long chamadoId) {
        return chamadoService.deleteCall(chamadoId);
    }

    @GetMapping("/cliente/{id}")
    public List<Chamado> getClientCalls(@PathVariable(name = "id") long clienteId) {
        return chamadoService.getCallsByClientId(clienteId);
    }
}
