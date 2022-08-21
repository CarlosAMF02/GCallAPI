package br.com.gcall.cliente.controller;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> findCreateClient(@PathVariable(name = "cpf") long cpf){
        return ResponseEntity.ok(clienteService.findCreateClient(cpf));
    }
}
