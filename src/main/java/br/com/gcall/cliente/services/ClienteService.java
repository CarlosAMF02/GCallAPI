package br.com.gcall.cliente.services;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findClient(long cpf){
        Cliente cliente = null;
        try {
            cliente = clienteRepository.findByCpf(cpf);
            if(cliente == null) {
                cliente = new Cliente(cpf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Cliente findClientById(long id) {
        Cliente cliente = null;
        try {
            cliente = clienteRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
