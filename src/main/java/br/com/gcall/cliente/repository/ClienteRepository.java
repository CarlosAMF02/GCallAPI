package br.com.gcall.cliente.repository;

import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(long cpf);

}
