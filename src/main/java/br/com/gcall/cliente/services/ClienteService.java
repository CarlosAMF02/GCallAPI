package br.com.gcall.cliente.services;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findCreateClient(long cpf){
        Cliente cliente = null;
        try {
            cliente = clienteRepository.findByCpf(cpf);
            if(cliente == null) {
                cliente = new Cliente(cpf);
                clienteRepository.save(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Optional<Cliente> findClientById(long id) {
        return clienteRepository.findById(id);
    }
}
