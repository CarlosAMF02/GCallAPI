package br.com.gcall.chamado.controller;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.services.AtendenteService;
import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.chamado.models.ChamadoVM;
import br.com.gcall.chamado.services.ChamadoService;
import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Chamado> getAllCalls() {
        return chamadoService.getCalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> getCall(@PathVariable(name = "id") long chamadoId) {
        Chamado chamado = chamadoService.getCallById(chamadoId).orElse(null);

        if (chamado == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(chamado);
    }

    @PostMapping()
    public ResponseEntity<ChamadoVM> createCall(@RequestBody ChamadoVM chamadoVM) {
        int responseStatus = chamadoService.insertCall(chamadoVM);

        if (responseStatus == 1) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoVM);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChamadoVM> updateCall(@RequestBody ChamadoVM chamadoVM, @PathVariable(name = "id") long chamadoId) {
        int responseStatus =  chamadoService.updateCall(chamadoVM, chamadoId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(chamadoVM);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCall(@PathVariable(name = "id") long chamadoId) {
        int responseStatus = chamadoService.deleteCall(chamadoId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Chamado>> getClientCalls(@PathVariable(name = "id") long clienteId) {
        Cliente cliente = clienteService.findClientById(clienteId).orElse(null);
        if (cliente == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(chamadoService.getCallsByClientId(cliente));
    }
}
