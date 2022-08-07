package br.com.gcall.cliente.controller;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/{cpf}")
    public Cliente findClient(@RequestParam(name = "cpf") long cpf){
        return clienteService.findClient(cpf);
    }
}
