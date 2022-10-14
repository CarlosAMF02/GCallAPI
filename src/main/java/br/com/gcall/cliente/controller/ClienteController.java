package br.com.gcall.cliente.controller;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.models.ClienteVM;
import br.com.gcall.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> findCreateClient(@RequestBody ClienteVM clienteVM){
        return ResponseEntity.ok(clienteService.findCreateClient(clienteVM));
    }
}
